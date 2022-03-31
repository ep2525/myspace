<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>     
updateForm.jsp<br>
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
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/check.js"></script>

<form action="updateForm.bv?pageNumber=${pageNumber }" method="post" onsubmit="return check()">
<input type="hidden" name="num" value="${article.getNum()}">

	<table border="1">
		<tr>
			<td colspan="2" align="right">
				<a href="boardList.bv">글목록</a>
			</td>
		</tr>
		<tr>
			<td align="center">이름</td>
			<td><input type="text" name="writer" value="${article.getWriter() }" readonly></td>
		</tr>
		<tr>
			<td align="center">제목</td>
			<td><input type="text" name="subject" value="${article.getSubject()}"></td>
		</tr>
		<tr>
			<td align="center">Email</td>
			<td><input type="text" name="email" value="${article.getEmail()}"></td>
		</tr>
		<tr>
			<td align="center">내용</td>
			<td>
				<textarea name="content" rows="15" cols="50">${article.getContent()}</textarea>
			</td>
		</tr>
		<tr>
			<td align="center">비밀번호</td>
			<td><input type="password" name="passwd"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" name="" value="수정하기" >
				<input type="reset" name="" value="다시작성">
				<input type="button" name="" value="목록보기" onclick="location.href='boardList.bv?pageNumber=${pageNumber}'">
			</td>
		</tr>
	</table>

</form>