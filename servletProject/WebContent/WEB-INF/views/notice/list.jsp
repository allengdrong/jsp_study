<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pageObject" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 리스트</title>

  
  <!-- 부트스트랩 라이브러리 등록 - CDN 방식 -->
<!--   <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<!--   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"> -->
<!--   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> -->
<!--   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> -->


</head>
<body>
<div class="container">
<h1>공지사항 리스트</h1>
<c:if test="${login.gradeNo >= 9 }">
<div class="pull-right" style="padding: 5px">
	<a href="list.do?page=${pageObject.page }&perPageNum=${pageObject.perPageNum}&period=all"
	 ${pageObject.period == "all"?"Active":""} class="btn btn-default" >ALL</a>
	<a href="list.do?page=${pageObject.page }&perPageNum=${pageObject.perPageNum}&period=pre"
	 ${pageObject.period == "pre"?"Active":""} class="btn btn-default" >현재</a>
	<a href="list.do?page=${pageObject.page }&perPageNum=${pageObject.perPageNum}&period=res"
	 ${pageObject.period == "res"?"Active":""} class="btn btn-default" >예약</a>
	<a href="list.do?page=${pageObject.page }&perPageNum=${pageObject.perPageNum}&period=old"
	 ${pageObject.period == "old"?"Active":""} class="btn btn-default" >OLD</a>
</div>
</c:if>
<table class="table">
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>공지시작일</th>
		<th>공지종료일</th>
		<th>최근수정일</th>
	</tr>
	<c:forEach items="${list }" var="vo">
		<tr class="dataRow">
			<td>${vo.no }</td>
			<td>${vo.title }</td>
			<td>${vo.startDate }</td>
			<td>${vo.endDate }</td>
			<td>${vo.updateDate }</td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="5">
			<pageObject:pageNav listURI="list.do" pageObject="${pageObject }" /> 
		</td>
	</tr>
	<tr>
		<td colspan="5">
			<a href="writeForm.do?perPageNum=${pageObject.perPageNum }" class="btn btn-default">공지등록</a>
		</td>
	</tr>
</table>
</div>
</body>
</html>