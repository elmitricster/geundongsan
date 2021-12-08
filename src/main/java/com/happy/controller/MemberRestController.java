package com.happy.controller;

import java.util.Map;

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

import com.happy.service.MemberService;
import com.happy.vo.Member;

@RestController
@RequestMapping("/member")
@CrossOrigin("*")
public class MemberRestController {

	@Autowired
	MemberService service;
	
	@GetMapping(value = "/getMember")
	public ResponseEntity<Member> getMember(HttpSession session){
		String userid= (String) session.getAttribute("id");
		Member member = service.getMember(userid);
		return new ResponseEntity<Member>(member, HttpStatus.OK);
	}
	
	@PostMapping(value = "/loginProcess")
	public ResponseEntity<Member> loginProcess(@RequestBody Map<String, String> map, HttpSession session) {
		Member member = service.login(map);
		
		if(member != null) {
			session.setAttribute("id", member.getUserid());
			member.setUserpwd(null);
			return new ResponseEntity<Member>(member, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(value = "/logout")
	public ResponseEntity<HttpStatus> logout(HttpSession session) {
		session.setAttribute("id", null);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	@PostMapping(value = "/registerProcess")
	public ResponseEntity<HttpStatus> registerProcess(@RequestBody Member member) {
		System.out.println(member.getUserpwd()+"++++++++");
		int result=service.registerMember(member);
		if(result==1) {
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	@PutMapping(value = "/modifyProcess")
	public ResponseEntity<HttpStatus> modifyProcess(@RequestBody Member member) {
		int x=service.modifyMember(member);
		if(x==1)
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	@DeleteMapping(value = "/deleteProcess")
	public ResponseEntity<HttpStatus> deleteProcess(HttpSession session) {
		service.deleteMember((String)session.getAttribute("id"));
		session.setAttribute("id", null);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}
