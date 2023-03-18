package com.batchMesComDKListener.util;

import org.ini4j.Wini;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

public class IniUtils {

    /**
     * 读取ini文件信息
     * @param urlPath ini文件位置
     * @return 读取ini文件以map返回
     * @throws Exception
     */
    public static Map<String,String> readIniFile(String urlPath) throws Exception {
        InputStream in = new FileInputStream(new File(urlPath));
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        Properties props = new Properties();
        props.load(br);
        Map<String, String> map = new HashMap<String, String>();
        for(Object s: props.keySet()){
            map.put(s.toString(), props.getProperty(s.toString()));
        }
        return map;
    }

    /**
     * 写入ini文件数据
     * @param urlPath ini文件位置
     * @param title 头信息（详细见ini文件格式）
     * @param map 写入的数据
     * @return 成功返回   不成功报错
     * @throws Exception
     */
    public static String  writeIniFile(String urlPath, String title, LinkedHashMap<String,Object> map) throws  Exception{
        Wini wini = new Wini(new File(urlPath));
        for (Map.Entry<String, Object> next : map.entrySet()) {
            wini.put(title, next.getKey(), next.getValue());
        }
        wini.store();
        return "写入成功";
    }
}
