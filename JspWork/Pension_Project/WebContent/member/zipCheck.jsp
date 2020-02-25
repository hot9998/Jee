<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주소체크</title>
    <link rel="stylesheet" href="/Pension_Project/css/bootstrap.min.css" />        
	<script src="/Pension_Project/js/jquery-3.4.1.min.js"></script>
    <script src="/Pension_Project/js/bootstrap.bundle.min.js"></script>
<script>
	$(function() {
		$("#zipBtn").click(function() {
			if ($("#dong").val() == "") {
				alert("동이름을 입력하세요");
				return false;
			}
			$.ajax({
				type : "post",
				url : "/Pension_Project/zipCheck.go",
				data : {
					"dong" : $("#dong").val()
				},
				success : function(data) {
					data = $.parseJSON(data);
					var htmlStr = "";					
					for (var i = 0; i < data.length; i++) {
						htmlStr += "<tr>";
						htmlStr += "<td>" + data[i].zipcode + "</td>";
						htmlStr += "<td>" + data[i].sido + "</td>";
						htmlStr += "<td>" + data[i].gugun + "</td>";
						htmlStr += "<td>" + data[i].dong + "</td>";
						htmlStr += "<td>" + data[i].bunji + "</td>";
						htmlStr += "</tr>";
					}
					$("#area").html(htmlStr);
					$("#dong").val("");
				}
			}), error(function(e) {
				alert("error:" + e);
			})
		})
		$("#area").on(
				"click",
				"tr",
				function() {
					var address = $("td:eq(1)", this).text() + " "
							+ $("td:eq(2)", this).text() + " "
							+ $("td:eq(3)", this).text() + " "
							+ $("td:eq(4)", this).text();
					var zip = $("td:eq(0)", this).text()
					$(opener.document).find("#zipcode").val(zip);
					$(opener.document).find("#zip").val(zip);
					$(opener.document).find("#addr").val(address);
					self.close();
				})
	})
</script>
</head>
<body>
	<div class="container my-2 small">
		<div class="input-group">
			<input type="text" class="form-control" id="dong" name="dong"
				placeholder="동이름을 입력하세요">
			<div class="input-group-append">
				<input type="button" class="btn btn-outline-primary" id="zipBtn"
					value="검색">
			</div>
		</div>
		<table class="table table-hover mt-2">
		<thead>	
			<tr>			
				<th>우편번호</th>
				<th>시/도</th>
				<th>구/군</th>
				<th>주소</th>
				<th>번지</th>
			</tr>		
			</thead>		
		<tbody id="area"></tbody>		
		</table>
	</div>
</body>
</html>