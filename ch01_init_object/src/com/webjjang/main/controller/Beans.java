package com.webjjang.main.controller;

import java.util.HashMap;
import java.util.Map;

public class Beans {
	
	// 객체를 저장하는 변수 선언, 생성 후 저장해 놓는다. - Service 객체 저장
	// Map<URL String, 실행할 서비스 객체>
	private static Map<String, Service> serviceMap = new HashMap<>();
	// Map<dao 구분 String, 실행할 DAO 객체>
	private static Map<String, Object> daoMap = new HashMap<>();

	// 저장을 위한 메서드 -> 서버가 실행되면서 객체 초기화 시킬 때 사용.
	public static void put(String url, Service service) {
		serviceMap.put(url, service);
	}

	// 실행할 객체 꺼내기 -> 사용자가 url을 통해서 요청이 있으면 실행할 객체를 꺼낸다.
	public static Service get(String url) {
		return serviceMap.get(url);
	}

	// 저장을 위한 메서드 -> 서버가 실행되면서 객체 초기화 시킬 때 사용. - dao 저장
	public static void putDAO(String str, Object dao) {
		daoMap.put(str, dao);
	}

	// 실행할 객체 꺼내기 -> 사용자가 url을 통해서 요청이 있으면 실행할 객체를 꺼낸다.
	public static Object getDAO(String str) {
		return daoMap.get(str);
	}
	
}
