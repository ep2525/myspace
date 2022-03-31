<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="style.css">
<%@ include file="../common/common.jsp" %>	
<style type="text/css">
	body{
		text-align: center;
	}
	textarea{
		width: 100%;
		resize: none;
	}
	table{
		margin:auto;
	}
</style>
<br>replyForm.jsp<br>
<b>답글쓰기</b>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/check.js"></script>

<form action="replyForm.bv" method="post">
	<input type="hidden" name="pageNumber" value="${pageNumber}">
	<input type="hidden" name="ref" value="${article.ref}">
	<input type="hidden" name="re_step" value="${article.re_step}">
	<input type="hidden" name="re_level" value="${article.re_level}">
<table border="1">
	<tr>
		<td colspan="2" align="right">
			<a href="boardList.bv?pageNumber=${pageNumber}">글목록</a>
		</td>
	</tr>
	<tr>
		<td align="center">이름</td>
		<td><input type="text" name="writer" value="${sessionScope.loginInfo.id}" readonly></td>
	</tr>
	<tr>
		<td align="center">제목</td>
		<td><input type="text" name="subject" value="[답글]"></td>
	</tr>
	<tr>
		<td align="center">Email</td>
		<td><input type="text" name="email" value="aa@xx.com"></td>
	</tr>
	<tr>
		<td align="center">내용</td>
		<td><textarea name="content" rows="15" cols="50">호호호</textarea></td>
	</tr>
	<tr>
		<td align="center">비밀번호</td>
		<td><input type="password" name="passwd" value="1234"></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="submit" name="" value="글쓰기" onclick="return check()">
			<input type="reset" name="" value="다시작성">
			<input type="button" name="" value="목록보기" onclick="location.href='boardList.bv?pageNumber=${pageNumber}'">
		</td>
	</tr>
</table>

</form>