package com.board.model;

public class Pension_BoardDTO {
	private String title, content, date, userid, name;
	private int num, type, count;

	public String getTitle() {
		return title == null ? "" : title.trim();
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getName() {
		return name == null ? "" : name.trim();
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	public String getUserid() {
		return userid == null ? "" : userid.trim();
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

}
