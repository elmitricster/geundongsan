package com.happy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happy.dao.NewsDao;
import com.happy.vo.News;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	NewsDao dao;
	
	@Override
	public List<News> getAllnews() {
		return dao.getAllnews();
	}

	@Override
	public News getNews(String no) {
		return dao.getNews(no);
	}
}
