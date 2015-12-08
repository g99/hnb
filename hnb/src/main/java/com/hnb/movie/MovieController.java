package com.hnb.movie;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/movie")
public class MovieController {
	private static final Logger logger = LoggerFactory.getLogger(MovieController.class);
	
	@Autowired
	MovieVO movie;
	@Autowired
	MovieServiceImpl service;
	
	@RequestMapping("/home")
	public String home() {
		logger.info("MovieController-home() 진입");
		return "movie/Movie";
	}
	
	@RequestMapping("/movie_info")
	public String movieInfo(Model model) {
		logger.info("MovieController-movieList() 진입");
		List<MovieVO> list = service.getList();
		logger.info("영화 리스트 {}", list);
		model.addAttribute("movieList", list);
		return "movie/movie_info";
	}
	
	@RequestMapping("/movie_name/{movieName}")
	public String memberName(
			Model model, 
			@PathVariable("movieName") String name
			) {
		logger.info("MovieController-memberList() 진입");
		logger.info("영화 아이디 {}",name);
		movie = service.searchByName(name);
		logger.info("영화제목 : {}", movie.getFilmNumber());
		model.addAttribute("movie", movie);
		
		return "movie/movie_name";
	}
	
	@RequestMapping("/movie_Cut")
	public String movieCut(Model model,	String filmNumber) {
		logger.info("MovieController-movieCut() 진입");
		logger.info("영화 아이디 {}",filmNumber);
		movie = service.searchByName(filmNumber);
		String cut = movie.getCut();
		String[] arr = cut.split("/");
		logger.info("스틸컷 {}", arr);
		model.addAttribute("arr", arr);
		return "movie/movie_Cut";
	}
	
	@RequestMapping("/movie_tra")
	public String movieTra(Model model, String filmNumber) {
		logger.info("MovieController-movieTra() 진입");
		logger.info("영화 아이디 {}",filmNumber);
		movie = service.searchByName(filmNumber);
		String trailer = movie.getTrailer();
		String[] arr = trailer.split("/");
		logger.info("트레일러 : {}", arr);
		model.addAttribute("arr", arr);
		return "movie/movie_tra";
	}
	
	@RequestMapping("/movie_basic")
	public String movieBasic(Model model, String filmNumber) {
		logger.info("MovieController-movieBasic() 진입");
		logger.info("영화 아이디 {}",filmNumber);
		movie = service.searchByName(filmNumber);
		logger.info("영화제목 : {}",movie.getFilmName());
		model.addAttribute("moive", movie);
		return "movie/movie_basic";
	}
	
	@RequestMapping("/movie_chart")
	public String movieChart(
				Model model
			) {
		logger.info("MovieController-movieChart() 진입");
		List<MovieVO> list = service.getList();
		logger.info("영화 리스트 {}", list);
		model.addAttribute("list", list);
		return "movie/movie_chart";
	}
}
