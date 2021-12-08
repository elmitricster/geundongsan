package com.happy.service;

import java.util.Map;

import com.happy.vo.Member;

public interface MemberService {
//	회원가입
	public int registerMember(Member member);
	
//	로그인
	public Member login(Map<String, String> map);
	
//	회원정보 수정을 위한 회원의 모든 정보 얻기
	public Member getMember(String userId);
	
//	회원정보 수정
	public int modifyMember(Member member);
	
//	회원탈퇴
	public int deleteMember(String userId);
}
