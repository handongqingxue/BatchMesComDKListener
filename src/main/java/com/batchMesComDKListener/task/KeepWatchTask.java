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
				Thread.sleep(3000);
				JSONObject kwowoJO = APIUtil.keepWatchOnWorkOrder();
				//APIUtil.keepWatchOnWorkOrderTest();
				System.out.println("Ѳ�ع���״̬........"+kwowoJO.getBoolean("success"));
				if(!kwowoJO.getBoolean("success")) {
					System.out.println("ooooooooooooooo");
					runBatFile("D:/apache-tomcat-8.5.57/bin/startup.bat");
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
		k.runBatFile("D:/apache-tomcat-8.5.57/bin/startup.bat");
	}
}
