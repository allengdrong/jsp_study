package com.webjjang.board.dao;
/*
 * 필요한 메서드들
 * list() getTotalRow() view() increase() write() update() delete()
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.webjjang.board.vo.BoardVO;
import com.webjjang.util.db.DBInfo;
import com.webjjang.util.db.DBSQL;

public class BoardDAO {

	// 필요한 객체 선언 - con, pstmt, rs
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// 1. 게시판 리스트
	public List<BoardVO> list() throws Exception{
		List<BoardVO> list = null;
		
		try {
			// 1. 드라이버 확인 + 2. 연결객체
			con = DBInfo.getConnection();
			// 3. sql - DBSQL + 4. 실행객체 + 데이터 셋팅
			pstmt = con.prepareStatement(DBSQL.BOARD_LIST);
			pstmt.setLong(1, 1); // 시작 번호
			pstmt.setLong(2, 10); // 끝 번호
			// 5. 실행
			rs = pstmt.executeQuery();
			// 6. 표시 -> 데이터 담기
			if(rs != null) {
				while(rs.next()) {
					if(list == null) list = new ArrayList<BoardVO>();
					BoardVO vo = new BoardVO();
					vo.setNo(rs.getLong("no"));
					vo.setTitle(rs.getString("title"));
					vo.setWriter(rs.getString("writer"));
					vo.setWriteDate(rs.getString("writeDate"));
					vo.setHit(rs.getLong("hit"));
					list.add(vo);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			// 개발자를 위해서 오류를 콘솔에 출력한다.
			e.printStackTrace();
			// 사용자를 위한 오류 처리
			throw new Exception("게시판 리스트 실행 중 DB 처리 오류");
		} finally {
			// 7. 닫기
			DBInfo.close(con, pstmt, rs);
		}
		
		return list;
	}
	
	// 1-1. 전체 데이터 갯수 구하기
	public long getTotalRow() throws Exception {
		return 0;
	}
	
	// 2. 게시판 글보기
	public BoardVO view(long no) throws Exception {
		
		BoardVO vo = null;
		
		try {
			// 1. 드라이버 확인 + 2. 연결객체
			con = DBInfo.getConnection();
			// 3. sql - DBSQL + 4. 실행객체 + 데이터 셋팅
			pstmt = con.prepareStatement(DBSQL.BOARD_VIEW);
			pstmt.setLong(1, no); // 시작 번호
			// 5. 실행 - 데이터 한개 반복문 필요 없음.
			rs = pstmt.executeQuery();
			// 6. 표시 -> 데이터 담기
			if(rs != null && rs.next()) {
				vo = new BoardVO();
				vo.setNo(rs.getLong("no"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setWriteDate(rs.getString("writeDate"));
				vo.setHit(rs.getLong("hit"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			// 개발자를 위해서 오류를 콘솔에 출력한다.
			e.printStackTrace();
			// 사용자를 위한 오류 처리
			throw new Exception("게시판 글보기 실행 중 DB 처리 오류");
		} finally {
			// 7. 닫기
			DBInfo.close(con, pstmt, rs);
		}
		
		return vo;
	}
	
	// 2-1. 조회수 1 증가(리스트 -> 글보기)
	public int increase(long no) throws Exception {
		return 0;
	}
	
	// 3. 게시판 글쓰기
	public int write(BoardVO vo) throws Exception {

		int result = 0;
		
		try {
			// 1. 드라이버 확인 + 2. 연결객체
			con = DBInfo.getConnection();
			// 3. sql - DBSQL + 4. 실행객체 + 데이터 셋팅
			pstmt = con.prepareStatement(DBSQL.BOARD_WRITE);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getWriter());
			
			result = pstmt.executeUpdate();
			
			return result;
			
		} catch (Exception e) {
			// TODO: handle exception
			// 개발자를 위해서 오류를 콘솔에 출력한다.
			e.printStackTrace();
			// 사용자를 위한 오류 처리
			throw new Exception("게시판 글쓰기 실행 중 DB 처리 오류");
		} finally {
			// 7. 닫기
			DBInfo.close(con, pstmt, rs);
		}
		
	}
	
	// 4. 게시판 글수정
	public int update(BoardVO vo) throws Exception {
		return 0;
	}
	
	// 5. 게시판 글삭제
	public int delete(long no) throws Exception {
		return 0;
	}
	
}
