<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function check(){
	// getElementById().value : 해당 id의 값을 가져옴 
	if(document.getElementById("name").value.length==0){
		alert("이름을 입력하세요");
		return;
	}
	//isNan : 해당 값이 문자인지 아닌지 구분
	if(document.getElementById("kor").value.length==0||isNaN(document.getElementById("kor").value)){
		alert("국어점수를 입력하세요");
		return;	
	}
	if(document.getElementById("math").value.length==0||isNaN(document.getElementById("math").value)){
		alert("수학점수를 입력하세요");
		return;	
	}	
	if(document.getElementById("eng").value.length==0||isNaN(document.getElementById("eng").value)){
		alert("영어점수를 입력하세요");
		return;	
	}	
	//document.forms[0].submit();
	//frm.submit();
	document.getElementById("frm").submit();
	}
</script>
</head>
<body>
<form action="scoreResult.jsp" name="frm" id="frm">
이름 : <input type="text" name="name" id="name"> <br>
국어 : <input type="text" name="kor" id="kor"> <br>
수학 : <input type="text" name="math" id="math"> <br>
영어 : <input type="text" name="eng" id="eng"> <br>
<!-- input 타입이 button인것은 submit을 포함 하지 않음 -->
<input type="button" value="전송" onclick="check()">
<!-- <button>버튼전송</button> -->	
<!-- button은 submit을 포함 -->
<input type="reset" value="취소">
</form>
</body>
</html> 