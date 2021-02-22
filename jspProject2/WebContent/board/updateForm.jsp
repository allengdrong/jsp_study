<%@page import="com.webjjang.main.controller.Beans"%>
<%@page import="com.webjjang.main.controller.ExeService"%>
<%@page import="com.webjjang.board.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

	// 자바 부분입니다.
	// 1. 넘어오는 데이터 받기 - 글번호
	String strNo = request.getParameter("no");
	long no = Long.parseLong(strNo);
	// 조회수 1증가하는 부분은 inc=0으로 강제 셋팅해서 넘긴다.
	// 2. 글번호에 맞는 데이터 가져오기 -> BoardViewService => /board/view.jsp
	String url="/board/view.jsp"; // 현재 URL과 다르므로 강제 셋팅했다.
	BoardVO vo = (BoardVO) ExeService.execute(Beans.get(url), new Long[]{no, 0L});
	// 3. 서버 객체에 넣기
	request.setAttribute("vo", vo);

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글수정 폼</title>

	<!-- 부트스트랩 라이브러리 등록 - CDN 방식 -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	
	<!-- formUtil.js 등록 -->
<script type="text/javascript" src="../js/formUtil.js"></script>
	
<script type="text/javascript">
$(function(){
	
	// 이벤트 처리
	// 취소 버튼 - 이전 페이지(리스트)로 돌아간다.
	$("#cancelBtn").click(function(){
		// alert("취소");
		// 이전페이지로 이동
		history.back();
	});
	
	// submit() 이벤트에 데이터 검사
	$("#writeForm").submit(function(){
		// alert("데이터 전달 이벤트");
		
		// 필수 입력
		// 제목
		// alert(!require($("#title"), "제목"));
		if(!require($("#title"), "제목")) return false;
		// 내용
		if(!require($("#content"), "내용")) return false;
		// 작성자
		if(!require($("#writer"), "작성자")) return false;
		
		// 길이
		// 제목 4자 이상
		if(!checkLength($("#title"), "제목", 4)) return false;
		// 내용 4자 이상
		if(!checkLength($("#content"), "내용", 4)) return false;
		// 작성자 2자 이상
		if(!checkLength($("#writer"), "작성자", 2)) return false;
		
	});
	
});

</script>

</head>
<body>
<div class="container">
<h1>게시판 글수정</h1>
	<form action="update.jsp" id="updateForm" method="post">
		<div class="form-group">
			<label for="no">번호</label>
			<input name="no" id="no" class="form-control" readonly="readonly" value="${vo.no}"/>
		</div>
		
		<div class="form-group">
			<label for="title">제목</label>
			<input name="title" id="title" class="form-control" required="required"
			 placeholder="제목 입력 - 4자 이상 입력하셔야 합니다." value="${vo.title }">
		</div>
		
		<div class="form-group">
			<label for="content">내용</label>
			<textarea name="content" id="content" rows="5" class="form-control" required="required"
			 placeholder="내용입력 - 내용을 입력하셔야 합니다.">${vo.content }</textarea>
		</div>
		
		<div class="form-group">
			<label for="writer" >작성자</label>
			<input name="writer" id="writer" class="form-control" required="required"
			 placeholder="작성자입력 - 작성자를 입력하셔야 합니다." value="${vo.writer }">
		</div>
		<button class="btn btn-default">수정</button>
		<button type="reset" class="btn btn-default">새로입력</button>
		<button type="button" id="cancelBtn" class="btn btn-default">취소</button>
		
	</form>
	</div>
</body>
</html>