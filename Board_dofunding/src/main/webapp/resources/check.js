/**
 * 
 */

function check(){
		//alert(1);
		if($('input[name="writer"]').val()==""){
			alert("이름이 누락되었습니다.");
			$('input[name="writer"]').focus();
			return false;
		}
		if($('input[name="subject"]').val()==""){
			alert("제목이 누락되었습니다.");
			$('input[name="subject"]').focus();
			return false;
		}
		if($('input[name="email"]').val()==""){
			alert("이메일이 누락되었습니다.");
			$('input[name="email"]').focus();
			return false;
		}
		//var con=document.getElementsByName("content");
		//alert(con);
		//$('textarea[name="context"]')
		if($('textarea[name="content"]').val()==""){
			alert("내용을 입력하세요.");
			$('textarea[name="content"]').focus();
			return false;
		}
		if($('input[name="passwd"]').val()==""){
			alert("비밀번호를 입력하세요.");
			$('input[name="passwd"]').focus();
			return false;
		}
		
	}//check
	