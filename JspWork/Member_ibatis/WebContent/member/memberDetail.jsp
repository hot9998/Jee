<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
check=function(){
		if($("#pwd").val()==""){
			alert("패스워드를 입력하세요");
			$("#pwd").focus();			
			return false;
		}		
		if($("#pwd").val()!=$("#pwd_check").val()){
			alert("패스워드가 일치하지 않습니다.")
			$("#pwd_check").focus();
			return false;
		}
		$("#frm").submit();
	}
</script>
</head>
<body>
	<div align="right">
		${sessDto.name }님 반갑습니다.
		<a href="logout.go">로그아웃</a> /
			<a href="memDelete.go">회원탈퇴</a>
	</div>
	<h2>회원정보변경</h2>
	<form action="memUpdate.go" id="frm" method="post">
		<input type="hidden" name="userid" id="userid" value="${mdto.userid }">
		<table>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" id="name"
					value="${mdto.name }"></td>
			</tr>
			<tr>
				<td>암 호</td>
				<td><input type="password" name="pwd" id="pwd" size="20"></td>
			</tr>
			<tr height="30">
				<td width="80">암호 확인</td>
				<td><input type="password" name="pwd_check" id="pwd_check"
					size="20"></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="email" id="email"
					value="${mdto.email }"></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type="text" name="phone" id="phone"
					value="${mdto.phone }"></td>
			</tr>
			<tr>
				<td>등급</td>
				<td><input type="radio" name="admin" value="0"> 일반회원 <input
					type="radio" name="admin" value="1"> 관리자</td>
				<script>
				if(${mdto.admin}==0){
					$("input:radio[value='0']").prop("checked",true);
				}else{
					$("input:radio[value='1']").prop("checked",true);
				}
				</script>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="button" value="수정"
					onclick="check()"> <input type="reset" value="취소">
				</td>
			</tr>
		</table>
	</form>

</body>
</html>