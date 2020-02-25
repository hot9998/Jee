package com.board;

public class CommentVO {
	private int cnum;  //comment 글번호
	private String userid; //작성자아이디
	private String regdate; // 작성일
	private String msg;  //내용
	private int bnum; //board 글번호
	
	public int getCnum() {
		return cnum ;
	}
	public void setCnum(int cnum) {
		this.cnum = cnum;
	}
	public String getUserid() {
		return userid == null ? "" : userid.trim();
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getRegdate() {
		return regdate == null ? "" : regdate.trim();
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	public String getMsg() {
		return msg == null ? "" : msg.trim();
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getBnum() {
		return bnum;
	}
	public void setBnum(int bnum) {
		this.bnum = bnum;
	}

}
