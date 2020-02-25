<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>객실정보</title>
<%@include file="/main/header.jsp" %>
</head>
<script>
$(function(){
	$.ajax({
		type:"post",
		url:"/Pension_Project/pensionMain.go",
		success:function(data){
			$("#results").html(data);					
		},
		error:function(e){
			alert("error"+e);
		}
	})		
})
function pensionDelete(rnum) {
	if(confirm("정말 삭제하시겠습니까?")){
		location.href="/Pension_Project/pensionDelete.go?rnum="+rnum;
	}
}
</script>
<body>
	<div class="container my-5">
		<div class="text-center mt-5">
			<h2 class="font-weight-bold">Room</h2>
			<br>
			<p class="text-secondary small">객실정보 입니다.</p>
		</div>
		<div id="results" class="row"></div>
		<c:if test="${sessPm.admin==1 }">
			<div class="text-right">
				<a href="/Pension_Project/pensionWrite.go" class="btn btn-sm btn-outline-secondary">글쓰기</a>								
			</div>
		</c:if>
	</div>
</body>
<%@include file="/main/footer.jsp"%>
</html>