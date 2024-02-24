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
        if(isRunning) {//�����Ź�֮ǰ�����У���ξ�û��Ҫ�ٴ������ˣ�ֱ�ӹر�
        	System.exit(0);
        }
        else {
        	//���exe:https://blog.csdn.net/qq_40298902/article/details/114489753
    		WatchDogManager.restart();//���ú�����Ҫִ�е���������
    		
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
	 * ��֤���Ź��Ƿ�������
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
			        if (line.contains(Constant.EXE_NAME)) {//ָ��Ҫ��ѯ��.exe�ļ���
			            runnerExeCount++;
			        }
			    }

			    //���Ź���ʱ�������б���϶��Ѿ�����runner.exe���̣��ø��������ж��Ƿ�������
			    if(runnerExeCount>1) {//�жϽ����б���runner.exe���Ƶ�������������1��˵��֮ǰ�򿪹�����δ򿪾����ظ����С�
			    	System.out.println("���Ź������ظ������ˣ�");
			    	isRunning=true;
			    }
			    else {
			        System.out.println("���Ź�����δ�ظ����С�");
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
	 * ��ʼ��ϵͳ����
	 */
	private static void initSystemTray() {
		//https://blog.csdn.net/iteye_9188/article/details/81719048
		try {
			if (SystemTray.isSupported()) {
				SystemTray tray = SystemTray.getSystemTray(); // ����ϵͳ����
				
				// ���������˵�
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
	
				Image image = Toolkit.getDefaultToolkit().getImage(StartTask.class.getClassLoader().getResource("watchDog.png"));//����ͼƬ
				TrayIcon trayIcon = new TrayIcon(image, Constant.TRAY_ICON_TITLE, popup);// ����trayIcon
				trayIcon.addActionListener(listener);
				tray.add(trayIcon);
			}
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public static void readIniFile(){
		//��ȡresources���ļ���·��
		File resourcesDir = new File(Constant.RESOURCES_DIR);
		String resourcesPath = null;
		try {
			resourcesPath = resourcesDir.getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//resource��������Ҫ��·�� eg: resource="D:\��Ŀ��\src\main\resources\files\****"
		String iniPath =resourcesPath+"/db.ini";
		try {
			Map<String, String> map = IniUtil.readKeys(iniPath);
			String ipAddressPort = map.get(Constant.IP_ADDRESS_PORT_KEY);
			String dbConnect = map.get(Constant.DB_CONNECT_KEY);
			System.out.println("���ݿ��ַ:"+ipAddressPort);
			System.out.println("���ݿ�����:"+dbConnect);
			
			LinkedHashMap<String,Object> map1=new LinkedHashMap();
			map1.put(Constant.DB_CONNECT_KEY, "BatchMesCom");
			IniUtil.writeKeys(iniPath, Constant.CONNECT_INFO_SECTION, map1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
