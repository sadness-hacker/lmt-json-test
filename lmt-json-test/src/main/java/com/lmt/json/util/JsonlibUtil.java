package com.lmt.json.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.JSONUtils;

/**
 * 
 * @author ducx
 * @date 2017-07-31
 * 利用net.sf.json解析json
 *
 */
public class JsonlibUtil {
	
	private static JsonConfig conf = new JsonConfig();
	
	static {
		conf.registerJsonValueProcessor(Long.class, new JsonValueProcessor() {
			
			@Override
			public Object processObjectValue(String key, Object value, JsonConfig conf) {
				return String.valueOf(value);
			}
			
			@Override
			public Object processArrayValue(Object value, JsonConfig conf) {
				if(value == null) {
					return null;
				}
				Long [] arr = (Long[]) value;
				String [] sarr = new String[arr.length];
				for(int i=0;i<arr.length;i++) {
					sarr[i] = String.valueOf(arr[i]);
				}
				return sarr;
			}
		});
		conf.registerJsonValueProcessor(Date.class, new JsonValueProcessor() {
			
			private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			@Override
			public Object processObjectValue(String key, Object value, JsonConfig conf) {
				if(value == null) {
					return null;
				}
				return sdf.format(value);
			}
			
			@Override
			public Object processArrayValue(Object value, JsonConfig conf) {
				if(value == null) {
					return null;
				}
				Date [] arr = (Date[]) value;
				String [] sarr = new String[arr.length];
				for(int i=0;i<arr.length;i++) {
					sarr[i] = sdf.format(arr[i]);
				}
				return sarr;
			}
		});
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] {"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd"}));
	}

	/**
	 * 对象转json字符串
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj) {
		if(obj.getClass().isArray() || obj instanceof List) {
			return JSONArray.fromObject(obj,conf).toString();
		}
		return JSONObject.fromObject(obj,conf).toString();
	}
	/**
	 * json字符串转对象
	 * @param json
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T toObject(String json,Class<T> clazz) {
		return (T) JSONObject.toBean(JSONObject.fromObject(json), clazz);
	}
	
}
