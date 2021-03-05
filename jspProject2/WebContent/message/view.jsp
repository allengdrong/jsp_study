<%@page import="com.webjjang.main.controller.Beans"%>
<%@page import="com.webjjang.util.filter.AuthorityFilter"%>
<%@page import="sun.net.www.protocol.http.AuthCache"%>
<%@page import="com.webjjang.main.controller.ExeService"%>
<%@page import="com.webjjang.message.vo.MessageVO"%>
<%@page import="com.webjjang.member.vo.LoginVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 자바 부분
// 넘어오는 데이터 받기 - 메시지 번호
String strNo = request.getParameter("no");
// Long no = Long.parseLong(strNo);

// 내 아이디 정보를 꺼내야 한다.
String id = ((LoginVO) session.getAttribute("login")).getId();

// vo객체 생성 - 데이터 셋팅
MessageVO vo = new MessageVO();
vo.setNo(Long.parseLong(strNo));
vo.setAccepter(id); // 받는 사람이 본인인 데이터를 읽기 표시 하기 위해서 accepter에 id 정보를 넣었다.

// DB처리 데이터 가져오기

// 1. 받은 사람이 로그인한 사람과 같아야 하고 번호가 같고 받은 날짜가 null인 메시지를 (읽지 않은) 
//		읽음 표시를 한다. (acceptDate를 현재 날짜로 넣어준다. - update)
// 2. 메시지 번호에 맞는 전체 메시지 정보 가져오기
MessageVO viewVO = (MessageVO) ExeService.execute(Beans.get(AuthorityFilter.url), vo);

// 서버 객체에 저장
request.setAttribute("vo", viewVO);
%>


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
				<a href="list.jsp" class="btn btn-info">메시지 리스트</a>
				<a href="delete.jsp?no=${vo.no }" class="btn btn-default" 
				 id="deleteBtn">글삭제</a>
			</td>
		</tr>
	</table>
</div>

</body>
</html>