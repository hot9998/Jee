<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<%@include file="/main/header.jsp"%>
</head>
<script>
	$(function() {
		var btype = ${btype};
		getData(1, btype);
	})
	function getData(pageNum, btype) {
		$.ajax({
			type : "post",
			url : "/Pension_Project/boardMain.go",
			data : {
				"pageNum" : pageNum,
				"btype" : btype
			},
			success : function(data) {
				$("#results").html(data);
			},
			error : function(e) {
				alert("요청실패");
			}
		})
	}
</script>
<body>
	<div class="container my-5">
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
			<div id="results"></div>
			<c:if test="${(btype==0 && sessPm.admin==1) || ((btype==1 || btype==2) && sessPm.admin==0 && sessPm!=null)}">
				<div class="text-right">
					<a type="button"
						href="/Pension_Project/boardWrite.go?btype=${btype }"
						class="btn btn-outline-dark btn-sm">글쓰기</a>
				</div>
			</c:if>
		</div>
		<%@include file="/main/footer.jsp"%>
</body>
</html>
