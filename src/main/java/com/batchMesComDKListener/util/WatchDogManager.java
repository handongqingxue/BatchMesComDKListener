package com.batchMesComDKListener.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class WatchDogManager {

	//ʱ����(һ��)
	private static final long PERIOD_DAY =7*24*60*60*1000;
	
	public static void restart() {
		Calendar calendar=Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 10);//����10��
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date date = calendar.getTime();//��һ��ִ�ж�ʱ�����ʱ��
		/**�����һ��ִ�ж�ʱ�����ʱ�� С�ڵ�ǰ��ʱ��  
                          ��ʱҪ�� ��һ��ִ�ж�ʱ�����ʱ���һ�죬�Ա���������¸�ʱ���ִ�С��������һ�죬���������ִ�С�
        **/ 
		if(date.before(new Date())){
			date=DateUtil.dayOper(DateUtil.getDate(new Date(), "yyyy-MM-dd"),7);
		}
		Timer timer = new Timer();
		timer.schedule(new WatchDogTask(), date, PERIOD_DAY);
	}
}
