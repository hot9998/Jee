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
	$("#sendBtn").click(function() {
		$("#frm").submit();
	})
	$("#zipBtn").click(function() {
		window.open("zipCheck.jsp","","width=800 height=500");		
	})
})
</script>
</head>
<body>
	<a href="list.jsp">전체보기</a>
	<form action="insertPro.jsp" method="post" id="frm" name="frm">
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
				<td><input type="text" name="zipcode" id="zipcode" size="d10"> <input
					type="button" value="검색" id="zipBtn"></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input type="text" name="addr" id="addr" size="50"></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type="text" name="tel" id="tel"></td>
			<tr>
				<td colspan="2"><input type="button" value="등록" id="sendBtn"> <input
					type="reset" value="취소"></td>
			</tr>
		</table>
	</form>
</body>
</html>