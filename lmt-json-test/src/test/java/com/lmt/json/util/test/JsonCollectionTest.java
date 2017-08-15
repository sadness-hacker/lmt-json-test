package com.lmt.json.util.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.TypeReference;
import com.google.gson.reflect.TypeToken;
import com.lmt.json.util.FastjsonUtil;
import com.lmt.json.util.GsonUtil;
import com.lmt.json.util.JacksonUtil;
import com.lmt.json.util.JsonSmartUtil;
import com.lmt.json.util.JsonUtil;
import com.lmt.json.util.JsonlibUtil;

/**
 * 
 * @author ducx
 * @date 2017-08-09
 * json集合测试
 *
 */
public class JsonCollectionTest {
	
	private static int num = 10000;
	
	private static int size = 10;
	
	private static List<List<SeriableBean>> beanList = new ArrayList<>(num);
	
	private static List<String> beanListStr = new ArrayList<>();
	
	private static List<Map<String,SeriableBean>> mapList = new ArrayList<>(num);
	
	private static List<String> mapListStr = new ArrayList<>();

	public static void main(String [] args) {
		for(int i=0;i<num;i++) {
			List<SeriableBean> list = new ArrayList<>(size);
			Map<String, SeriableBean> map = new HashMap<>();
			for(int j=0;j<size;j++) {
				SeriableBean bean = BeanUtil.initBean();
				list.add(bean);
				map.put(bean.getIdNum(), bean);
			}
			beanList.add(list);
			mapList.add(map);
			beanListStr.add(FastjsonUtil.toJson(list));
			mapListStr.add(FastjsonUtil.toJson(map));
		}
		System.out.println("初始化完毕...");
		SeriableBean b = BeanUtil.initBean();
		System.out.println("fastjson->" + FastjsonUtil.toJson(b));
		System.out.println("fastjson反解析->" + FastjsonUtil.toObject(FastjsonUtil.toJson(b), SeriableBean.class));
		System.out.println("gson->" + GsonUtil.toJson(b));
		System.out.println("gson反解析->" + GsonUtil.toObject(FastjsonUtil.toJson(b), SeriableBean.class));
		System.out.println("jackson->" + JacksonUtil.toJson(b));
		System.out.println("jackson反解析->" + JacksonUtil.fromJson(FastjsonUtil.toJson(b), SeriableBean.class));
		System.out.println("jsonlib->" + JsonlibUtil.toJson(b));
		System.out.println("jsonlib反解析->" + JsonlibUtil.toObject(FastjsonUtil.toJson(b), SeriableBean.class));
		System.out.println("jsonsmart->" + JsonSmartUtil.toJson(b));
		System.out.println("jsonsmart反解析->" + JsonSmartUtil.toObject(FastjsonUtil.toJson(b), HashMap.class));
		System.out.println("orgjson->" + JsonUtil.toJson(b));
		testFastjson();
		testGson();
		testJackson();
		testJsonlib();
		testJsonSmart();
		testOrgJson();
	}
	
	public static void testFastjson() {
		long t = 0;
		for(int i=0;i<10;i++) {
			long s = System.currentTimeMillis();
			for(List<SeriableBean> b : beanList) {
				FastjsonUtil.toJson(b);
			}
			long e = System.currentTimeMillis();
			t = t + (e - s);
		}
		System.out.println("fastjson-list->" + (t/10));
		t = 0;
		for(int i=0;i<10;i++) {
			long s = System.currentTimeMillis();
			for(Map<String,SeriableBean> m : mapList) {
				FastjsonUtil.toJson(m);
			}
			long e = System.currentTimeMillis();
			t = t + (e - s);
		}
		System.out.println("fastjson-map->" + (t/10));
		
		t = 0;
		for(int i=0;i<10;i++) {
			long s = System.currentTimeMillis();
			for(String str : beanListStr) {
				FastjsonUtil.toList(str, SeriableBean.class);
			}
			long e = System.currentTimeMillis();
			t = t + (e - s);
		}
		System.out.println("fastjson-list反解析->" + (t/10));
		t = 0;
		TypeReference<Map<String,SeriableBean>> type = new TypeReference<Map<String,SeriableBean>>(){};
		for(int i=0;i<10;i++) {
			long s = System.currentTimeMillis();
			for(String str : mapListStr) {
				FastjsonUtil.toList(str, type);
			}
			long e = System.currentTimeMillis();
			t = t + (e - s);
		}
		System.out.println("fastjson-map反解析->" + (t/10));
	}
	
