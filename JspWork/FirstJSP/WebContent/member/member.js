var exp=/^[0-9]{3}-[0-9]{4}-[0-9]{4}$/
//정규식 /^: 시작                $/: 종료
$(function(){
	$("#send").click(function(){
		if($("#name").val()==""){
			alert("이름을 입력하세요");
			$("#name").focus();
			return false;
		}if($("#userid").val()==""){
			alert("아이디를 입력하세요");
			$("#userid").focus();
			return false;
		}if($("#pwd").val()==""){
			alert("암호를 입력하세요");
			$("#pwd").focus();
			return false;
		}if($("#pwd").val()!=$("#pwd_check").val()){
			alert("비밀번호가 일치하지 않습니다.");
			$("#pwd_check").focus();
			return false;
		}
		if(!$("#phone").val().match(exp)){
			// match 함수 : 정규표현식과 형식을 비교하는 함수
			alert("전화번호 입력 양식이 아닙니다.")
			$("#phone").focus();
			return false;
		}
		$("#frm").submit();		
	})
	//중복체크버튼
	$("#idBtn").click(function(){
		window.open("idCheck.jsp","","width=800 height=500");		
	})
	//아이디 중복확인 체크
	$("#idCheckBtn").click(function(){
		if($("#userid").val()==""){
			alert("아이디를 입력하세요");
			$("#userid").focus();
			return false;
		}		
		$.ajax({
			type : "post",
			url : "idCheckPro.jsp",
			data : {"userid" : $("#userid").val()},
			success:function(data){
				if(data.trim()=="YES"){
					alert("사용 가능한 아이디입니다.");
					$(opener.document).find("#userid").val($("#userid").val());
					$(opener.document).find("#uid").val($("#userid").val());
					self.close();
				}else if(data.trim()=="NO"){
					alert("사용 불가능한 아이디입니다.");
				}
			},
			error : function(e){
				alert("error"+e);
			}
		})
	})	
	
})

function del(userid)
{
	//load
	$("table tbody").load("memberDeletePro.jsp?userid="+userid,
			function(data){
				data = $.parseJSON(data);
				var htmlStr = "";
				for(var i=0;i<data.length;i++){
					htmlStr+="<tr>";
					htmlStr+="<td>"+data[i].name+"</td>";
					htmlStr+="<td>"+data[i].userid+"</td>";
					htmlStr+="<td>"+data[i].phone+"</td>";
					htmlStr+="<td>"+data[i].email+"</td>";
					htmlStr+="<td>"+data[i].admin+"</td>";
					htmlStr+="<td onclick=del('"+data[i].userid+"')>삭제</td>";					
					htmlStr+="</tr>"						
				}
				$("table tbody").html(htmlStr);			
		
	})	
}