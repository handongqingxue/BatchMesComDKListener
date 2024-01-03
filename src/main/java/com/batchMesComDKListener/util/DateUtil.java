package com.batchMesComDKListener.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
	
	private static String formatDefault = "yyyy-MM-dd";

	/**
	 * 天数操作 根据传入日期字符串和天数获取结果
	 * @param dateStr 日期字符串 yyyy-MM-dd
	 * @param days 操作天数
	 * @return days后的日期Date对象
	 */
    public static Date dayOper(String dateStr,int days) {

        int year = new Integer(dateStr.split("-")[0]);
        int month = new Integer(dateStr.split("-")[1]);
        int day = new Integer(dateStr.split("-")[2]);
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.DAY_OF_MONTH,day);
        
        int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        
        calendar.set(Calendar.DAY_OF_YEAR, dayOfYear + days);
        return calendar.getTime();
    }

    /**
     * 取得当前日期是 本年的第几周
     * @param dateStr 当前日期字符串
     * @return
     */
    public static int weekOfYear(String dateStr){
    	
    	int year = new Integer(dateStr.split("-")[0]);
        int month = new Integer(dateStr.split("-")[1]);
        int day = new Integer(dateStr.split("-")[2]);
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.DAY_OF_MONTH,day);
        
    	return calendar.get(Calendar.WEEK_OF_YEAR);
    }

   	/**
   	 * 月数操作
   	 */
    public static String MonthAdd(int days) {
	    SimpleDateFormat form = new SimpleDateFormat("yyyy-MM");
	    Calendar calendar = Calendar.getInstance();
	    int day = calendar.get(Calendar.MONTH);
	    calendar.set(Calendar.MONTH, day + days);
	    Date cc = calendar.getTime();
	    return form.format(cc);
	}
    
    /**
     * 日期操作
     * @param days
     * @return
     */
    public static String getDate(int days) {
    	SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
	    Calendar calendar = Calendar.getInstance();
	    int day = calendar.get(Calendar.DATE);
	    calendar.set(Calendar.DATE, day + days);
	    Date cc = calendar.getTime();
	    return form.format(cc);
	}

	/**
	 * 格式化时间的显示
	 * @param date	如果date为null那么就是当前时间
	 * @param format 日期格式 默认yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getDate(Date date,String format){
		String str = "";
		SimpleDateFormat sdf = null;
		if(format == null){
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}else{
			sdf = new SimpleDateFormat(format);
		}
		if(date==null){
			str = sdf.format(new Date());
		}else{
			str = sdf.format(date);
		}
		return str;
	}
	
	public static Date getCurrentDate(){
		Locale locale = Locale.CHINA;
		Calendar calendar = Calendar.getInstance(locale);
		return calendar.getTime();
	}
	
	public static Date getDate(String str,String type){
		
		if(str == null||str.equals(""))
			return null;
		Date d = null;
		SimpleDateFormat format = new SimpleDateFormat(type,Locale.CHINA);  
		try{
			d = format.parse(str);
			
		}catch(Exception e){
			System.out.println(e);
		}
		return d;
	}
	
	public static String format(Date d,String type) {
		
		String strDate = "";
		if(d == null)
			return strDate;
		SimpleDateFormat format = new SimpleDateFormat(type,Locale.CHINA);  
		try{
			strDate = format.format(d);
			
		}catch(Exception e){
			System.out.println(e);
		}
		return strDate;
	}
	
	public static String format(Date d){
		
		String strDate = "";
		if(d == null)
			return strDate;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA);  
		try{
			strDate = format.format(d);
			
		}catch(Exception e){
			System.out.println(e);
		}
		return strDate;
	}
	
	public static String format(long date){
		
		String strDate = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			strDate = format.format(new Date(date));
			
		}catch(Exception e){
			System.out.println(e);
		}
		return strDate;
	}
	
	 // 求2个日期的天数
    public static long getMothDays(String date1, String date2){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        long myTime;
        Date aDate2;
        Date aDate;
        long myTime2;
        long days = 0;
        try {
            aDate = formatter.parse(date1);// 任意日期，包括当前日期
            myTime = (aDate.getTime() / 1000);

            // SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd");
            aDate2 = formatter.parse(date2);// 任意日期，包括当前日期
            myTime2 = (aDate2.getTime() / 1000);

            if (myTime > myTime2) {
                days = (myTime - myTime2) / (1 * 60 * 60 * 24);
            } else {
                days = (myTime2 - myTime) / (1 * 60 * 60 * 24);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return days;
    }
    
    @SuppressWarnings("static-access")
	public static String nextday(Date date,int interval,String format){
    	
    	String nextDay = "";
    	Calendar cal = Calendar.getInstance(); 
        SimpleDateFormat sdf = new SimpleDateFormat(format); 
        try{ 
            cal.setTime(date); 
            cal.add(cal.DATE, interval); 
            nextDay = sdf.format(cal.getTime());
            
        } catch (Exception e){ 
            e.printStackTrace(); 
        } 
        return nextDay;
    } 

    public static int getDay(Date date){
    	
        Calendar aCalendar = Calendar.getInstance(); 
        //里面野可以直接插入date类型 
        aCalendar.setTime(date); 
        //计算此日期是一周中的哪一天 
        int x = aCalendar.get(Calendar.DAY_OF_WEEK); 
        return x; 
    }
    public static String getDayStr(Date date){
    	
    	String day = "";
    	
    	int x = getDay(date);
    	if(x==1)
    		day = "星期日";
    	else if(x==2)
    		day = "星期一";
    	else if(x==3)
    		day = "星期二";
    	else if(x==4)
    		day = "星期三";
    	else if(x==5)
    		day = "星期四";
    	else if(x==6)
    		day = "星期五";
    	else if(x==7)
    		day = "星期六";
    	
    	return day;
    }
    
	/**
	 * 日期格式化
	 * getDateFormat(String date, String format) 方法说明
	 * return Date
	 */
	public static Date getDateFormat(String date, String format){
		
		if("".equals(format)){
			format = formatDefault;
		}
		if(date == null || "".equals(date)){
			return null;
		}
		
		DateFormat df = new SimpleDateFormat(format);
		Date dd = null;
		try {
			dd = df.parse(date);
		} catch (ParseException e) {
			System.out.println(date+"转换日期"+format+"异常");
			e.printStackTrace();
		}
		return dd;
	}
	
	/**
	 * 日期格式化
	 * getDateFormat(Date date, String format) 方法说明
	 * return String
	 */
	public static String getDateFormat(Date date, String format){
		
		if("".equals(format)){
			format = formatDefault;
		}
		if(date == null){
			return "";
		}
		SimpleDateFormat simple = new SimpleDateFormat(format);
		return simple.format(date);
	}
	
	public static long getInterval_minite(Date d1, Date d2){
		
		long interval = 0;
		long time1 = d1.getTime() / 1000;
		long time2 = d2.getTime() / 1000;

		interval = (time2 - time1) / (1 * 60);
		return interval;
	}
	public static long getInterval_second(Date d1, Date d2){
		
		long interval = 0;
		long time1 = d1.getTime() / 1000;
		long time2 = d2.getTime() / 1000;
		
		interval = (time2 - time1);
		return interval;
	}
	
	/**
	 * 根据系统自动获取当前年份
	 */
	public static String getYear(){
		
		Calendar calendar = Calendar.getInstance();
		String year = calendar.get(Calendar.YEAR)+"";
        return year;
	}
	
	/**
	 * 根据系统自动获取当前月份
	 */
    public static String getMonth() {

    	Calendar calendar = Calendar.getInstance();
    	String month = calendar.get(Calendar.MONTH)+1+"";
        return month;
    }
	
	/**
	 * 根据传入日期字符串获取结果
	 * @param dateStr 日期字符串 yyyy-MM-dd
	 * @return days后的年份
	 */
    public static String getYear(String dateStr) {

    	String year = new Integer(dateStr.split("-")[0])+"";
        return year;
    }

	public static void main(String[] args) throws Exception{
		
		/*
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		long l1 = f.parse("2013-8-6").getTime();
		long l2 = f.parse("2009-6-30").getTime();
		
		long l = l1-l2;
		System.out.println(l/60/60/24/1000);
	
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		System.out.print(formatter.parse("2002-12-11 42:12:12"));
	
//		System.out.println(dd.getDay(dd.getDate("2010-6-13", "yyyy-MM-dd") ));
		//System.out.println(DateUtil.getDate("2010-09-01","yyyy-MM-dd"));
		//System.out.println(new Date());
		 * */
	}
}