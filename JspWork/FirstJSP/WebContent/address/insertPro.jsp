<%@page import="com.address.AddressDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 
    1. insert.jsp 에서 넘어오는 매개변수르 bean 객체에 저장
    2. hr계정의 address 테이블에 넘어온 값 저장
     -->
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="ad" class="com.address.Address"></jsp:useBean>
<jsp:setProperty property="*" name="ad" />
<%
	AddressDAO dao = AddressDAO.getInstance();
	dao.addrInsert(ad);
	response.sendRedirect("list.jsp");
%>