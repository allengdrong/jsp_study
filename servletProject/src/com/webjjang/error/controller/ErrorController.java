package com.webjjang.error.controller;


import javax.servlet.http.HttpServletRequest;

import com.webjjang.main.controller.Controller;
import com.webjjang.util.filter.AuthorityFilter;

public class ErrorController implements Controller{

	private final String MODULE = "error";
	private String jspInfo = null;
	
	@Override
	public String execute(HttpServletRequest request) throws Exception{
		System.out.println("BoardController.execute()");
		
		switch (AuthorityFilter.url) {
		// 1. 권한 없음 오류 페이지
		case "/" + MODULE +"/auth_error.do":
			jspInfo = MODULE + "/auth_error";			
			break;

		
		default:
			throw new Exception("페이지 오류 404 - 존재하지 않는 페이지입니다.");
		}
		
		// jsp의 정보를 가지고 리턴한다.
		return jspInfo;
	}
	
}
