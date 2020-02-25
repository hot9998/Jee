<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>header</title>
    <!-- 부트스트랩, 제이쿼리 링크 -->
    <link rel="stylesheet" href="/Pension_Project/css/bootstrap.min.css" />        
	<script src="/Pension_Project/js/jquery-3.4.1.min.js"></script>
    <script src="/Pension_Project/js/bootstrap.bundle.min.js"></script> 
    <script src="https://cdn.ckeditor.com/4.13.1/standard/ckeditor.js"></script>
    <script>
    function logout() {
    	alert("로그아웃 되었습니다");
    	location.href="/Pension_Project/logout.go";    	
	}
    </script>
  </head>
  <body>
    <!-- 로그인 네비게이션 바 시작 -->
    <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
      <ul class="navbar-nav ml-auto">
        <!-- 로그인 되었는지 확인 후 출력 -->
        <c:if test="${sessPm!=null }">
        <li class="nav-item">
        <b class="nav-link text-white small">     
        <a href="/Pension_Project/memberUpdate.go?userid=${sessPm.userid }">${sessPm.name }</a>님 반갑습니다.</b>
        </li>
        <li class="nav-item"> 
        <b class="nav-link text-white small">
           <c:if test="${sessPm.admin==1 }">[관리자 계정]</c:if>
        <c:if test="${sessPm.admin==0 }">[일반회원]</c:if>
        </b>
        </li>
        <li class="nav-item">        
          <a style="cursor:pointer" onclick="logout()"  class="nav-link text-white small">로그아웃</a>
        </li>
        </c:if> 
        <c:if test="${sessPm==null }">              
        <li class="nav-item">            
          <a href="/Pension_Project/login.go" class="nav-link text-white small">로그인</a>
        </li>
        <li class="nav-item">
          <a href="/Pension_Project/memberInsert.go" class="nav-link text-white small">회원가입</a>
        </li>
        </c:if>        
      </ul>
    </nav>
    <!-- 로그인 네비게이션 바 끝 -->
    <!-- 메뉴 네비게이션 바 시작 -->
    <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
      <!-- 컨테이너 시작 -->
      <div class="container">
        <ul class="navbar-nav">
        <li>
          <a href="/Pension_Project/index.jsp" class="nav-link text-primary h3">펜션</a>
          </li>
        </ul>
        <ul class="navbar-nav ml-auto">
          <li class="nav-item">
            <a href="/Pension_Project/index.jsp" class="nav-link">펜션소개</a>
          </li>
          <li class="nav-item">
            <a href="/Pension_Project/pensionMain.go" class="nav-link">객실정보</a>
          </li>          
          <li class="nav-item">
            <a href="/Pension_Project/boardMain.go?btype=0" class="nav-link">커뮤니티</a>
          </li>
        </ul>
      </div>
      <!-- 컨테이너 끝 -->
    </nav>
    <!-- 메뉴 네비게이션 바 끝 -->
  </body>
</html>
