<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<%@include file="/main/header.jsp"%>
<script>
	$(function() {
		$("#pwBtn").click(function() {
			if ($("#name").val() == "") {
				alert("방이름을 입력해 주세요");
				return false;
			}
			if ($("#content").val() == "") {
				alert("객실타입을 입력해 주세요");
				return false;
			}
			if ($("#peakprice").val() == "") {
				alert("성수기 가격을 입력해 주세요");
				return false;
			}
			if ($("#lowseasonPrice").val() == "") {
				alert("비수기 가격을 입력해 주세요");
				return false;
			}
			if ($("#sperson").val() == "") {
				alert("기준인원을 입력해 주세요");
				return false;
			}
			if ($("#mperson").val() == "") {
				alert("최대인원을 입력해 주세요");
				return false;
			}
			if ($("#file").val()==""){
				alert("이미지를 넣어주세요");
				return false;
			}
			$("#frm").submit();
		})
		$("input:text[numberOnly]").keyup(function(){
			$(this).val($(this).val().replace(/[^0-9]/g,""));			
		})	
		$("#file").on("change",function(){
			var fileName = $(this).val();
			$(this).next("#filelabel").html(fileName);
		})
	})
</script>
</head>
<body>
	<div class="container mt-5">
		<div class="text-center my-5">
			<h2 class="font-weight-bold">Room</h2>
			<br>
			<p class="text-secondary small">객실안내</p>
		</div>
		<div class="card my-5">
			<form action="/Pension_Project/pensionWrite.go" method="post" id="frm" enctype="multipart/form-data">
				<div class="card-body">
					<div class="form-group">
						<label for="title">방 이름</label> <input type="text" name="name"
							id="name" class="form-control" placeholder="방이름을 입력해 주세요">
					</div>
					<div class="form-group">
						<label for="content">객실타입</label> <input type="text"
							name="content" id="content" class="form-control"
							placeholder="객실타입을 입력해 주세요">
					</div>
					<div class="form-group">
						<label for="peakprice">성수기 가격</label> <input type="text"
							name="peakPrice" id="peakprice" class="form-control" placeholder="성수기 가격을 입력해 주세요" numberOnly>
					</div>
					<div class="form-group">
						<label for="lowseasonPrice">비수기 가격</label> <input type="text"
							name="lowseasonPrice" id="lowseasonPrice" class="form-control" placeholder="비수기 가격을 입력해 주세요" numberOnly>
					</div>
					<div class="form-group">
						<label for="sperson">기준인원</label> <input type="text"
							name="sperson" id="sperson" class="form-control" placeholder="기준인원을 입력해 주세요" numberOnly>
					</div>
					<div class="form-group">
						<label for="mperson">최대인원</label> <input type="text"
							name="mperson" id="mperson" class="form-control" placeholder="최대인원을 입력해 주세요" numberOnly>
					</div>
					<div class="form-group">					
						<label for="file">이미지</label>
					<div class="input-group">
					<div class="custom-file">
						 <input type="file" accept="image/*" class="custom-file-input" id="file" name="file"  aria-describedby="inputGroupFileAddon04">
						<label id="filelabel" class="custom-file-label" for="file">Choose file</label>
						</div>
					</div>
					</div>
					<div class="form-group text-right">
						<button type="button" name="pwBtn" id="pwBtn"
							class="btn btn-sm btn-outline-info">글쓰기</button>
							<a href="/Pension_Project/pensionMain.go" class="btn btn-sm btn-outline-secondary">목록으로</a>
					</div>
				</div>
			</form>
		</div>
	</div>
	
	<%@include file="/main/footer.jsp"%>
</body>
</html>