package com.webjjang.util.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
// import javax.websocket.Session;

import com.webjjang.member.vo.LoginVO;
// import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class AuthorityFilter
 */
// @WebFilter("/AuthorityFilter")
public class AuthorityFilter implements Filter {

	// url에 대한 권한 정보를 저장하는 Map
	// Map<url, gradeNo>
	private static Map<String, Integer> authMap = new HashMap<>();
	
	// 페이지에 대한 등급 정보를 저장하는 메서드
	// 데이터를 넣는 방법 : static 초기화 블록
	static {
		// 공지사항 - 등록, 수정, 삭제 - 관리자 : 9
		authMap.put("/notice/writeForm.jsp", 9);
		authMap.put("/notice/write.jsp", 9);
		authMap.put("/notice/updateForm.jsp", 9);
		authMap.put("/notice/update.jsp", 9);
		authMap.put("/notice/delete.jsp", 9);
		// 메시지 - 리스트, 보기, 보내기, 삭제 : 1
		authMap.put("/message/list.jsp", 1);
		authMap.put("/message/view.jsp", 1);
		authMap.put("/message/writeForm.jsp", 1);
		authMap.put("/message/write.jsp", 1);
		authMap.put("/message/delete.jsp", 1);
	}
	
	// 요청한 url
	public static String url;
	
    /**
     * Default constructor. 
     */
    public AuthorityFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		
		// 전처리 - 권한 체크 프로그램
		System.out.println("Authority.doFilter - 권한 처리를 한다.");
		// jsp의 request와 여기의 request는 같은 것이다. HttpServletRequest가 ServletRequest을 상속하고 있다.
		url = req.getServletPath();
		System.out.println("Authority.doFilter.url : " + url);
		
		// 로그인 객체 꺼내기
		// 로그인 정보는 session에 있다. session이 안보인다. request에서 꺼낼수 있다.
		HttpSession session = req.getSession();
		LoginVO vo = (LoginVO) session.getAttribute("login");
		
		// 권한이 없는 경우의 처리
		if(!checkAuthority(vo)) {
			// 오류페이지로 이동 시킵니다.
			((HttpServletResponse)response)
			.sendRedirect(req.getContextPath() + "/error/auth_error.jsp");
			// 호출한 쪽으로 되돌아 갑니다. -> 없으면 계속 아래로 실행이 된다.
			return;
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	private boolean checkAuthority(LoginVO vo) {
		// url 정보가 authMap 있는지 확인 한다.
		// 데이터가 없으면(null이면) 권한체크가 필요 없는 페이지 요청입니다.
		Integer pageGradeNo = authMap.get(url);
		if(pageGradeNo == null) {
			System.out.println("AuthorityFilter.checkAuthority() - 권한이 필요없는 페이지 입니다. ");
			return true;
		}
		// 여기서 부터 로그인이 필요한 처리입니다. vo가 null이면 안된다.
		if(vo == null) {
			System.out.println("AuthorityFilter.checkAuthority() - 로그인이 필요합니다.");
			return false;
			
		}
		System.out.println("AuthorityFilter.checkAuthority().pageGradeNo : " + pageGradeNo);
		System.out.println("AuthorityFilter.checkAuthority().userGradeNo : " + vo.getGradeNo());
		// 권한이 없는 페이지 요청에 대한 처리
		if(pageGradeNo > vo.getGradeNo()) {
			System.out.println("AuthorityFilter.checkAuthority() - 권한이 없습니다. ");
			return false;
		}
		System.out.println("AuthorityFilter.checkAuthority() - 권한이 있습니다. ");
		return true;
	}
	
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
