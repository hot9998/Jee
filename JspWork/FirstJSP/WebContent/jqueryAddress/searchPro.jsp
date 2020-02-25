<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="com.jquery.address.AddressVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.jquery.address.JAddressDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String field = request.getParameter("field");
	String word = request.getParameter("word");
	JAddressDAO dao = JAddressDAO.getInstance();
	ArrayList<AddressVO> arr = dao.getSearch(field, word);
	JSONArray jarr = new JSONArray();
	for (AddressVO av : arr) {
		JSONObject obj = new JSONObject();
		obj.put("num", av.getNum());
		obj.put("name", av.getName());
		obj.put("zipcode", av.getZipcode());
		obj.put("tel", av.getTel());
		obj.put("addr", av.getAddr());
		jarr.add(obj);
	}
	out.println(jarr.toString());
%>
