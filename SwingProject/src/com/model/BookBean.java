package com.model;

public class BookBean {
	private int num, price;
	private String writer, title, indate, outdate, gubun;
	
	public BookBean() {
		
	}

	public BookBean(String title, String writer, String indate, String outdate, String gubun, int price) {
		super();
		this.price = price;
		this.writer = writer;
		this.title = title;
		this.indate = indate;
		this.outdate = outdate;
		this.gubun = gubun;
	}

	public String getGubun() {
		return gubun;
	}

	public void setGubun(String gubun) {
		this.gubun = gubun;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIndate() {
		return indate;
	}

	public void setIndate(String indate) {
		this.indate = indate;
	}

	public String getOutdate() {
		return outdate;
	}

	public void setOutdate(String outdate) {
		this.outdate = outdate;
	}

}
