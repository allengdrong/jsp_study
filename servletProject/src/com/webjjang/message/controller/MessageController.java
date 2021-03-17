package com.webjjang.message.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.webjjang.main.controller.Beans;
import com.webjjang.main.controller.Controller;
import com.webjjang.main.controller.ExeService;
import com.webjjang.member.vo.LoginVO;
import com.webjjang.message.vo.MessageVO;
import com.webjjang.util.PageObject;
import com.webjjang.util.filter.AuthorityFilter;

public class MessageController implements Controller {

	private final String MODULE = "message";
	private String jspInfo = null;

	@Override
	public String execute(HttpServletRequest request) throws Exception {
		System.out.println("BoardController.execute()");

		// 페이지를 위한 처리
		PageObject pageObject = PageObject.getInstance(request);
		request.setAttribute("pageObject", pageObject); // 페이지를 보여주기 위해 서버객체에 담는다.

		switch (AuthorityFilter.url) {
		// 1. 메시지 리스트
		case "/" + MODULE + "/list.do":
			list(request, pageObject);
			jspInfo = MODULE + "/list";
			break;

		// 2. 메시지 글보기
		case "/" + MODULE + "/view.do":
			// service - dao -- request에 저장 까지 해준다.
			view(request);

			// "board/view" 넘긴다. -> WEB-INF/views/ + board/view + .jsp를 이용해서 HTML을 만든다.
			jspInfo = MODULE + "/view";
			break;

		// 3-1. 메시지 글쓰기 폼
		case "/" + MODULE + "/writeForm.do":
			// "board/view" 넘긴다. -> WEB-INF/views/ + board/view + .jsp를 이용해서 HTML을 만든다.
			jspInfo = MODULE + "/writeForm";
			break;

		// 3-2. 메시지 글쓰기 처리
		case "/" + MODULE + "/write.do":
			// service - dao -- request에 저장 까지 해준다.
			write(request);

			// "board/view" 넘긴다. -> WEB-INF/views/ + board/view + .jsp를 이용해서 HTML을 만든다.
			jspInfo = "redirect:list.do?page=1&perPageNum=" + pageObject.getPerPageNum();
			break;
			
		// 4. 메시지 글삭제 처리
		case "/" + MODULE + "/delete.do":
			delete(request);
		jspInfo = "redirect:list.do?page=1&perPageNum=" + pageObject.getPerPageNum();
		break;

		default:
			throw new Exception("페이지 오류 404 - 존재하지 않는 페이지 입니다.");
		}

		return jspInfo;
	}

	// 1. 메시지 리스트 처리
	private void list(HttpServletRequest request, PageObject pageObject) throws Exception {

		// 내 아이디를 가져와서 pageObject에 저장을 해둔다.
		pageObject.setAccepter(((LoginVO) request.getSession().getAttribute("login")).getId());

		// DATA 가져오기
		@SuppressWarnings("unchecked")
		List<MessageVO> list = (List<MessageVO>) ExeService.execute(Beans.get(AuthorityFilter.url), pageObject);

		// 서버 객체에 저장
		request.setAttribute("list", list);
	}

	// 2. 메시지 보기
	private void view(HttpServletRequest request) throws Exception {
		// 넘어오는 데이터 받기 - 메시지 번호
		String strNo = request.getParameter("no");
		// Long no = Long.parseLong(strNo);

		// 내 아이디 정보를 꺼내야 한다.
		String id = ((LoginVO) request.getSession().getAttribute("login")).getId();

		// vo객체 생성 - 데이터 셋팅
		MessageVO vo = new MessageVO();
		vo.setNo(Long.parseLong(strNo));
		vo.setAccepter(id); // 받는 사람이 본인인 데이터를 읽기 표시 하기 위해서 accepter에 id 정보를 넣었다.

		// DB처리 데이터 가져오기

		// 1. 받은 사람이 로그인한 사람과 같아야 하고 번호가 같고 받은 날짜가 null인 메시지를 (읽지 않은)
		// 읽음 표시를 한다. (acceptDate를 현재 날짜로 넣어준다. - update)
		// 2. 메시지 번호에 맞는 전체 메시지 정보 가져오기
		MessageVO viewVO = (MessageVO) ExeService.execute(Beans.get(AuthorityFilter.url), vo);

		// 서버 객체에 저장
		request.setAttribute("vo", viewVO);
	}

	// 3. 메시지 쓰기
	private void write(HttpServletRequest request) throws Exception {
		// 넘어오는 데이터 수집 - 받는 사람 아이디, 내용
		String accepter = request.getParameter("accepter");
		String content = request.getParameter("content");

		// session에서 내 아이디 가져오기
		// session의 내용은 /member/login.jsp 를 확인. 이때 key = login이라는 것이 다르면 null이 나온다.
		LoginVO vo = (LoginVO) request.getSession().getAttribute("login");
		String sender = vo.getId(); // id를 가져와서 sender에 집어 넣는다.

		// vo 객체를 생성하고 데이터를 넣는다.
		MessageVO messageVO = new MessageVO();
		messageVO.setContent(content);
		messageVO.setSender(sender);
		messageVO.setAccepter(accepter);

		// db 처리 : jsp - service - dao - db
		// ExeService.execute(실행할 Service, 저Service에 전달되는 데이터)
		ExeService.execute(Beans.get(AuthorityFilter.url), messageVO);
	}
	
	// 4. 메시지 삭제
	private void delete(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("utf-8");

		// 1. 데이터 수집
		String strNo = request.getParameter("no");
		long no = Long.parseLong(strNo);

		// 2. DB 처리 - delete.jsp -> service -> dao
		String url = request.getServletPath();
		Integer result = (Integer) ExeService.execute(Beans.get(url), no);
		if(result == 0 ) throw new Exception("게시판 글삭제 오류 - 존재하지 않는 글은 삭제할수 없습니다.");

	}
}
