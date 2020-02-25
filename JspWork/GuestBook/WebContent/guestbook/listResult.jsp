<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div align="right">
게시물 수 : ${count }
</div>
<table border="1" align="center" width="900px">
	<tr>
		<th>NO_seq</th>
		<th>번호</th>
		<th>이름</th>
		<th>평가</th>
		<th>날짜</th>
		<c:if test="${login!=null }">
		<th>삭제</th>
		</c:if>
	</tr>
	<c:forEach items="${lists}" var="dto" varStatus="st">
		<tr align="center">
			<td>${dto.num }</td>
			<td>${rowNo-st.index}</td>
			<td><a href="javascript:fview(${dto.num })">${dto.name }</a></td>
			<td>${dto.grade }</td>
			<td>${dto.created }</td>
			<c:if test="${login!=null }">
			<td><a href="javascript:fdelete(${dto.num },'${dto.name }')">삭제</a></td>
			</c:if>
		</tr>
	</c:forEach>	
</table>
<div align="center">
<c:if test="${pu.startpage > pu.pageblock }"> <!-- 이전 -->
<a href="javascript:getData(${pu.startpage-pu.pageblock },'${field }','${word }')">[이전]</a>
</c:if>
<c:forEach begin="${pu.startpage }" end="${pu.endpage }" var="i"> <!-- 숫자 페이지 -->
<c:if test="${i==pu.currentPage }">
<c:out value="${i }"/> <!-- %{i} -->
</c:if>
<c:if test="${i!=pu.currentPage }">
<a href="javascript:getData(${i },'${field}','${word}')">${i }</a>
</c:if>
</c:forEach>
<c:if test="${pu.endpage < pu.totpage }"> <!-- 다음 -->
<a href="javascript:getData(${pu.endpage+1},'${field }','${word }')">[다음]</a>
</c:if>
</div>
