<%@page import="com.exam.DateBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
// DateBean 객체를 불러와 함수를 사용
DateBean bean = new DateBean();
String str = bean.getToday();
%>
</head>
<body>
<%=str %>


</body>
</html>