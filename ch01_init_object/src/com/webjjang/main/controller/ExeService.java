/*
 * 서비스를 실행하는 객체
 * 실행하려는 서비스와 전달되는 데이터를 받아서 정보를 출력하고 서비스를 실행한 후 결과를 리턴해 준다.
 * 작성일 : 2021.01.18
 * 작성자 : 이영환 
 */
package com.webjjang.main.controller;

public class ExeService {

	// 사용방법 : 변수 = (변수타입) ExeService.execute(생성된 서비스, 전달 데이터)
	// 서비스 실행을 위한 메서드 - service(실행하려는 서비스, 전달 객체)
	// 전달 객체가 필요없는 경우는 null을 전달하면 됩니다.
	// 프록시 프로그램 패턴 - 필터(JSP), 인터셉터(Spring)
	public static Object execute(Service service, Object obj) throws Exception{
		// 공통처리부분
		// 실행하려는 객체의 정보 출력
		System.out.println(service.getClass().getSimpleName() + ".service()");
		// 전달되는 데이터 출력
		System.out.println("전달되는 데이터 : " + obj);
		
		// 실제적을 서비스를 실행하는 부분 -> 호출한다.
		Object result = service.service(obj);
		System.out.println("실행결과 : " + result);
		return result;
	}
	
}
