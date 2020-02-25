<%@page import="com.board.BoardVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
request.setCharacterEncoding("utf-8");
BoardDAO dao = BoardDAO.getInstance();
String pageNum = request.getParameter("pageNum");
if(pageNum ==null) pageNum ="1";
int currentPage = Integer.parseInt(pageNum);

String word ="";
String field ="";
if(request.getParameter("word")!=null){
	word = request.getParameter("word");
	field = request.getParameter("field");
}

int pageSize = 5; // 한 화면에 보여지는 게시물 수
// 총 개수 23: 현재페이지 :2
// 1페이지 -> 1,2,3,4,5 / 2페이지 -> 6,7,8,9,10 /3페이지 ->11,12,13,14,15
int startRow=(currentPage-1)*pageSize+1;
int endRow=currentPage* pageSize;
ArrayList<BoardVO> arr = dao.getBoardList(field, word,startRow,endRow);
int count = dao.getBoardCount(field, word);
int number= count-(currentPage-1)*pageSize;
%>
</head>
<body>
글목록 (전체글 :<%=  count %>)
<div align="right">
<a href=" writeForm.jsp">글쓰기</a>	
</div>
<table width=500 border=1>
<tr>
	<td> 번호 </td>
	<td> 글번호 </td>
	<td> 제목 </td>
	<td> 작성자 </td>
	<td> 작성일 </td>
	<td> 조회수 </td>
    <td> IP </td>
</tr>
<%
	for(BoardVO bo : arr){
%>
	<tr>
		<td> <%=number--%> </td>
		<td> <%=bo.getNum()%> </td>
		<td>
<%
			int wid =0;
  			if(bo.getRe_level()>0){
  				wid = 5*(bo.getRe_level());
 %> 				
  				<img src="images/level.gif" width="<%=wid %>">
  				<img src="images/re.gif" >
 <% 				
  			}else {
 %> 				
  				<img src="images/level.gif" width="<%=wid %>">
 <% 				
  			}
%>		
		<a href="boardView.jsp?num=<%=bo.getNum()%>"><%=bo.getSubject()%></a> </td>
		<td> <%=bo.getWriter()%></td>
		<td> <%=bo.getReg_date() %> </td>
		<td> <%=bo.getReadcount()%> </td>
	    <td> <%=bo.getIp() %> </td>
    </tr>
<%		
	}
%>
</table>
<form action="list.jsp" name="search" method="get">
   			<select name="field" size=1 >
    			<option value="subject"> 제 목
    			<option value="writer"> 작성자
    		</select>
  		 <input type="text" size=16 name="word"  >
   		<input type="submit"  value="찾기">
</form>

<div align="center">
<%
	if(count>0){  //count=23;
	
		int pageCount = count/pageSize +(count%pageSize==0?0:1);
		int pageBlock = 3 ; // 보여지는 페이지 수 [이전] 456[다음]
		int startPage = (int)((currentPage-1)/pageBlock)*pageBlock+1; //7
		int endPage = startPage+pageBlock-1; // 6(계산상이지만 사실은 허수)
		if(endPage >pageCount ){
			endPage = pageCount;  //endPage = 5;
		}
		if(startPage > pageBlock){  //4>3
%>			
			<a href="list.jsp?pageNum=<%=startPage-pageBlock%>&field=<%=field%>&word=<%=word%>">[이전]</a>
<%			
		}
		for(int i=startPage; i<=endPage;i++){
			if(i==currentPage){  //현재 보이는게 자신 페이지 링크 없음
%>		
					[<%=i%>]		
<%				
			}else {
%>
		<a href="list.jsp?pageNum=<%=i%>&field=<%=field%>&word=<%=word%>"><%=i%></a>			
<%	
			}
		}
		if(endPage<pageCount){ //3 < 5
%>			
			<a href="list.jsp?pageNum=<%=startPage+pageBlock%>&field=<%=field%>&word=<%=word%>">[다음]</a>
<%			
		}
	}

%>
</div>


</body>
</html>