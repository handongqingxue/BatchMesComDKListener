package com.batchMesComDKListener.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONObject;

import com.batchMesComDKListener.util.*;

public class KeepWatchTask extends Thread {
	
	/**
	 * �Ƿ�����
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
				if(!active)//�������ˣ�������Ѳ�ؼ��
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
					System.out.println("Ѳ�ع���״̬........"+kwowoJO.getBoolean("success"));
					if(!kwowoJO.getBoolean("success")) {
						//https://blog.csdn.net/qq_41548233/article/details/116566144
						//The CATALINA_HOME environment variable is not defined correctly�������:���û�������,����CATALINA_HOME:E:\tomcat8.5.57
						String message = kwowoJO.getString("message");
						System.out.println("message==="+message);
						if("Read timed out".equals(message)||
							message.contains("/BatchMesComDK/batch/keepWatchOnWorkOrder")) {//��ȡʱ�䳬ʱ��˵��Tomcat���Ѿ�崻��ˣ��͵ùر�Tomcat������������
							runBatFile("cmd /c taskkill /f /im java.exe");
							runBatFile("cmd /c "+Constant.TOMCAT_STARTUP_DIR);
						}
						else if("Connection refused: connect".equals(message)||//�ܾ�����ʱ��˵��Tomcatû�������͵ÿ���
								"Can't pass in null Dispatch object".equals(message)||//û��batch�����ͻᱨ����쳣
								"Can't map name to dispid: GetItem".equals(message)) {//batch����û�����ͻᱨ����쳣��Ҫ������tomcat�����¼��batch�����Ƿ���
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
