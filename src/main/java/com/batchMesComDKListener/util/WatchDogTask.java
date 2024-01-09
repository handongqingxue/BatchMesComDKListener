package com.batchMesComDKListener.util;

import java.io.IOException;
import java.util.TimerTask;

public class WatchDogTask extends TimerTask {
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		runBatFile("cmd /c c:/runner.exe");//自杀前先重启
		//runBatFile("cmd /c D:/BatchMesComDKListener/runner.exe");//自杀前先重启
		LogUtil.writeInLog("start next runner.exe");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LogUtil.writeInLog("exit pre runner.exe");
		System.exit(0);
	}
	
	public void runBatFile(String fileUrl) {
		//https://blog.csdn.net/m0_50852776/article/details/125668719
		System.out.println("fileUrl==="+fileUrl);
		StringBuilder sb = new StringBuilder();
	    try {
	        Process child = Runtime.getRuntime().exec(fileUrl);
	        /*
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
	        */
	    } catch (IOException e) {
	    	System.out.println(e.getMessage());
	    }
	}

}
