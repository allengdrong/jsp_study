package com.webjjang.main.controller;

import java.io.IOException;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webjjang.util.filter.AuthorityFilter;

/**
 * Servlet implementation class DispatcherServlet
 */
//@WebServlet("/DispatcherServlet")
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
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("DispatcherServlet.service()");
		
		// /board/list.do - /board : substring(0, 6(->indexOf("/",1))
		// /qna/list.do - /qna : substring(0, 4(->indexOf("/",1))
		int endIndex = AuthorityFilter.url.indexOf("/", 1);
		String module = "/main";
		// module이 존재하면 바꾼다. "/main.do" : module이 존재하지 않는다. module변수에 있는 값은 바뀌지 않는다.
		if (endIndex >= 0) module = AuthorityFilter.url.substring(0, endIndex);
		
		System.out.println("DispatcherServlet.service().module : " + module);
		
		try {
			
			Controller controller = Beans.getController(module);
			if(controller == null) throw new Exception("DispatcherServlet.controller가 null : Error 404 - 요청하신 URL이 존재하지 않습니다.");
			
			String jspInfo = controller.execute(request);
			
			// sendRedirect를 하려면 리턴되는 되는 문자열 앞에 "redirect:"붙여준다.
			if(jspInfo.indexOf("redirect:") == 0) {
				// "redirect:list.do" -> jspInfo.substring("redirect:".length()) -> list.do
				jspInfo = jspInfo.substring("redirect:".length());
				response.sendRedirect(jspInfo);
			// "redirect:"이 없으면 jsp로 forward 된다.
			} else {
				request.getRequestDispatcher("/WEB-INF/views/" + jspInfo + ".jsp")
				.forward(request, response);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("exception", e);
			// forward 시킨 내용의 url은 변경이 되지 않는다.
			request.getRequestDispatcher("/WEB-INF/views/error/error_page.jsp")
			.forward(request, response);
			
		}
		
		
	}

}
