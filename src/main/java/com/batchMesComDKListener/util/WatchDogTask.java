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
				APIUtil.restartWatchDog();//��ɱǰ��֪ͨTomcat����Ҫ����
			}
		}).start();
		System.exit(0);//���������Ŀ��Ź�����ɱ�رգ�Ҫ�Ƿ���Tomcat�˹رգ����ܻ������java�������Ӱ��
	}

}
