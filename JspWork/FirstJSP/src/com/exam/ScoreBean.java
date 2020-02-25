package com.exam;

public class ScoreBean {
	private String name;
	private int kor, math, eng;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGrade() {
		String grade="";
		switch ((kor+math+eng)/3/10) {
		case 10:
		case 9:grade="A";break;
		case 8:grade="B";break;
		case 7:grade="C";break;
		default:grade="F";
		}
		return grade;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getTotal() {
		return kor+math+eng;
	}	
	public int getAvg() {		
		return (kor+math+eng)/3;
	}

}
