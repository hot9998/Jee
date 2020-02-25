<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글보기</title>
<%@include file="/main/header.jsp"%>
</head>
<script>
	$(function() {
		$.ajax({
			type : "post",
			url : "/Pension_Project/boardView.go",
			data : {
				"bnum" : $("#bnum").val()
			},
			success : function(data) {
				data = $.parseJSON(data);
				var htmlStr = "";
				htmlStr += "작성일 : " + data.date + "<br>";
				htmlStr += "제목 : " + data.title + "<br>";
				htmlStr += "작성자 : " + data.name + "<br>";
				htmlStr += "조회수 : " + data.count + "<br>";
				$("#viewHead").html(htmlStr);
				$("#viewBody").html(data.content);
				$("#type").val(data.type);
			},
			error : function(e) {
				alert("error:" + e);
			}
		})
		$.ajax({
					type : "post",
					url : "/Pension_Project/commentView.go",
					data : {
						"bnum" : $("#bnum").val()
					},
					success : function(data) {
						data = $.parseJSON(data);
						var htmlStr = "";
						var bnum = $("#bnum").val();
						for (var i = 0; i < data.length; i++) {
							htmlStr += "<div>";
							htmlStr += "작성자 : " + data[i].name + " 작성일 : "
									+ data[i].date + "<br>";
							htmlStr += data[i].content;
							if ($("#muserid").val() == data[i].userid) {
								htmlStr += "<div class=text-right>"
								htmlStr += "<button onclick = 'commentDel("
										+ data[i].num
										+ ")' type='button' class='btn btn-sm btn-outline-danger'>삭제하기</button></div>";
							}
							htmlStr += "<hr></div>";
						}
						$("#commentBody").html(htmlStr);
					},
					error : function(e) {
						alert("error" + e);
					}
				})

		$("#delBtn")
				.click(
						function() {
							if (confirm("글을 삭제하시겠습니까?")) {
								location.href = "/Pension_Project/boardDelete.go?bnum=${bnum }&btype=${btype}";
							}
						})

		$("#commentWriteBtn")
				.click(
						function() {
							$("#content").val(
									CKEDITOR.instances.writeContent.getData());
							if ($("#content").val() == "") {
								alert("댓글 내용을 입력하세요");
								return false;
							}
							$.ajax({
										type : "post",
										url : "/Pension_Project/commentWrite.go",
										data : {
											"content" : $("#content").val(),
											"bnum" : $("#bnum").val(),
											"muserid" : $("#muserid").val()
										},
										success : function(data) {
											data = $.parseJSON(data);
											var htmlStr = "";
											var bnum = $("#bnum").val();
											for (var i = 0; i < data.length; i++) {
												htmlStr += "<div>";
												htmlStr += "작성자 : "
														+ data[i].name
														+ " 작성일 : "
														+ data[i].date + "<br>";
												htmlStr += data[i].content;
												if ($("#muserid").val() == data[i].userid) {
													htmlStr += "<div class=text-right>"
													htmlStr += "<button onclick = 'commentDel("
															+ data[i].num
															+ ")' type='button' class='btn btn-sm btn-outline-danger'>삭제하기</button></div>";
												}
												htmlStr += "<hr></div>";
											}
											$("#commentBody").html(htmlStr);
											$("#content").val("");
											CKEDITOR.instances.writeContent
													.setData("");
										},
										error : function(e) {
											alert("error" + e);
										}
									})
						})
	})
	function commentDel(cnum) {
		if (confirm("댓글을 삭제하시겠습니까?")) {
			$
					.ajax({
						type : "post",
						url : "/Pension_Project/commentDelete.go",
						data : {
							"bnum" : $("#bnum").val(),
							"cnum" : cnum
						},
						success : function(data) {
							data = $.parseJSON(data);
							var htmlStr = "";
							var bnum = $("#bnum").val();
							for (var i = 0; i < data.length; i++) {
								htmlStr += "<div>";
								htmlStr += "작성자 : " + data[i].name
										+ " 작성일 : " + data[i].date + "<br>";
								htmlStr += data[i].content;
								if ($("#muserid").val() == data[i].userid) {
									htmlStr += "<div class=text-right>"
									htmlStr += "<button onclick = 'commentDel("
											+ data[i].num
											+ ")' type='button' class='btn btn-sm btn-outline-danger'>삭제하기</button></div>";
								}
								htmlStr += "<hr></div>";
							}
							$("#commentBody").html(htmlStr);
						},
						error : function(e) {
							alert("error" + e);
						}
					})
		}
	}
