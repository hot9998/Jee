<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <body>
    <!-- FOOTER -->
    <footer id="main-footer" class="bg-dark text-center mt-5 p-3">
      <div class="container">
        <div class="row">
          <div class="col">
          <button class="btn btn-secondary" id="topBtn">TOP</button>          
            <p class="lead text-primary font-weight-normal mt-5">
              T.010-0000-0000 , 051-000-0000
            </p>
            <p class="text-white font-weight-bold small">
              입금계좌 : 카카오뱅크 | 3333-05-1234567 | 예금주 홍길동
            </p>
            <p class="text-white small">
              주소 : (47291) 부산광역시 부산진구 중앙대로 708
            </p>
            <p class="text-white small">
              펜션명 : OO펜션 | 대표 : 홍길동
            </p>
            <p class="text-secondary small">
              copyright ⓒ.KCH .all right reserved. Design by KCH
            </p>
          </div>
        </div>
      </div>
    </footer>
    <script>
    $("#topBtn").click(function(){
    	$("html, body").animate({scrollTop:0}, 'slow');
    })
    </script>
  </body>
</html>
