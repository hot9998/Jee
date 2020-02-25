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
request.setCharacterEncoding("utf-8");
String[] str = request.getParameterValues("item");
for(String s:str){
%>
악세사리 : <%=s %><br>
<%
}
%>
<hr>
<!-- jstl 사용 -->
<c:forEach items="${paramValues.item}" var = "item" varStatus="st">
${item}
<c:if test="${not st.last}">,</c:if>
</c:forEach>
</body>
</html>