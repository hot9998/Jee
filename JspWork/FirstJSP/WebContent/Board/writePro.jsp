<%@page import="com.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="bo" class="com.board.BoardVO"/>
<jsp:setProperty property="*" name="bo"/>
<%
bo.setIp(request.getRemoteAddr());
BoardDAO  dao = BoardDAO.getInstance();
dao.boardInsert(bo);  //글쓰기
response.sendRedirect("list.jsp");
%>