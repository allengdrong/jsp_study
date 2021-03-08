package com.webjjang.qna.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.webjjang.qna.vo.QnaVO;
import com.webjjang.util.PageObject;
import com.webjjang.util.db.DBInfo;
import com.webjjang.util.db.DBSQL;

public class QnaDAO {
	
	// 필요한 객체 선언
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// 1. list
	public List<QnaVO> list(PageObject pageObject) throws Exception {
		List<QnaVO> list = null;
		
		try {
			
			// 1. 2.
			con = DBInfo.getConnection();
			// 3. 4.
			
			pstmt = con.prepareStatement(DBSQL.QNA_LIST);
			pstmt.setLong(1, pageObject.getStartRow());
			pstmt.setLong(2, pageObject.getEndRow());
			
			// 5. 
			rs = pstmt.executeQuery();
			
			// 6.
			if(rs !=null) {
				while(rs.next()) {
					if(list == null) list = new ArrayList<>();
					// 번호, 제목, 작성자이름(작성자ID), 작성일, 조회수, 들여쓰기
					QnaVO vo = new QnaVO();
					vo.setNo(rs.getLong("no"));
					vo.setTitle(rs.getString("title"));
					vo.setName(rs.getString("name"));
					vo.setId(rs.getString("id"));
					vo.setWriteDate(rs.getString("writeDate"));
					vo.setHit(rs.getLong("hit"));
					vo.setLevNo(rs.getLong("levNo"));
					
					list.add(vo);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("질문 답변 리스트 DB 처리 오류 발생");
		} finally {
			DBInfo.close(con, pstmt, rs);
		}
		
		return list;
	}

	// 1-1. getTotalRow - 전체 데이터 갯수 가져오기
		public Long getTotalRow () throws Exception {
			Long result = 0L;
			
			try {
				
				// 1. 2.
				con = DBInfo.getConnection();
				// 3. 4.
				
				pstmt = con.prepareStatement(DBSQL.QNA_GET_TOTALROW);
				
				// 5. 
				rs = pstmt.executeQuery();
				
				// 6.
				if(rs !=null && rs.next()) {
					result = rs.getLong(1);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("질문 답변 전체데이터 갯수 DB 처리 오류 발생");
			} finally {
				DBInfo.close(con, pstmt, rs);
			}
			
			return result;
		}


	// 3-1. 질문하기 - question : 제목, 내용 -> 사용자 입력, 아이디 -> session의 로그인 정보
		public int question (QnaVO vo) throws Exception {
			int result = 0;
			
			try {
				
				// 1. 2.
				con = DBInfo.getConnection();
				// 3. 4.
				
				pstmt = con.prepareStatement(DBSQL.QNA_QUESTION);
				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getContent());
				pstmt.setString(3, vo.getId());
				
				// 5. 
				result = pstmt.executeUpdate();
				
				// 6.
				System.out.println("QnaDAO.question() - 질문 등록 완료 ");
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("질문 등록 중 DB 처리 오류 발생");
			} finally {
				DBInfo.close(con, pstmt, rs);
			}
			
			return result;
		}


}
