<%@page import="com.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
 request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="vo" class="com.board.BoardVO"/>
<jsp:setProperty property="*" name="vo"/>
<%
	BoardDAO dao = BoardDAO.getInstance();
	int flag = dao.bardUpdate(vo);//비번맞으면 업데이트 아니면 오류
    if(flag==1){ //정상처리
    	response.sendRedirect("list.jsp");
    }else{ // 비번오류  ->alert창 띄우기
 %>
 	<script>   	
    	alert("비밀번호가 맞지 않습니다.");
        history.back(); //이전페이지로 돌아가기 : history.go(-1)
    </script>
<%    	
    }
%>



