package com.webjjnag.test.dao;

import com.webjjang.board.dao.BoardDAO;
import com.webjjang.util.db.DBSQL;

public class BoardDAOTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		// BoardDAO 테스팅.
		// BoardDAO 객체 생성
		BoardDAO dao = new BoardDAO();
	// 	System.out.println(dao.list());
		
		System.out.println(DBSQL.MEMBER_LIST);
	}

}
