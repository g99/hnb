package com.hnb.movie;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/movie")
public class MovieController {
	private static final Logger logger = LoggerFactory.getLogger(MovieController.class);
	
	@Autowired
	MovieVO movie;
	@Autowired
	MovieServiceImpl service;
	
	@RequestMapping("/home")
	// 반환형이 스트링인경우 prefix 와 suffix가 추가된 경로로 전달된다.
	// 그 외의 경우(ajax)는 이벤트를 전송한 경로에 다시 전송해준다. 
	public String home() { 
		logger.info("MovieController-home() 진입");
		return "movie/Movie.tiles";
	}
	
	@RequestMapping("/movie_info")
	// 리스트를 전달하려면 Model을 전달하는 방법이 가장 편리
	public Model movieInfo(Model model) {
		logger.info("MovieController-movieList() 진입");
		List<MovieVO> list = service.getList();
		logger.info("영화 리스트 {}", list);
		model.addAttribute("movieList", list);
		return model;
	}
	
	@RequestMapping("/movie_name/{movieName}")
	// 객체 하나를 전송할때는 @ResponseBody를 활용하는 것이 편리
	public @ResponseBody MovieVO memberName(
			@PathVariable("movieName") String name
			) {
		logger.info("MovieController-memberList() 진입");
		logger.info("영화 아이디 {}",name);
		movie = service.searchByName(name);
		logger.info("영화제목 : {}", movie.getFilmNumber());
		
		return movie;
	}
	
	@RequestMapping("/movie_Cut")
	public Model movieCut(Model model,	String filmNumber) {
		logger.info("MovieController-movieCut() 진입");
		logger.info("영화 아이디 {}",filmNumber);
		movie = service.searchByName(filmNumber);
		String cut = movie.getCut();
		String[] arr = cut.split("/");
		logger.info("스틸컷 {}", arr);
		model.addAttribute("arr", arr);
		return model;
	}
	
	@RequestMapping("/movie_tra")
	public Model movieTra(Model model, String filmNumber) {
		logger.info("MovieController-movieTra() 진입");
		logger.info("영화 아이디 {}",filmNumber);
		movie = service.searchByName(filmNumber);
		String trailer = movie.getTrailer();
		String[] arr = trailer.split("/");
		logger.info("트레일러 : {}", arr);
		model.addAttribute("arr", arr);
		return model;
	}
	
	@RequestMapping("/movie_basic")
	public @ResponseBody MovieVO movieBasic(String filmNumber) {
		logger.info("MovieController-movieBasic() 진입");
		logger.info("영화 아이디 {}",filmNumber);
		movie = service.searchByName(filmNumber);
		logger.info("영화제목 : {}",movie.getFilmName());
		return movie;
	}
	
	@RequestMapping("/movie_chart")
	@ModelAttribute
	public Model movieChart(Model model) {
		logger.info("MovieController-movieChart() 진입");
		List<MovieVO> list = service.getList();
		logger.info("영화 리스트 {}", list);
		model.addAttribute("list", list);
		return model;
	}
}
