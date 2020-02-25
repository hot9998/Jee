
<%@page import="com.address.Address"%>
<%@page import="com.address.AddressDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	AddressDAO dao = AddressDAO.getInstance();
	Address a = dao.addrDetail(num); // num에 해당하는 Address객체 정보
%>
<script>
	function del() {
		if (confirm("정말 삭제할까요?")) {
			location.href = "deletePro.jsp?num=" + <%=num%>;
		}
	}
	function zipfinder() {
		window.open("zipCheck.jsp","","width=700 height=400");
	}
</script>
</head>
<body>
	<form action="updatePro.jsp" method="post">
		<input type="hidden" name="num" value="<%=a.getNum()%>">
		<input type="hidden" name="zipcode" id="zipcode" value="<%=a.getZipcode()%>">		
		<!-- hidden : 화면에 보이진 않지만 서버로 전달하기 위해 만들어 줌-->
		<table border="1" style="width: 500px">
			<tr>
				<th colspan="2">주소록 등록하기</th>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="name" id="" value="<%=a.getName()%>"></td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td><input type="text" name="zip" id="zip"
					value="<%=a.getZipcode()%>" size="20" disabled="disabled"> <input type="button"
					value="검색" onclick="zipfinder()"></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input type="text" name="addr" id="addr" value="<%=a.getAddr()%>"
					size="50"></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type="text" name="tel" id="tel" value="<%=a.getTel()%>"></td>
			<tr>
				<td colspan="2"><input type="submit" value="수정"> <input
					type="button" value="삭제" onclick="del()"></td>
			</tr>
		</table>
	</form>
</body>
</html>