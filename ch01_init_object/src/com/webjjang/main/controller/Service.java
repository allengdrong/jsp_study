package com.webjjang.main.controller;

public interface Service {
	
	// 실행할 메서드 선언 - 구현하지는 않는다. (추상)
	public Object service(Object obj) throws Exception;
	
	// dao 넣어주는 setter 선언
	public void setDAO(Object dao) throws Exception;

}
