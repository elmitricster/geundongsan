package com.happy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.happy.service.NewsService;
import com.happy.vo.News;

@CrossOrigin("*")
@RequestMapping("/news")
@RestController
public class NewsRestController {

	@Autowired
	NewsService service;
	
	@GetMapping(value = "/newslist")
	public ResponseEntity<List<News>> getAllNews() {
		List<News> list=service.getAllnews();
		if(list != null && !list.isEmpty()) {
			return new ResponseEntity<List<News>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(value = "/newslist/{no}")
	public ResponseEntity<News> getNews(@PathVariable String no) {
		News news =service.getNews(no);
		if(news != null) {
			return new ResponseEntity<News>(news, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
}
