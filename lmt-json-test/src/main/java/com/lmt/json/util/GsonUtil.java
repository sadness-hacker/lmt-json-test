package com.lmt.json.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * 
 * @author ducx
 * @date 2017-07-31
 * 利用gson解析json
 *
 */
public class GsonUtil {
	
	private static final Gson gson = getGson();
	
	/**
	 * 获取一个gson对象
	 * @return
	 */
	public static final Gson getGson() {
		GsonBuilder gsonBulder = new GsonBuilder();
		gsonBulder.registerTypeAdapter(Long.class, new LongConversionString());
		gsonBulder.registerTypeAdapter(Date.class, new DateConversionString());
		return gsonBulder.create();
	}

	/**
	 * 对象转json字符串
	 * @param object
	 * @return
	 */
	public static String toJson(Object object) {
		return gson.toJson(object);
	}
	
	/**
	 * 字符串转对象
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T toObject(String json,Class<T> clazz) {
		return gson.fromJson(json, clazz);
	}
	
	public static <T> T toObject(String json,TypeToken<T> type) {
		return gson.fromJson(json, type.getType());
	}
	
}

/**
 * 
 * @author  ducx
 * @date 2016-09-01
 *
 */
class LongConversionString extends TypeAdapter<Long>{

	@Override
	public Long read(JsonReader in) throws IOException {
		if(in.hasNext()) {
			return Long.valueOf(in.nextString());
		}
		return null;
	}
	@Override
	public void write(JsonWriter out, Long value) throws IOException {
		if(null == value){
			out.value("");
		}else{
			out.value(Long.toString(value));
		}
	}
}
/**
 * 
 * @author ducx
 * @date 2016-09-01
 *
 */
class DateConversionString extends TypeAdapter<Date>{
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public void write(JsonWriter out, Date value) throws IOException {
		if(value == null){
			out.value("");
		}else{
			out.value(sdf.format(value));
		}
	}

	@Override
	public Date read(JsonReader in) throws IOException {
		if(in.hasNext()) {
			try {
				return sdf.parse(in.nextString());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
}
