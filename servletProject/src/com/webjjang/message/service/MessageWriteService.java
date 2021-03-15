package com.webjjang.message.service;

import com.webjjang.message.dao.MessageDAO;
import com.webjjang.message.vo.MessageVO;
import com.webjjang.main.controller.Service;

public class MessageWriteService implements Service{

	//dao가 필요하다. 밖에서 생성한 후 넣어준다. - 1. 생성자. 2. setter()
	private MessageDAO dao;
	
	public MessageWriteService() {
		System.out.println("MessageWriteService.MessageWriteService() - 생성 완료");
		// 서버가 시작될때 확인 - 안나오면 Init.init()
	}
	
	@Override
	public void setDAO(Object dao) {
		// Init.init() 조립을 할때 dao 확인 - null이면 안된다. (서버가 시작될때 확인)
		System.out.println("MessageWriteService.setDAO().dao : " + dao);
		// 받아온 dao를 저장한다.
		this.dao = (MessageDAO) dao;
	}
	
	// url 요청에 따른 처리
		// 넘어오는 데이터가 MessageVO ==> obj
		@Override
		public Object service(Object obj) throws Exception {
			// TODO Auto-generated method stub
			// 넘어오는 데이터 확인
			System.out.println("MessageWriteService.obj : " + obj);
			// dao의 write()를 실행해서 결과를 리턴해 준다.
			return dao.write((MessageVO) obj);
	}

}
