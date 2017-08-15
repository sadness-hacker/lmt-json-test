package com.lmt.json.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import org.json.JSONObject;

/**
 * 
 * @author ducx
 * @date 2017-07-31
 * 利用org.json解析json
 */
public class JsonUtil {
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static final SimpleDateFormat sdf2 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);

	/**
	 * 对象序列化为json字符串
	 * @param object
	 * @return
	 */
	public static String toJson(Object object) {
//		JSONObject obj = new JSONObject(object);
//		Iterator<String> iterator = obj.keys();
//		while(iterator.hasNext()) {
//			String key = iterator.next();
//			Object o = obj.get(key);
//			if(o instanceof Long) {
//				obj.put(key, String.valueOf(o));
//			}
//			else if(o instanceof String) {
//				Date value = parseDate(String.valueOf(o));
//				if(value != null) {
//					obj.put(key, sdf.format(value));
//				}
//			}
//		}
//		return obj.toString();
		return JSONObject.wrap(object).toString();
	}
	
	/**
	 * 字符串转JsonObject对象
	 * @param json
	 * @return
	 */
	public static Map<String,Object> toJSONObject(String json) {
		JSONObject o = new JSONObject(json);
		return o.toMap();
	}
	
	private static Date parseDate(String date) {
		try {
			return sdf2.parse(date);
		} catch (ParseException e) {
		}
		return null;
	}
	
}
