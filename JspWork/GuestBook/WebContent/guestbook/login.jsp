<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="adminLogin.gb" method="post">
<table>
<tr>
<td><b>ID</b></td>
<td><input type="text" id="userid" name="userid"></td>
</tr>
<tr>
<td><b>PW</b></td>
<td><input type="password" id="pwd" name="pwd"></td>
</tr>
<tr>
<td colspan="2" align="center">
<input type="submit" value="로그인"></td>
</tr>
<tr>
<td colspan="2" align="center">
<span style="color: red;">${errMsg} </span></td>
</tr>
</table>
</form>
</body>
</html>