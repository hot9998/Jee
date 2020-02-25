<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Guestbook Insert</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
	$(function() {
		$("#submit").click(sendIt);
		$("#btnSearch").click(function(){
			getData(1,$("#field").val(),$("#word").val());
			$("#word").val("");
		})
		getData(1,"","");
	})
	function sendIt() {
		var name = $("#name").val();
		var content = $("#content").val();
		var grade = $("input:radio[name=grade]:checked").val();
		var postString = "name=" + name + "&content=" + content + "&grade="
				+ grade;
		$.ajax({
			type : "post",
			url : "create.gb",
			data : postString, //{"name":$("#name").val() 등 여러개}
			success : function(d) {
				$("#results").html(d);
				$("#name").val("");
				$("#content").val("");
				$("#name").focus();
			},
			beforeSend : showRequest,
			error : function(e) {
				alert("error" + e);
			}
		})
	}
	function showRequest() {
		if (!$("#name").val()) {
			alert("글쓴이를 입력하세요.")
			$("#name").focus();
			return false;
		}
		if (!$("#content").val()) {
			alert("내용을 입력하세요.")
			$("#content").focus();
			return false;
		}
		if ($("input:radio[name=grade]:checked").length == 0) {
			alert("평가를 해주세요");
			return false;
		}
		return true;
	}
	function getData(pageNum,field,word) {
		$.get("list.gb", {
			"pageNum" : pageNum,
			"field" : field,
			"word" : word
		}, function(data) {
			$("#results").html(data);
		})
	}

	// 입력한 글자 수 카운트	
	function textCount(obj, target) {
		//var len = obj.value.length;
		var len = $("#" + obj.id).val().length;
		if (obj.size < len) {
			alert("글자 수 초과");
			return false;
		}
		$("#" + target).text(len);
	}

	// 상세 보기
	function fview(num) {
		$.get("view.gb", {
			"num" : num
		}, function(d) {
			data = $.parseJSON(d);
			var htmlStr = "";
			htmlStr += "<table border=1 width=500px align=center>";
			htmlStr += "<tr>";
			htmlStr += "<td width='100px'>이름</td>";
			htmlStr += "<td>" + data.name + "</td></tr>"
			htmlStr += "<tr>";
			htmlStr += "<td>내용</td>";
			htmlStr += "<td>" + data.content + "</td></tr>"
			htmlStr += "<tr>";
			htmlStr += "<td>평가</td>";
			htmlStr += "<td>" + data.grade + "</td></tr>"
			htmlStr += "<tr>";
			htmlStr += "<td>작성일</td>";
			htmlStr += "<td>" + data.created + "</td></tr>"
			htmlStr += "<tr>";
			htmlStr += "<td>IP주소</td>";
			htmlStr += "<td>" + data.ipaddr+ "</td></tr>"
			htmlStr += "</table>"
			$("#views").html(htmlStr);			
		})
	}
function fdelete(num, name){
	if(confirm("["+name+"]의 게시물을 삭제할까요?")){
		$.get("delete.gb?num="+num,function(data){
			$("#results").html(data);
		})
	}
}
</script>
</head>
<body>
<div align="right">
<c:if test="${login==null }">
<a href="adminLogin.gb">[관리자]</a>
</c:if>
<c:if test="${login!=null }">
${login }님 반갑습니다. 
<a href="logout.gb">로그아웃</a>
</c:if>
 </div>
	<form method="post" action="create.gb">
		<table align="center" width=900px>
			<tr>
				<td align="center">글쓴이<input type="text" id="name" name="name"
					size=20 onkeyup="textCount(this,'nameCount')"> *20글자 이내 (<span id="nameCount" style="color: red;">0</span>)
				</td>
			</tr>
			<tr>
				<td align="center">내용<input type="text" id="content"
					name="content" size=70 onkeyup="textCount(this,'contentCount')"> *70글자 이내 (<span id="contentCount"
					style="color: red;">0</span>)
				</td>
			</tr>
			<tr>
			<td align="center">평가
			<input type="radio" name="grade" value="excellent" checked="checked">아주잘함(excellent)
			<input type="radio" name="grade" value="good">잘함(good)
			<input type="radio" name="grade" value="normal">보통(normal)
			<input type="radio" name="grade" value="fail">노력(fail)
			</td>			
			</tr>
			<tr>
			<td colspan="2" align="center">
			<input type="submit" value="submit 전송">			
			<input type="button" value="id버튼 입력" id="submit">		
			</td>
			</tr>		
		</table>
	</form>
	<br>	
	<div align="right">
	<form name="search" id="search">
	<select name="field" id="field">
	<option value="name">이름</option>
	<option value="content">내용</option>
	</select>
	<input type="text" name="word" id="word">
	<input type="button" value="찾기" id="btnSearch">
	</form>
	</div>
	<br><br><hr>
	<div align="center" id="results"></div>
	<hr>
	<div id="views"></div>
</body>
</html>