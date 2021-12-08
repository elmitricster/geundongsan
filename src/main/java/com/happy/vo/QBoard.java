package com.happy.vo;

public class QBoard {
	private String no;
	private String title;
	private String content;
	private String userid;
	private String write_date;

	public QBoard() {
	}

	public QBoard(String no, String title, String content, String userid, String write_date) {
		this.no = no;
		this.title = title;
		this.content = content;
		this.userid = userid;
		this.write_date = write_date;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getWrite_date() {
		return write_date;
	}

	public void setWrite_date(String write_date) {
		this.write_date = write_date;
	}

}
