<%@page import="com.webjjang.main.controller.Beans"%>
<%@page import="com.webjjang.main.controller.ExeService"%>
<%@page import="com.webjjang.board.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글쓰기 폼</title>

	<!-- 부트스트랩 라이브러리 등록 - CDN 방식 -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

</head>
<body>
<div class="container">
<h1>게시판 글쓰기</h1>
	<form action="write.jsp" method="post">
		<div class="form-group">
			<label for="title">제목</label>
			<input name="title" class="form-control" id="title" required="required"
			 placeholder="제목 입력 - 3글자 이상 100자 이내">
		</div>
		
		<div class="form-group">
			<label for="content">내용</label>
			<textarea rows="7" name="content" class="form-control" id="content"
			 required="required"></textarea>
		</div>
		
		<div class="form-group">
			<label for="writer" >작성자</label>
			<input name="writer" class="form-control" id="writer" required="required">
		</div>
		
		<a href="write.jsp" class="btn btn-default">등록</a>
		<button input type="reset" class="btn btn-default">새로입력</button>
		<a type="button" onclick="history.back()" class="btn btn-default">취소</a>
		
	</form>
</div>
</body>
</html>