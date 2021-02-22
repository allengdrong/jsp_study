<%@page import="com.webjjang.board.vo.BoardVO"%>
<%@page import="com.webjjang.main.controller.Beans"%>
<%@page import="com.sun.rmi.rmid.ExecOptionPermission"%>
<%@page import="com.webjjang.board.service.BoardWriteService"%>
<%@page import="com.webjjang.main.controller.ExeService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
// 자바 부분 입니다.

// 한글처리
request.setCharacterEncoding("utf-8");

// 1. 데이터 수집
String strNo = request.getParameter("no");
long no = Long.parseLong(strNo);
String title = request.getParameter("title");
String content = request.getParameter("content");
String writer = request.getParameter("writer");

BoardVO vo = new BoardVO();
vo.setNo(no);
vo.setTitle(title);
vo.setContent(content);
vo.setWriter(writer);

// 2. DB 처리 - /board/update.jsp -> service -> dao
String url = request.getServletPath();
Integer result = (Integer) ExeService.execute(Beans.get(url), vo);

// 3. view로 자동 이동
response.sendRedirect("view.jsp?no=" + no + "&inc=0");
%>
