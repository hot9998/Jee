<%@page import="com.member.model.MemberVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
	function del(userid) {
		$.getJSON("memAdminDelete.go?userid=" + userid, function(d) {
			var htmlStr = "";
			$.each(d, function(key,val){
				htmlStr +="<tr>";
				htmlStr +="<td>"+val.name+"</td>";
				htmlStr +="<td>"+val.userid+"</td>";
				htmlStr +="<td>"+val.phone+"</td>";
				htmlStr +="<td>"+val.email+"</td>";
				htmlStr +="<td>"+val.admin+"</td>";				
				htmlStr +="<td onclick=del('"+val.userid+"')>삭제</td>";
				htmlStr +="</tr>"
			})
			$("table tbody").html(htmlStr);
		})
	}
</script>

</head>
<body>
	<div align="right">
		<a href="memView.go">${sessDto.name }</a>관리자님 반갑습니다. <a
			href="logout.go">로그아웃</a>
	</div>
	<table>
		<thead>
			<tr>
				<th>이름</th>
				<th>아이디</th>
				<th>전화번호</th>
				<th>이메일</th>
				<th>구분</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${arr }" var="i">
				<tr>
					<td>${i.name }</td>
					<td>${i.userid }</td>
					<td>${i.phone }</td>
					<td>${i.email }</td>
					<td><c:if test="${i.admin==1 }">관리자</c:if> <c:if
							test="${i.admin==0 }">일반회원</c:if></td>
					<td onclick="del('${i.userid }')">삭제</td>
					<!-- a href="javascript:del('${i.userid }')" 을 써도 상관없음 -->
				</tr>
			</c:forEach>

		</tbody>
	</table>

</body>
</html>