package com.happy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happy.dao.NoticeDao;
import com.happy.vo.Notice;

@Service
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	NoticeDao dao;

	@Override
	public List<Notice> getAllnotice() {
		return dao.getAllnotice();
	}

	@Override
	public int addNotice(Notice notice) {
		return dao.addNotice(notice);
	}

	@Override
	public int editNotice(Notice notice) {
		return dao.editNotice(notice);
	}

	@Override
	public Notice getNotice(String no) {
		return dao.getNotice(no);
	}

	@Override
	public int deleteNotice(String no) {
		return dao.deleteNotice(no);
	}


}
