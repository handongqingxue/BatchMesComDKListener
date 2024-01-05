package com.batchMesComDKListener.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class WatchDogManager {

	//延迟时间(一周)
	private static final int DELAY_DAY =7;
	
	public static void restart() {
		Calendar calendar=Calendar.getInstance();
		Date nowDate = new Date();
		int hours = nowDate.getHours();
		int minutes = nowDate.getMinutes();
		//System.out.println("hours="+hours);
		//System.out.println("minutes="+minutes);
		minutes++;
		calendar.set(Calendar.HOUR_OF_DAY, hours);
		calendar.set(Calendar.MINUTE, minutes);
		calendar.set(Calendar.SECOND, 0);
		Date date = calendar.getTime();//第一次执行定时任务的时间
        //System.out.println(date);
        //在第一次执行定时任务的时间加一周，以便此任务在下个时间点执行。如果不加一周，任务会立即执行。
		//date=DateUtil.minuteOper(DateUtil.getDate(new Date(), "yyyy-MM-dd HH:mm"),DELAY_DAY);
		date=DateUtil.dayOper(DateUtil.getDate(new Date(), "yyyy-MM-dd"),DELAY_DAY);
		Timer timer = new Timer();
		timer.schedule(new WatchDogTask(), date);
	}
}
