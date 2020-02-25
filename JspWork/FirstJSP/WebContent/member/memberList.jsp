<%@page import="com.member.MemberVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.member.MemberDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="member.js"></script>
<%
	request.setCharacterEncoding("utf-8");
	String userid = (String) session.getAttribute("userid");
	// session의 값을 가져오면 object 형태이기 때문에 String으로 캐스팅
	MemberDAOImpl dao = MemberDAOImpl.getInstance();
	ArrayList<MemberVO> arr = dao.memberList();
%>
</head>
<body>
	<div align="right">
		<a href="memberView.jsp"><%=userid%>관리자</a>님 반갑습니다. <a href="logout.jsp">로그아웃</a>
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
				<%
					for (MemberVO vo : arr) {
						String mode = vo.getAdmin()==0?"일반사용자":"관리자";
				%>
				<tr>
				<td><%=vo.getName() %> </td>
				<td><%=vo.getUserid() %> </td>
				<td><%=vo.getPhone() %> </td>
				<td><%=vo.getEmail() %> </td>
				<td><%=mode %> </td>
				<td onclick="del('<%=vo.getUserid()%>')">삭제
				</td>
				<!-- 자바 스크립트는 매개변수를 줄 때 ''를 붙여야 에러가 잘 나지 않음 -->
				
				
				</tr>
				<%
					}
				%>
			</tbody>
		</table>

</body>
</html>