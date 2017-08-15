package com.lmt.common.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.lmt.common.serializer.CustomLongSerializer;

/**
 * 
 * @author ducx
 * @date 2017-07-31
 * 利用fastjson实现的通用json序列化与反序列化工具类
 *
 */
public class JSONUtil {
	
	private static final ObjectMapper mapper = getObjectMapper();
	
	/**
	 * 获取一个mapper,日期格式化成yyyy-MM-dd HH:mm:ss格式，long型格式化成字符串，避免前端处理long型失精度问题
	 * @return
	 */
	private static ObjectMapper getObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		SimpleModule module = new SimpleModule();
		module.addSerializer(new CustomLongSerializer());
		mapper.registerModule(module);
		return mapper;
	}

	/**
	 * 对象序列化成字符串
	 * @param object
	 * @return
	 */
	public static String toJson(Object object) {
		if(object == null) {
			return null;
		}
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * json字符串转化成map
	 * @param json
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,Object> fromJson(String json){
		if(json == null || json.trim().equals("")) {
			return null;
		}
		try {
			return mapper.readValue(json, Map.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * json字符串转化成指定的对象
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T fromJson(String json,Class<T> clazz) {
		if(json == null || json.trim().equals("") || clazz == null) {
			return null;
		}
		try {
			return mapper.readValue(json, clazz);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * json字符串数组转list对象列表
	 * @param jsonArr
	 * @return
	 */
	public static <T> List<T> fromJsonArr(String jsonArr){
		if(jsonArr == null || jsonArr.trim().equals("")) {
			return null;
		}
		try {
			return mapper.readValue(jsonArr, new TypeReference<List<T>>() {});
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
