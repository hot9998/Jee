<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>            
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
이름 : ${ps.name } <br>
아이디 : ${ps.id } <br>
패스워드 : ${ps.password } <br>
성별 : ${ps.gender } <br>
수신메일 :
<c:forEach items="${ps.notice}" var = "notice">
 ${notice }
</c:forEach>
<br>
직업 : ${ps.job }
</body>
</html>