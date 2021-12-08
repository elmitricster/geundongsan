package com.happy.service;


import java.util.List;

import com.happy.vo.News;

public interface NewsService {
	public List<News> getAllnews();	
	public News getNews(String no);
	
}
