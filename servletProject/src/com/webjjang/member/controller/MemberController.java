package com.webjjang.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.webjjang.member.vo.LoginVO;
import com.webjjang.member.vo.MemberVO;
import com.webjjang.main.controller.Beans;
import com.webjjang.main.controller.Controller;
import com.webjjang.main.controller.ExeService;
import com.webjjang.util.PageObject;
import com.webjjang.util.filter.AuthorityFilter;

public class MemberController implements Controller{

	private final String MODULE = "member";
	private String jspInfo = null;
	
	@Override
	public String execute(HttpServletRequest request) throws Exception{
		System.out.println("MemberController.execute()");
		
		// 페이지를 위한 처리
		PageObject pageObject = PageObject.getInstance(request);
		request.setAttribute("pageObject", pageObject); // 페이지를 보여주기 위해 서버객체에 담는다.
		
		switch (AuthorityFilter.url) {
		// 1. 회원 리스트
		case "/" + MODULE +"/list.do":
			// service - dao --> request에 저장까지 해준다.
			list(request, pageObject);
		
			// "member/list" 넘긴다. -> /WEB-INF/views/ + member/list + .jsp를 이용해서 HTML을 만든다.
			jspInfo = MODULE + "/list";			
			break;

		// 2. 회원 글보기
		case "/" + MODULE +"/view.do":
			// service - dao --> request에 저장까지 해준다.
			view(request);
		
			// "member/view" 넘긴다. -> /WEB-INF/views/ + member/view + .jsp를 이용해서 HTML을 만든다.
			jspInfo = MODULE + "/view";			
			break;
		
		// 3-1. 회원 글쓰기 폼
		case "/" + MODULE +"/writeForm.do":
			// "member/view" 넘긴다. -> /WEB-INF/views/ + member/view + .jsp를 이용해서 HTML을 만든다.
			jspInfo = MODULE + "/writeForm";			
			break;
		
		// 3-2. 회원 글쓰기 처리
		case "/" + MODULE +"/write.do":
			// service - dao --> request에 저장까지 해준다.
			write(request);
		
			// "member/view" 넘긴다. -> /WEB-INF/views/ + member/view + .jsp를 이용해서 HTML을 만든다.
			jspInfo = "redirect:list.do?page=1&perPageNum=" + pageObject.getPerPageNum();			
			break;
		
		// 4-1. 회원 글수정 폼
		case "/" + MODULE +"/updateForm.do":
			updateForm(request);
			// "member/updateForm" 넘긴다. -> /WEB-INF/views/ + member/updateForm + .jsp를 이용해서 HTML을 만든다.
			jspInfo = MODULE + "/updateForm";			
			break;
		
		// 4-2. 회원 글수정 처리
		case "/" + MODULE +"/update.do":
			// service - dao --> request에 저장까지 해준다.
			Long no = update(request);
		
			// "member/view" 넘긴다. -> view.do로 자동으로 이동
			jspInfo = "redirect:view.do?no=" + no + "&inc=0&page="
					+ pageObject.getPage() + "&perPageNum=" + pageObject.getPerPageNum();			
			break;
		
		// 5. 회원 글삭제 처리
		case "/" + MODULE +"/delete.do":
			// service - dao --> request에 저장까지 해준다.
			delete(request);
		
			// list.do로 자동으로 이동
			jspInfo = "redirect:list.do?page=1&perPageNum=" + pageObject.getPerPageNum();			
			break;
		
		// 6-1. 로그인 폼
		case "/" + MODULE +"/loginForm.do":
			// list.do로 자동으로 이동
			jspInfo = MODULE + "/loginForm";			
			break;
		
		// 6-2. 로그인 처리
		case "/" + MODULE +"/login.do":
			// service - dao --> request에 저장까지 해준다.
			login(request);
		
			// list.do로 자동으로 이동
			jspInfo = "redirect:/board/list.do";			
			break;
		
		// 6-2. 로그아웃 처리
		case "/" + MODULE +"/logout.do":
			// service - dao --> request에 저장까지 해준다.
			logout(request);
		
		// list.do로 자동으로 이동
		jspInfo = "redirect:/board/list.do";			
		break;
		
		// 7. 회원가입 처리
		case "/" + MODULE + "/join.do;"
			join(request);
		jspInfo = "redirect:/main.do";
		break;
		
		default:
			throw new Exception("페이지 오류 404 - 존재하지 않는 페이지입니다.");
		}
		
		// jsp의 정보를 가지고 리턴한다.
		return jspInfo;
	}
	
	
	// 1. 회원 리스트 처리.
	private void list(HttpServletRequest request, PageObject pageObject) throws Exception {
		// 여기가 자바 코드입니다. servlet-controller(*)-Service-DAO

		//request.getServletPath(); -> AuthorityFilter 76줄에서 실행한 후 url 저장하도록 하고 있다.
		@SuppressWarnings("unchecked")
		List<MemberVO> list 
		= (List<MemberVO>) ExeService.execute(Beans.get(AuthorityFilter.url), pageObject);
		// 서버객체 request에 담는다.
		request.setAttribute("list", list);
		
	}
	
