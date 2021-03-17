<%@page import="com.webjjang.member.vo.LoginVO"%>
<%@page import="com.webjjang.util.filter.AuthorityFilter"%>
<%@page import="com.webjjang.message.vo.MessageVO"%>
<%@page import="com.webjjang.main.controller.Beans"%>
<%@page import="com.webjjang.main.controller.ExeService"%>
<%@page import="java.util.List"%>
<%@page import="com.webjjang.util.PageObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pageObject" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메시지 리스트</title>
<style type="text/css">
tr {
	color: #777;
}
.noRead{
	color: #0459c1;
}
.dataRow:hover{
	cursor: pointer;
	background: #eee;
	
}
</style>

<script type="text/javascript">
$(function(){
	// 이벤트 처리
	// 메시지 보기로 이동
	$(".dataRow").click(function(){
		// alert("data 보기 클릭");
		// $(this) : 자기 자신(이벤트가 일어 난 곳 - 현재는 tr). 클래스가 no인 객체를 찾기. 태그 안에 있는 글자 가져오기
		var no = $(this).find(".no").text();
		location = "view.do?no=" + no;
	});
});
</script>
</head>
<body>
<div class="container">
<h1>메시지 리스트</h1>
<table class="table">
	<tr>
		<th>번호</th>
		<th>보낸사람</th>
		<th>보낸날짜</th>
		<th>받는사람</th>
		<th>받은날짜</th>
	</tr>
	<c:forEach items="${list }" var="vo">
		<tr class='dataRow ${(empty vo.acceptDate)?"noRead":""}'>
		<!-- dataRow : 데이터 한줄 -->
			<td class="no">${vo.no }</td>
			<td>${vo.sender }</td>
			<td>${vo.sendDate }</td>
			<td>${vo.accepter }</td>
			<td>${(empty vo.acceptDate)?"읽지 않음":vo.acceptDate }</td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="5">
			<pageObject:pageNav listURI="list.do" pageObject="${pageObject }" />
		</td>
	</tr>
	<tr>
		<td colspan="5">
			<a href="writeForm.do" class="btn btn-default">메시지 보내기</a>
		</td>
	</tr>
</table>
</div>
</body>
</html>