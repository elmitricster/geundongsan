package com.happy.service;


import java.util.List;

import com.happy.vo.Notice;

public interface NoticeService {
	public List<Notice> getAllnotice();

	public int addNotice(Notice notice);
	
	public int editNotice(Notice notice);
	
	public Notice getNotice(String no);
	
	public int deleteNotice(String no);
}
