package com.webjjang.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.webjjang.board.vo.BoardVO;
import com.webjjang.main.controller.Beans;
import com.webjjang.main.controller.Controller;
import com.webjjang.main.controller.ExeService;
import com.webjjang.util.PageObject;
import com.webjjang.util.filter.AuthorityFilter;

public class BoardController implements Controller {

	private final String MODULE = "board";
	private String jspInfo = null;

	@Override
	public String execute(HttpServletRequest request) throws Exception {
		System.out.println("BoardController.execute()");

		switch (AuthorityFilter.url) {
		// 1. 게시판 리스트
		case "/" + MODULE + "/list.do":
			// service - dao -- request에 저장 까지 해준다.
			list(request);

			// "board/list" 넘긴다. -> WEB-INF/views/ + board/list + .jsp를 이용해서 HTML을 만든다.
			jspInfo = MODULE + "/list";

			break;

		// 2. 게시판 글보기
		case "/" + MODULE + "/view.do":
			// service - dao -- request에 저장 까지 해준다.
			view(request);

			// "board/view" 넘긴다. -> WEB-INF/views/ + board/view + .jsp를 이용해서 HTML을 만든다.
			jspInfo = MODULE + "/view";

			break;

		// 3-1. 게시판 글쓰기 폼
		case "/" + MODULE + "/writeForm.do":
			// "board/view" 넘긴다. -> WEB-INF/views/ + board/view + .jsp를 이용해서 HTML을 만든다.
			jspInfo = MODULE + "/writeForm";
			break;

		// 3-2. 게시판 글쓰기 처리
		case "/" + MODULE + "/write.do":
			// service - dao -- request에 저장 까지 해준다.
			write(request);

			// "board/view" 넘긴다. -> WEB-INF/views/ + board/view + .jsp를 이용해서 HTML을 만든다.
			jspInfo = "redirect:list.do";
			break;

		default:
			break;
		}
		// jsp의 정보를 가지고 리턴한다.
		return jspInfo;
	}

	// 1. 게시판 리스트 처리.
	private void list(HttpServletRequest request) throws Exception {
		// 여기가 자바 코드입니다. servlet-controller(*)-Service-DAO

		// 페이지 처리를 위한 프로그램
		// 페이지 처리를 위한 객체 사용
		PageObject pageObject = new PageObject();
		// 페이지에 대한 정보를 받는다.
		// page는 jsp에서 기본객체로 사용하고 있다. -> 페이지의 정보가 담겨져 있다.
		String strCurPage = request.getParameter("page");
		// 넘어오는 페이지가 있는 경우는 넘어오는 페이지를 현재 페이지로 셋팅. 그렇지 않으면 1이 셋팅된다.
		if (strCurPage != null)
			pageObject.setPage(Integer.parseInt(strCurPage));
		// 한페이지에 표시할 데이터의 수를 받는다.
		String strPerPageNum = request.getParameter("perPageNum");
		// 한 페이지당 표시할 데이터의 수가 안넘어오면 10으로 셋팅된다. 넘어오면 넘어 오는 데이터를 사용한다.
		if (strPerPageNum != null)
			pageObject.setPerPageNum(Integer.parseInt(strPerPageNum));
		// 넘어온 데이터 확인
		System.out.println("BoardController.execute() [page = " + strCurPage + ", perPageNum = " + strPerPageNum + "]");
		// PageObject - 확인
		System.out.println("BoardController.execute() [pageObject = " + pageObject + " ]");

		String url = request.getServletPath();
		@SuppressWarnings("unchecked")
		List<BoardVO> list = (List<BoardVO>) ExeService.execute(Beans.get(url), pageObject);
		// 서버객체 request에 담는다.
		request.setAttribute("list", list);
		request.setAttribute("pageObject", pageObject); // 페이지를 보여주기 위해 서버객체에 담는다.

	}

	// 2. 게시판 글보기 처리.
	private void view(HttpServletRequest request) throws Exception {

		// 여기가 자바 코드입니다. servlet - controller - Service-DAO -> /board/view.do

		// 넘어오는 데이터 받기
		// - 글번호
		String strNo = request.getParameter("no");
		long no = Long.parseLong(strNo);
		// - 조회수 1증가
		String strInc = request.getParameter("inc");
		long inc = Long.parseLong(strInc);

		BoardVO vo = (BoardVO) ExeService.execute(Beans.get(AuthorityFilter.url), new Long[] { no, inc });
		// 서버객체 request에 담는다.
		request.setAttribute("vo", vo);
	}

	// 3. 게시판 글쓰기 처리
	private void write (HttpServletRequest request) throws Exception {
		// 1. 데이터 수집
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");

		BoardVO vo = new BoardVO();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setWriter(writer);

		// 2. DB 처리 - write.jsp -> service -> dao
		Integer result = (Integer) ExeService.execute(Beans.get(AuthorityFilter.url), vo);

		System.out.println("BoardController.write().result : " + result);
	}
}
