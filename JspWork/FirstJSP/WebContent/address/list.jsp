<%@page import="com.address.Address"%>
<%@page import="com.address.AddressDAO"%>
<%@page import="java.util.ArrayList"%>
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
		$("#searchBtn").click(function() {
			if ($("#word").val() == "") {
				alert("검색어를 입력하세요");
				return false;
			}
			searchFrm.submit();
		});
	});
</script>
<%
	request.setCharacterEncoding("utf-8");
	String field = "";
	String word = "";
	if (request.getParameter("word") != null) {
		field = request.getParameter("field");
		word = request.getParameter("word");
	}
	AddressDAO dao = AddressDAO.getInstance();
	ArrayList<Address> arr = dao.addrList(field, word);
	int count = dao.getCount(field, word);
%>
</head>
<div align="right">
	주소록 갯수
	<%=count%>
	<br> <a href="insert.jsp">추가하기</a> / <a href="list.jsp">전체보기</a>
</div>
<body>
	<table border="1">
		<tr>
			<th>이름</th>
			<th>우편번호</th>
			<th>주소</th>
			<th>전화번호</th>
		</tr>
		<%
			for (int i = 0; i < arr.size(); i++) {
		%>
		<tr>
			<td><a href="detail.jsp?num=<%=arr.get(i).getNum()%>"><%=arr.get(i).getName()%></a></td>
			<td><%=arr.get(i).getZipcode()%></td>
			<td><%=arr.get(i).getAddr()%></td>
			<td><%=arr.get(i).getTel()%></td>
		</tr>
		<%
			}
		%>
	</table>
	<form action="list.jsp" name="searchFrm">
		<select name="field" id="field">
			<option value="name">이름</option>
			<option value="tel">전화번호</option>
		</select> <input type="text" name="word" id="word"> <input
			type="button" value="검색" id="searchBtn">
	</form>
</body>
</html>