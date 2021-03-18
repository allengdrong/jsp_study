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
	public List<MessageVO> list(PageObject pageObject) throws Exception {
		System.out.println("MessageDAO.list().pageObect : " + pageObject);
		List<MessageVO> list = null;

		try {
			// 1.2.
			con = DBInfo.getConnection();
			// 3.4.
			pstmt = con.prepareStatement(DBSQL.MESSAGE_LIST);
			pstmt.setString(1, pageObject.getAccepter());
			pstmt.setString(2, pageObject.getAccepter());
			pstmt.setLong(3, pageObject.getStartRow());
			pstmt.setLong(4, pageObject.getEndRow());
			// 5.
			rs = pstmt.executeQuery();
			// 6.
			if (rs != null) {
				while (rs.next()) {
					if (list == null)
						list = new ArrayList<>();
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
			throw new Exception("메시지 리스트 DB 처리 중 오류");
		} finally {
			DBInfo.close(con, pstmt, rs);
		}

		return list;
	} // end of list()

	// 메시지 전체 데이터 갯수 가져오기
	public long getTotalRow() throws Exception {
		System.out.println("MessageDAO.getTotalRow()");
		long result = 0;

		try {
			// 1.2.
			con = DBInfo.getConnection();
			// 3.4.
			pstmt = con.prepareStatement(DBSQL.MESSAGE_GET_TOTALROW);
			// 5.
			rs = pstmt.executeQuery();
			// 6.
			if (rs != null && rs.next()) {
				result = rs.getLong(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("메시지 전체 데이터 가져오기 DB 처리 중 오류");
		} finally {
			DBInfo.close(con, pstmt, rs);
		}

		return result;
	} // end of getTotalRow()

	// 메시지 보내기(write) 처리
	public int write(MessageVO vo) throws Exception {
		int result = 0;

		try {
			// 1. 2.
			con = DBInfo.getConnection();
			// 3. 4.
			pstmt = con.prepareStatement(DBSQL.MESSAGE_WRITE);
			pstmt.setString(1, vo.getSender());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getAccepter());
			// 5.
			result = pstmt.executeUpdate();
			// 6.
			System.out.println("MessageDAO.write() - 메시지 보내기 완료");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("메시지 보내기 DB 처리 중 오류");
		} finally {
			DBInfo.close(con, pstmt);
		}

		return result;
	}

	// 메시지 보기(view)의 읽음 처리
	public int viewUpdateReaded(MessageVO vo) throws Exception {
		int result = 0;

		try {
			// 1. 2.
			con = DBInfo.getConnection();
			// 3. 4.
			System.out.println(DBSQL.MESSAGE_VIEW_READ);
			pstmt = con.prepareStatement(DBSQL.MESSAGE_VIEW_READ);
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getAccepter());
			// 5.
			result = pstmt.executeUpdate();
			// 6.
			System.out.println("MessageDAO.viewUpdateReaded() - 메시지 읽음 표시 처리 완료");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("메시지 읽음 표시  DB 처리 중 오류");
		} finally {
			DBInfo.close(con, pstmt);
		}

		return result;
	}

	// 메시지 읽기(view) 처리
	public MessageVO view(long no) throws Exception {
		MessageVO vo = null;

		try {
			// 1.2.
			con = DBInfo.getConnection();
			// 3.4.
			System.out.println(DBSQL.MESSAGE_VIEW);
			pstmt = con.prepareStatement(DBSQL.MESSAGE_VIEW);
			pstmt.setLong(1, no);
			// 5.
			rs = pstmt.executeQuery();
			// 6. 데이터를 한개만 가져 오므로 반복문이 필요 없어서 조건 2개를 합친다.
			if (rs != null && rs.next()) {
				vo = new MessageVO();
				vo.setNo(rs.getLong("no"));
				vo.setContent(rs.getString("content"));
				vo.setSender(rs.getString("sender"));
				vo.setSendDate(rs.getString("sendDate"));
				vo.setAccepter(rs.getString("accepter"));
				vo.setAcceptDate(rs.getString("acceptDate"));
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("메시지 보기 DB 처리 중 오류");
		} finally {
			DBInfo.close(con, pstmt, rs);
		}

		return vo;
	} // end of view()

	// 5. 게시판 글삭제
	public int delete(long no) throws Exception {
		int result = 0;
		try {
			// 1. 확인 2. 연결
			con = DBInfo.getConnection();
			// 3. sql . 4.실행&데이터셋팅
			pstmt = con.prepareStatement(DBSQL.MESSAGE_DELETE);
			pstmt.setLong(1, no);
			// 5. 실행
			result = pstmt.executeUpdate();
			// 6. 출력
			if (result == 1)
				System.out.println("게시판 글삭제에 성공하셨습니다.");
			else
				System.out.println("삭제하려는 글의 정보를 확인하세요.");

		} catch (Exception e) {
			// TODO: handle exception
			// 개발자를 위한 예외출력(500) -> 콘솔
			e.printStackTrace();
			// 사용자를 위한 예외처리. -> jsp까지 전달된다.
			throw new Exception("게시판 글삭제 DB 처리 중 오류 발생.");
		} finally {
			DBInfo.close(con, pstmt);
		}
		return result;
	}

	// 6. 새로운 메시지 갯수 가져오기
	public Long getMessageCnt(String id) throws Exception {
		// TODO Auto-generated method stub

		Long cnt = 0L;
		
		try {
			
			// 1. 2.
			con = DBInfo.getConnection();
			// 3. 4.
			pstmt = con.prepareStatement(DBSQL.MESSAGE_GET_MESSAGE_CNT);
			pstmt.setString(1, id);
			// 5.
			rs = pstmt.executeQuery();
			// 6.
			if(rs != null && rs.next()) {
				cnt = rs.getLong(1);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("MessageDAO - 새로운 메시지 갯수 DB 처리중 오류");
		} finally {
			DBInfo.close(con, pstmt, rs);
		}

		System.out.println("MessageDAO.getMessageCnt().cnt : " + cnt);
		return cnt;
	}

}
