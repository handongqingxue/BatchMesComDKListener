package com.batchMesComDKListener.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.*;
import java.util.Map.*;

import org.json.JSONException;
import org.json.JSONObject;

public class APIUtil {

	public static final String SERVICE_URL="http://localhost:8080/BatchMesComDK/batch/";
	//public static final String SERVICE_URL="http://192.168.1.106:8080/BatchMesComDK/batch/";
	/**
	 * 是否正在读取接口数据
	 */
	public static boolean reading;

	//https://www.cnblogs.com/aeolian/p/7746158.html
	//https://www.cnblogs.com/bobc/p/8809761.html
	public static JSONObject doHttp(String method, Map<String, Object> params) throws IOException {
		JSONObject resultJO = null;
		try {
			// 构建请求参数  
			StringBuffer paramsSB = new StringBuffer();
			if (params != null) {  
			    for (Entry<String, Object> e : params.entrySet()) {
			    	paramsSB.append(e.getKey());  
			    	paramsSB.append("=");  
			    	paramsSB.append(e.getValue());  
			    	paramsSB.append("&");  
			    }  
			    paramsSB.substring(0, paramsSB.length() - 1);
			}  
			
			StringBuffer sbf = new StringBuffer(); 
			String strRead = null; 
			URL url = new URL(SERVICE_URL+method);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			//connection.setConnectTimeout(15000);
			connection.setReadTimeout(15000);//Read timed out
			connection.setRequestMethod("POST");//请求post方式
			connection.setDoInput(true); 
			connection.setDoOutput(true); 
			//header内的的参数在这里set    
			//connection.setRequestProperty("key", "value");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.connect(); 
			
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(),"UTF-8"); 
			//OutputStream writer = connection.getOutputStream(); 
			writer.write(paramsSB.toString());
			writer.flush();
			InputStream is = connection.getInputStream(); 
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			while ((strRead = reader.readLine()) != null) {
				sbf.append(strRead); 
				sbf.append("\r\n"); 
			}
			reader.close();
			
			connection.disconnect();
			String result = sbf.toString();
			System.out.println("result==="+result);
			resultJO = new JSONObject(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultJO = new JSONObject();
			resultJO.put("success", "false");
			resultJO.put("message", e.getMessage());//Read timed out
		}
		return resultJO;
	}

	/**
	 * 巡回工单状态
	 * @return
	 */
	public static JSONObject keepWatchOnWorkOrder() {
		// TODO Auto-generated method stub
		JSONObject resultJO = null;
		reading=true;
		System.out.println("reading2==="+reading);
		try {
	        resultJO = doHttp("keepWatchOnWorkOrder",null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			String message = e.getMessage();
			e.printStackTrace();
			if("Connection refused: connect".equals(message)) {
				resultJO.put("success", "false");
				resultJO.put("message", e.getMessage());
			}
		}
		finally {
			reading=false;
			System.out.println("resultJO==="+resultJO.toString());
			return resultJO;
		}
	}

	/**
	 * 巡回工单状态(测试用)
	 * @return
	 */
	public static JSONObject keepWatchOnWorkOrderTest() {
		// TODO Auto-generated method stub
		JSONObject resultJO = null;
		try {
	        resultJO = doHttp("keepWatchOnWorkOrderTest",null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return resultJO;
		}
	}

	/**
	 * 巡检已完成的工单状态
	 * @return
	 */
	public static JSONObject keepWatchOnWOFinish() {
		// TODO Auto-generated method stub
		JSONObject resultJO = null;
		try {
	        resultJO = doHttp("getSendToMesBRData",null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return resultJO;
		}
	}
	
	public static JSONObject restartWatchDog() {
		// TODO Auto-generated method stub
		JSONObject resultJO = null;
		try {
	        resultJO = doHttp("restartWatchDog",null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return resultJO;
		}
	}
}
