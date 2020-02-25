<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="table small table-bordered my-5">
<thead>
	<tr>		
		<th style="width: 10%">번호</th>
		<th style="width: 40%">제목</th>
		<th style="width: 20%">작성자</th>
		<th style="width: 20%">작성일</th>
		<th style="width: 10%">조회수</th>
	</tr>
</thead>
<tbody>
	<c:forEach items="${lists}" var="dto" varStatus="st">
		<tr>
			<td>${rowNo-st.index}</td>
			<td><a href='/Pension_Project/boardView.go?userid=${dto.userid }&bnum=${dto.num }&btype=${dto.type}'>${dto.title }</a></td>		
			<td>${dto.name }</td>
			<td>${dto.date }</td>	
			<td>${dto.count }</td>
		</tr>
	</c:forEach>
</tbody>	
</table>
<ul class="pagination" style="justify-content: center;">
<c:if test="${pu.startpage > pu.pageblock }"> <!-- 이전 -->
<li class="page-item">
<a href="javascript:getData(${pu.startpage-pu.pageblock },${btype })" class="page-link">이전</a>
</li>
</c:if>
<c:forEach begin="${pu.startpage }" end="${pu.endpage }" var="i"> <!-- 숫자 페이지 -->
<c:if test="${i==pu.currentPage }">
<li class="page-item">
<a class="page-link disabled">${i }</a>
</li>
</c:if>
<c:if test="${i!=pu.currentPage }">
<li class="page-item">
<a href="javascript:getData(${i },${btype })" class="page-link">${i }</a>
</li>
</c:if>
</c:forEach>
<c:if test="${pu.endpage < pu.totpage }"> <!-- 다음 -->
<li class="page-item">
<a href="javascript:getData(${pu.endpage+1},${btype })" class="page-link">다음</a>
</li>
</c:if>
</ul>