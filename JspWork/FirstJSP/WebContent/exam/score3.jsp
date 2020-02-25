<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 제이쿼리 사용 -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
$(document).ready(function(){
	$("#btn").click(function() {
		if($("#name").val()==""){
			alert("이름을 입력하세요");
			return false;
		}
		if($("#kor").val()==""||!($.isNumeric($("#kor").val()))){
		alert("국어점수를 입력하세요");
		return false;		
		}
		if($("#math").val()==""||!$.isNumeric($("#math").val())){
		alert("수학점수를 입력하세요");
		return false;	
		}
		if($("#eng").val()==""||!$.isNumeric($("#eng").val())){
		alert("영어점수를 입력하세요");
		return false;
		}
		$("#frm").submit();
	})
});
</script>
</head>
<body>
<form action="scoreBeanResult.jsp" name="frm" id="frm" method="post">
이름 : <input type="text" name="name" id="name"> <br>
국어 : <input type="text" name="kor" id="kor" value="0"> <br>
수학 : <input type="text" name="math" id="math" value="0"> <br>
영어 : <input type="text" name="eng" id="eng" value="0"> <br>
<!-- input 타입이 button인것은 submit을 포함 하지 않음 -->
<input type="button" value="전송" id="btn">
<!-- <button>버튼전송</button> -->	
<!-- button은 submit을 포함 -->
<input type="reset" value="취소">
</form>
</body>
</html> 