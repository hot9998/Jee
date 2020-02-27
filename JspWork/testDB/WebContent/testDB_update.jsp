<%@ page import="db.beans.*,java.sql.*,java.util.*,java.io.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="QueryBean" scope="page" class="db.beans.QueryBean" />
<jsp:setProperty property="*" name="QueryBean" />
<%
	// 캐쉬 제거
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);

	request.setCharacterEncoding("utf-8");

	String id = request.getParameter("id") == null ? "" : request.getParameter("id").trim();
	String name = request.getParameter("name") == null ? "" : request.getParameter("name").trim();
	String phone = request.getParameter("phone") == null ? "" : request.getParameter("phone").trim();
	String grade = request.getParameter("grade") == null ? "" : request.getParameter("grade").trim();

/* 	System.out.println("id: " + id);
	System.out.println("name: " + name);
	System.out.println("phone: " + phone);
	System.out.println("grade: " + grade); */

	QueryBean.getConnection();
	
	int res=0;

	try {
		res = QueryBean.updateUserInfo(id, name, phone, grade);
	} catch (Exception e) {
		out.print(e.toString());
	} finally {
		QueryBean.closeConnection();
	}

	out.print("[");
	out.print("{");
	out.print("\"RESULT_OK\": \"" + res + "\" ");
	out.print("}");
	out.print("]");
	
	System.out.println("res"+ res);
%>