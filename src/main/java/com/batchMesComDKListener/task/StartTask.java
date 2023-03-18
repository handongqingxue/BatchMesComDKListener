package com.batchMesComDKListener.task;

import com.batchMesComDKListener.util.IniUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class StartTask {
	
	static KeepWatchTask keepWatchTask;
	static SendMesBRTask sendMesBRTask;
	static ListenerTask listenerTask;
	
	public static void main(String[] args) {
		//打包exe:https://blog.csdn.net/qq_40298902/article/details/114489753
		//keepWatchTask=new KeepWatchTask();
		//keepWatchTask.setActive(true);
		
		//sendMesBRTask=new SendMesBRTask();
		//sendMesBRTask.setActive(true);
		
		listenerTask=new ListenerTask();
		listenerTask.initMainJFrame();

		//keepWatchTask.start();
		//sendMesBRTask.start();
		listenerTask.start();
		readIniFile();
	}

	public static void readIniFile(){
		//获取resources下文件夹路径
		File directory = new File("../BatchMesComDKListener/src/main/resources");
		String reportPath = null;
		try {
			reportPath = directory.getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//resource就是所需要的路径 eg: resource="D:\项目名\src\main\resources\files\****"
		String resource =reportPath+"/dbconfig.ini";
		try {
			Map<String, String> map = IniUtils.readIniFile(resource);
			String sqlserver = map.get("sqlserver");
			String databaseName = map.get("DatabaseName");
			System.out.println("数据库地址:"+sqlserver);
			System.out.println("数据库名称:"+databaseName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
