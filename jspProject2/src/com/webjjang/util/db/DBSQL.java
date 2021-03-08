package com.webjjang.util.db;

public class DBSQL {

	// 게시판 쿼리 --------------------------------------------------------------
	// 게시판 리스트
	public static final String BOARD_LIST 
	= "select rnum, no, title, writer,"
	+ " to_char(writeDate, 'yyyy.mm.dd') writeDate, hit from( "
		+ " select rownum rnum, no, title, writer, writeDate, hit from ("
			+ " select no, title, writer, writeDate, hit from board"
			+ " order by no desc "
		+ " ) "
	+ ") where rnum between ? and ?  ";
	public static final String BOARD_VIEW 
	= " select no, title, content, writer, "
	+ " to_char(writeDate, 'yyyy.mm.dd') writeDate, hit"
	+ " from board where no = ? ";
	public static final String BOARD_WRITE 
	= " insert into board(no, title, content, writer) "
	+ " values(board_seq.nextval, ?, ?, ?) ";
	public static final String BOARD_UPDATE 
	= " update board set title = ?, content = ?, writer = ? where no = ? ";
	public static final String BOARD_DELETE 
	= " delete from board where no = ? ";
	public static final String BOARD_INCREASE
	= " update board set hit = hit + 1 where no = ? ";
	public static final String BOARD_GET_TOTALROW
	= " select count(*) from board ";
	
	
	// 공지사항 쿼리
	// 1. 리스트 - 번호, 제목, 공지시작일, 공지종료일, 최근 수정일
	public static final String NOTICE_LIST 
	= "select rnum, no, title, "
	+ " to_char(startDate, 'yyyy.mm.dd') startDate, "
	+ " to_char(endDate, 'yyyy.mm.dd') endDate, "
	+ " to_char(updateDate, 'yyyy.mm.dd') updateDate "
	+ " from( "
		+ " select rownum rnum, no, title, startDate, endDate, updateDate from ("
			+ " select no, title, startDate, endDate, updateDate from notice "
			+ " order by no desc "
		+ " ) "
	+ ") where rnum between ? and ?  ";
	public static final String NOTICE_GET_TOTALROW
	= " select count(*) from notice ";
	public static final String NOTICE_WRITE 
	= " insert into notice(no, title, content, startDate, endDate) "
	+ " values(notice_seq.nextval, ?, ?, ?, ?) ";

	
	// 회원관리 쿼리 ---------------------------------------------------------
	// 로그인 처리
	public static final String MEMBER_LOGIN
	= " select m.id, m.name, m.gradeNo, g.gradeName from member m, grade g "
	+ " where (m.id = ? and m.pw = ?) and (m.gradeNo = g.gradeNo) ";
	// 회원리스트 - id, name, gender, birth, tel, status, gradeNo, gradeName
	public static final String MEMBER_LIST 
	= "select rnum, id, name, gender,"
	+ " to_char(birth, 'yyyy.mm.dd') birth, tel, status, gradeNo, gradeName from( "
		+ " select rownum rnum, id, name, gender, birth, tel, status,"
		+ " gradeNo, gradeName from ("
			+ " select m.id, m.name, m.gender, m.birth, m.tel, m.status,"
			+ " m.gradeNo, g.gradeName"
			+ " from member m, grade g "
			+ " where m.gradeNo = g.gradeNo "
			+ " order by id "
		+ " ) "
	+ ") where rnum between ? and ?  ";
	// 회원 정보 보기
	public static final String MEMBER_VIEW
	= "select m.id, m.name, m.gender, "
			+ " to_char(m.birth, 'yyyy.mm.dd') birth, m.tel, m.email, "
			+ " to_char(m.regDate,'yyyy.mm.dd') regDate, m.status, m.gradeNo, g.gradeName "
			+ " from member m, grade g where (m.id = ?) and (m.gradeNo = g.gradeNo)";
	// 회원등급 수정
	public static final String MEMBER_GRADE_MODIFY
	= "update member set gradeNo = ? where id = ?";
	
	// 메시지 쿼리 ----------------------------------------------------------
	// 메시지 리스트 쿼리 - 번호, 보낸사람의 아이디, 보낸날짜, 받은사람의 아이디, 받은 날짜
	public static final String MESSAGE_LIST
	= " select rnum, no, sender, "
	+ " to_char(sendDate, 'yyyy.mm.dd') sendDate, accepter, "
	+ " to_char(acceptDate, 'yyyy.mm.dd') acceptDate from ( "
		+ " select rownum rnum, no, sender, sendDate, accepter, acceptDate from ( "
			+ " select no, sender, sendDate, accepter, acceptDate from message "
			+ " where sender = ? or accepter = ? "
			+ " order by no desc "
		+ " ) "
	+ " ) where rnum between ? and ? ";
	// 메시지 전체보기 쿼리
	public static final String MESSAGE_GET_TOTALROW
	= " select count(*) from message ";
	// 메시지 보내기 쿼리
	public static final String MESSAGE_WRITE 
	= " insert into message(no, sender, content, accepter ) "
			+ " values(message_seq.nextval, ?, ?, ? ) ";
	
	// 3. 메시지 읽기 쿼리
	public static final String MESSAGE_VIEW
	= " select no, content, sender, to_char(sendDate, 'yyyy.mm.dd') sendDate, "
	+ " accepter,  to_char(acceptDate, 'yyyy.mm.dd') acceptDate from message "
	+ " where no = ? ";
	// 3-1. 메시지 읽기 표시 쿼리 - 보려고 하는 글과 같고 받은 사람이 본인이어야 하고 받은 날짜가 없어야만 한다.
	public static final String MESSAGE_VIEW_READ 
	= " update message set acceptDate = sysdate "
	+ " where no = ? and accepter = ? and acceptDate is null ";
	
	// 4. 메시지 삭제 쿼리
	public static final String MESSAGE_DELETE 
	= " delete from message where no = ? ";
	
	// QnA 쿼리
	// 1. 리스트 - 번호, 제목, 작성자이름(작성자ID), 작성일, 조회수
	public static final String QNA_LIST 
	= "select rnum,  no, title, name, id, "
	+ "to_char(writeDate, 'yyyy.mm.dd') writeDate, hit, levNo "
	+ " from( "
		+ " select rownum rnum, no, title, name, id, writeDate, hit, levNo from ("
			+ " select q.no, q.title, m.name, q.id, q.writeDate, q.hit, q.levNo "
			+ " from qna q, member m "
			+ " where q.id = m.id "
			+ " order by q.refNo desc, q.ordNo "
		+ " ) "
	+ ") where rnum between ? and ?  ";
	
	public static final String QNA_GET_TOTALROW
	= " select count(*) from qna ";
	
	public static final String QNA_QUESTION
	= " insert into qna(no, title, content, id, refNo, ordNo, levNo, parentNo) "
	+ " values(qna_seq.nextval, ?, ?, ?, qna_seq.nextval, 1, 0, qna_seq.nextval) ";
	public static final String QNA_ANSWER
	= " insert into qna(no, title, content, id, refNo, ordNo, levNo, parentNo) "
			+ " values(qna_seq.nextval, ?, ?, ?, ?, ?, ?, ?) ";
}
