package com.happy.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happy.dao.MemberDao;
import com.happy.vo.Member;

@Service
public class MemberServiceImpl implements MemberService {
	
	
	@Autowired
	MemberDao dao;

	@Override
	public int registerMember(Member member) {
		return dao.registerMember(member);
	}

	@Override
	public Member login(Map<String, String> map) {
		// TODO Auto-generated method stub
//		Map<String,String> map=new HashMap<>();
//		map.put("userid",userId);
//		map.put("userpwd", userPwd);
		return dao.login(map);
	}

	@Override
	public Member getMember(String userId) {
		return dao.getMember(userId);
	}

	@Override
	public int modifyMember(Member member) {
		// TODO Auto-generated method stub
		return dao.modifyMember(member);
	}

	@Override
	public int deleteMember(String userId) {
		// TODO Auto-generated method stub
		return dao.deleteMember(userId);
	}
}
