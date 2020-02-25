<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<%@include file="/main/header.jsp"%>
</head>
<script>
	$(function() {
		$("#loginBtn").click(function() {
			if ($("#userid").val() == "") {
				alert("아이디를 입력하세요");
				return false;
			}
			if ($("#pwd").val() == "") {
				alert("패스워드를 입력하세요");
				return false;
			}
			$.ajax({
				type : "post",
				url : "/Pension_Project/login.go",
				data : {
					"userid" : $("#userid").val(),
					"pwd" : $("#pwd").val()
				},
				success : function(data) {
					if (data.trim() == -1) {					
						alert("회원이 아닙니다.");						
					} else if (data.trim() == 0) {
						alert("일반회원 로그인");
						location.href = "/Pension_Project/index.jsp";
					} else if (data.trim() == 1) {
						alert("관리자 로그인");
						location.href = "/Pension_Project/index.jsp";
					} else if (data.trim() == 2) {
						alert("패스워드를 확인해 주세요");
					}
				},
				error : function(e) {
					alert("error:" + e);
				}
			})
		})

	})
</script>
<body>
	<div class="container mt-5">
		<div class="row">
			<div class="col-6 mx-auto">
				<div class="card">
					<div class="card-header">
						<h4 class="text-center">로그인</h4>
					</div>
					<div class="card-body">
						<form action="login.go" method="post" id="frm">
							<div class="form-group">
								<label for="userid">ID</label> <input type="text" name="userid"
									id="userid" class="form-control" placeholder="아이디를 입력해 주세요">
							</div>
							<div class="form-group">
								<label for="pwd">패스워드</label> <input type="password" name="pwd"
									id="pwd" class="form-control" placeholder="패스워드를 입력해 주세요">
							</div>
							<div class="text-center">
								<button type="button" id="loginBtn"
									class="btn btn-outline-primary">로그인</button>
								<button type="reset" class="btn btn-outline-danger">초기화</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
<%@include file="/main/footer.jsp"%>	
</body>
</html>
