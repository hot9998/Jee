<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container my-5">
	<div class="row">
		<c:forEach items="${dto }" var="dto">		
				<div class="col-4">
					<div class="card mb-5">
						<img alt="" src="/Pension_Project/pension/image/${dto.imgname }"
							class="card-img-top card-fluid">
						<div class="card-body text-center">
							<h4>${dto.name }</h4>
							<p class="card-text">객실타입 : ${dto.content }</p>
							<p class="card-text">입실인원 : 기준${dto.sperson}인 /
								최대${dto.mperson }인</p>
							<p class="card-text">성수기가격 : ${dto.peakPrice }원</p>
							<p class="card-text">비수기가격 : ${dto.lowseasonPrice }원</p>
							<c:if test="${sessPm.admin==1 }">							
							<a href="#" onclick="pensionDelete(${dto.num })" class="btn btn-sm btn-outline-danger">삭제하기</a>
							</c:if>
						</div>
					</div>
				</div>		
		</c:forEach>
		</div>
	</div>

</body>
</html>