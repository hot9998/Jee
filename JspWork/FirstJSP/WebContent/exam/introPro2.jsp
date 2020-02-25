<%@page import="com.exam.IntroBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
request.setCharacterEncoding("utf-8");
%>
<!-- IntroBean ib = new IntroBean(); -->
<jsp:useBean id="ib" class="com.exam.IntroBean"/>
<jsp:setProperty property="*" name="ib"/>

<%
String []  pgm = ib.getPgm();
String strPgm = "";
for(int i=0;i<pgm.length;i++){
	strPgm += pgm[i] +" ";
}

String []  play = ib.getPlay();
String strPlay = "";
for(int i=0;i<play.length;i++){
	strPlay += play[i] +" ";
}
%>
</head>
<body>
이름:<%=ib.getName() %><br>
성별:<%=ib.getMan() %><br>
생일:<%=ib.getYear()%>-<%=ib.getMonth()%>
		-<%=ib.getDay() %>-<%=ib.getYy() %><br>
주소:<br>
전화번호:<%=ib.getPhone() %>-<%=ib.getPhone1() %>
-<%=ib.getPhone2() %><br>
프로그램:<%=strPgm %><br>
여행지:<%=strPlay %><br>
메모 :<%=ib.getMemo() %> <br>
<hr>
이름 : <jsp:getProperty property="name" name="ib"/>


</body>
</html>