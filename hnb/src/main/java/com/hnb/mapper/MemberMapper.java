package com.hnb.mapper;

import java.util.List;

import com.hnb.global.Command;
import com.hnb.member.MemberVO;

public interface MemberMapper {
	public List<MemberVO> selectAll(Command command);
	public int insert(MemberVO o);
	public int update(MemberVO o);
	public List<MemberVO> selectSomeBy(Command command);
	public MemberVO selectOneBy(String userid);
	public int count();
	public int delete(String userid);
	public MemberVO login(String id, String pass);
	public int countSomeBy(Command command);
}
