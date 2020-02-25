<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//session.removeAttribute("userid"); // 세션의 해당 요소만 삭제
	session.invalidate(); // 세션내용 전체 삭제
	response.sendRedirect("loginForm.jsp");
%>