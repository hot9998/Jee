<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	String name = request.getParameter("name");
	String age = request.getParameter("age");
	String rd1 = request.getParameter("gender");
	String[] hobby = request.getParameterValues("hobby");
	String job = request.getParameter("job");
	String tmp="";
	if(hobby!=null){
	for(int i=0;i<hobby.length;i++){
		tmp+=hobby[i]+" ";
	}
	}	
%>
</head>
<body>
결과
<hr>
이름 : <%=name%>  <br>
나이 : <%=age%> <br>
성별 : <%=rd1%> <br>
관심분야 : <%=tmp%><br>
직업 : <%=job%> <br>
</body>
</html>