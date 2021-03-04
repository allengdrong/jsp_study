package com.webjjang.message.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.webjjang.message.vo.MessageVO;
import com.webjjang.util.PageObject;
import com.webjjang.util.db.DBInfo;
import com.webjjang.util.db.DBSQL;

public class MessageDAO {

	// 필요한 객체 선언
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// 메시지 리스트
	public List<MessageVO> list(PageObject pageObject) throws Exception{
		System.out.println("MessageDAO.list().pageObect : " + pageObject);
		List<MessageVO> list = null;
		
		try {
			//1.2.
			con = DBInfo.getConnection();
			// 3.4.
			pstmt = con.prepareStatement(DBSQL.MESSAGE_LIST);
			pstmt.setLong(1, pageObject.getStartRow());
			pstmt.setLong(2, pageObject.getEndRow());
			//5. 
			rs = pstmt.executeQuery();
			//6.
			if(rs != null) {
				while(rs.next()) {
					if(list == null) list = new ArrayList<>();
					MessageVO vo = new MessageVO();
					vo.setNo(rs.getLong("no"));
					vo.setSender(rs.getString("sender"));
					vo.setSendDate(rs.getString("sendDate"));
					vo.setAccepter(rs.getString("accepter"));
					vo.setAcceptDate(rs.getString("acceptDate"));
					list.add(vo);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("공지사항 리스트 DB 처리 중 오류");
		} finally {
			DBInfo.close(con, pstmt, rs);
		}
		
		return list;
	}
	
	// 메시지 전체 데이터 갯수 가져오기
	public long getTotalRow() throws Exception{
		System.out.println("MessageDAO.getTotalRow()");
		long result = 0;
		
		try {
			//1.2.
			con = DBInfo.getConnection();
			// 3.4.
			pstmt = con.prepareStatement(DBSQL.MESSAGE_GET_TOTALROW);
			//5. 
			rs = pstmt.executeQuery();
			//6.
			if(rs != null && rs.next()) {
				result = rs.getLong(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("공지사항 전체 데이터 가져오기 DB 처리 중 오류");
		} finally {
			DBInfo.close(con, pstmt, rs);
		}
		
		return result;
	} // end of getTotalRow()
	
	
}
