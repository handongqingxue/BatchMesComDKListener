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
	 * �������� ���ݴ��������ַ�����������ȡ���
	 * @param dateStr �����ַ��� yyyy-MM-dd
	 * @param days ��������
	 * @return days�������Date����
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
     * ȡ�õ�ǰ������ ����ĵڼ���
     * @param dateStr ��ǰ�����ַ���
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
   	 * ��������
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
     * ���ڲ���
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
	 * ��ʽ��ʱ�����ʾ
	 * @param date	���dateΪnull��ô���ǵ�ǰʱ��
	 * @param format ���ڸ�ʽ Ĭ��yyyy-MM-dd HH:mm:ss
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
	
	 // ��2�����ڵ�����
    public static long getMothDays(String date1, String date2){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        long myTime;
        Date aDate2;
        Date aDate;
        long myTime2;
        long days = 0;
        try {
            aDate = formatter.parse(date1);// �������ڣ�������ǰ����
            myTime = (aDate.getTime() / 1000);

            // SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd");
            aDate2 = formatter.parse(date2);// �������ڣ�������ǰ����
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
        //����Ұ����ֱ�Ӳ���date���� 
        aCalendar.setTime(date); 
        //�����������һ���е���һ�� 
        int x = aCalendar.get(Calendar.DAY_OF_WEEK); 
        return x; 
    }
    public static String getDayStr(Date date){
    	
    	String day = "";
    	
    	int x = getDay(date);
    	if(x==1)
    		day = "������";
    	else if(x==2)
    		day = "����һ";
    	else if(x==3)
    		day = "���ڶ�";
    	else if(x==4)
    		day = "������";
    	else if(x==5)
    		day = "������";
    	else if(x==6)
    		day = "������";
    	else if(x==7)
    		day = "������";
    	
    	return day;
    }
    
	/**
	 * ���ڸ�ʽ��
	 * getDateFormat(String date, String format) ����˵��
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
			System.out.println(date+"ת������"+format+"�쳣");
			e.printStackTrace();
		}
		return dd;
	}
	
	/**
	 * ���ڸ�ʽ��
	 * getDateFormat(Date date, String format) ����˵��
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
	 * ����ϵͳ�Զ���ȡ��ǰ���
	 */
	public static String getYear(){
		
		Calendar calendar = Calendar.getInstance();
		String year = calendar.get(Calendar.YEAR)+"";
        return year;
	}
	
	/**
	 * ����ϵͳ�Զ���ȡ��ǰ�·�
	 */
    public static String getMonth() {

    	Calendar calendar = Calendar.getInstance();
    	String month = calendar.get(Calendar.MONTH)+1+"";
        return month;
    }
	
	/**
	 * ���ݴ��������ַ�����ȡ���
	 * @param dateStr �����ַ��� yyyy-MM-dd
	 * @return days������
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