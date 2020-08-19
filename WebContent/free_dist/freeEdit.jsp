<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
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
	<form action="freeModify.jsp" method="post">
	<input type="hidden" name="boNo" value="17">	
	<table class="table table-striped table-bordered">
		<colgroup>
			<col width="20%" />
			<col/>
		</colgroup>
		<tr>
      <th>글번호</th>
      <td>17</td>
    </tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="boTitle" value="김광석의 일어나 가사입니다." class="form-control input-sm" required="required" ></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><input type="text" name="boWriter" value="말자엄마"  class="form-control input-sm" required="required" ></td>
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
					<option value="BC01">프로그램</option>
					<option value="BC02">웹</option>
					<option value="BC03" selected="selected" >사는 이야기</option>
					<option value="BC04">취업</option>
				</select>
			</td>
		</tr>					
		<tr>
			<th>내용</th>
			<td><textarea rows="10" name="boContent" class="form-control input-sm">일어나 - 김광석 
검은 밤의 가운데 서 있어
한 치 앞도 보이질 않아
어디로 가야 하나 어디에 있을까
둘러 봐도 소용없었지

인생이란 강물 위를 끝없이
부초처럼 떠다니다가
어느 고요한 호숫가에 닿으면 물과 함께 썩어가겠지

일어나 일어나 다시 한번 해보는 거야
일어나 일어나 봄의 새싹들처럼</textarea>
			</td>
		</tr>
    <tr>
      <th>IP</th>
      <td>192.168.20.45</td>
    </tr>
    <tr>
      <th>조회수</th>
      <td>25</td>
    </tr>
    <tr>
      <th>등록일자</th>
      <td>2020.08.12 15:25</td>
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

</div><!-- container -->
</body>
</html>


