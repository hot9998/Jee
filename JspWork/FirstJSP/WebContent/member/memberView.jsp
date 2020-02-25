<%@page import="com.member.MemberVO"%>
<%@page import="com.member.MemberDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script   src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
check= function(){
	if($("#pwd").val()==""){
		alert("비밀번호를 입력하세요");
		return false;
	}
	if($("#pwd").val()!=$("#pwd_check").val()){
		alert("비밀번호가 일치하지 않습니다.");
		$("#pwd_check").focus();
		return false;
	}
	$("#frm").submit();   //action 한테 감
}
</script>
<%
 request.setCharacterEncoding("utf-8");
String userid =(String) session.getAttribute("userid");
MemberDAOImpl dao = MemberDAOImpl.getInstance();
MemberVO vo =  dao.memberView(userid);
%>
</head>
<body>
<div align="right">
<%=userid %>님 반갑습니다.
<a href="http://localhost:8888/FirstJSP/Board/list.jsp">글 목록</a> /
<a href="logout.jsp">로그아웃</a>  /
<a href="deletePro.jsp?userid=<%=userid %>">회원탈퇴</a>
</div>
<h2>회원정보변경 </h2>
<form action =" updatePro.jsp" id="frm" method="post" >
<input type="hidden" name="userid" id="userid" value="<%=userid %>">
		<table>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" id="name"  
				       value="<%=vo.getName()%>"></td>
			</tr>
			<tr>
				<td>암 호</td>
				<td><input type="password" name="pwd" id="pwd" size="20"></td>
			</tr>
			<tr height="30">
				<td width="80">암호 확인</td>
				<td><input type="password" name="pwd_check" id="pwd_check" size="20"></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="email"  id="email" 
				value="<%=vo.getEmail() %>"></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type="text" name="phone" id="phone" 
				 value="<%=vo.getPhone() %>"></td>
			</tr>
			<tr>
				<td>등급</td>
				<td>
				<input type="radio" name="admin" value="0" > 일반회원 
				<input type="radio" name="admin" value="1"> 관리자
				</td>
				<script>
				if(<%=vo.getAdmin()%>==0 ){   //일반사용자
					$("input:radio[value='0']").prop("checked",true);
				}else{  //관리자
					$("input:radio[value='1']").prop("checked",true);
				}
				</script>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<input type="button"  value="수정"  onclick="check()">
				 <input type="reset"     value="취소">
				</td>
			</tr>
			</table>
</form>

</body>
</html>