package com.webjjang.main.controller;

import java.util.HashMap;
import java.util.Map;

public class Beans {
	
	// service 저장 객체
	private static Map<String, Service> serviceMap = new HashMap<>();
	
	// dao 저장 객체
	private static Map<String, Object> daoMap = new HashMap<>();
	
	// service 객체에 넣기와 가져오기
	public static Service get(String key) {
		return serviceMap.get(key);
	}
	public static void put(String key, Service service) {
		serviceMap.put(key, service);
	}
	
	// dao 객체에 넣기와 가져오기
	public static Object getDAO(String key) {
		return daoMap.get(key);
	}
	public static void putDAO(String key, Object dao) {
		daoMap.put(key, dao);
	}
	

}
