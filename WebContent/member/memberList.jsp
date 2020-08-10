<%@page import="com.study.member.vo.MemberVO"%>
<%@page import="com.study.member.service.MemberServiceImpl"%>
<%@page import="com.study.member.service.IMemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/inc/header.jsp"%>
<title>memberList.jsp</title>
</head>
<body>
	<%@ include file="/WEB-INF/inc/top.jsp"%>
	<div class="container">
		<h3>회원목록</h3>
		<%
		IMemberService memberService = new MemberServiceImpl();
		List<MemberVO> members = memberService.getMemberList();
		request.setAttribute("members", members); /* members란 속성에 members를 대입시키는 것*/
		%>
		<%-- <c:set var="members" value = "<%=members %>" scope = "request"> --%>
		<div>
			<a href="memberForm.jsp" class="btn btn-primary btn-sm pull-right">회원등록</a>
		</div>
		<table class="table table-striped table-bordered">
			<caption class="hidden">회원목록 조회</caption>
			<colgroup>
				<col style="width: 15%;" />
				<col />
				<col style="width: 15%;" />
				<col style="width: 15%;" />
				<col style="width: 15%;" />
				<col style="width: 15%;" />
			</colgroup>
			<thead>
				<tr>
					<th>ID</th>
					<th>회원명</th>
					<th>HP</th>
					<th>생일</th>
					<th>직업</th>
					<th>마일리지</th>
				</tr>
			</thead>
			<tbody>
			<!-- 아래의 스크립트 코드를 EL/JSTL 사용해서 변경 -->
			<c:forEach var="members" items="${members}">
				<tr>
					<td>${members.memId }</td>
					<td><a href="memberView.jsp?memId=${members.memId}">${members.memName}</a></td>
					<td>${members.memHp}</td>
					<td>${members.memBir}</td>
					<td>${members.memJob}</td>
					<td>${members.memMileage}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	
</body>
</html>