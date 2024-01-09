package com.batchMesComDKListener.task;

import com.batchMesComDKListener.util.Constant;
import com.batchMesComDKListener.util.IniUtil;
import com.batchMesComDKListener.util.WatchDogManager;

import java.awt.*;
import java.awt.event.*;
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
		WatchDogManager.restart();//设置好每周的重启任务
		
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
		
		initSystemTray();
	}
	
	private static void initSystemTray() {
		//https://blog.csdn.net/iteye_9188/article/details/81719048
		try {
			if (SystemTray.isSupported()) {
				SystemTray tray = SystemTray.getSystemTray(); // 创建系统托盘
				
				// 创建弹出菜单
				PopupMenu popup = new PopupMenu();
				
				final MenuItem openItem = new MenuItem("打开");
				popup.add(openItem);
				
				final MenuItem closeItem = new MenuItem("关闭");
				popup.add(closeItem);
				
				final MenuItem exitItem = new MenuItem("退出");
				popup.add(exitItem);
				
				ActionListener listener = new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						MenuItem menuItem = (MenuItem)e.getSource();
						if(menuItem==openItem) {
							listenerTask.showMainJFrame(true);
						}
						else if(menuItem==closeItem) {
							listenerTask.showMainJFrame(false);
						}
						else if(menuItem==exitItem) {
							System.exit(0);
						}
					}
				};
				openItem.addActionListener(listener);
				closeItem.addActionListener(listener);
				exitItem.addActionListener(listener);
	
				Image image = Toolkit.getDefaultToolkit().getImage(StartTask.class.getClassLoader().getResource("watchDog.png"));//载入图片
				TrayIcon trayIcon = new TrayIcon(image, "看门狗程序", popup);// 创建trayIcon
				trayIcon.addActionListener(listener);
					tray.add(trayIcon);
			}
		} catch (AWTException e) {
			e.printStackTrace();
		}
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
