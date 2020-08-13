<%@page import="com.study.free.vo.FreeBoardVO"%>
<%@page import="com.study.member.service.FreeBoardServiceImpl"%>
<%@page import="com.study.free.service.IFreeBoardService"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="/WEB-INF/inc/header.jsp" %>
<title>freeList.jsp</title>
</head>
<body>
<%@include file="/WEB-INF/inc/top.jsp" %>
<div class="container">
	<div class="page-header">
		<h3>자유게시판</h3>
		<%
		IFreeBoardService freeBoardService = new FreeBoardServiceImpl();
		List<FreeBoardVO> boards = freeBoardService.getBoardList();
		request.setAttribute("boards", boards); /* members란 속성에 members를 대입시키는 것*/
		%>
	</div>
<div class="row">
    <div class="col-sm-2 col-sm-offset-10 text-right" style="margin-bottom: 5px;" >
        <a href="freeForm.jsp" class="btn btn-primary btn-sm"> 
        	<span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
        	&nbsp;새글쓰기
		</a>
    </div>
</div>
	<table class="table table-striped table-bordered table-hover">
	<colgroup>
		<col width="10%" />
		<col width="15%" />
		<col />
		<col width="10%" />
		<col width="15%" />
		<col width="10%" />
	</colgroup>
	<thead>
		<tr >
			<th>글번호</th>
			<th>분류명</th>
			<th>제목</th>
			<th>작성자</th>
			<th>등록일</th>
			<th>조회수</th>
		</tr>
	</thead>	
	<tbody>
	<c:forEach var="vo" items="${boards}">
		<tr class="text-center">
			<td>${vo.boNo}</td>
			<td>${vo.boCategoryNm}</td>
			<td class="text-left">
				<a href="freeView.jsp?boNo=${vo.boNo}">
					${vo.boTitle}
				</a>
			</td>
			<td>${vo.boWriter}</td>
			<td>${vo.boRegDate}</td>
			<td>${vo.boHit}</td>
		</tr>
		<!-- <tr class="text-center">
			<td>1</td>
			<td>프로그램</td>
			<td class="text-left">
				<a href="freeView.jsp?boNo=1">
					Java에서 Map을 조회하는 것 외워야 하나요??
				</a>
			</td>
			<td>밀키스</td>
			<td>2020-05-03</td>
			<td>25</td>
		</tr> -->
		</c:forEach>
	</tbody>
	</table>
	</div>
</body>
</html>




