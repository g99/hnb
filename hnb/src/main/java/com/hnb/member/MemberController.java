package com.hnb.member;

import javax.jws.WebParam.Mode;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.hnb.movie.MovieVO;
@Controller
@SessionAttributes("user") /* 세션명을 user로 명명 */
@RequestMapping("/member")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberVO member;
	@Autowired
	MemberServiceImpl service;
	
	@RequestMapping("/provision")
	public String provision() {
		return "/member/provision";
	}
	
	@RequestMapping("/join_member")
	public Model joinMember(Model model, String id, 
			String password, String name, String birth, 
			String addr, String gender, String email, 
			String phone) {
		logger.info("가입 id : {}", id);
		logger.info("가입 패스워드 : {}", password);
		logger.info("가입 이름 : {}", name);
		logger.info("가입 생년 : {}", birth);
		logger.info("가입 주소 : {}", addr);
		logger.info("가입 성별 : {}", gender);
		logger.info("가입 이메일 : {}", email);
		logger.info("가입 전화번호 : {}", phone);
		
		member.setId(id);
		member.setPassword(password);
		member.setName(name);
		member.setBirth(birth);
		member.setAddr(addr);
		member.setGender(gender);
		member.setEmail(email);
		member.setPhone(phone);
		
		int result = service.join(member);
		if (result == 1) {
			logger.info("회원가입 성공");
			model.addAttribute("result", "success");
			model.addAttribute("name", member.getName());
		} else {
			logger.info("회원가입 실패");
			model.addAttribute("result", "fail");
		}
		
		return model;
	}
	
	@RequestMapping("/join_Result")
	public String joinResult() {
		return "/member/join_Result";
	}
	
	@RequestMapping("/logout")
	public String logout(Model model, SessionStatus status) {
		logger.info("Member : 로그아웃 진입");
	/*	session.invalidate(); 와 같은의미*/
		status.setComplete();
		model.addAttribute("result", "success");
		return "global/default.tiles";
	}
	/* SessionStatus를 이용해서 세션을 생성 */
	@RequestMapping("/login")
	public @ResponseBody MemberVO login(
			Model model, String id, @RequestParam("pw")String password
			) {
		logger.info("Member : 로그인 진입");
		logger.info("유저아이디 : {}", id);
		logger.info("유저비  번 : {}", password);
		member = service.login(id, password);
		// 로그인 실패시
		if (member == null) {
		} else {
		// 로그인 성공시
			logger.info("로그인 성공!!!");
			model.addAttribute("user", "member");
		}
		return member;
	}
	
	@RequestMapping("/check_Overlap")
	public Model checkOverlap(Model model, String id) {
		logger.info("컨트롤러 / 중복체크로 진입");
		if (service.searchById(id).getId() == null) {
			model.addAttribute("result", "usable");
		} else {
			model.addAttribute("result", "unusable");
		}
		model.addAttribute("id", id);
		return model;
	}
	
	@RequestMapping("/mypage")
	public String mypage() {
		logger.info("mypage() 진입");
		String page = "member/mypage.tiles";
		logger.info("페이지 : {}", page);
		return page;
	}
}
