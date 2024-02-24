package com.batchMesComDKListener.task;

import com.batchMesComDKListener.util.Constant;
import com.batchMesComDKListener.util.IniUtil;
import com.batchMesComDKListener.util.LogUtil;
import com.batchMesComDKListener.util.WatchDogManager;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class StartTask {
	
	static KeepWatchTask keepWatchTask;
	static SendMesBRTask sendMesBRTask;
	static ListenerTask listenerTask;
	
	public static void main(String[] args) {
		boolean isRunning = checkIfRunning();
        if(isRunning) {//若看门狗之前已运行，这次就没必要再次运行了，直接关闭
        	System.exit(0);
        }
        else {
        	//打包exe:https://blog.csdn.net/qq_40298902/article/details/114489753
    		WatchDogManager.restart();//设置好马上要执行的重启任务
    		
    		keepWatchTask=new KeepWatchTask();
    		keepWatchTask.setActive(true);
    		
    		sendMesBRTask=new SendMesBRTask();
    		sendMesBRTask.setActive(true);
    		
    		listenerTask=new ListenerTask();
    		listenerTask.initMainJFrame();

    		keepWatchTask.start();
    		//sendMesBRTask.start();
    		listenerTask.start();
    		//readIniFile();
    		
    		initSystemTray();
        }
	}
	
	/**
	 * 验证看门狗是否在运行
	 * @return
	 */
	private static boolean checkIfRunning() {
		// TODO Auto-generated method stub
		//https://blog.csdn.net/weixin_39709134/article/details/131857449
		boolean isRunning=false;
        int runnerExeCount=0;
        try {
			ProcessBuilder processBuilder = new ProcessBuilder("tasklist");
			Process process = processBuilder.start();
			
			InputStream inputStream = process.getInputStream();
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			    for (String line; (line = reader.readLine()) != null; ) {
		            //System.out.println("line=="+line);
			        if (line.contains(Constant.EXE_NAME)) {//指定要查询的.exe文件名
			            runnerExeCount++;
			        }
			    }

			    //看门狗打开时，进程列表里肯定已经存在runner.exe进程，得根据数量判断是否已运行
			    if(runnerExeCount>1) {//判断进程列表里runner.exe名称的数量，若大于1，说明之前打开过，这次打开就是重复运行。
			    	System.out.println("看门狗程序重复运行了！");
			    	isRunning=true;
			    }
			    else {
			        System.out.println("看门狗程序未重复运行。");
			    	isRunning=false;
			    }
			} catch (Exception e) {
			    e.printStackTrace();
			} 
			finally {
				inputStream.close();
			    process.destroy();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        finally {
        	return isRunning;
		}
	}

	/**
	 * 初始化系统托盘
	 */
	private static void initSystemTray() {
		//https://blog.csdn.net/iteye_9188/article/details/81719048
		try {
			if (SystemTray.isSupported()) {
				SystemTray tray = SystemTray.getSystemTray(); // 创建系统托盘
				
				// 创建弹出菜单
				PopupMenu popup = new PopupMenu();
				
				final MenuItem openItem = new MenuItem(Constant.TRAY_OPEN_MENU_TEXT);
				popup.add(openItem);
				
				final MenuItem closeItem = new MenuItem(Constant.TRAY_CLOSE_MENU_TEXT);
				popup.add(closeItem);
				
				final MenuItem exitItem = new MenuItem(Constant.TRAY_EXIT_MENU_TEXT);
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
				TrayIcon trayIcon = new TrayIcon(image, Constant.TRAY_ICON_TITLE, popup);// 创建trayIcon
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
