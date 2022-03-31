<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script type="text/javascript">
	function register(){
		location.href="register.mem"; // MemberRegisterController 로 이동
	}

	function memberList(){
		location.href="memberList.mem"; // MemberListController
	}
	function boardList(){
		location.href="boardList.bv"; // MemberListController
	}
</script>
<!-- 상품 추가하기 버튼 클릭 => member\memberLoginForm.jsp -->

<br>
memberLoginForm.jsp <br>
<!-- loginForm.mem => MemberLoginController로 이동 -->
<form method="post" action="loginForm.mem"> 
	<table border="1" width="300" height="150px">
		<tr>
			<td>아이디</td>
			<td><input type="text" name="id" value="kim"></td>
		</tr>

		<tr>
			<td>비번</td>
			<td><input type="password" name="password" value=""></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="로그인">
				<input type="reset" value="취소">
				<input type="button" value="회원가입" onClick="register()">
				<input type="button" value="회원목록보기"  onClick="memberList()">
				<input type="button" value="게시판"  onClick="boardList()">
			</td>
		</tr>
	</table>

</form>