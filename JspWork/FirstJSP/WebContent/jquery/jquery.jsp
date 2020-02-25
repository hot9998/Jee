<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
$(function(){
	$("#btn").click(function(){
		$("#result").load("jquerytest.jsp",function(data,stu){
			// 콜백 함수(결과값,상태값을 가져 와야 함)
			if(stu=="success"){
			$("#result").html(data+"<b>성공입니다.</b>");			
			}else{
				alert("실패");
			}
		})		
	})
})

</script>
</head>
<body>
<button id="btn">결과</button>
<div id="result"></div>

</body>
</html>