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
	$("#delBtn").click(function(){
		if(confirm("정말 삭제할까요?")){
			location.href="deleteAction.ib?num=${ad.num}"			
		}
				
	})
})
</script>
<script type="text/javascript">
function zipfinder(){
	window.open("zipAction.ib","","width=700 height=400");	
}
</script>
</head>
<body>
<div align="right">
<a href="listAction.ib">목록보기(상대경로)</a> /
<a href="/Address_ibatis/address/listAction.ib">목록보기(절대경로)</a> 
</div>
	<form action="updateAction.ib" method="post">	
	<input type="hidden" name="zipcode" id="zipcode" value="${ad.zipcode }">
	<input type="hidden" name="num" id="num" value="${ad.num }">
		<table border="1" style="width: 500px">		
			<tr>
				<th colspan="2">주소록 수정하기</th>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="name" value="${ad.name }"></td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td><input type="text" name="zip" id="zip"
					value="${ad.zipcode }" size="20" disabled> <input type="button"
					value="검색" onclick="zipfinder()"></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input type="text" name="addr" id="addr" value="${ad.addr }"
					size="50"></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type="text" name="tel" id="tel" value="${ad.tel }"></td>
			<tr>
				<td colspan="2"><input type="submit" value="수정"> <input type="button"
					value="삭제" id="delBtn"></td>
			</tr>
		</table>
	</form>
</body>
</html>