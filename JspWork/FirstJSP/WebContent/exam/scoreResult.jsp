<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
String name = request.getParameter("name");
int kor = Integer.parseInt(request.getParameter("kor"));
int math = Integer.parseInt(request.getParameter("math"));
int eng = Integer.parseInt(request.getParameter("eng"));
int sum = kor+math+eng;
int avg = sum/3;
String grade="";
/*if(avg>=90){
	grade = "A";
}else if (avg>=80){
	grade = "B";
}else if (avg>=70){
	grade = "C";
}else{
	grade = "F";
}*/
switch(avg/10){
 	case 10:
	case 9:grade="A";break;
	case 8:grade="B";break;
	case 7:grade="C";break;
	default:grade="F";
}
%>
</head>
<body>
이름 : <%=name %> <br>
국어 : <%=kor%> <br>
수학 : <%=math %> <br>
영어 : <%=eng %> <br>
총점 : <%=sum %> <br>
평균 : <%=avg %>
학점 : <%=grade %>
</body>
</html>