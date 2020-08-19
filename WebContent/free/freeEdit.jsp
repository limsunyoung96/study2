<%@page import="com.study.code.vo.CodeVO"%>
<%@page import="com.study.code.service.CommonCodeServiceImpl"%>
<%@page import="com.study.code.service.ICommonCodeService"%>
<%@page import="com.study.exception.BizNotFoundException"%>
<%@page import="com.study.free.vo.FreeBoardVO"%>
<%@page import="com.study.free.service.FreeBoardServiceImpl"%>
<%@page import="com.study.free.service.IFreeBoardService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="/WEB-INF/inc/header.jsp"%>
<title>자유게시판 - 글 수정</title>
</head>
<body>
<%@ include file="/WEB-INF/inc/top.jsp"%>
<div class="container">
  <div class="page-header">
    <h3>자유게시판 - <small>글 수정</small></h3>
  </div>
  <% 
		IFreeBoardService freeBoardService = new FreeBoardServiceImpl();
 		ICommonCodeService codeService = new CommonCodeServiceImpl();
		try{
			String boNo = request.getParameter("boNo");
			FreeBoardVO boardVO = freeBoardService.getBoard(Integer.parseInt(boNo));
			request.setAttribute("boardVO", boardVO);
			
			List<CodeVO> categorylist = codeService.getCodeListByParent("BC00");
			request.setAttribute("categorylist", categorylist);
		}catch(BizNotFoundException ex){
			request.setAttribute("ex", ex);
		}
%>
	<c:if test="${not empty ex}">
			<div class="alert alert-warning">
				<p> 해당 글이 존재하지 않습니다. </p> 
				<a href="freeList.jsp" class="btn btn-default btn-sm"> 
					<span class="glyphicon glyphicon-list" aria-hidden="true"></span>
					&nbsp;목록  
				</a>
			</div>		
		</c:if>		
  
  <c:if test="${empty ex}">	
	<form action="freeModify.jsp" method="post">
	<input type="hidden" name="boNo" value="${boardVO.boNo}">	
	<table class="table table-striped table-bordered">
		<colgroup>
			<col width="20%" />
			<col/>
		</colgroup>
		<tr>
      <th>글번호</th>
      <td>${boardVO.boNo}</td>
    </tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="boTitle" value="${boardVO.boTitle}" class="form-control input-sm" required="required" ></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><input type="text" name="boWriter" value="${boardVO.boWriter}"  class="form-control input-sm" required="required" ></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="password" name="boPass" value="" class="form-control input-sm" 
			           required="required" pattern="\w{4,}" title="알파벳과 숫자로 4글자 이상 입력" >
				   <span class="text-danger">
				    <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span> 
				    글 등록시에 입력한 비밀번호를 입력하세요.
				  </span> 
			</td>
		</tr>
		<tr>
			<th>분류</th>
			<td>
				<select name="boCategory" class="form-control input-sm" required="required" >
					<option value="">-- 선택하세요--</option>		
						<c:forEach items="${categorylist}" var="code">
							<option value="${code.commCd}" ${code.commCd eq boardVO.boCategory ? "selected='selected'": "" }>${code.commNm}</option>
						</c:forEach>			
					<!-- <option value="BC01">프로그램</option>
					<option value="BC02">웹</option>
					<option value="BC03" selected="selected" >사는 이야기</option>
					<option value="BC04">취업</option> -->
				</select>
			</td>
		</tr>					
		<tr>
			<th>내용</th>
			<td><textarea rows="10" name="boContent" class="form-control input-sm">${boardVO.boContent}</textarea>
			</td>
		</tr>
    <tr>
      <th>IP</th>
      <td>${boardVO.boIp}</td>
    </tr>
    <tr>
      <th>조회수</th>
      <td>${boardVO.boHit}</td>
    </tr>
    <tr>
      <th>등록일자</th>
      <td>${boardVO.boRegDate}</td>
    </tr>   		
		<tr>
			<td colspan="2">
          <div class="pull-left">
              <a href="freeList.jsp" class="btn btn-default btn-sm"> 
                <span class="glyphicon glyphicon-list" aria-hidden="true"></span>
                &nbsp;&nbsp;목록
              </a>
            </div>
            
            <div class="pull-right">
              <!-- 문제점 : 사용자가 입력박스에서 엔터를 치면 첫번째 submit의 formaction 방향으로 이동한다.  -->
              <button type="submit"  formaction="freeDelete.jsp" class="btn btn-sm btn-danger"> 
                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                &nbsp;&nbsp;삭제
              </button>
              
              <button type="submit" class="btn btn-sm btn-primary" > 
                <span class="glyphicon glyphicon-save" aria-hidden="true"></span>
                &nbsp;&nbsp;저장
              </button>
            </div>					
			</td>
		</tr>
	</table>
	</form>
	</c:if>	
</div><!-- container -->
</body>
</html>