	public static void testGson() {
		long t = 0;
		for(int i=0;i<10;i++) {
			long s = System.currentTimeMillis();
			for(List<SeriableBean> b : beanList) {
				GsonUtil.toJson(b);
			}
			long e = System.currentTimeMillis();
			t = t + (e - s);
		}
		System.out.println("gson-list->" + (t/10));
		t = 0;
		for(int i=0;i<10;i++) {
			long s = System.currentTimeMillis();
			for(Map<String,SeriableBean> m : mapList) {
				GsonUtil.toJson(m);
			}
			long e = System.currentTimeMillis();
			t = t + (e - s);
		}
		System.out.println("gson-map->" + (t/10));
		
		t = 0;
		TypeToken<List<SeriableBean>> type = new TypeToken<List<SeriableBean>>() {};
		for(int i=0;i<10;i++) {
			long s = System.currentTimeMillis();
			for(String str : beanListStr) {
				GsonUtil.toObject(str, type);
			}
			long e = System.currentTimeMillis();
			t = t + (e - s);
		}
		System.out.println("gson-list反解析->" + (t/10));
		t = 0;
		TypeToken<Map<String,SeriableBean>> type1 = new TypeToken<Map<String,SeriableBean>>() {};
		for(int i=0;i<10;i++) {
			long s = System.currentTimeMillis();
			for(String str : mapListStr) {
				GsonUtil.toObject(str, type1);
			}
			long e = System.currentTimeMillis();
			t = t + (e - s);
		}
		System.out.println("gson-map反解析->" + (t/10));
	}
	
	public static void testJackson() {
		long t = 0;
		for(int i=0;i<10;i++) {
			long s = System.currentTimeMillis();
			for(List<SeriableBean> b : beanList) {
				JacksonUtil.toJson(b);
			}
			long e = System.currentTimeMillis();
			t = t + (e - s);
		}
		System.out.println("jackson-list->" + (t/10));
		t = 0;
		for(int i=0;i<10;i++) {
			long s = System.currentTimeMillis();
			for(Map<String,SeriableBean> m : mapList) {
				JacksonUtil.toJson(m);
			}
			long e = System.currentTimeMillis();
			t = t + (e - s);
		}
		System.out.println("jackson-map->" + (t/10));
		
		t = 0;
		com.fasterxml.jackson.core.type.TypeReference<List<SeriableBean>> type = new com.fasterxml.jackson.core.type.TypeReference<List<SeriableBean>>(){};
		for(int i=0;i<10;i++) {
			long s = System.currentTimeMillis();
			for(String str : beanListStr) {
				JacksonUtil.fromJsonArr(str,type);
			}
			long e = System.currentTimeMillis();
			t = t + (e - s);
		}
		System.out.println("jackson-list反解析->" + (t/10));
		t = 0;
		com.fasterxml.jackson.core.type.TypeReference<Map<String,SeriableBean>> type1 = new com.fasterxml.jackson.core.type.TypeReference<Map<String,SeriableBean>>(){};
		for(int i=0;i<10;i++) {
			long s = System.currentTimeMillis();
			for(String str : mapListStr) {
				JacksonUtil.fromJsonArr(str,type1);
			}
			long e = System.currentTimeMillis();
			t = t + (e - s);
		}
		System.out.println("jackson-map反解析->" + (t/10));
	}
	
	public static void testJsonlib() {
		long t = 0;
		for(int i=0;i<10;i++) {
			long s = System.currentTimeMillis();
			for(List<SeriableBean> b : beanList) {
				JsonlibUtil.toJson(b);
			}
			long e = System.currentTimeMillis();
			t = t + (e - s);
		}
		System.out.println("jsonlib-list->" + (t/10));
		t = 0;
		for(int i=0;i<10;i++) {
			long s = System.currentTimeMillis();
			for(Map<String,SeriableBean> m : mapList) {
				JsonlibUtil.toJson(m);
			}
			long e = System.currentTimeMillis();
			t = t + (e - s);
		}
		System.out.println("jsonlib-map->" + (t/10));
	}
	
	public static void testJsonSmart() {
		long t = 0;
		for(int i=0;i<10;i++) {
			long s = System.currentTimeMillis();
			for(List<SeriableBean> b : beanList) {
				JsonSmartUtil.toJson(b);
			}
			long e = System.currentTimeMillis();
			t = t + (e - s);
		}
		System.out.println("jsonsmart-list->" + (t/10));
		t = 0;
		for(int i=0;i<10;i++) {
			long s = System.currentTimeMillis();
			for(Map<String,SeriableBean> m : mapList) {
				JsonSmartUtil.toJson(m);
			}
			long e = System.currentTimeMillis();
			t = t + (e - s);
		}
		System.out.println("jsonsmart-map->" + (t/10));
	}
	
	public static void testOrgJson() {
		long t = 0;
		for(int i=0;i<10;i++) {
			long s = System.currentTimeMillis();
			for(List<SeriableBean> b : beanList) {
				JsonUtil.toJson(b);
			}
			long e = System.currentTimeMillis();
			t = t + (e - s);
		}
		System.out.println("orgjson-list->" + (t/10));
		t = 0;
		for(int i=0;i<10;i++) {
			long s = System.currentTimeMillis();
			for(Map<String,SeriableBean> m : mapList) {
				JsonUtil.toJson(m);
			}
			long e = System.currentTimeMillis();
			t = t + (e - s);
		}
		System.out.println("org.json-map->" + (t/10));
	}
}
