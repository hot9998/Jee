<%@page import="com.jquery.address.AddressVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.jquery.address.JAddressDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	JAddressDAO dao = JAddressDAO.getInstance();
	ArrayList<AddressVO> arr = dao.getList();
%>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
$(function() {
	$("#searchBtn").click(function(){
		$.ajax({
			type : "get",
			url : "searchPro.jsp",
			data : {"field":$("#field").val(),"word":$("#word").val()},			
			success: function(data){
				data = $.parseJSON(data);
	               var htmlStr = "";
	               for(var i=0;i<data.length;i++){
	                  htmlStr+="<tr>";
	                  htmlStr+="<td>" + data[i].num + "</td>";
	                  htmlStr+="<td>" + data[i].name + "</td>";
	                  htmlStr+="<td>" + data[i].addr + "</td>";
	                  htmlStr+="<td>" + data[i].tel + "</td>";
	                  htmlStr+="</tr>";
	               }
	               $("table tbody").html(htmlStr);		
			},
			error :function(e){
				alert("error:"+e);
			}
		})
	})	
})

</script>
</head>
<div align="right">
	주소록 갯수 <br> <a href="insert.jsp">추가하기</a> / <a href="list.jsp">전체보기</a>
</div>
<body>
	<table border="1">
		<tr>
			<th>이름</th>
			<th>우편번호</th>
			<th>주소</th>
			<th>전화번호</th>
		</tr>
		<%
			for (AddressVO vo : arr) {
		%>
		<tr>
			<td><a href="detail.jsp?num=<%=vo.getNum()%>"><%=vo.getName()%></a></td>
			<td><%=vo.getZipcode()%></td>
			<td><%=vo.getAddr()%></td>
			<td><%=vo.getTel()%></td>

		</tr>
		<%
			}
		%>

	</table>

	<select name="field" id="field">
		<option value="name">이름</option>
		<option value="tel">전화번호</option>
	</select>
	<input type="text" name="word" id="word">
	<input type="button" value="검색" id="searchBtn">


</body>
</html>