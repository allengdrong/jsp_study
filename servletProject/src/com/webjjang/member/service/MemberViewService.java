package com.webjjang.member.service;

import com.webjjang.main.controller.Service;
import com.webjjang.member.dao.MemberDAO;

public class MemberViewService implements Service{

	//dao가 필요하다. 밖에서 생성한 후 넣어준다. - 1. 생성자. 2. setter()
	private MemberDAO dao;
	
	@Override
	public void setDAO(Object dao) {
		this.dao = (MemberDAO) dao;
	}
	
	@Override
	// 넘어오는 데이터 : 아이디 - 타입 : String
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return dao.view((String) obj);
		
	}
}
