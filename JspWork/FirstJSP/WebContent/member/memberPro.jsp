<%@page import="com.member.MemberDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="mem" class="com.member.MemberVO"></jsp:useBean>
<jsp:setProperty property="*" name="mem"/>
<%
MemberDAOImpl dao = MemberDAOImpl.getInstance();
String uid = request.getParameter("uid");
mem.setUserid(uid);
dao.memberInsert(mem);
response.sendRedirect("loginForm.jsp");
%>