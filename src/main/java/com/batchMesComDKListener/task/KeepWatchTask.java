package com.batchMesComDKListener.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONObject;

import com.batchMesComDKListener.util.*;

public class KeepWatchTask extends Thread {
	
	/**
	 * 是否运行
	 */
	private boolean active;
	private boolean checked;
	private long restartDogTime=0;

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				if(!active)//不运行了，则跳出巡回检测
					break;
				checked=false;
				
				restartDogTime+=3;
				if(restartDogTime>200) {
					APIUtil.restartWatchDog();
				}
				
				Thread.sleep(3000);
				System.out.println("reading1==="+APIUtil.reading);
				if(!APIUtil.reading) {
					JSONObject kwowoJO = APIUtil.keepWatchOnWorkOrder();
					System.out.println("reading3==="+APIUtil.reading);
					//APIUtil.keepWatchOnWorkOrderTest();
					System.out.println("巡回工单状态........"+kwowoJO.getBoolean("success"));
					if(!kwowoJO.getBoolean("success")) {
						//https://blog.csdn.net/qq_41548233/article/details/116566144
						//The CATALINA_HOME environment variable is not defined correctly解决方案:配置环境变量,新增CATALINA_HOME:E:\tomcat8.5.57
						String message = kwowoJO.getString("message");
						System.out.println("message==="+message);
						if("Read timed out".equals(message)||
							message.contains("/BatchMesComDK/batch/keepWatchOnWorkOrder")) {//读取时间超时，说明Tomcat端已经宕机了，就得关闭Tomcat进程重启服务
							runBatFile("cmd /c taskkill /f /im java.exe");
							runBatFile("cmd /c "+Constant.TOMCAT_STARTUP_DIR);
						}
						else if("Connection refused: connect".equals(message)||//拒绝连接时，说明Tomcat没开启，就得开启
								"Can't pass in null Dispatch object".equals(message)||//没有batch环境就会报这个异常
								"Can't map name to dispid: GetItem".equals(message)) {//batch服务没开启就会报这个异常，要重启下tomcat，重新检测batch服务是否开启
							runBatFile("cmd /c "+Constant.TOMCAT_SHUTDOWN_DIR);
							runBatFile("cmd /c "+Constant.TOMCAT_STARTUP_DIR);
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void runBatFile(String fileUrl) {
		System.out.println("fileUrl==="+fileUrl);
		StringBuilder sb = new StringBuilder();
	    try {
	        Process child = Runtime.getRuntime().exec(fileUrl);
	        InputStream in = child.getInputStream();
	        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
	        String line;
	        while ((line = bufferedReader.readLine()) != null) {
	        	System.out.println(line);
	            sb.append(line + "\n");
	        }
	        in.close();
	        try {
	            child.waitFor();
	            System.out.println("call cmd process finished");
	        } catch (InterruptedException e) {
	        	System.out.println("faild to call cmd process cmd because " + e.getMessage());
	        }
	    } catch (IOException e) {
	    	System.out.println(e.getMessage());
	    }
	}
	
	public static void main(String[] args) {
		KeepWatchTask k=new KeepWatchTask();
		k.runBatFile("cmd /c D:/tomcat8.5.57/bin/startup.bat");
	}
}
