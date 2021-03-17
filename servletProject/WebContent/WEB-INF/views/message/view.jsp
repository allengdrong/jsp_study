<%@page import="com.webjjang.main.controller.Beans"%>
<%@page import="com.webjjang.util.filter.AuthorityFilter"%>
<%@page import="sun.net.www.protocol.http.AuthCache"%>
<%@page import="com.webjjang.main.controller.ExeService"%>
<%@page import="com.webjjang.message.vo.MessageVO"%>
<%@page import="com.webjjang.member.vo.LoginVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메시지 보기</title>
<script type="text/javascript">
// 객체 선택에 문제가 있다. 아래 Document가 다 로딩이 되면 실행되는 스크립트 작성
// jquery -> $(function(){처리문 만들기;}) = jquery(function(){처리문 만들기;})
$(function(){ // jquery에서 익명함수를 전달해서 저장해놨다가 Document가 로딩이 다되면 호출해서 처리해준다.
	// 삭제 버튼을 클릭하면 실제적으로 삭제를 진행할 건지에 대한 여부를 물어본다.
	$("#deleteBtn").click(function(){
		if(!confirm("정말로 삭제하시겠습니까?")) return false; // a tag의 이동 취소
	});
	
});
</script>
</head>
<body>
<div class="container">
	<h1>메시지 보기</h1>
	<table class="table">
		<tr>
			<th>번호</th>
			<td class="no">${vo.no }</td>
		</tr>
		<tr>
			<th>내용</th>
			<td><pre style="border: none; padding: 0px;">${vo.content }</pre></td>
		</tr>
		<tr>
			<th>보낸사람</th>
			<td>${vo.sender }</td>
		</tr>
		<tr>
			<th>보낸날짜</th>
			<td>${vo.sendDate }</td>
		</tr>
		<tr>
			<th>받은사람</th>
			<td>${vo.accepter }</td>
		</tr>
		<tr>
			<th>받은날짜</th>
			<td>${vo.acceptDate }</td>
		</tr>
		<tr>
			<td colspan="5">
				<a href="list.do" class="btn btn-info">메시지 리스트</a>
				<a href="delete.do?no=${vo.no }" class="btn btn-default" 
				 id="deleteBtn">글삭제</a>
			</td>
		</tr>
	</table>
</div>

</body>
</html>