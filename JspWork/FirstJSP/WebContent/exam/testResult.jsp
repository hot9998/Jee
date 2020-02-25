<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 스크립트 릿 -->
<%
	// name, addr 에 해당하는 내용을 넘겨받음
	String name = request.getParameter("name");
	String addr = request.getParameter("addr");
%>
</head>
<body>
결과
<hr>
이름 : <%out.print(name);%>  <br>
주소 : <%out.print(addr);%>
<!-- out.print를 이용 -->
<hr>
이름 : <%=name %> <br>
주소 : <%=addr %>
<!-- %=(표현식)을 이용 --> 
</body>
</html>