package com.gzw.wuye.bean;

public class Notices {
	
	
	private String time;//消息的时间
	
	private String theme;//消息主题
	
	private String text;//消息正文
	
	private int id;//消息在数据库中的id
	
	private int mark;//消息是否已读的标记，1 已读 0未读
	
	private String author;//消息的来源
	

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
