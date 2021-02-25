package com.webjjang.util.db;

public class DBSQL {

	// 게시판 쿼리 --------------------------------------------------------------
	// 게시판 리스트 쿼리
	public static final String BOARD_LIST 
	= "select rnum, no, title, writer,"
	+ " to_char(writeDate, 'yyyy.mm.dd') writeDate, hit from( "
		+ " select rownum rnum, no, title, writer, writeDate, hit from ("
			+ " select no, title, writer, writeDate, hit from board"
			+ " order by no desc "
		+ " ) "
	+ ") where rnum between ? and ?  ";
	// 게시판 보기 쿼리
	public static final String BOARD_VIEW 
	= " select no, title, content, writer, "
	+ " to_char(writeDate, 'yyyy.mm.dd') writeDate, hit"
	+ " from board where no = ? ";
	// 게시판 작성자 쿼리
	public static final String BOARD_WRITE 
	= " insert into board(no, title, content, writer) "
	+ " values(board_seq.nextval, ?, ?, ?) ";
	// 게시판 수정 쿼리
	public static final String BOARD_UPDATE 
	= " update board set title = ?, content = ?, writer = ? where no = ? ";
	// 게시판 삭제 쿼리
	public static final String BOARD_DELETE 
	= " delete from board where no = ? ";
	// 게시판 리스트 조회수 1증가 쿼리
	public static final String BOARD_INCREASE
	= " update board set hit = hit + 1 where no = ? ";
	// 게시판 전체 데이터 확인 쿼리
	public static final String BOARD_GET_TOTALROW
	= " select count(*) from board ";
	
	// 회원관리 쿼리 ---------------------------------------------------------------
	// 로그인 처리
	public static final String MEMBER_LOGIN
	= " select m.id, m.name, m.gradeNo, g.gradeName from member m, grade g "
	+ " where (m.id = ? and m.pw = ?) and (m.gradeNo = g.gradeNo) ";
	
	// 회원 리스트 처리 - id, name, gender, birth, tel, status, gradeNo, gradeName
	public static final String MEMBER_LIST
	/* = " select m.id, m.name, m.gender, to_char(birth, 'yyyy.mm.dd') birth, m.tel, m.status, "
	+ " m.gradeNo, g.gradeName "
	+ " from member m, grade g"
	+ " where (m.gradeNo = g.gradeNo) "
	+ " order by m.id desc ";
	*/
	
	= "select rnum, id, name, gender, "
		+ " to_char(birth, 'yyyy.mm.dd') birth, tel, status, gradeNo, gradeName from( "
			+ " select rownum rnum, id, name, gender, birth, tel, status, "
			+ " gradeNo, gradeName from ("
				+ " select m.id, m.name, m.gender, m.birth, m.tel, m.status, "
				+ " m.gradeNo, g.gradeName "
				+ " from member m, grade g "
				+ " where m.gradeNo = g.gradeNo "
				+ " order by id "
			+ " ) "
		+ ") where rnum between ? and ?  ";
	
}