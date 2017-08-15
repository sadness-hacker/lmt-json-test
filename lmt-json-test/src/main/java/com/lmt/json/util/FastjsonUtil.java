package com.lmt.json.util;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lmt.common.serializer.FastjsonLongSerializer;

/**
 * 利用fastjson解析json
 * @author ducx
 * @date 2017-07-31
 *
 */
public class FastjsonUtil {
	
	static {
		SerializeConfig.globalInstance.put(Long.class, new FastjsonLongSerializer());
		System.out.println("SerializeConfig.globalInstance.put(Long.class, new FastjsonLongSerializer())");
	}
	/**
	 * 对象转成json字符串，日期格式为yyyy-MM-dd HH:mm:ss
	 * @param object
	 * @return
	 */
	public static String toJson(Object object) {
		return JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss",SerializerFeature.WriteDateUseDateFormat);
	}

	/**
	 * json字符串转成对象
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T toObject(String json,Class<T> clazz) {
		return JSON.parseObject(json, clazz);
	}
	/**
	 * 反解析为list
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static<T> List<T> toList(String json,Class<T> clazz){
		return JSONArray.parseArray(json, clazz);
	}
	/**
	 * 反解析为list
	 * @param json
	 * @param type
	 * @return
	 */
	public static<T> T toList(String json,TypeReference<T> type){
		return JSONArray.parseObject(json, type);
	}
	
}
