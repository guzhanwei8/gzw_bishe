package com.gzw.wuye.bean;

public class Notices {
	
	
	private String time;//��Ϣ��ʱ��
	
	private String theme;//��Ϣ����
	
	private String text;//��Ϣ����
	
	private int id;//��Ϣ�����ݿ��е�id
	
	private int mark;//��Ϣ�Ƿ��Ѷ��ı�ǣ�1 �Ѷ� 0δ��
	
	private String author;//��Ϣ����Դ
	

	public Notices() {
		super();
	}



	public Notices(String time, String theme, String text) {
		super();
		this.time = time;
		this.theme = theme;
		this.text = text;
	}
	
	
	
	public String getAuthor() {
		return author;
	}



	public void setAuthor(String author) {
		this.author = author;
	}



	public int getMark() {
		return mark;
	}



	public void setMark(int mark) {
		this.mark = mark;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getTime() {
		return time;
	}



	public void setTime(String time) {
		this.time = time;
	}



	public String getTheme() {
		return theme;
	}



	public void setTheme(String theme) {
		this.theme = theme;
	}



	public String getText() {
		return text;
	}



	public void setText(String text) {
		this.text = text;
	}
	
	
	
	
	

}
