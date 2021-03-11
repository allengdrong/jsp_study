<%@page import="com.webjjang.util.filter.AuthorityFilter"%>
<%@page import="com.webjjang.main.controller.Beans"%>
<%@page import="com.webjjang.main.controller.ExeService"%>
<%@page import="com.webjjang.image.vo.ImageVO"%>
<%@page import="com.webjjang.image.dao.ImageDAO"%>
<%@page import="java.util.List"%>
<%@page import="com.webjjang.util.PageObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
// 자바 - 게시판 리스트 동일
// 페이지 정보의 초기값 셋팅
Long curPage = 1L;
Long perPageNum = 12L;
// 넘어오는 데이터 저장하기
String strCurPage = request.getParameter("page");
String strPerPageNum = request.getParameter("perPageNum");
// PageObject 생성과 셋팅
PageObject pageObject = new PageObject();
if(strCurPage != null) curPage = Long.parseLong(strCurPage);
if(strPerPageNum != null) perPageNum = Long.parseLong(strPerPageNum);
pageObject.setPage(curPage);
pageObject.setPerPageNum(perPageNum);
// DB에서 데이터 가져오기
@SuppressWarnings("unchecked")
List<ImageVO> list = (List<ImageVO>)ExeService.execute(Beans.get(AuthorityFilter.url), pageObject);
// 서버 객체에 담기 - List와 PageObject, 프로젝트의 패스
request.setAttribute("list", list);
request.setAttribute("pageObject", pageObject);
request.setAttribute("path", request.getContextPath());

%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 리스트</title>
<style type="text/css">
	.dataRow:hover{
		background: #eee;
		cursor: pointer;
		border: 3px dotted #888;
	}
</style>
<script type="text/javascript">
$(function(){
	$(".dataRow").click(function(){
		// alert("click");
		var no = $(this).find(".no").text();
		location = "view.jsp?no=" + no;
	});
});
</script>
</head>
<body>
<div class="container">
	<h1>이미지 리스트</h1>
	<div id="image_gallery">
		
		 <div class="row">
		 	<c:forEach items="${list }" var="vo" >
		 		<!-- BootStrap Grid : 한줄은 12칸으로 나눠서 운영하는 시스템 -->
			  <div class="col-md-3 dataRow" >
			    <div class="thumbnail">
			        <img src="${path }${vo.fileName}" alt="Lights" style="width:100%">
			        <div class="caption">
			          <p>[<span class="no">${vo.no }</span>] ${vo.title }</p>
			          ${vo.writeDate } - ${vo.name }(${vo.id })
			        </div>
			    </div>
			  </div>
		  </c:forEach>
		</div>

		<c:if test="${!empty login }">
			<div>
				<a href="writeForm.jsp" class="btn btn-default">등록</a>
			</div>
		</c:if>
	</div>
</div>
</body>
</html>