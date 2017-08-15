package com.lmt.json.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.minidev.json.JSONStyle;
import net.minidev.json.JSONValue;
import net.minidev.json.reader.JsonWriterI;
import net.minidev.json.writer.ArraysMapper;

/**
 * 
 * @author ducx
 * @date 2017-07-31
 * 利用json-smart解析json
 *
 */
public class JsonSmartUtil {
	
	static {
		System.out.println("JSONValue.registerWriter");
		JSONValue.registerWriter(Long.class, new JsonWriterI<Long>() {

			@Override
			public void writeJSONString(Long value, Appendable out, JSONStyle compression)
					throws IOException {
				if (value == null)
					out.append("null");
				else
				out.append("\"").append(String.valueOf(value)).append("\"");
			}
			
		});
		JSONValue.registerWriter(Date.class, new JsonWriterI<Date>() {
			private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			@Override
			public void writeJSONString(Date value, Appendable out, JSONStyle compression)
					throws IOException {
				if (value == null)
					out.append("null");
				else
				out.append("\"").append(sdf.format(value)).append("\"");
			}
			
		});
		JSONValue.registerReader(long[].class, new ArraysMapper<long[]>(null) {
			@Override
			public long[] convert(Object current) {
				int p = 0;
				long[] r = new long[((List<?>) current).size()];
				for (Object e : ((List<?>) current))
					r[p++] = Long.valueOf(String.valueOf(e));
				return r;
			}
		});
		JSONValue.registerReader(Long[].class, new ArraysMapper<Long[]>(null) {
			@Override
			public Long[] convert(Object current) {
				int p = 0;
				Long[] r = new Long[((List<?>) current).size()];
				for (Object e : ((List<?>) current))
					r[p++] = Long.valueOf(String.valueOf(e));
				return r;
			}
		});
		JSONValue.registerReader(Date.class, new ArraysMapper<Date>(null) {
			private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			@Override
			public Date convert(Object current) {
				try {
					return sdf.parse(String.valueOf(current));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return null;
			}
		});
	}

	/**
	 * 对象转字符串
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj) {
		return JSONValue.toJSONString(obj);
	}
	
	/**
	 * 字符串转对象
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T toObject(String json,Class<T> clazz) {
		return JSONValue.parse(json, clazz);
	}
	
}

