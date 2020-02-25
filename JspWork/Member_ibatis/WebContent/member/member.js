var exp=/^[0-9]{3}-[0-9]{4}-[0-9]{4}$/
//정규식 /^: 시작                $/: 종료
$(function(){
	$("#send").click(function(){
		if($("#name").val()==""){
			alert("이름을 입력하세요");
			$("#name").focus();
			return false;
		}if($("#userid").val()==""){
			alert("아이디를 입력하세요");
			$("#userid").focus();
			return false;
		}if($("#pwd").val()==""){
			alert("암호를 입력하세요");
			$("#pwd").focus();
			return false;
		}if($("#pwd").val()!=$("#pwd_check").val()){
			alert("비밀번호가 일치하지 않습니다.");
			$("#pwd_check").focus();
			return false;
		}
		if(!$("#phone").val().match(exp)){
			// match 함수 : 정규표현식과 형식을 비교하는 함수
			alert("전화번호 입력 양식이 아닙니다.")
			$("#phone").focus();
			return false;
		}
		$("#frm").submit();		
	})
	//중복체크버튼
	$("#idBtn").click(function(){
		window.open("idCheck.go","","width=800 height=500");		
	})	
})