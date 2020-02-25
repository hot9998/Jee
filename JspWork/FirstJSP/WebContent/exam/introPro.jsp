<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
 request.setCharacterEncoding("utf-8");
 String[] play = request.getParameterValues("play");
 String strPlay ="";
 String strPgm="";
 String[] pgm = request.getParameterValues("pgm");
 for(int i=0; i<play.length;i++){
	 strPlay += play[i]+" ";
 }
 for(int i=0; i<pgm.length;i++){
	 strPgm += pgm[i]+" ";
 }
%>
</head>
<body>
이름 : <%=request.getParameter("name") %><br>
성별 : <%=request.getParameter("man") %><br>
생일 :<%=request.getParameter("year") %><br>
   -<%=request.getParameter("month") %>-<%=request.getParameter("day") %><br>
주소 : <%=request.getParameter("addr") %><br>
전화번호 : <%=request.getParameter("phone") %>
               - <%=request.getParameter("phone1") %>
               - <%=request.getParameter("phone2") %><br>
프로그램 : <%= strPgm %>
여행지 :<%= strPlay %>
메모 :<%=request.getParameter("memo") %><br>

</body>
</html>