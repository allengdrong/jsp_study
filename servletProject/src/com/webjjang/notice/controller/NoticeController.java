package com.webjjang.notice.controller;

import javax.servlet.http.HttpServletRequest;

public class NoticeController {

	public String excute(HttpServletRequest request) throws Exception{
		System.out.println("NoticeController.execute()");
		return "notice/list";
	}
	
}
