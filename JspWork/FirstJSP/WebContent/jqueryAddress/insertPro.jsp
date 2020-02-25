<%@page import="com.jquery.address.JAddressDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="vo" class="com.jquery.address.AddressVO"></jsp:useBean>
<jsp:setProperty property="*" name="vo"/>
<%
	JAddressDAO jdao = JAddressDAO.getInstance();
	jdao.addressInsert(vo);
	response.sendRedirect("list.jsp");
%>
</html>