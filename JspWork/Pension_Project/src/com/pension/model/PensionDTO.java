package com.pension.model;

public class PensionDTO {
	private String name,content,src,imgname;
	private int num,peakPrice,lowseasonPrice,sperson,mperson;
	public String getName() {
		return name == null ? "" : name.trim();
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content == null ? "" : content.trim();
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSrc() {
		return src == null ? "" : src.trim();
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getPeakPrice() {
		return peakPrice;
	}
	public void setPeakPrice(int peakPrice) {
		this.peakPrice = peakPrice;
	}
	public int getLowseasonPrice() {
		return lowseasonPrice;
	}
	public String getImgname() {
		return imgname == null ? "" : imgname.trim();
	}
	public void setImgname(String imgname) {
		this.imgname = imgname;
	}
	public void setLowseasonPrice(int lowseasonPrice) {
		this.lowseasonPrice = lowseasonPrice;
	}
	public int getSperson() {
		return sperson;
	}
	public void setSperson(int sperson) {
		this.sperson = sperson;
	}
	public int getMperson() {
		return mperson;
	}
	public void setMperson(int mperson) {
		this.mperson = mperson;
	}
	

}
