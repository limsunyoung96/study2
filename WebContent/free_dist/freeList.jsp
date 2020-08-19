<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="/WEB-INF/inc/header.jsp" %>
<title>자유게시판 - 글 목록</title>
</head>
<body>
<%@ include file="/WEB-INF/inc/top.jsp"%>
<div class="container">
	<div class="page-header">
		<h3>자유게시판 - <small>글 목록</small></h3>
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
		<tr>
			<th>글번호</th>
			<th>분류</th>
			<th>제목</th>
			<th>작성자</th>
			<th>등록일</th>
			<th>조회수</th>
		</tr>
	</thead>	
	<tbody>
		<tr class="text-center">
			<td>2</td>
			<td>사는 이야기</td>
			<td class="text-left">
				<a href="freeView.jsp?boNo=2">
					JSP로 웹프로그램 만드는게 재미있어요!!
				</a>
			</td>
			<td>말자</td>
			<td>2020-05-04</td>
			<td>12</td>
		</tr>
		<tr class="text-center">
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
		</tr>
	</tbody>
	</table>
</div><!-- container --> 
</body>
</html>




