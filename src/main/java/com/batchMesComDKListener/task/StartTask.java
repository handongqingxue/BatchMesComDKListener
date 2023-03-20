package com.batchMesComDKListener.task;

import com.batchMesComDKListener.util.Constant;
import com.batchMesComDKListener.util.IniUtil;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class StartTask {
	
	static KeepWatchTask keepWatchTask;
	static SendMesBRTask sendMesBRTask;
	static ListenerTask listenerTask;
	
	public static void main(String[] args) {
		//打包exe:https://blog.csdn.net/qq_40298902/article/details/114489753
		keepWatchTask=new KeepWatchTask();
		keepWatchTask.setActive(true);
		
		//sendMesBRTask=new SendMesBRTask();
		//sendMesBRTask.setActive(true);
		
		listenerTask=new ListenerTask();
		listenerTask.initMainJFrame();

		keepWatchTask.start();
		//sendMesBRTask.start();
		listenerTask.start();
		//readIniFile();
	}

	public static void readIniFile(){
		//获取resources下文件夹路径
		File resourcesDir = new File(Constant.RESOURCES_DIR);
		String resourcesPath = null;
		try {
			resourcesPath = resourcesDir.getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//resource就是所需要的路径 eg: resource="D:\项目名\src\main\resources\files\****"
		String iniPath =resourcesPath+"/db.ini";
		try {
			Map<String, String> map = IniUtil.readKeys(iniPath);
			String ipAddressPort = map.get(Constant.IP_ADDRESS_PORT_KEY);
			String dbConnect = map.get(Constant.DB_CONNECT_KEY);
			System.out.println("数据库地址:"+ipAddressPort);
			System.out.println("数据库连接:"+dbConnect);
			
			LinkedHashMap<String,Object> map1=new LinkedHashMap();
			map1.put(Constant.DB_CONNECT_KEY, "BatchMesCom");
			IniUtil.writeKeys(iniPath, Constant.CONNECT_INFO_SECTION, map1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