</script>
<body>
	<input type="hidden" name="bnum" id="bnum" value="${bnum }">
	<input type="hidden" name="type" id="type">
	<input type="hidden" name="userid" id="userid" value="${userid }">
	<input type="hidden" name="muserid" id="muserid"
		value="${sessPm.userid }">
	<div class="container my-5">
		<c:if test="${btype==0 }">
			<div class="text-center my-5">
				<h2 class="font-weight-bold">Notice</h2>
				<br>
				<p class="text-secondary small">펜션 공지사항 입니다.</p>
			</div>
		</c:if>
		<div class="container my-5">
			<c:if test="${btype==1 }">
				<div class="text-center my-5">
					<h2 class="font-weight-bold">Qna</h2>
					<br>
					<p class="text-secondary small">객실 예약 및 궁금한점 질문과답변 게시판입니다.</p>
				</div>
			</c:if>
			<div class="container my-5">
				<c:if test="${btype==2 }">
					<div class="text-center my-5">
						<h2 class="font-weight-bold">Review</h2>
						<br>
						<p class="text-secondary small">고객님들의 소중한 이용후기 입니다.</p>
					</div>
				</c:if>
			</div>
		</div>
			<div class="text-center">
				<a type="button" href="/Pension_Project/boardMain.go?btype=0"
					class="btn btn-sm btn-dark">공지사항</a> <a type="button"
					href="/Pension_Project/boardMain.go?btype=1"
					class="btn btn-sm btn-dark">질문과답변</a> <a type="button"
					href="/Pension_Project/boardMain.go?btype=2"
					class="btn btn-sm btn-dark">이용후기</a>
			</div>
		<div class="card small my-5">
			<div class="card-header" id="viewHead"></div>
			<div class="card-body" id="viewBody"></div>
		</div>
		<div class="card small my-5">
			<div class="card-header">댓글</div>
			<div class="card-body" id="commentBody"></div>
			<c:if
				test="${(btype==0 && sessPm.userid!=null) || (btype==2 && sessPm.userid!=null) }">
				<form action="">
					<input type="hidden" id="content" name="content">
					<div class="form-group">
						<textarea id="writeContent" name="writeContent"
							class="form-control"></textarea>
					</div>
				</form>
				<div class="text-right">
					<button type="button"
						class="btn btn-sm btn-outline-success mx-1 my-1"
						id="commentWriteBtn">댓글쓰기</button>
				</div>
			</c:if>
			<c:if
				test="${btype==1 && (sessPm.admin==1 || userid == sessPm.userid)}">
				<form action="">
					<input type="hidden" id="content" name="content">
					<div class="form-group">
						<textarea id="writeContent" name="writeContent"
							class="form-control"></textarea>
					</div>
				</form>
				<div class="text-right">
					<button type="button"
						class="btn btn-sm btn-outline-success mx-1 my-1"
						id="commentWriteBtn">댓글쓰기</button>
				</div>
			</c:if>
		</div>
		<div class="text-right">
				<c:if test="${(btype==0 && sessPm.admin == 1) || ((btype==1 || btype==2) && sessPm.userid == userid && sessPm.admin==0)}">
					<a href="/Pension_Project/boardUpdate.go?bnum=${bnum }&btype=${btype}"
						id="updateBtn" class="btn btn-sm btn-outline-info">수정하기</a>
				</c:if>			
				<c:if test="${sessPm.admin == 1 || ((btype==1 || btype==2) && sessPm.userid == userid)}">
					<button type="button" id="delBtn"
						class="btn btn-sm btn-outline-danger">삭제하기</button>
				</c:if>
				<a href="/Pension_Project/boardMain.go?btype=${btype }"
					class="btn btn-sm btn-outline-secondary">목록으로</a>			
		</div>
	</div>
	<%@include file="/main/footer.jsp"%>
	<script>
		CKEDITOR.replace("writeContent");
		CKEDITOR.config.enterMode = CKEDITOR.ENTER_BR;
	</script>
</body>
</html>