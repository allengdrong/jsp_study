<%@page import="com.webjjang.util.PageObject"%>
<%@page import="com.webjjang.main.controller.Beans"%>
<%@page import="com.webjjang.main.controller.ExeService"%>
<%@page import="com.webjjang.board.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pageObject" tagdir="/WEB-INF/tags" %>
<%
// 여기가 자바 코드입니다. JSP-Service-DAO

// 페이지 처리를 위한 프로그램
// 페이지 처리를 위한 객체 사용
PageObject pageObject = new PageObject();
// 페이지에 대한 정보를 받는다.
// page는 jsp에서 기본객체로 사용하고 있다. -> 페이지의 정보가 담겨져 있다.
String strCurPage = request.getParameter("page");
// 넘어오는 페이지가 있는 경우는 넘어오는 페이지를 현재 페이지로 셋팅. 그렇지 않으면 1이 셋팅된다.
if(strCurPage != null) pageObject.setPage(Integer.parseInt(strCurPage));
// 한페이지에 표시할 데이터의 수를 받는다.
String strPerPageNum = request.getParameter("perPageNum");
// 한 페이지당 표시할 데이터의 수가 안넘어오면 10으로 셋팅된다. 넘어오면 넘어 오는 데이터를 사용한다.
if(strPerPageNum != null) pageObject.setPerPageNum(Integer.parseInt(strPerPageNum));
// 넘어온 데이터 확인
System.out.println("/board/list.jsp [page = " 
+ strCurPage + ", perPageNum = " + strPerPageNum+ "]");
// PageObject - 확인
System.out.println("/board/list.jsp [pageObject = " + pageObject + " ]");

String url = request.getServletPath();
@SuppressWarnings("unchecked")
List<BoardVO> list = (List<BoardVO>) ExeService.execute(Beans.get(url), pageObject);
// 서버객체 request에 담는다.
request.setAttribute("list", list);
request.setAttribute("pageObject", pageObject); // 페이지를 보여주기 위해 서버객체에 담는다.

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 리스트</title>
  
  <!-- 부트스트랩 라이브러리 등록 - CDN 방식 -->
<!--   <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<!--   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"> -->
<!--   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> -->
<!--   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> -->

<style type="text/css">
.dataRow:hover{
	cursor: pointer;
	background: #eee;
}
</style>

<script type="text/javascript">
// 객체 선택에 문제가 있다. 아래 Document가 다 로딩이 되면 실행되는 스크립트 작성
// jquery -> $(function(){처리문 만들기;}) = jquery(function(){처리문 만들기;})
$(function(){ // jquery에서 익명함수를 전달해서 저장해놨다가 Document가 로딩이 다되면 호출해서 처리해준다.
	// 데이터 한줄 선택하기와 이벤트처리
	$(".dataRow").click(function(){
		// alert($(this));
		// $(this) : 이벤트가 일어난(현재 처리되고 있는 객체) 자신을 의미
		// .find(selector) : .앞에 객체 안에서 선택한 것을 찾아라.
		// .text() : 객체(태그) 사이에 문자를 가져온다.
		// .text(data) : 객체(태그) 사이에 문자를 셋팅한다.
		var no = $(this).find(".no").text();
		// 조회수 1 증가를 위해 inc=1을 넘긴다.
		location = "view.jsp?no=" + no + "&inc=1";
	});
});
</script>

</head>
<body>
<div class="container">
<h1>게시판 리스트</h1>
<table class="table">
	<!-- 제목 -->
	<thead>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
	</thead>
	<tbody>
		<!-- 데이터가 있는 만큼 반복이 되어 지는 시작 부분 -->
		<c:forEach items="${list }" var="vo">
		<tr class="dataRow">
			<td class="no">${vo.no }</td>
			<td>${vo.title }</td>
			<td>${vo.writer }</td>
			<td>${vo.writeDate }</td>
			<td>${vo.hit }</td>
		</tr>
		</c:forEach>
		<!-- 데이터가 있는 만큼 반복이 되어 지는 끝 부분 -->
	</tbody>
	<tfoot>
		<tr>
			<td colspan="5">
				<pageObject:pageNav listURI="list.jsp" pageObject="${pageObject }" />
			</td>
		</tr>
		<tr>
			<td colspan="5">
				<a href="writeForm.jsp" class="btn btn-default">글쓰기</a>
			</td>
		</tr>
	</tfoot>
</table>
</div>
</body>
</html>