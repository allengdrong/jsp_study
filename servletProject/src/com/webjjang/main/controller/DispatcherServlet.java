package com.OOTD.main.controller;

import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.OOTD.util.filter.AuthorityFilter;

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
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("DispatcherServlet.service : *.do");
		
		int endIndex = AuthorityFilter.url.indexOf("/", 1);
		
		String module = "/main";
		
		if(endIndex >= 0) {
			
			module = AuthorityFilter.url.substring(0, endIndex);	// module이 존재하면 다른 module로 바꿈
			
			System.out.println("DispatcherServlet.service() [module] : " + module);
			
		}
		
		// 모듈에 포함이 되어 있지 않는 URL의 처리 : siteMesh에 적용이 되지 않도록 해야 함
//		if(AuthorityFilter.url.equals("/?.do")) {
			
//			module = "/main";
			
//		}
		
		try {

			Controller controller = Beans.getController(module);	// 실행할 Controller를 선택
			
			if(controller == null) {
				
				throw new Exception("DispatcherServlet.Controller = null : 404 Error : 요청하신 URL이 존재하지 않습니다.");
				
			}
			
			// Controller를 실행하고 forward 혹은 sendRedirect 정보를 돌려받음
			String jspInfo = controller.execute(request);
			
			if(jspInfo.indexOf("redirect:") == 0) {
				
				jspInfo = jspInfo.substring("redirect:".length());
				
				response.sendRedirect(jspInfo);
				
				return;
				
			} else {
				
				request.getRequestDispatcher("/WEB-INF/views/" + jspInfo + ".jsp").forward(request, response);
				
			} 
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			request.setAttribute("exception", e);
			
			request.getRequestDispatcher("/WEB-INF/views/error/error_page.jsp").forward(request, response);
			
		}
		
	}
    
}
