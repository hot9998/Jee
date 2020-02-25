<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글수정</title>
<%@include file="/main/header.jsp"%>
</head>
<script>
	$(function() {
		$("#updateBtn").click(function() {			
			if ($("#title").val() == "") {
				alert("제목을 입력하세요");
				return false;
			}
			if (CKEDITOR.instances.writeContent.getData() == "") {
				alert("내용을 입력하세요");
				return false;
			}
			$("#content").val(CKEDITOR.instances.writeContent.getData());
			$("#frm").submit();			
		})

	$('input[type="text"]').keydown(function() {
			if (event.keyCode === 13) {
				event.preventDefault();
			}
		});
	})
</script>
<body>
	<div class="container mt-5">
			<div class="text-center my-5">			
			<c:if test="${btype==0 }">
			<h2 class="font-weight-bold">Notice</h2>
			<br>
			<p class="text-secondary small">펜션 공지사항 입니다.</p>
			</c:if>
			<c:if test="${btype==1 }">
			<h2 class="font-weight-bold">Qna</h2>
			<br>
			<p class="text-secondary small">객실 예약 및 궁금한점 질문과답변 게시판입니다.</p>
			</c:if>
			<c:if test="${btype==2 }">
			<h2 class="font-weight-bold">Review</h2>
			<br>
			<p class="text-secondary small">고객님들의 소중한 이용후기 입니다.</p>
			</c:if>
		</div>
			<div class="text-center">
				<a type="button" href="/Pension_Project/boardMain.go?btype=0"
					class="btn btn-sm btn-dark">공지사항</a> <a type="button"
					href="/Pension_Project/boardMain.go?btype=1"
					class="btn btn-sm btn-dark">질문과답변</a> <a type="button"
					href="/Pension_Project/boardMain.go?btype=2"
					class="btn btn-sm btn-dark">이용후기</a>
			</div>
		<div class="card my-5">
			<form action="/Pension_Project/boardUpdate.go" method="post" id="frm">
			<input type="hidden" id="content" name="content">
			<input type="hidden" id="btype" name="btype" value="${btype }">
			<input type="hidden" id="userid" name="userid" value="${sessPm.userid }">
			<input type="hidden" id="bnum" name="bnum" value="${bnum }">		
				<div class="card-body">
					<div class="form-group">
						<label for="title">제목</label> <input type="text" name="title"
							id="title" class="form-control" placeholder="제목을 입력해 주세요" value="${pb.title }">
					</div>
					<div class="form-group">					
						<textarea id="writeContent" name="writeContent"
							class="form-control"></textarea>
					</div>
					<div class="form-group text-right">
						<button type="button" name="updateBtn" id="updateBtn"
							class="btn btn-sm btn-outline-info">수정하기</button>
							<a href="/Pension_Project/boardMain.go?btype=${btype }"
					class="btn btn-sm btn-outline-secondary">목록으로</a>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script>
		CKEDITOR.replace("writeContent");		
		CKEDITOR.instances.writeContent.setData("${pb.content}");
		CKEDITOR.config.enterMode = CKEDITOR.ENTER_BR;
	</script>
</body>
<%@include file="/main/footer.jsp"%>	
</html>