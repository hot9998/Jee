<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js">	
</script>
<script>
	function zipfinder() {
		window.open("zipCheck.jsp", "", "width=700 height=400");
	}
	//$(document).ready(fuction() 생략하고 $(function()을 사용해도 똑같음	
	$(function() {
		$("#btn").click(function() {
			if ($("#name").val() == "") {
				alert("이름을 입력하세요");
				return false;
			}
			if ($("#zip").val() == "") {
				alert("우편번호를 입력하세요");
				return false;
			}
			if ($("#tel").val() == "") {
				alert("전화번호를 입력하세요");
				return false;
			}
			frm.submit();
		});
	});
</script>
</head>
<body>
	<a href="list.jsp">전체보기</a>
	<form action="insertPro.jsp" method="post" name="frm">
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
				<td><input type="text" name="zip" id="zip" size="10"
					disabled="disabled"> <input type="button" value="검색"
					onclick="zipfinder()"></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input type="text" name="addr" id="addr" size="50"></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type="text" name="tel" id="tel"></td>
			<tr>
				<td colspan="2"><input type="button" value="등록" id="btn">
					<input type="reset" value="취소"></td>
			</tr>
		</table>
	</form>
</body>
</html>