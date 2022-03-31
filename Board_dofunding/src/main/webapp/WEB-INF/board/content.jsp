<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>  
<%@ include file="color.jsp" %>	 
content.jsp<br>
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
<body bgcolor="<%=bodyback_c%>">
<h1>글내용 보기</h1>
<table border="1" width="500">
	<tr align="center" >
		<td >글번호</td>
		<td>${article.num}</td>
		<td >조회수</td>
		<td>${article.readcount}</td>
	</tr>
	<tr align="center">
		<td >작성자</td>
		<td>${article.writer}</td>
		<td >작성일</td>
		<td>
			<fmt:formatDate value="${article.reg_date}" pattern="yyyy-MM-dd HH:mm"/>
		</td>
	</tr>
	<tr align="center">
		<td >글제목</td>
		<td colspan="3">${article.subject}</td>
	</tr>
	<tr height="50">
		<td align="center" >글내용</td>
<%-- 		
		<td colspan="3" >
			<textarea name=content rows="2"  readonly="readonly" >${article.getContent() }</textarea>
		</td> 
--%>
 		<td colspan="3" >
			${article.getContent() }
		</td>
	</tr>

	<tr height="30">
		<td align="center" colspan="4">
			<input type="button" name="update_btn" value="글수정" <c:if test="${sessionScope.loginInfo.id != article.writer}"> disabled </c:if>  onclick="location.href='updateForm.bv?num=${article.getNum()}&pageNumber=${pageNumber}'" >
			<input type="button" name="delete_btn" value="글삭제" <c:if test="${sessionScope.loginInfo.id != article.writer}"> disabled </c:if> onclick="location.href='deleteForm.bv?num=${article.getNum()}&pageNumber=${pageNumber }'" >
			<input type="button" name="reple_btn" value="답글쓰기" <c:if test="${sessionScope.loginInfo == null}"> disabled </c:if> onclick="location.href='replyForm.bv?ref=${article.getRef()}&re_step=${article.getRe_step()}&re_level=${article.getRe_level()}&pageNumber=${pageNumber}'" >
			<input type="button" name="list_btn" value="글목록" onclick="location.href='boardList.bv?pageNumber=${pageNumber}'">
		</td>
	</tr>		
</table>
</body> 