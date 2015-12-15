package com.hnb.event;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hnb.global.Command;
import com.hnb.global.CommandFactory;
import com.hnb.member.MemberServiceImpl;
import com.hnb.member.MemberVO;

@Controller
@RequestMapping("/event")
public class EventController {
	private static final Logger logger = LoggerFactory.getLogger(EventController.class);
	
	@Autowired MemberVO member;
	@Autowired MemberServiceImpl service;
	
/*	
 	SOAP 방식처리 (url에 ? 있는경우)
  	<a href="${context}/event/boardList?pageNo=${status.index}">
  	
  	@RequestMapping("/boardList")
	public String boardList(
			@RequestParam(value="pageNo", defaultValue="1")String pageNo,
			@RequestParam(value="column", required=false)String column,
			@RequestParam(value="searchKey", required=false)String searchKey,
			Model model
			) {
		logger.info("boardList() 입장");
		logger.info("페이지번호 {}", pageNo);
		logger.info("컬럼  번호 {}", column);
		logger.info("검색된키값 {}", searchKey);
		Command command = CommandFactory.list(pageNo);
		model.addAttribute("member", service.getList(command));
		model.addAttribute("count", service.count());
		model.addAttribute("pageNo", pageNo);
		return "event/BoardList.jsp";
	}*/
	
	
	// RESTful 방식 (url 에 {} 이 있어서 @PathVariable 사용한 경우)
	@RequestMapping("/boardList/{pageNo}")
	public String boardList(
			@PathVariable(value="pageNo")String pageNo,
			Model model
			) {
		logger.info("boardList() 입장");
		logger.info("페이지번호 {}", pageNo);
		Command command = CommandFactory.list(pageNo);
		model.addAttribute("member", service.getList(command));
		model.addAttribute("count", service.count());
		model.addAttribute("pageNo", pageNo);
		return "event/BoardList.tiles";
	}
	
	@RequestMapping("/memberSearch/{pageNo}")
	public String memberSearch(
			@PathVariable("pageNo")String pageNo,
			@RequestParam("column")String column,
			@RequestParam("keyword")String keyword,
			Model model
			) {
		logger.info("memberSearch() 입장");
		logger.info("페이지번호 {}", pageNo);
		logger.info("컬럼  번호 {}", column);
		logger.info("검색된키값 {}", keyword);
		Command command = CommandFactory.search(column, keyword, pageNo);
		List<MemberVO> list = service.searchByKeyword(command);
		model.addAttribute("member", list);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("column", column);
		model.addAttribute("keyword", keyword);
		model.addAttribute("count", service.countByKeyword(command));
		return "event/BoardSearch.tiles";
	}
}
