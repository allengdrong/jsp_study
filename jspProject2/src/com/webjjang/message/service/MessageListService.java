package com.webjjang.message.service;

import com.webjjang.main.controller.Service;
import com.webjjang.message.dao.MessageDAO;
import com.webjjang.util.PageObject;

public class MessageListService implements Service{

	//dao가 필요하다. 밖에서 생성한 후 넣어준다. - 1. 생성자. 2. setter()
	MessageDAO dao;
	
	@Override
	public void setDAO(Object dao) {
		this.dao = (MessageDAO) dao;
	}
	
	// url 요청에 따른 처리
	// 넘어오는 데이터가 PageObject ==> obj
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		// 넘어오는 데이터 확인
		System.out.println("MessageListService.obj : " + obj);
		// 전체 데이터를 가져오기
		int totalRow = (int) dao.getTotalRow();
		PageObject pageObject = (PageObject) obj;
		pageObject.setTotalRow(totalRow);
		// 전체 페이지 셋팅 후 페이지 객체 출력
		System.out.println("MessageListService.pageObject : " + pageObject);
		return dao.list(pageObject);
	}

}
