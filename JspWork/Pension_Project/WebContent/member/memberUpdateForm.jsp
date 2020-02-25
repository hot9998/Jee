<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정</title>
<%@include file="/main/header.jsp"%>
</head>
<script>
	$(function() {
		var expNum = /^\d{2,3}-\d{3,4}-\d{4}$/;
		var expEmail = /^[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[a-zA-Z0-9]{2,6}$/;
		
		$("#updateBtn").click(function() {
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
			alert("회원정보 수정이 완료되었습니다");
			$("#frm").submit();

		})
		$("#zipBtn").click(
				function() {
					window.open("/Pension_Project/zipCheck.go", "",
							"width=800 height=500 top=100 left=500");
				})
		$("#cb").click(function() {
			if ($("#cb").is(":checked")) {
				$("#admin").val("0");
			} else {
				$("#admin").val("1");
			}
		})
		$("#deleteBtn").click(function(){
			if(confirm("정말 탈퇴하시겠습니까?")){
				location.href="/Pension_Project/memberDelete.go?userid="+${sessPm.userid};
				alert("회원 탈퇴가 완료되었습니다.");
			}
		})

	})	
</script>
<style>
input[type="number"]{
ime-mode : disabled
}
input[type="number"]::-webkit-outer-spin-button,
input[type="number"]::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
}
</style>
<body>
	<div class="container mt-5">
		<div class="row">
			<div class="col-6 mx-auto">
				<div class="card">
					<div class="card-header">
						<h4 class="text-center">회원정보수정</h4>
					</div>
					<div class="card-body">
						<form action="/Pension_Project/memberUpdate.go" method="post"
							id="frm">
							<input type="hidden" id="zipcode" name="zipcode"
								value="${pm.zipcode }"> <input type="hidden" id="userid"
								name="userid" value="${pm.userid }">
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
									id="name" class="form-control" value="${pm.name }"
									placeholder="이름을 입력해 주세요">
							</div>
							<div class="form-group">
								<label for="tel">전화번호</label> <input type="text" name="tel"
									id="tel" class="form-control" value="${pm.tel }" placeholder="숫자와 -만 입력해주세요">
							</div>

							<div class="form-group">
								<label for="email">이메일</label> <input type="email" name="email"
									id="email" class="form-control" value="${pm.email }"
									placeholder="이메일을 입력해 주세요">
							</div>
							<div class="form-group">
								<label for="zip">우편번호</label>
								<div class="input-group">
									<input type="text" name="zip" id="zip" class="form-control"
										placeholder="주소를 검색해 주세요" value="${pm.zipcode }"
										disabled="disabled">
									<div class="input-group-append">
										<button type="button" id="zipBtn" class="btn btn-outline-info">검색</button>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="addr">주소</label> <input type="text" name="addr"
									id="addr" class="form-control" value="${pm.addr }"
									placeholder="주소를 입력해 주세요">
							</div>
							<div>
								<c:if test="${sessPm.admin==1 }">
									<input type="hidden" id="admin" name="admin" value="1">
									<input type="hidden" id="code" name="code" value="0">
									<div class="custom-control custom-checkbox">
										<input type="checkbox" id="cb" name="cb"
											class="custom-control-input"> <label
											class="custom-control-label" for="cb">일반회원으로 변경</label>
									</div>
								</c:if>
								<c:if test="${sessPm.admin==0 }">
									<div class="form-group">
										<input type="hidden" id="admin" name="admin" value="0">
										<label for="code">관리자 코드</label> <input type="password"
											name="code" id="code" class="form-control"
											placeholder="관리자가 되려면 코드를 입력하세요">
									</div>
								</c:if>
							</div>
							<div class="text-center">
								<button type="button" id="updateBtn"
									class="btn btn-outline-primary">정보수정</button>
								<button type="button" id="deleteBtn" class="btn btn-outline-danger">회원탈퇴</button>
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
