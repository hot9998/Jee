<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="right">
<a href="insertAction.ib">추가하기</a>
</div>
	<table>
		<thead>
			<tr>
				<td>번호</td>
				<td>이름</td>
				<td>주소</td>
				<td>전화번호</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${arrList}" var="i">
				<tr>
					<td>${i.num }</td>
					<td><a href="viewAction.ib?num=${i.num }">${i.name }</a></td>
					<td>${i.addr }</td>
					<td>${i.tel }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>