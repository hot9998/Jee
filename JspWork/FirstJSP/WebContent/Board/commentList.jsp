<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="com.board.CommentVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	int bnum = Integer.parseInt(request.getParameter("num"));
	BoardDAO dao = BoardDAO.getInstance();
   ArrayList<CommentVO> arr=	dao.commentList(bnum);
   JSONArray jarr = new JSONArray();
   for(CommentVO cv : arr){
	   JSONObject obj = new JSONObject();
	   obj.put("bnum" , cv.getBnum());
	   obj.put("cnum", cv.getCnum());
	   obj.put("userid", cv.getUserid());
	   obj.put("msg", cv.getMsg());
	   obj.put("regdate", cv.getRegdate());
	   jarr.add(obj);
  }
   out.println(jarr.toString());
%>