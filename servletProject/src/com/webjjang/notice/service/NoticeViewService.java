package com.webjjang.notice.service;

import com.webjjang.notice.dao.NoticeDAO;
import com.webjjang.main.controller.Service;

public class NoticeViewService {
	
	NoticeDAO dao = new NoticeDAO();
	
	public Object service(Object obj) throws Exception {
		return dao.view((Long) obj);
	}

}
