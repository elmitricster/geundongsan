package com.happy.dao;

import java.util.List;

import com.happy.vo.News;

public interface NewsDao {
	public List<News> getAllnews();	
	public News getNews(String no);
}
