<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%	
Calendar ca = Calendar.getInstance();
String[] arr = {"일","월","화","수","목","금","토"};

String day="";

switch(ca.get(Calendar.DAY_OF_WEEK)){
case 1:day="일요일";break;
case 2:day="월요일";break;
case 3:day="화요일";break;
case 4:day="수요일";break;
case 5:day="목요일";break;
case 6:day="금요일";break;
case 7:day="토요일";break;
}
String strDay="";
switch(ca.get(Calendar.DAY_OF_WEEK)){
case Calendar.MONDAY : strDay = "월요일입니다."; break;
case Calendar.TUESDAY : strDay = "화요일입니다."; break;
case Calendar.WEDNESDAY : strDay = "수요일입니다."; break;
case Calendar.THURSDAY : strDay = "목요일입니다."; break;
case Calendar.FRIDAY : strDay = "금요일입니다."; break;
case Calendar.SATURDAY : strDay = "토요일입니다."; break;
case Calendar.SUNDAY : strDay = "일요일입니다."; break;
}
%>
<%!
public String getDay(int x){
	String str="";
	switch(x){
	case 1:str="일요일";break;
	case 2:str="월요일";break;
	case 3:str="화요일";break;
	case 4:str="수요일";break;
	case 5:str="목요일";break;
	case 6:str="금요일";break;
	case 7:str="토요일";break;
	}
	return str;
}
%>
</head>
<body>
	오늘은
	<%=ca.get(Calendar.YEAR) %>년
	<%=ca.get(Calendar.MONTH)+1 %>월
	<%=ca.get(Calendar.DATE) %>일
	<%=ca.get(Calendar.DAY_OF_WEEK) %>요일
	<hr>
	<%=arr[ca.get(Calendar.DAY_OF_WEEK)-1] %>
	<hr>
	<%=day %>
	<hr>
	<%=strDay %>
	<hr>
	함수사용 : <%=getDay(ca.get(Calendar.DAY_OF_WEEK)) %>
</body>
</html>