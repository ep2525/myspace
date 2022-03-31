<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>    
list.jsp<br>
<style type="text/css">
	body{
		text-align: center;
	}
	table{
		margin: auto;
	}
</style>
<center>
<h1>글목록(전체 글:${totalCount})</h1>
<form action="boardList.bv" method="get">
<p>
	<select name="whatColumn">
		<option value="all">선택
		<option value="content">내용
		<option value="subject">제목
		<option value="writer">글쓴이
	</select>
	<input type="text" name="keyword">
	<input type="submit" value="검색">
	<c:if test="${sessionScope.loginInfo != null}">&emsp;<a href="logout.jsp">로그아웃</a></c:if>
	<c:if test="${sessionScope.loginInfo == null}">&emsp;<a href="loginForm.mem">로그인</a></c:if>
	
</p>	
</form>
<table width="700" border="1">
	<tr>
		<td align="right"><a href="writeArticle.bv">글쓰기</a></td>
	</tr>
</table>

<table width="700" border="1">
	<tr>
		<td align="center">번호</td>
		<td align="center">제목</td>
		<td align="center">작성자</td>
		<td align="center">작성일</td>
		<td align="center">조회</td>
		<td align="center">IP</td>
	</tr>
		<c:forEach var="article" items="${requestScope.articleList}">
		<tr>
			<td align="center" >${requestScope.totalCount - (requestScope.pageInfo.pageNumber-1)*requestScope.pageInfo.pageSize}</td>
			<td>
				<c:if test="${article.re_level>0}">
					<c:set var="wid" value="${article.re_level*20}"/>
					<img src="<%=request.getContextPath() %>/resources/images/level.gif" width="${wid}" height="15">
					<img src="<%=request.getContextPath() %>/resources/images/re.gif">
				</c:if>

			<a href="content.bv?num=${article.num}&pageNumber=${pageInfo.pageNumber}" > ${article.subject}</a>
				<!-- 글번호 뿐만 아니라 현재페이지도 넘겨야함! -->					
			<c:if test="${article.readcount >=10 }">
				<img src='<%=request.getContextPath()%>/resources/images/hot.gif' height='15'>
			</c:if>
			</td>
			<td align="center" >${article.writer}</td>
			<td align="center" >
				<fmt:formatDate value="${article.reg_date}" pattern="yyyy-MM-dd HH:mm"/>
			</td>
			<td align="center" >${article.readcount}</td>
			<td align="center" >${article.ip}</td>
		</tr>
		</c:forEach>

</table>
<br><br>
${pageInfo.pagingHtml}
</center>

