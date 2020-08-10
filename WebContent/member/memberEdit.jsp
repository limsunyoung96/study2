<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/inc/header.jsp"%>
<title>memberEdit.jsp</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
	%>
	<%@ include file="/WEB-INF/inc/top.jsp"%>
	<div class="container">
		<h3>회원 정보 수정</h3>

		<%
			//1. 드라이버 로딩
		// Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = null;
		// Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		//2. 커넥션 구하기
		conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");

		// 4. 실행
		// String vs StringBuffer
		StringBuffer sb = new StringBuffer();

		sb.append("SELECT mem_id    , mem_pass  , mem_name       ");
		sb.append("  , TO_CHAR(mem_bir, 'YYYY-MM-DD') AS mem_bir ");
		sb.append(" 	  , mem_zip   , mem_add1       ");
		sb.append("     , mem_add2  , mem_hp    , mem_mail       ");
		sb.append("     , mem_job   , mem_like  , mem_mileage    ");
		sb.append("     , NVL(mem_delete,'N')AS mem_delete       ");
		sb.append("  FROM member                                 ");
		sb.append(" WHERE mem_id = ? ");

		// 3. 구름 객체 생성
		pstmt = conn.prepareStatement(sb.toString());

		// 구문 실행 전에 파라미터 설정
		pstmt.setString(1, request.getParameter("memId"));

		System.out.println(sb.toString());

		rs = pstmt.executeQuery();

		if (rs.next()) {
		%>
		<form action="memberModify.jsp" method="post">
			<input type="hidden" name="memId" value='<%=rs.getString("mem_id")%>'>
			
			<table class="table table-striped ">
				<tbody>
					<tr>
						<th>아이디</th>
						<td><%=rs.getString("mem_id")%></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="memPass"
							class="form-control input-sm" required="required"
							pattern="\w{4,}" title="알파벳과 숫자로 4글자 이상 입력"></td>
					</tr>
					<tr>
						<th>회원명</th>
						<td><input type="text" name="memName" value="<%=rs.getString("mem_name")%>"
							class="form-control input-sm" required="required"
							pattern="[가-힣]{2,}" title="한글로 2글자 이상 입력"></td>
					</tr>
					<tr>
						<th>우편번호</th>
						<td><input type="text" name="memZip" value="<%=rs.getString("mem_zip")%>"
							class="form-control input-sm"></td>
					</tr>
					<tr>
						<th>주소</th>
						<td><input type="text" name="memAdd1" value="<%=rs.getString("mem_add1")%>"
							class="form-control input-sm"> 
							<input type="text" name="memAdd2" value="<%=rs.getString("mem_add2")%>" class="form-control input-sm"></td>
					</tr>
					<tr>
						<th>생일</th>
						<td><input type="date" name="memBir" value="<%=rs.getString("mem_bir")%>"
							class="form-control input-sm"></td>
					</tr>
					<tr>
						<th>메일</th>
						<td><input type="email" name="memMail" value="<%=rs.getString("mem_mail")%>"
							class="form-control input-sm" required="required"></td>
					</tr>
					<tr>
						<th>핸드폰</th>
						<td><input type="tel" name="memHp" value="<%=rs.getString("mem_hp")%>"
							class="form-control input-sm"></td>
					</tr>
					<tr>
						<th>직업</th>
						<td>
						<%
							String job = rs.getString("mem_job");
						%> <select name="memJob" class="form-control input-sm">
							<option value="">-- 직업 선택 --</option>
							<option value="JB01"
								<%="JB01".equals(job) ? "selected='selected'" : ""%>>주부</option>
							<option value="JB02"
								<%="JB02".equals(job) ? "selected='selected'" : ""%>>은행원</option>
							<option value="JB03"
								<%="JB03".equals(job) ? "selected='selected'" : ""%>>공무원</option>
							<option value="JB04"
								<%="JB04".equals(job) ? "selected='selected'" : ""%>>축산업</option>
							<option value="JB05"
								<%="JB05".equals(job) ? "selected='selected'" : ""%>>회사원</option>
							<option value="JB06"
								<%="JB06".equals(job) ? "selected='selected'" : ""%>>농업</option>
							<option value="JB07"
								<%="JB07".equals(job) ? "selected='selected'" : ""%>>자영업</option>
							<option value="JB08"
								<%="JB08".equals(job) ? "selected='selected'" : ""%>>학생</option>
							<option value="JB09"
								<%="JB09".equals(job) ? "selected='selected'" : ""%>>교사</option>
					</select>
						</td>
					</tr>
					<tr>
						<th>취미</th>
						<td>
						<%
							String hobby = rs.getString("mem_like");
						%> <select name="memLike" class="form-control input-sm">
							<option value="">-- 취미 선택 --</option>
							<option value="HB01"
								<%="HB01".equals(hobby) ? "selected='selected'" : ""%>>서예</option>
							<option value="HB02"
								<%="HB02".equals(hobby) ? "selected='selected'" : ""%>>장기</option>
							<option value="HB03"
								<%="HB03".equals(hobby) ? "selected='selected'" : ""%>>수영</option>
							<option value="HB04"
								<%="HB04".equals(hobby) ? "selected='selected'" : ""%>>독서</option>
							<option value="HB05"
								<%="HB05".equals(hobby) ? "selected='selected'" : ""%>>당구</option>
							<option value="HB06"
								<%="HB06".equals(hobby) ? "selected='selected'" : ""%>>바둑</option>
							<option value="HB07"
								<%="HB07".equals(hobby) ? "selected='selected'" : ""%>>볼링</option>
							<option value="HB08"
								<%="HB08".equals(hobby) ? "selected='selected'" : ""%>>스키</option>
							<option value="HB09"
								<%="HB09".equals(hobby) ? "selected='selected'" : ""%>>만화</option>
							<option value="HB10"
								<%="HB10".equals(hobby) ? "selected='selected'" : ""%>>낚시</option>
							<option value="HB11"
								<%="HB11".equals(hobby) ? "selected='selected'" : ""%>>영화감상</option>
							<option value="HB12"
								<%="HB12".equals(hobby) ? "selected='selected'" : ""%>>등산</option>
							<option value="HB13"
								<%="HB13".equals(hobby) ? "selected='selected'" : ""%>>개그</option>
							<option value="HB14"
								<%="HB14".equals(hobby) ? "selected='selected'" : ""%>>카레이싱</option>
					</select>
					</td>
					</tr>
					<tr>
						<th>마일리지</th>
						<td><%=rs.getString("mem_mileage")%></td>
					</tr>
					<tr>
					<th>탈퇴여부</th>
					<td><%=rs.getString("mem_delete")%></td>
				</tr>
					
					<tr>
						<td colspan="2">
							<a href="memberList.jsp" class="btn btn-info"> <span
								class="glyphicon glyphicon-apple" aria-hidden="true"></span>
								&nbsp; 목록
							</a>
							
							<button type="submit" class="btn btn-default">
								<span class="glyphicon glyphicon-circle-arrow-right"
									aria-hidden="true"></span> 
								&nbsp; 저장
							</button> 
							
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		<%
			} // if
		//자원 종료
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
			}
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException e) {
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
			}
		%>
	</div>

</body>
</html>