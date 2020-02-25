<%@page import="com.board.BoardVO"%>
<%@page import="com.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script   src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
$(function(){
	$.ajax({   //boardView.jsp 로드되면 실행
		url :"commentList.jsp?num="+$("#num").val(),
		type:"get",
		success:function(d){
			d=$.parseJSON(d);
			var htmlStr ="";
			htmlStr +="<table>";
			for(var i=0; i<d.length;i++){
				htmlStr +="<tr>";
				htmlStr +="<td>"+d[i].msg +"</td>";
				htmlStr +="<td>"+d[i].userid +"</td>";
				htmlStr +="<td>"+d[i].regdate +"</td>";
				htmlStr +="</tr>";
			}
			htmlStr +="</table>";
			$("#area").html(htmlStr);
		}
	});
	$("#commentBtn").click(function(){ //댓글추가
		$. ajax({
				url : "commentInsert.jsp",
				type :"post",
				data: {"msg" : $("#msg").val(), "num" :$("#num").val()},
				success: function(d){
					if(d.trim()==1){  //로그인 안됨
						alert("로그인하세요");
					  // location.href="../member/loginForm.jsp";
				location.href="http://localhost:8888/FirstJSP/member/loginForm.jsp"
					}else{  //로그인 됨
						d=$.parseJSON(d);
						var htmlStr ="";
						htmlStr +="<table>";
						for(var i=0; i<d.length;i++){
							htmlStr +="<tr>";
							htmlStr +="<td>"+d[i].msg +"</td>";
							htmlStr +="<td>"+d[i].userid +"</td>";
							htmlStr +="<td>"+d[i].regdate +"</td>";
							htmlStr +="</tr>";
						}
						htmlStr +="</table>";
						$("#area").html(htmlStr);
					}
					
				}
		});
	});
});

</script>
<%
request.setCharacterEncoding("utf-8");
int num = Integer.parseInt(request.getParameter("num"));
BoardDAO dao =BoardDAO.getInstance();
BoardVO vo =dao. boardView(num);
int ref = vo.getRef();
int re_step = vo.getRe_step();
int re_level = vo.getRe_level();
%>
</head>
<body>
<input type="hidden" name="num" id='num' value="<%=num %>">
<table width="500" border="1">   
  <tr  align="center">
    <td >글번호</td>  <td ><%=vo.getNum()%></td>
    <td  >조회수</td>  <td ><%=vo.getReadcount() %></td>
  </tr>
  <tr align="center" >
    <td  >작성자</td>  <td ><%=vo.getWriter() %></td>
    <td  >작성일</td>  <td ><%=vo.getReg_date() %></td>
  </tr>
  <tr align="center">
    <td >글제목</td> <td  colspan="3"><%=vo.getSubject() %></td>
  </tr>
  <tr align="center">
    <td  >글내용</td>   <td  colspan="3"><%=vo.getContent() %></td>
  </tr>
  <tr height="30">      
    <td colspan="4"  align="right" > 
	  <input type="button" value="글수정"  
	  onclick="location.href='updateForm.jsp?num=<%=vo.getNum() %>'"> 
	  <input type="button" value="글삭제"  
	  onclick="location='deleteForm.jsp?num=<%=vo.getNum()%>'" >
   <input type="button" value="답글쓰기" 
   onclick="location.href='writeForm.jsp?num=<%=num%>&ref=<%=ref%>&re_step=<%=re_step%>&re_level=<%=re_level%>'">	 
   <input type="button" value="글목록" 
	  onclick="location.href='list.jsp'">
    </td>
  </tr>
</table> 
<br><br><br><br>
<div align="center">
	<textarea rows="5" cols="50" id="msg"></textarea>
	<input type="button" value="댓글쓰기" id="commentBtn">
</div>
<div id="area"></div>

</body>
</html>