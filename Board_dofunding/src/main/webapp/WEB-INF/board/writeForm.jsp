<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>	
<%@ include file="color.jsp" %>	
writeForm.jsp<br>
<style type="text/css">
	body{
		text-align: center;
	}
	table{
		margin: auto;
	}
</style>
<script type="text/javascript">
	function boardList(){
		location.href="boardList.bv"; // MemberListController
	}
</script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/style.css">

<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/check.js"></script>
<body bgcolor="<%=bodyback_c%>">
<b>글쓰기</b>
<form action="writeArticle.bv" method="post">
<table border="1">
	<tr>
		<td colspan="2" align="right">
			<a href="boardList.bv">글목록</a>
		</td>
	</tr>
	<tr>
		<td align="center" bgcolor="<%=value_c%>">이름</td>
		<td><input type="text" name="writer" value="${sessionScope.loginInfo.id}" readonly></td>
	</tr>
	<tr>
		<td align="center" bgcolor="<%=value_c%>">제목</td>
		<td><input type="text" name="subject" value="제목"></td>
	</tr>
	<tr>
		<td align="center" bgcolor="<%=value_c%>">Email</td>
		<td><input type="text" name="email" value="aa@xx.com"></td>
	</tr>
	<tr>
		<td align="center" bgcolor="<%=value_c%>">내용</td>
		<td><textarea name="content" rows="15" cols="50">호호호</textarea></td>
	</tr>
	<tr>
		<td align="center" bgcolor="<%=value_c%>">비밀번호</td>
		<td><input type="password" name="passwd" value="1234" ></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="submit" name="" value="글쓰기" onclick="return check()">
			<input type="reset" name="" value="다시작성">
			<input type="button" name="" value="목록보기" onclick="boardList()">
		</td>
	</tr>
</table>
</form>
</body>