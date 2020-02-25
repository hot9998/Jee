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
			// send 버튼 클릭 시 함수 실행
			zipSend();
		});
		$("#dong").keydown(function(e) {
			// 텍스트 창에 엔터 누를 시 함수 실행
			if(e.keyCode==13){
				zipSend();
			}
		});				
		
		$("#area").on("click","tr",function(){
			var address = $("td:eq(1)",this).text()+" "+
			$("td:eq(2)",this).text()+" "+
			$("td:eq(3)",this).text()+" "+
			$("td:eq(4)",this).text()+" "+
			$("td:eq(5)",this).text();
			$(opener.document).find("#zipcode").val($("td:eq(0)",this).text());
			$(opener.document).find("#addr").val(address);
			self.close();
			// val() : input 타입 text에 있는 값을 가져오거나 세팅
			// text() : 나머지 타입 값을 가져오거나 세팅 
		})

		function zipSend(){
			$.get("zipCheckPro.jsp", {
				"dong" : $("#dong").val()
			}, function(data) {
				data = $.parseJSON(data);
				var htmlStr = "";
				// parsing
				htmlStr += "<table>";
				for (var i = 0; i < data.length; i++) {
					htmlStr += "<tr>";
					htmlStr += "<td>" + data[i].zipcode + "</td>";
					htmlStr += "<td>" + data[i].sido + "</td>";
					htmlStr += "<td>" + data[i].gugun + "</td>";
					htmlStr += "<td>" + data[i].dong + "</td>";
					htmlStr += "<td>" + data[i].bunji + "</td>";
					htmlStr += "</tr>";
				}
				htmlStr += "</table>"
				$("#area").html(htmlStr);
			}) //get
		} //send
		
	}) //document
	

</script>
</head>
<body>
	<table>
		<tr>
			<td>동이름 입력 <input type="text" name="dong" id="dong"> <input
				type="button" id="send" value="검색">
			</td>
		</tr>
	</table>
	<div id="area"></div>
</body>
</html>