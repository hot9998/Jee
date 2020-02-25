<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
	$(function() {
		$("#send").click(function() {
			if ($("#dong").val() == "") {
				alert("동이름을 입력하세요");
				$("#dong").focus();
				return false;
			}
			$.post("zip.do", {
				"dong" : $("#dong").val()
			}, function(data) {
				data = $.parseJSON(data);
				var htmlStr = ""
				htmlStr 	+= "<table>";
				for (var i = 0; i < data.length; i++) {
					htmlStr += "<tr>";
					htmlStr += "<td>" + data[i].zipcode + "</td>";
					htmlStr += "<td>" + data[i].sido + "</td>";
					htmlStr += "<td>" + data[i].gugun + "</td>";
					htmlStr += "<td>" + data[i].dong + "</td>";
					htmlStr += "<td>" + data[i].bunji + "</td>";
					htmlStr += "</tr>";
				}
				htmlStr += "</table>";
				$("#area").html(htmlStr);
			})
		})
		$("#area").on("click", "tr", function() {
			var address = $("td:eq(1)",this).text()+" "+
			$("td:eq(2)",this).text()+" "+
			$("td:eq(3)",this).text()+" "+
			$("td:eq(4)",this).text();			
			var zip = $("td:eq(0)",this).text()
		$(opener.document).find("#zipcode").val(zip);
		$(opener.document).find("#zip").val(zip);
		$(opener.document).find("#addr").val(address);
		self.close();
		})

	})
</script>
</head>
<body>
	<h4>우편번호찾기</h4>
	<table>
		<tr>
			<td>동이름입력 : <input type="text" name="dong" id="dong"> <input
				type="button" id="send" value="검색">
			</td>
		</tr>
		<tr>
		</tr>
	</table>
	<div id="area"></div>
</body>
</html>