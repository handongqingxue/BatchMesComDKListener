package com.batchMesComDKListener.util;

import java.util.TimerTask;

public class WatchDogTask extends TimerTask {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				APIUtil.restartWatchDog();//自杀前先通知Tomcat端需要重启
			}
		}).start();
		System.exit(0);//最好在这里的看门狗端自杀关闭，要是放在Tomcat端关闭，可能会对其他java进程造成影响
	}

}
