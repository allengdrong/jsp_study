<%@page import="com.webjjang.board.service.BoardWriteService"%>
<%@page import="com.webjjang.main.controller.ExeService"%>
<%@page import="com.webjjang.board.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
System.out.println("--- write.jsp : 게시판 글쓰기 처리 ----");
request.setCharacterEncoding("utf-8");

String title = request.getParameter("title");
String content = request.getParameter("content");
String writer = request.getParameter("writer");

BoardVO vo = new BoardVO();
vo.setTitle(title);
vo.setContent(content);
vo.setWriter(writer);

int writeResult = (Integer) ExeService.execute(new BoardWriteService(), vo);

response.sendRedirect("list.jsp");
%>