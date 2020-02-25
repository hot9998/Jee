<%@page import="com.address.ZipCodeBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.address.AddressDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
a:link {
	text-decoration: none;
	color: #000
}

a:hover {
	text-decoration: none;
	color: #000
}

a:visited {
	text-decoration: none;
	color: #000
}
</style>
<%
	request.setCharacterEncoding("utf-8");
	String dong = request.getParameter("dong");
	AddressDAO dao = AddressDAO.getInstance();
	ArrayList<ZipCodeBean> zarr = dao.zipfinder(dong);
%>
<script>
	/* 	function dongCheck() {
	 if (document.getElementById("dong").value == "") {
	 alert("동이름을 입력하세요");
	 return;		
	 }
	 frm.submit();
	 } */
	 // 위, 아래 방식 차이
	dongCheck = function() {
		if (document.getElementById("dong").value == "") {
			alert("동이름을 입력하세요");
			return;
		}
		frm.submit();
	}

	function send(code, sido, gugun, dong, bunji) {
		var address = sido + " " + gugun + " " + dong + " " + bunji;
		opener.document.getElementById("zipcode").value = code;
		opener.document.getElementById("zip").value = code;
		opener.document.getElementById("addr").value = address;
		self.close();
	}
</script>
</head>
<body>
	<h4>우편번호찾기</h4>
	<form action="zipCheck.jsp" name="frm">
		<!-- action을 비워도 자기 자신 -->
		<table>
			<tr>
				<td>동이름입력 : <input type="text" name="dong" id="dong"> <input
					type="button" value="검색" onclick="dongCheck()">
				</td>
			</tr>
			<%
				if (zarr.isEmpty()) {
			%>
			<tr>
				<td>검색된 결과가 없습니다.</td>
			</tr>
			<%
				} else {
			%>
			<tr>
				<td>*검색 후,아래 우편번호를 클릭하면 자동으로 입력됩니다.</td>
			</tr>
			<%
				for (ZipCodeBean z : zarr) {
						String zip = z.getZipcode();
						String sido = z.getSido();
						String bunji = z.getBunji();
						String gugun = z.getGugun();
						String d = z.getDong();
			%>
			<tr>
				<td><a
					href="javascript:send('<%=zip%>','<%=sido%>','<%=gugun%>','<%=d%>','<%=bunji%>')">
						<%=zip%> <%=sido%> <%=gugun%> <%=d%> <%=bunji%></a></td>
			</tr>
			<%
				}
				}
			%>

		</table>
	</form>

</body>
</html>