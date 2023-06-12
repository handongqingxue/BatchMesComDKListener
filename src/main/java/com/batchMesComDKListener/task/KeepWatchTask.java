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
				Thread.sleep(3000);
				JSONObject kwowoJO = APIUtil.keepWatchOnWorkOrder();
				//APIUtil.keepWatchOnWorkOrderTest();
				System.out.println("巡回工单状态........"+kwowoJO.getBoolean("success"));
				if(!kwowoJO.getBoolean("success")) {//https://blog.csdn.net/qq_41548233/article/details/116566144
					runBatFile("cmd /c D:/tomcat8.5.57/bin/shutdown.bat");
					runBatFile("cmd /c D:/tomcat8.5.57/bin/startup.bat");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void runBatFile(String fileUrl) {
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
