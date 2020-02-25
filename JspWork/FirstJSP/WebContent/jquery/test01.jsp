<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js">	
</script>
<script>
$(function(){
	$("#b2").click(function() {
		//$.get방식으로 결과가 result에 출력
		$.get("test01Result.jsp",
				{"id" : $("#id").val() , "pwd" : $("#pwd").val()},
				function(data){			
			$("#result").html(data);			
		})
		
	});	
	$("#postBtn").click(function(){
		$.post("test01Result.jsp",
				{"id":$("#id").val(),"pwd":$("#pwd").val()},
				function(data){
					$("#result").html(data);
				})
	})
	$("#ajaxBtn").click(function(){
		$.ajax({
			type : "post",
			url : "test01Result.jsp",
			data : {"id":$("#id").val(),"pwd":$("#pwd").val()},
			success : function(d){
				$("#result").html(d);
			},
			error : function(e){
				alert("error :"+e);
			}			
		})
	})
});

</script>
</head>
<body>
<form action="test01Result.jsp" method="post" name="frm">
id : <input type="text" name="id" id="id"><br>
pwd : <input type="password" name="pwd" id="pwd"><br>	
<input type="button" id="b2" value="전송">
<input type="button" id="postBtn" value="post전송">
<input type="button" id="ajaxBtn" value="ajax전송"><br><br>
</form>
<div id="result"></div>
</body>
</html>