<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
<div class="container">
<h1>회원가입</h1>
<form action="join.do" id="joinForm" method="post">
<!-- 페이지에 대한 정보 넘기기 -->
<div class="form-group">
	<label for="id">아이디</label>
	<input name="id" id="id" class="form-control" required="required"/>
</div>
<div class="form-group">
	<label for="pw">비밀번호</label>
	<input name="pw" id="pw" class="form-control" required="required"/>
</div>
<div class="form-group">
	<label for="name">이름</label>
	<input name="name" id="name" class="form-control" required="required"/>
</div>
<div class="form-group">
	<label for="gender">성별</label>
	<input name="gender" id="gender" class="form-control" required="required"/>
</div>
<div class="form-group">
	<label for="birth">생일</label>
	<input name="birth" id="birth" class="form-control" required="required"/>
</div>
<div class="form-group">
	<label for="tel">전화번호</label>
	<input name="tel" id="tel" class="form-control" required="required"/>
</div>
<div class="form-group">
	<label for="email">이메일</label>
	<input name="email" id="email" class="form-control" required="required"/>
</div>


<button>등록</button>
<button type="reset">새로입력</button>
<button type="button" id="cancelBtn">취소</button>
</form>
</div>
</body>
</html>