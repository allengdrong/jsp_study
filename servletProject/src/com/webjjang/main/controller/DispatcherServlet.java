package com.webjjang.main.controller;

import java.io.IOException;
import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webjjang.util.filter.AuthorityFilter;

/**
 * Servlet implementation class DispacherServlet
 */
// @WebServlet("/DispacherServlet")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DispatcherServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 이곳에서 처리해야할 모든 URL(*.do)을 받도록 설정 -> web.xml

		System.out.println("DispatcherServlet.service()");

		String module = AuthorityFilter.url.substring(0, AuthorityFilter.url.indexOf("/", 1));

		System.out.println("DispatcherServlet.service().module : " + module);

		try {

			Controller controller = Beans.getController(module);
			if (controller == null) throw new Exception("Error 404 - 요청하신 URL이 존재하지 않습니다.");

			String jspInfo = controller.execute(request);

			// sendRedirect를 하려면 리턴되는 문자열 앞에 "redirect:" 붙여준다.
			if (jspInfo.indexOf("redirect:") == 0) {
				// "redirect:list.do" -> jspInfo.substring("redirect".length() -> list.do
				jspInfo = jspInfo.substring("redirect:".length());
				response.sendRedirect(jspInfo);
			// "redirect:"이 없으면 jsp로 foward 된다.
			} else {
				request.getRequestDispatcher("/WEB-INF/views/" + jspInfo + ".jsp")
				.forward(request, response);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}

//		
//		// 요청한 uri를 처리해서 출력
//		String url = request.getServletPath();
//		System.out.println("DispatcherServlet.service().url - " + url);
//		
//		String jspInfo = null;
//
//		try {
//			// "/board"로 시작을 하면 BoardController가 실행이 되게 한다.
//			// "/board"로 시작을 하면 --> url.indexOf("/board") == 0
//			if (url.indexOf("/board") == 0) {
//				BoardController boardController = new BoardController();
//				// "board/list"
//				jspInfo = boardController.excute(request);
//			} else if (url.indexOf("/notice") == 0) {
//				NoticeController noticeController = new NoticeController();
//				// "notice/list"
//				jspInfo = noticeController.excute(request);
//			}
//			
//			else {
//				System.out.println("DispatcherServlet.service() - 404 존재하지 않는 URL");
//
//			}
//			// jspInfo를 가지고 jsp로 이동시키는 프로그램 작성
//			request.getRequestDispatcher("/WEB-INF/views/" + jspInfo + ".jsp")
//			.forward(request, response);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			System.out.println("DispatcherServlet.service() - 예외발생 처리");
//		}
	}

}