	// 2. 회원 등록 처리.
	private void view (HttpServletRequest request) throws Exception {
		// 여기가 자바 코드입니다. servlet - Controller-Service-DAO -> /member/view.do

		// 넘어오는 데이터 받기 
		// - 글번호
		String strNo = request.getParameter("no");
		long no = Long.parseLong(strNo);
		// - 조회수 1증가
		String strInc = request.getParameter("inc");
		long inc = Long.parseLong(strInc);

		MemberVO vo = (MemberVO) ExeService.execute(Beans.get(AuthorityFilter.url),
				new Long[]{no, inc});
		// 서버객체 request에 담는다.
		request.setAttribute("vo", vo);

	}
	
	// 3. 회원 등록 처리.
	private void write (HttpServletRequest request) throws Exception {

		// 1. 데이터 수집
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");

		MemberVO vo = new MemberVO();
//		vo.setTitle(title);
//		vo.setContent(content);
//		vo.setWriter(writer);

		// 2. DB 처리 - write.jsp -> service -> dao
		Integer result = (Integer) ExeService.execute(Beans.get(AuthorityFilter.url), vo);

		System.out.println("MemberController.write().result : " + result);
	}

	// 4-1. 회원 수정 폼
	private void updateForm(HttpServletRequest request) throws Exception {
		// 자바 부분입니다.
		// 1. 넘어오는 데이터 받기 - 글번호
		String strNo = request.getParameter("no");
		long no = Long.parseLong(strNo);
		// 조회수 1증가하는 부분은 inc=0으로 강제 셋팅해서 넘긴다.
		// 2. 글번호에 맞는 데이터 가져오기 -> MemberViewService => /member/view.jsp
		String url = "/member/view.do"; // 현재 URL과 다르므로 강제 셋팅했다.
		MemberVO vo = (MemberVO) ExeService.execute(Beans.get(url), new Long[]{no, 0L});

		// 3. 서버 객체에 넣기
		request.setAttribute("vo", vo);

	}

	// 4-2. 회원 수정 처리
	private Long update(HttpServletRequest request) throws Exception {

		// 1. 데이터 수집
		String strNo = request.getParameter("no");
		long no = Long.parseLong(strNo);
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");

		MemberVO vo = new MemberVO();
//		vo.setNo(no);
//		vo.setTitle(title);
//		vo.setContent(content);
//		vo.setWriter(writer);

		// 2. DB 처리 - update.jsp -> service -> dao
		String url = request.getServletPath();
		Integer result = (Integer) ExeService.execute(Beans.get(url), vo);

		if(result < 1) throw new Exception("회원 글수정 - 수정할 데이터가 존재하지 않습니다.");
		
		return no;
	}

	// 5. 회원 탈퇴 처리
	private void delete(HttpServletRequest request) throws Exception {
		// 1. 데이터 수집
		String strNo = request.getParameter("no");
		long no = Long.parseLong(strNo);

		// 2. DB 처리 - delete.jsp -> service -> dao
		String url = request.getServletPath();
		Integer result = (Integer) ExeService.execute(Beans.get(url), no);
		if(result ==0 ) throw new Exception("회원 글삭제 오류 - 존재하지 않는 글은 삭제할 수 없습니다.");
	}
	
	// 6. 로그인 처리
	private void login(HttpServletRequest request) throws Exception {
		// 로그인 정보를 가져오는데 성공하는 로그인 처리를 한다.
		// 데이터 받기
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		// 받은 데이터를 VO 객체이 저장을 한다.(한개를 넘겨야 해서)
		LoginVO vo = new LoginVO();
		vo.setId(id);
		vo.setPw(pw);

		// DB 처리 - 아이디, 이름, 등급번호, 등급이름을 가져온다.
		// jsp - service - dao
		String url = request.getServletPath();
		LoginVO loginVO = (LoginVO) ExeService.execute(Beans.get(url), vo);

		// id, pw가 틀린 경우의 처리
		if(loginVO == null) throw new Exception("로그인 정보를 확인해 주세요.");

		// 로그인 처리
		request.getSession().setAttribute("login", loginVO);
	}
	
	// 6. 로그아웃 처리
	private void logout(HttpServletRequest request) throws Exception {
		// 로그아웃 처리
		request.getSession().invalidate();
		System.out.println("로그아웃 처리가 되었습니다.");
	}
}
