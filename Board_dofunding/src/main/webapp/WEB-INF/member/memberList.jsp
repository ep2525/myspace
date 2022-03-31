<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./../common/common.jsp"%>
<br>

<a href="start.jsp">시작 페이지</a>
<br>
<br>
	<c:if test="${sessionScope.loginInfo != null}">&emsp;<a href="logout.jsp">로그아웃</a></c:if>
	<c:if test="${sessionScope.loginInfo == null}">&emsp;<a href="loginForm.mem">로그인</a></c:if>
<br>
<br>
<center>
	<h1>회원 리스트 화면</h1>
	<form action="memberList.mem" method="get">
		<select name="whatColumn">
			<option value="all">전체 검색</option>
			<option value="name">이름</option>
			<option value="gender">성별</option>
		</select> <input type="text" name="keyword"> 
		<input type="submit" value="검색">
	</form>
	<table border="1">
		<tr>
			<td colspan="9" align="right">
				<input type="button" value="추가하기"	onclick="register()">
			</td>
		</tr>
		<tr>
			<th>id</th>
			<th>이름</th>
			<th>비번</th>
			<th>성별</th>
			<th>취미</th>
			<th>주소</th>
			<th>포인트</th>
			<th>삭제</th>
			<th>수정</th>
		</tr>
		<c:forEach var="mb" items="${list }">
			<tr>
				<td>${mb.id}</td>
				<td>${mb.name}</td>
				<td>${mb.password}</td>
				<td>${mb.gender}</td>
				<td>${mb.hobby}</td>
				<td>${mb.address1} ${mb.address2}</td>
				<td>${mb.mpoint}</td>
				<td>삭제</td>
				<td>수정</td>
			</tr>
		</c:forEach>
	</table>
	<br> ${pageInfo.pagingHtml}
</center>
