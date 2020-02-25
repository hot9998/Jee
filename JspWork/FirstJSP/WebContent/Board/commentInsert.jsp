<%@page import="com.board.BoardDAO"%>
<%@page import="com.board.CommentVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
    String msg = request.getParameter("msg");
    int bnum = Integer.parseInt(request.getParameter("num"));
    String userid = (String)session.getAttribute("userid");
    if(userid==null){ //로그인 안됨
    	out.println("1");
    }else{
 	    CommentVO cvo = new CommentVO();
 	   	cvo.setUserid(userid);
	    cvo.setMsg(msg);
	    cvo.setBnum(bnum);
	    BoardDAO dao = BoardDAO.getInstance();
	    dao.commentInsert(cvo);
	    response.sendRedirect("commentList.jsp?num="+bnum);
    }
%>