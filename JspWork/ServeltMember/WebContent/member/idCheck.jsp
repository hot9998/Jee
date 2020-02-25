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
		$("#idCheckBtn").click(
				function() {
					if ($("#userid").val() == "") {
						alert("아이디를 입력하세요");
						$("#userid").focus();
						return false;
					}
					$.ajax({
						type : "post",
						url : "idCheck.go",
						data : {
							"userid" : $("#userid").val()
						},
						success : function(data) {
							if (data.trim() == "YES") {
								alert("사용 가능한 아이디입니다.");
								$(opener.document).find("#userid").val(
										$("#userid").val());
								$(opener.document).find("#uid").val(
										$("#userid").val());
								self.close();
							} else if (data.trim() == "NO") {
								alert("사용 불가능한 아이디입니다.");
							}
						},
						error : function(e) {
							alert("error" + e);
						}
					})
				})
	})
</script>
</head>
<body>
	<h2>아이디 중복확인</h2>
	아이디
	<input type="text" name="userid" id="userid">
	<input type="button" value="사용" id="idCheckBtn">
</body>
</html>