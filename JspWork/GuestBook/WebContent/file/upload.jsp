<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="upload.do" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td>글쓴이 : <input type="text" name="name"></td>
			</tr>
			<tr>
				<td>제목 : <input type="text" name="title"></td>
			</tr>
			<tr>
				<td>파일 : <input type="file" name="uploadFile" value="파일 선택">
				</td>
			</tr>
			<tr>
				<td><input type="submit" value="전송"></td>
			</tr>
		</table>		
	</form>
	<img src="/GuestBook/upload/1.jpg" width="300" height="200">
</body>
</html>