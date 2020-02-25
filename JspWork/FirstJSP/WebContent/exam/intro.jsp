<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script   src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
$(document).ready(function(){
	$("#btn").click(function(){
		if($("#name").val()==""){
			alert("성명을 입력하세요");
			return false;
		}
		if($("#year").val()=="" || $("#month").val()=="" || $("#day").val()==""){
			alert("생년월일을 입력하세요");
			return false;
		}
		if($("input:radio[name='yy']:checked").length==0){
			alert("양력/음력을 선택하세요");
			return false;
		}
		if($("input:checkbox[name='pgm']:checked").length==0){
			alert("사용 가능한 프로그램을 선택하세요");
			return false;
		}
		$("#frm").submit();
	})

})
</script>
</head>
<body>
<form action="introPro2.jsp" id="frm" name="frm" method="post">
<table border=2 >
	<tr>
		<th>성명</th>
		<td><input type=text name=name id="name" style='width:200px'></td>
		<th>성별</th>
		<th><label><input type=radio name="man" value="남" checked>남  </label>
		        <label><input type=radio name="man" value="여">여</label></th>
	</tr>
	<tr>
		<th>생년월일</th>
		<td colspan=3><input type=text name=year id="year" size=15  >년 
		<input type=text name=month  id=month  size=5>월 
		<input type=text name=day  id="day" size=5>일 
		<label><input type=radio name=yy value="양력">양력</label>
		 <label><input type=radio name=yy value="음력">음력</label></td>
	</tr>
	<tr>
		<th>주소</th>
		<td colspan=3><input type=text size=55 name=addr  id="addr"></td>
	</tr>
	<tr>
		<th>전화번호</th>
		<td colspan=3><input type=text size=15 name=phone id="phone"> 
		- <input type=text size=15 name=phone1  id="phone1"> 
		- <input type=text size=15 name=phone2  id="phone2"></td>
	</tr>
	<tr>
		<th colspan=4 bgcolor=skyblue>사용가능한 프로그램 선택하기</th>
	</tr>
	<tr>
		<td colspan=4>
			<ol type=A>
				<li><label><input type=checkbox name="pgm" value="한글">한글</label>
				<li><label><input type=checkbox name="pgm" value="포토샵">포토샵</label>
				<li><label><input type=checkbox name="pgm" value="매크로 미디어 디렉터">매크로 미디어 디렉터</label>
				<li><label><input type=checkbox name="pgm" value="드림위버">드림위버</label>
				<li><label><input type=checkbox name="pgm" value="3D MAX">3D MAX</label></li>
			</ol>
		</td>
	</tr>
	<tr>
		<th colspan=4 bgcolor=skyblue>가고싶은 여행지를 모두 선택하세요.</th>
	</tr>
	<tr>
		<td colspan=4><select size=3 name="play" multiple>
				<option value="설악산" selected>설악산
				<option value="경포대">경포대
				<option value="토발">토발
				<option value="거제도">거제도
				<option value="변산반도">변산반도
				<option value="단양8경">단양8경
				</select>
		</td>
	</tr>
	<tr>
		<th colspan=4 bgcolor=skyblue>미래의 꿈은 어떠한가요</th>
	</tr>	
	<tr>
		<td colspan=4>
			<textarea cols=50 rows=5 name="memo" id="memo">미래의 꿈은 희망입니다.</textarea>
	</tr>
	<tr>
		<th colspan=4><input type=button  id="btn" value="전송" >
		<input type=reset name=reset value="다시쓰기"></th>
	</tr>

</table>
</form>
</body>
</html>