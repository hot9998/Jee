<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<%@include file="/main/header.jsp"%>
</head>
<script>
	$(function() {
		var expNum = /^\d{2,3}-\d{3,4}-\d{4}$/;
		var expEmail = /^[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[a-zA-Z0-9]{2,6}$/;
				
		$("#memberBtn").click(function() {
			if ($("#userid").val() == "") {
				alert("아이디를 입력하세요");
				return false;
			}
			if ($("#idcheck").val() == "0") {
				alert("아이디 중복확인을 하세요");
				return false;
			}
			if ($("#uid").val() == "") {
				alert("아이디 중복체크를 하세요");
				return false;
			}
			if ($("#pwd").val() == "") {
				alert("패스워드를 입력하세요");
				return false;
			}
			if ($("#pwd").val() != $("#pwd_check").val()) {
				alert("패스워드를 확인하세요");
				return false;
			}
			if ($("#name").val() == "") {
				alert("이름을 입력하세요");
				return false;
			}
			if ($("#tel").val() == "") {
				alert("전화번호를 입력하세요");
				return false;
			}
			if ($("#email").val() == "") {
				alert("이메일을 입력하세요");
				return false;
			}
			if ($("#zip").val() == "") {
				alert("우편번호를 입력하세요");
				return false;
			}
			if ($("#addr").val() == "") {
				alert("주소를 입력하세요");
				return false;
			}
			if(!$("#tel").val().match(expNum)){
				alert("전화번호 입력 양식이 아닙니다.");
				return false;
			}
			if(!$("#email").val().match(expEmail)){
				alert("이메일 입력 양식이 아닙니다.");
				return false;
			}
			alert("회원가입이 완료되었습니다");
			$("#frm").submit();

		})
		$("#idBtn").click(function() {
			if ($("#userid").val() == "") {
				alert("아이디를 입력하세요");
				return false;
			}
			if ($("#idcheck").val() == "1") {
				$("#userid").removeAttr("disabled");
				$("#userid").val("")
				$("#idcheck").val("0");
			} else {
				$.ajax({
					type : "post",
					url : "/Pension_Project/idCheck.go",
					data : {
						"userid" : $("#userid").val()
					},
					success : function(data) {
						if (data.trim() == "YES") {
							alert("사용 가능한 아이디 입니다");
							$("#idcheck").val("1");
							$("#uid").val($("#userid").val())
							$("#userid").attr("disabled", true);
							$("#idcheck").val("1");
						} else if (data.trim() == "NO") {
							alert("사용 불가능한 아이디 입니다");
							$("#userid").val("");
						}
					},
					error : function(e) {
						alert("error:" + e);
					}

				})
			}
		})
		$("#zipBtn").click(
				function() {
					window.open("/Pension_Project/zipCheck.go", "",
							"width=800 height=500 top=100 left=500");
				})		
		
	})
	

</script>
<body>
	<div class="container mt-5">
		<div class="row">
			<div class="col-6 mx-auto">
				<div class="card">
					<div class="card-header">
						<h4 class="text-center">회원가입</h4>
					</div>
					<div class="card-body">
						<form action="/Pension_Project/memberInsert.go" method="post"
							id="frm">
							<input type="hidden" id="zipcode" name="zipcode"> <input
								type="hidden" id="uid" name="uid"> <input type="hidden"
								id="idcheck" name="idcheck" value="0">
							<div class="form-group">
								<label for="userid">ID</label>
								<div class="input-group">
									<input type="text" name="userid" id="userid"
										class="form-control" placeholder="아이디를 입력해 주세요">
									<div class="input-group-append">
										<button type="button" id="idBtn" class="btn btn-outline-info">중복체크</button>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="pwd">패스워드</label> <input type="password" name="pwd"
									id="pwd" class="form-control" placeholder="패스워드를 입력해 주세요">
							</div>
							<div class="form-group">
								<label for="pwd">패스워드 확인</label> <input type="password"
									id="pwd_check" class="form-control" placeholder="패스워드를 입력해 주세요">
							</div>
							<div class="form-group">
								<label for="name">이름</label> <input type="text" name="name"
									id="name" class="form-control" placeholder="이름을 입력해 주세요">
							</div>
							<div class="form-group">
								<label for="tel">전화번호</label> <input type="text" name="tel"
									id="tel" class="form-control" placeholder="숫자와 -만 입력해주세요">
							</div>
							<div class="form-group">
								<label for="email">이메일</label> <input type="email" name="email"
									id="email" class="form-control" placeholder="이메일을 입력해 주세요">
							</div>
							<div class="form-group">
								<label for="zip">우편번호</label>
								<div class="input-group">
									<input type="text" name="zip" id="zip" class="form-control"
										placeholder="주소를 검색해 주세요" disabled="disabled">
									<div class="input-group-append">
										<button type="button" id="zipBtn" class="btn btn-outline-info">검색</button>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="addr">주소</label> <input type="text" name="addr"
									id="addr" class="form-control" placeholder="주소를 입력해 주세요">
							</div>
							<div class="form-group">
								<label for="code">관리자 코드</label> <input type="password" name="code"
									id="code" class="form-control" placeholder="관리자가 되려면 코드를 입력하세요">
							</div>
							<div class="text-center">
								<button type="button" id="memberBtn"
									class="btn btn-outline-primary">회원가입</button>
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
