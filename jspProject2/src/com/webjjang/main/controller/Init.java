package com.webjjang.main.controller;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.webjjang.board.dao.BoardDAO;
import com.webjjang.board.service.BoardDeleteService;
import com.webjjang.board.service.BoardListService;
import com.webjjang.board.service.BoardUpdateService;
import com.webjjang.board.service.BoardViewService;
import com.webjjang.board.service.BoardWriteService;
import com.webjjang.member.dao.MemberDAO;
import com.webjjang.member.service.MemberGradeModifyService;
import com.webjjang.member.service.MemberListService;
import com.webjjang.member.service.MemberLoginService;
import com.webjjang.member.service.MemberViewService;

/**
 * Servlet implementation class Init
 */
@WebServlet(value = "/Init", loadOnStartup = 1)
public class Init extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Init() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("jspProject2에서의 실행--------->>>");
		System.out.println("서버가 실행을 시작할때 실행되는 처리-----");
		
		// 게시판 객체를 생성 후 저장 =============================================
		// dao 생성 저장
		Beans.putDAO("boardDAO", new BoardDAO());
		
		// service 생성 저장
		Beans.put("/board/list.jsp", new BoardListService());
		Beans.put("/board/view.jsp", new BoardViewService());
		Beans.put("/board/write.jsp", new BoardWriteService());
		Beans.put("/board/update.jsp", new BoardUpdateService());
		Beans.put("/board/delete.jsp", new BoardDeleteService());
		
		// service에 dao 넣기 - 조립
		Beans.get("/board/list.jsp").setDAO(Beans.getDAO("boardDAO"));
		Beans.get("/board/view.jsp").setDAO(Beans.getDAO("boardDAO"));
		Beans.get("/board/write.jsp").setDAO(Beans.getDAO("boardDAO"));
		Beans.get("/board/update.jsp").setDAO(Beans.getDAO("boardDAO"));
		Beans.get("/board/delete.jsp").setDAO(Beans.getDAO("boardDAO"));
		
		// 회원 관리 객체를 생성 후 저장 =============================================
		// dao 생성 저장
		Beans.putDAO("memberDAO", new MemberDAO());
		
		// service 생성 저장
		Beans.put("/member/login.jsp", new MemberLoginService());
		Beans.put("/member/list.jsp", new MemberListService());
		Beans.put("/member/gradeModify.jsp", new MemberGradeModifyService());
		System.out.println("Init.init().Beans.get(\"/member/gradeModify.jsp\" : " 
		+ Beans.get("/member/gradeModify.jsp"));
		
		Beans.put("/member/view.jsp", new MemberViewService());
		System.out.println("Init.init().Beans.get(\"/member/view.jsp\" : " 
				+ Beans.get("/member/view.jsp"));
		
		// service에 dao 넣기 - 조립
		Beans.get("/member/login.jsp").setDAO(Beans.getDAO("memberDAO"));
		Beans.get("/member/list.jsp").setDAO(Beans.getDAO("memberDAO"));
		System.out.println("Init.init().Beans.get(\"memberDAO\" : " 
				+ Beans.getDAO("memberDAO"));
		Beans.get("/member/gradeModify.jsp").setDAO(Beans.getDAO("memberDAO"));
		
		Beans.get("/member/view.jsp").setDAO(Beans.getDAO("memberDAO"));
		
		// 생성 저장이 잘되어 있는지 확인
		// System.out.println(Beans.get("/board/list.jsp"));
		// System.out.println(Beans.getDAO("boardDAO"));
		
		// 오라클 드라이버와 필요한 메서드 로딩
		try {
			// class 안에 있는 static 부분이 로딩 되고 static{} 초기화 블록이 실행됨.
			Class.forName("com.webjjang.util.db.DBInfo");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServletException("드라이버 확인하는 처리 중 오류 발생");
		}
	}

}
