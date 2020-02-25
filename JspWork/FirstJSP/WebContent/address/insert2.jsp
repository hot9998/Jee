<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function zipfinder() {
	window.open("zipCheck.jsp","","width=700 height=400");
}
function check() {	
	if(document.getElementById("name").value == ""){
		alert("이름을 입력하세요");
		return false;		
	}
	if(document.getElementById("zip").value == ""){
		alert("우편번호를 입력하세요");
		return false;
	}
	if(document.getElementById("tel").value == ""){
		alert("전화번호를 입력하세요");
		return false;
	}
	return true;
}

</script>
</head>
<body>
	<a href="list.jsp">전체보기</a>
	<form action="insertPro.jsp" method="post" name="frm" onsubmit="return check()">
	<!-- submit 타입은 폼 태그에 onsubmit에 함수를 넣어 true면 submit 실행, false면 submit 실행 안 하게끔 함 -->
	<input type="hidden" name="zipcode" id="zipcode">
	<!-- disabled를 사용했기때문에 hidden으로 zipcode 값을 전달 -->
		<table border="1" style="width: 500px">
			<tr>			
				<th colspan="2">주소록 등록하기</th>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="name" id="name"></td>
			</tr>
			<tr>
				<th>우편번호</th>
				<!-- disabled 를 사용하면 값 전달이 불가능 -->
				<td><input type="text" name="zip" id="zip" size="10" disabled="disabled"> <input
					type="button" value="검색" onclick="zipfinder()"></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input type="text" name="addr" id="addr" size="50"></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type="text" name="tel" id="tel"></td>
			<tr>
				<td colspan="2"><input type="submit" value="등록"> <input
					type="reset" value="취소"></td>
			</tr>
		</table>
	</form>
</body>
</html>