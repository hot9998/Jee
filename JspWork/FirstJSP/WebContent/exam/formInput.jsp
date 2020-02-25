<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="formResult.jsp">
이름 : <input type="text" name="name"> <br>
나이 : <input type="text" name="age"> <br>
성별 :
<input type="radio" name="gender" value="남자" id="m" checked>
<label for="m">남자</label>
<input type="radio" name="gender" value="여자" id="fm"> 
<label for="fm">여자</label> <br>
관심분야 <br>
<input type="checkbox" name="hobby" value="운동" id="cb1">
<label for="cb1">운동</label>
<input type="checkbox" name="hobby" value="게임" id="cb2">
<label for="cb2">게임</label>
<input type="checkbox" name="hobby" value="등산" id="cb3">
<label for="cb3">등산</label>
<input type="checkbox" name="hobby" value="영화" id="cb4">
<label for="cb4">영화</label> <br>
직업 : 
<select name="job">
<option value="학생">학생
<option value="선생님">선생님 
</select>
<br>
<input type="submit" value="전송">
</form>

</body>
</html>