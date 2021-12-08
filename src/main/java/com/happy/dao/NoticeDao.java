package com.happy.dao;

import java.util.List;

import com.happy.vo.Notice;

public interface NoticeDao {
	public List<Notice> getAllnotice();

	public int addNotice(Notice notice);
	
	public int editNotice(Notice notice);
	
	public Notice getNotice(String no);
	
	public int deleteNotice(String no);
}
