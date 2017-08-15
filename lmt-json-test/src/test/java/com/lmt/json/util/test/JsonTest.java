package com.lmt.json.util.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lmt.json.util.FastjsonUtil;
import com.lmt.json.util.GsonUtil;
import com.lmt.json.util.JacksonUtil;
import com.lmt.json.util.JsonSmartUtil;
import com.lmt.json.util.JsonUtil;
import com.lmt.json.util.JsonlibUtil;
/**
 * 测试类
 * @author ducx
 *
 */
public class JsonTest {
	
	private static int num = 100000;
	private static List<SeriableBean> list = new ArrayList<SeriableBean>(num);
	
	private static List<String> strList = new ArrayList<String>();

	public static void testFastjson() {
		long t = 0;
		for(int i=0;i<10;i++) {
			long s = System.currentTimeMillis();
			for(SeriableBean b : list) {
				FastjsonUtil.toJson(b);
			}
			long e = System.currentTimeMillis();
			t = t + (e - s);
		}
		System.out.println("fastjson->" + (t/10));
		t = 0;
		for(int i=0;i<10;i++) {
			long s = System.currentTimeMillis();
			for(String str : strList) {
				FastjsonUtil.toObject(str, SeriableBean.class);
			}
			long e = System.currentTimeMillis();
			t = t + (e - s);
		}
		System.out.println("fastjson反解析->" + (t/10));
	}
	
	public static void testGson() {
		long t = 0;
		for(int i=0;i<10;i++) {
			long s = System.currentTimeMillis();
			for(SeriableBean b : list) {
				GsonUtil.toJson(b);
			}
			long e = System.currentTimeMillis();
			t = t + (e - s);
		}
		System.out.println("gson->" + (t/10));
		t = 0;
		for(int i=0;i<10;i++) {
			long s = System.currentTimeMillis();
			for(String str : strList) {
				GsonUtil.toObject(str, SeriableBean.class);
			}
			long e = System.currentTimeMillis();
			t = t + (e - s);
		}
		System.out.println("gson反解析->" + (t/10));
	}
	
	public static void testJackson() {
		long t = 0;
		for(int i=0;i<10;i++) {
			long s = System.currentTimeMillis();
			for(SeriableBean b : list) {
				JacksonUtil.toJson(b);
			}
			long e = System.currentTimeMillis();
			t = t + (e - s);
		}
		System.out.println("jackson->" + (t/10));
		t = 0;
		for(int i=0;i<10;i++) {
			long s = System.currentTimeMillis();
			for(String str : strList) {
				JacksonUtil.fromJson(str, SeriableBean.class);
			}
			long e = System.currentTimeMillis();
			t = t + (e - s);
		}
		System.out.println("jackson反解析->" + (t/10));
	}
	
	public static void testJsonlib() {
		long t = 0;
		for(int i=0;i<10;i++) {
			long s = System.currentTimeMillis();
			for(SeriableBean b : list) {
				JsonlibUtil.toJson(b);
			}
			long e = System.currentTimeMillis();
			t = t + (e - s);
		}
		System.out.println("jsonlib->" + (t/10));
		t = 0;
		for(int i=0;i<10;i++) {
			long s = System.currentTimeMillis();
			for(String str : strList) {
				JsonlibUtil.toObject(str, SeriableBean.class);
			}
			long e = System.currentTimeMillis();
			t = t + (e - s);
		}
		System.out.println("jsonlib反解析->" + (t/10));
	}
	
	public static void testJsonSmart() {
		long t = 0;
		for(int i=0;i<10;i++) {
			long s = System.currentTimeMillis();
			for(SeriableBean b : list) {
				JsonSmartUtil.toJson(b);
			}
			long e = System.currentTimeMillis();
			t = t + (e - s);
		}
		System.out.println("jsonsmart->" + (t/10));
		t = 0;
		for(int i=0;i<10;i++) {
			long s = System.currentTimeMillis();
			for(String str : strList) {
				JsonSmartUtil.toObject(str, HashMap.class);
			}
			long e = System.currentTimeMillis();
			t = t + (e - s);
		}
		System.out.println("jsonsmart反解析->" + (t/10));
	}
	
	public static void testOrgJson() {
		long t = 0;
		for(int i=0;i<10;i++) {
			long s = System.currentTimeMillis();
			for(SeriableBean b : list) {
				JsonUtil.toJson(b);
			}
			long e = System.currentTimeMillis();
			t = t + (e - s);
		}
		System.out.println("orgjson->" + (t/10));
		t = 0;
		for(int i=0;i<10;i++) {
			long s = System.currentTimeMillis();
			for(String str : strList) {
				JsonUtil.toJSONObject(str);
			}
			long e = System.currentTimeMillis();
			t = t + (e - s);
		}
		System.out.println("org.json反解析->" + (t/10));
	}
	
	public static void main(String [] args) {
		for(int i=0;i<num;i++){
			SeriableBean b = BeanUtil.initBean();
			list.add(b);
			strList.add(JsonlibUtil.toJson(b));
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
	
}
