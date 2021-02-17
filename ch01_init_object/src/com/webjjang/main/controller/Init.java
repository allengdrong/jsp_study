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

/**
 * Servlet implementation class Init
 * @인터페이스(초기화값...)
 * @WebServlet("/Init") -> name에 들어간다. 여러개를 초가화값으로 입력 시에는 항목
 * 이름과 값을 "," 이용해서 구분해서 넣으면 된다.
 */
@WebServlet(value = "/Init" , loadOnStartup = 1)
public class Init extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     * 생성자 - 기본 생성자
     */
    public Init() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 * 서버가 실행하면서 호출되는 메서드
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Init.init() - 초기화 : 객체 생성");
		// DAO 객체 생성
		Beans.putDAO("boardDAO", new BoardDAO());
		
		// Service 객체를 생성해서 저장하는 프로그램
		Beans.put("/board/list.jsp", new BoardListService());
		Beans.put("/board/view.jsp", new BoardViewService());
		Beans.put("/board/write.jsp", new BoardWriteService());
		Beans.put("/board/update.jsp", new BoardUpdateService());
		Beans.put("/board/delete.jsp", new BoardDeleteService());
		
		// DAO 객체를 찾아서 Service 객체에 넣어 준다.
		try {
			Beans.get("/board/list.jsp").setDAO(Beans.getDAO("boardDAO"));
			Beans.get("/board/view.jsp").setDAO(Beans.getDAO("boardDAO"));
			Beans.get("/board/write.jsp").setDAO(Beans.getDAO("boardDAO"));
			Beans.get("/board/update.jsp").setDAO(Beans.getDAO("boardDAO"));
			Beans.get("/board/delete.jsp").setDAO(Beans.getDAO("boardDAO"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("객체 생성에 성공하셨습니다. --------------------");
	}

}
