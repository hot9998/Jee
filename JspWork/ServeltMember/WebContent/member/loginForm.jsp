<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
	$(function() {
		$("#loginBtn").click(function() {
			if ($("#userid").val() == "") {
				alert("아이디를 입력하세요");
				$("#userid").focus();
				return false;
			}
			if ($("#pwd").val() == "") {
				alert("패스워드를 입력하세요");
				$("#pwd").focus();
				return false;
			}
			$.ajax({
				type:"post",
				url:"login.go",
				data:{"userid":$("#userid").val(),"pwd":$("#pwd").val()},
				success:function(data){
					if(data.trim()==-1){
						alert("회원이 아닙니다. 회원가입을 하세요");
						location.href="memInsert.go";
					}else if(data.trim()==0){
						alert("일반회원 로그인");
						location.href="memView.go";						
					}else if(data.trim()==1){
						alert("관리자 로그인");
						location.href="memList.go";						
					}else if(data.trim()==2){
						alert("패스워드 오류");												
					}
				},				
				error:function(e){
					alert("error:"+e);
				}				
			})

		})
	})
</script>
</head>
<body>
	<h2>로그인</h2>
	<form action="memInsert.go" method="post" id="frm">
		<table>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="userid" id="userid"></td>
			</tr>
			<tr>
				<td>암 호</td>
				<td><input type="password" name="pwd" id="pwd"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="button" value="로그인"
					id="loginBtn"> <input type="reset" value="취소"> <input
					type="button" value="회원가입" onclick="location.href='memInsert.go'">
			</tr>
		</table>
	</form>
</body>
</html>