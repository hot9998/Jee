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
		$("#btn").click(function() {
			//load
			//jqueryResult.jsp?name=load방식&status=ajaxload 방식
			/*
			$("#result").load("jqueryResult.jsp", {
				"name" : "load방식",
				"status" : "ajaxload 방식"
			}, function(str) {
				alert(str);
				$("#result").html(str);
			});//load
			});

			//get			
			$.get("jqueryResult.jsp", {
				"name" : "get방식",
				"status" : "ajaxget 방식"
			}, function(str) {
				$("#result").html(str);
			});
			
			//post
			$.post("jqueryResult.jsp", {
				"name" : "post방식",
				"status" : "ajaxpost 방식"
			}, function(str) {
				$("#result").html(str);
			})
			*/
			
			$.ajax({
				type : "POST",
				url : "jqueryResult.jsp",
				data : { "name" : "ajax방식", "status" : "ajax 방식"},
				success : function(d){
					$("#result").html(d);
				},
				error : function(e){
					alert("error :"+e);
				}
			})
		});

	})
</script>
</head>
<body>
	<button id="btn">결과</button>
	<div id="result"></div>
	

</body>
</html>