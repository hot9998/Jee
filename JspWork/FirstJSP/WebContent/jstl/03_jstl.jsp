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
<%
	String[] movieList={"영화1","영화2","영화3","영화4","영화5" };
   pageContext.setAttribute("movielist", movieList);
%>
 <c:forEach items="${movielist }"   var="movie" varStatus="status">
  index :  ${status.index} / count : ${status.count} /
  <c:if test="${status.index==3 }">
    <span style="color:red"> 영화 :  ${movie }</span>
  </c:if>
  <c:if test="${status.index!=3 }">
     영화 :  ${movie }
  </c:if>
  <br>
  </c:forEach>
  <hr>
  <%
   for(int i =0; i<movieList.length;i++){
	   if(i==3){
		   out.println("<span style='color:red'> 영화 : "+movieList[i]+"</span><br>");
	   }else{
		   out.println("영화 : "+movieList[i]+"<br>");
	   }
   }
    %>
</body>
</html>