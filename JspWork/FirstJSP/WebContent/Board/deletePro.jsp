<%@page import="com.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
   int num = Integer.parseInt(request.getParameter("num"));
   String passwd = request.getParameter("passwd");

   BoardDAO dao = BoardDAO.getInstance();
   int flag = dao.boardDelete(num, passwd);
   if(flag==1){
	   response.sendRedirect("list.jsp");
   }else{
%>
<script>
	alert("비밀번호가 맞지 않습니다.");
	history.back(); //이전페이지로 이동
</script>
<%  
   }
%>