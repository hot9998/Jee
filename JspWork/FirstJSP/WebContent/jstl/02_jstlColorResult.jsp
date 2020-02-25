<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  request.setCharacterEncoding("utf-8");
  String colors = request.getParameter("color");
  if(colors.equals("1")){
	  out.println("빨강");
  }else if(colors.equals("2")){
	  out.println("초록");
  }else if(colors.equals("3")){
	  out.println("파랑");
  }
%>
