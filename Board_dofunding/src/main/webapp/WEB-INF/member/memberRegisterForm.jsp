<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="./../common/common.jsp" %>      
<style type="text/css">
	.err{
	font-size: 9pt;
	color: red;
	font-weight: bold;
	}
</style> 
MemberRegisterController에서 member\memberRegisterForm.jsp<br>
<h2>회원 가입 화면</h2>
<form:form commandName="member" method="post" action="register.mem"> 
<!-- register.mem => MemberRegisterController -->
	
		<p>
			<label for="id">아이디</label>
			<input type="text" name="id" id="id" value="lee">
			<form:errors cssClass="err" path="id" />
		</p>
		<p>
			<label for="name">이름</label>
			<input type="text" name="name" id="name" value="김수현">
			<form:errors cssClass="err" path="name" />
		</p>
		<p>
			<label for="password">비번</label>
			<input type="text" name="password" id="password" value="1234">
			<form:errors cssClass="err" path="password" />
		</p>	
		<p>
			<label for="gender">성별</label>
			<input type="radio" name="gender" id="gender" value="남자">남자
			<input type="radio" name="gender" id="gender" value="여자">여자
			<form:errors cssClass="err" path="gender" />
		</p>	
		<p>
			<label for="hobby">취미</label>
			<input type="checkbox" name="hobby" id="hobby" value="마라톤">마라톤
			<input type="checkbox" name="hobby" id="hobby" value="영화감상">영화감상
			<input type="checkbox" name="hobby" id="hobby" value="게임">게임
			<input type="checkbox" name="hobby" id="hobby" value="공부">공부
			<form:errors cssClass="err" path="hobby" />
		</p>	
		<p>
			<label for="address1">주소1</label>
			<input type="text" name="address1" id="address1" value="서울시">
		</p>	
		<p>
			<label for="address2">주소2</label>
			<input type="text" name="address2" id="address2" value="강남구">
		</p>	
		
		<p>
			<label for="price">적립포인트</label>
			<input type="text" name="mpoint" id="mpoint" value="200">
		</p>	
		<p>
			<input type="submit" value="추가하기" >		
		</p>
	</form:form>