package com.hnb.admin;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnb.member.MemberServiceImpl;
import com.hnb.member.MemberVO;
import com.hnb.movie.MovieVO;


@Controller
@RequestMapping("/admin")
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired // 어노테이션을 이용해서 root-context.xml에 자동으로 매핑해줌
	MemberServiceImpl service; // 객체를 반환해야 하므로 Impl을 bean으로 넘김 
	@Autowired
	MemberVO member;
	@Autowired
	MovieVO movie;
	
	@RequestMapping("/Admin")
	public String home() {
		logger.info("AdminController-home() 진입");
		return "admin/Admin";
	}
	
	@RequestMapping("/moive_list")
	public String movieList() {
		logger.info("AdminController-movieList() 진입");
		return "";
	}
	
	@RequestMapping("/member_list")
	public String memberList() {
		logger.info("AdminController-memberList() 진입");
		return "admin/member_list";
	}
	
	@RequestMapping("/member_profile")
	public String memberProfile() {
		logger.info("AdminController-memberProfile() 진입");
		return "admin/member_profile";
	}
	
	@RequestMapping("/insert")
	public String insert() {
		logger.info("AdminController-insert() 진입");
		return "admin/insert";
	}
	
	@RequestMapping("/insert2")
	public String insert2() {
		logger.info("AdminController-insert2() 진입");
		return "admin/insert2";
	}
	
	@RequestMapping("/delete")
	public String delete() {
		logger.info("AdminController-delete() 진입");
		return "admin/delete";
	}
}
