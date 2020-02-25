<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
$(function(){
	$("#loginBtn").click(function(){
		if($("#userid").val()==""){
			alert("아이디를 입력하세요");
			$("#userid").focus();
			return false;
		}
		if($("#pwd").val()==""){
			alert("비밀번호를  입력하세요");
			$("#pwd").focus();
			return false;
		}
		$.ajax({
			type:"post",
			url :"loginPro.jsp",
			data:{"userid" :$("#userid").val(), "pwd" :$("#pwd").val()},
			success : function(data){
				 if(data.trim()==-1){
					 alert("회원이 아닙니다. 회원가입 하세요")
				 }else if(data.trim()==2){
					 alert("비밀번호를 확인하세요")
				 }else if(data.trim()==0){
					 alert("일반회원 로그인");
					 location.href="memberView.jsp";
				 }else if(data.trim()==1){
					 alert("관리자 로그인");
					 location.href="memberList.jsp";
				 }
			},
			error : function(e){
				alert("error : " + e);
			}
		})
	})
})


</script>
</head>
<body>
<h2>로그인</h2>
	<form method="post" id="frm" action="loginPro.jsp">
		<table>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="userid"  id="userid"></td>
			</tr>
			<tr>
				<td>암 호</td>
				<td><input type="password" name="pwd" id="pwd"></td>
			</tr>
			<tr>
			<td colspan="2" align="center">
				<input type="button" value="로그인"	id="loginBtn"> 
				<input	type="reset" value="취소"> 
				<input type="button" value="회원가입" 
				onclick="location.href='memberForm.jsp'">
			</tr>
		</table>
	</form>
</body>
</html>