<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	request.setCharacterEncoding("utf-8");
    int num = Integer.parseInt(request.getParameter("num"));
%>
<script   src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
del = function(){
	if($("#passwd").val()==""){
		alert("비밀번호를 입력하세요");
		$("#passwd").focus();
		return false;
	}
	$("#frm").submit();
}
</script>
</head>
<body>
<h2>글삭제</h2>
<form id="frm" action="deletePro.jsp" method="post">
<input type="hidden" name="num" value="<%=num %>">
<table border="1" >
  <tr>
     <td>
       <b>비밀번호를 입력해 주세요.</b></td>
  </tr>
  <tr>
     <td>비밀번호 :   
       <input type="password" name="passwd" id="passwd">
     </td>
</tr>
<tr>
  <td><input type="button" value="글삭제" onclick="del()">
  <input type="button" value="글목록" onclick="location.href='list.jsp'"></td>
</tr>
</table>
</form>
</body>
</html>