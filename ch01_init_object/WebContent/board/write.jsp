<%@page import="com.webjjang.main.controller.Beans"%>
<%@page import="com.webjjang.main.controller.ExeService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// ------------- 데이터 처리
// 요청한 url 가져 오기
// String url = request.getRequestURL().toString();
String url = request.getServletPath();
// 생성되어 있는 서비스를 url에 맞는 것을 찾아서 실행할 수 있도록 전달한다. - 생성하지 않는다.
int result = (int) ExeService.execute(Beans.get(url), null);

// 자동으로 리스트로 이동시킨다.
response.sendRedirect("list.jsp");
%>