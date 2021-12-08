package com.happy.dao;

import java.util.Map;

import com.happy.vo.Member;

public interface MemberDao {
	public int registerMember(Member member);
	public Member login(Map<String,String> map);
	public Member getMember(String userId);
	public int modifyMember(Member member);
	int  deleteMember(String userId);
}
