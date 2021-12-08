package com.happy.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.happy.service.NoticeService;
import com.happy.vo.Notice;

@RestController
@RequestMapping("/notice")
@CrossOrigin("*")
public class NoticeRestController {

	@Autowired
	NoticeService service;
	
	@GetMapping(value = "/noticelist")
	public ResponseEntity<List<Notice>> getAllQBoard() {
		List<Notice> list=service.getAllnotice();
		if(list != null && !list.isEmpty()) {
			return new ResponseEntity<List<Notice>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(value = "/noticelist/{no}")
	public ResponseEntity<Notice> getNotice(@PathVariable String no) {
		Notice notice =service.getNotice(no);
		if(notice != null) {
			return new ResponseEntity<Notice>(notice, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping(value = "/noticelist")
	public ResponseEntity<HttpStatus> addNotice(@RequestBody Notice notice, HttpSession session) {
		String id = (String)session.getAttribute("id");
		notice.setUserid(id);
		if (!id.equals("admin")) return new ResponseEntity(HttpStatus.BAD_REQUEST);
		int x = service.addNotice(notice);
		if(x==1) {
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}

	@PutMapping(value = "/noticelist")
	public ResponseEntity<HttpStatus> editNotice(@RequestBody Notice notice, HttpSession session) {
		String id = (String)session.getAttribute("id");
		notice.setUserid(id);
		if (!id.equals("admin")) return new ResponseEntity(HttpStatus.BAD_REQUEST);
		int x = service.editNotice(notice);
		if(x==1) {
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	@DeleteMapping(value = "/noticelist/{no}")
	public ResponseEntity<HttpStatus> deleteNotice(@PathVariable String no, HttpSession session) {
		int x= service.deleteNotice(no);
		if(x==1) {
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
}
