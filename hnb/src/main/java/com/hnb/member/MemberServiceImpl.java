package com.hnb.member;

import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnb.global.Command;
import com.hnb.mapper.MemberMapper;
@Service
public class MemberServiceImpl  implements MemberService{
	private static MemberService instance = new MemberServiceImpl();
	public static MemberService getInstance() {
		return instance;
	} 
	@Autowired private SqlSession sqlSession;
	/**
	 * DML
	 */
	// 회원가입
	@Override
	public int join(MemberVO member) {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		return mapper.insert(member);
	}
	//비번변경
	@Override
	public int change(MemberVO o) {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		return mapper.update(o);
	}
	 // 회원탈퇴
	@Override
	public int remove(String userid) {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		return mapper.delete(userid);
	}
	/**
	 * DQL
	 */
	// 로그인
	@Override
	public MemberVO login(String id, String pass) {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		MemberVO loginMember = null;
		loginMember = mapper.selectOneBy(id);
		if (loginMember.getId() == null) {
			return null;
		} 
		if (loginMember.getPassword().equals(pass)) {
			return loginMember;
		}else{
			return null;
		}
	}
	//전체 회원수 
	@Override
	public int count() {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		return mapper.count();
	}
	//ID로 회원검색
	@Override
	public MemberVO searchById(String id) {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		return mapper.selectOneBy(id);
	}
	// 검색어로 검색
	@Override
	public List<MemberVO> searchBySearchword(String domain,String searchword) {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		return mapper.selectSomeBy(domain, searchword);
	}
	// 전체 회원목록 
	@Override
	public List<MemberVO> getList(Command command) {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		return mapper.selectAll(command);
	}
}
