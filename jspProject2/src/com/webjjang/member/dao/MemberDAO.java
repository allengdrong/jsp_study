package com.webjjang.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.webjjang.member.vo.LoginVO;
import com.webjjang.member.vo.MemberVO;
import com.webjjang.util.db.DBInfo;
import com.webjjang.util.db.DBSQL;

public class MemberDAO {

	// 필요한 객체 선언
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// 로그인 처리를 위한 메서드
	public LoginVO login(LoginVO vo) throws Exception {
		LoginVO loginVO = null;
		
		try {
			// 1. 2. 
			con = DBInfo.getConnection();
			// 3. 4.
			pstmt = con.prepareStatement(DBSQL.MEMBER_LOGIN);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			// 5. 실행
			rs = pstmt.executeQuery();
			// 6. 표시
			if(rs != null && rs.next()) {
				loginVO = new LoginVO();
				loginVO.setId(rs.getString("id"));
				loginVO.setName(rs.getString("name"));
				loginVO.setGradeNo(rs.getInt("gradeNo"));
				loginVO.setGradeName(rs.getString("gradeName"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("로그인 DB 처리중 오류");
		} finally {
			DBInfo.close(con, pstmt, rs);
		}
		
		return loginVO;
	}
	
	// 1. 회원 리스트
		public List<MemberVO> list() throws Exception{
			List<MemberVO> list = null;
			
			try {
				//1. 드라이버 확인 + 2. 연결
				con = DBInfo.getConnection();
				//3. sql -> DBSQL + 4. 실행객체 + 데이터 셋팅
				pstmt = con.prepareStatement(DBSQL.MEMBER_LIST);
				pstmt.setLong(1, 1);
				pstmt.setLong(2, 10);
				
				// 5. 실행
				rs = pstmt.executeQuery();
				// 6. 표시 -> 데이터담기
				if(rs != null) {
					while(rs.next()) {
						if(list == null) list = new ArrayList<MemberVO>();
						MemberVO vo = new MemberVO();
						vo.setId(rs.getString("id"));
						vo.setName(rs.getString("name"));
						vo.setGender(rs.getString("gender"));
						vo.setBirth(rs.getString("birth"));
						vo.setTel(rs.getString("tel"));
						vo.setStatus(rs.getString("status"));
						vo.setGradeNo(rs.getInt("gradeNo"));
						vo.setGradeName(rs.getString("gradeName"));
						
						list.add(vo);
					}
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				// 개발자를 위해서 오류를 콘솔에 출력한다.
				e.printStackTrace();
				// 사용자를 위한 오류 처리
				throw new Exception("회원 리스트 실행 중 DB 처리 오류");
			} finally {
				// 7. 닫기
				DBInfo.close(con, pstmt, rs);
			}
			
			return list;
		}
	
}
