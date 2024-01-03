package com.batchMesComDKListener.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class WatchDogManager {

	//时间间隔(一周)
	private static final long PERIOD_DAY =7*24*60*60*1000;
	
	public static void restart() {
		Calendar calendar=Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 10);//上午10点
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date date = calendar.getTime();//第一次执行定时任务的时间
		/**如果第一次执行定时任务的时间 小于当前的时间  
                          此时要在 第一次执行定时任务的时间加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。
        **/ 
		if(date.before(new Date())){
			date=DateUtil.dayOper(DateUtil.getDate(new Date(), "yyyy-MM-dd"),7);
		}
		Timer timer = new Timer();
		timer.schedule(new WatchDogTask(), date, PERIOD_DAY);
	}
}
