package com.happy.controller;

import java.util.ArrayList;
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

import com.happy.service.QBoardService;
import com.happy.vo.QBoard;
import com.happy.vo.SidoCode;

@CrossOrigin("*")
@RequestMapping("/qboard")
@RestController
public class QBoardRestController {

	@Autowired
	QBoardService service;
	
	@GetMapping(value = "/qboardlist")
	public ResponseEntity<ArrayList<QBoard>> getAllQBoard() {
		ArrayList<QBoard> list=service.getAllQBoard();
		if(list != null && !list.isEmpty()) {
			return new ResponseEntity<ArrayList<QBoard>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(value = "/qboardlist/{no}")
	public ResponseEntity<QBoard> getQBoard(@PathVariable String no) {
		QBoard qboard =service.getQBoard(no);
		if(qboard != null) {
			return new ResponseEntity<QBoard>(qboard, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping(value = "/qboardlist")
	public ResponseEntity<HttpStatus> addQBoard(@RequestBody QBoard qboard, HttpSession session) {
		qboard.setUserid((String)session.getAttribute("id"));
		int x = service.addQBoard(qboard);
		if(x==1) {
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}

	@PutMapping(value = "/qboardlist")
	public ResponseEntity<HttpStatus> editQBoard(@RequestBody QBoard qboard, HttpSession session) {
		qboard.setUserid((String)session.getAttribute("id"));
		int x = service.editQBoard(qboard);
		if(x==1) {
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	@DeleteMapping(value = "/qboardlist/{no}")
	public ResponseEntity<HttpStatus> deleteQBoard(@PathVariable String no) {
		int x= service.deleteQBoard(no);
		if(x==1) {
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
}
