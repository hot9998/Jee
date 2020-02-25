<%@page import="com.member.MemberDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="vo" class="com.member.MemberVO"></jsp:useBean>
<jsp:setProperty property="*" name="vo"/>
<%
MemberDAOImpl dao = MemberDAOImpl.getInstance();
dao.memberUpdate(vo);
session.invalidate();
response.sendRedirect("loginForm.jsp");
%>
