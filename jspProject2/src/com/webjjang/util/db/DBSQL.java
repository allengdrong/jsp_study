package com.webjjang.util.db;

public class DBSQL {
	
	// 게시판 쿼리
	public static final String BOARD_LIST 
		= " select rnum, no, title, writer, "
		+ " to_char(writeDate, 'yyyy.mm.dd') writeDate, hit from( "
		+ " select rownum rnum, no, title, writer, writeDate, hit from ( "
		+ " select no, title, writer, writeDate, hit from board "
		+ " order by no desc "
		+ " ) "
		+ ") where rnum between ? and ? ";
	public static final String BOARD_VIEW 
		= " select no, title, content, writer, "
		+ " to_char(writeDate, 'yyyy.mm.dd') writeDate, hit"
		+ " from board where no = ? ";
	
	public static final String BOARD_WRITE 
		= " insert into board(no, title, content, writer)"
		+ " values(board_seq.nextval, ?, ?, ?) ";
	
	public static final String BOARD_UPDATE
		= " update board set title = ?, content = ?, writer = ? where no = ? ";
	
	public static final String BOARD_DELETE 
		= " delete from board where no = ? ";
	
	public static final String BOARD_INCREASE 
		= " updeate board set hit = hit + 1 where no = ? ";
	
	public static final String BOARD_GET_TOTALROW 
		= " select count(*) from board ";

}
