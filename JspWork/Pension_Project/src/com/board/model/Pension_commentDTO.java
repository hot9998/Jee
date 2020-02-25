package com.board.model;

public class Pension_commentDTO {
	private String userid, content, date, name;
	private int num, bnum;

	public String getUserid() {
		return userid == null ? "" : userid.trim();
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getContent() {
		return content == null ? "" : content.trim();
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date == null ? "" : date.trim();
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getBnum() {
		return bnum;
	}

	public void setBnum(int bnum) {
		this.bnum = bnum;
	}
	public String getName() {
		return name == null ? "" : name.trim();
	}

	public void setName(String name) {
		this.name = name;
	}

}
