package com.hnb.ticket;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/ticket")
public class TicketController {
	private static final Logger logger = LoggerFactory.getLogger(TicketController.class);

	@Autowired
	TicketVO ticketVO;
	@Autowired
	TicketServiceImpl ticketService;
	
	@RequestMapping("/home")
	public String home() {
		return "/Ticket";
	}
	
	@RequestMapping("/movieSelect")
	public Model movieSelect(Model model, String movie, String theater, String date) {
		List theaterList = null, dateList = null, timeList = null;
		if (theater==null && date!=null) {
			System.out.println("극장널");
			theaterList = ticketService.getTheaterListByMD(movie,date);
		} else if (theater!=null && date==null) {
			System.out.println("날짜널");
			dateList = ticketService.getShowDateListByMT(movie,theater);
		} else if (theater==null && date==null) {
			System.out.println("다널");
			theaterList = ticketService.getTheaterListByM(movie);
			dateList = ticketService.getShowDateListByM(movie);
		} else if (movie!=null&&theater!=null&&date!=null) {
			timeList = ticketService.getTimeList(movie, theater, date);
		}
		List movieSelectList = new ArrayList();
		logger.info("극장 {}",theaterList);
		logger.info("날짜 {}",dateList);
		movieSelectList.add(theaterList);
		movieSelectList.add(dateList);
		movieSelectList.add(timeList);
		model.addAttribute("movieSelectList", movieSelectList);
		return model;
	}
	
	@RequestMapping("/theaterSelect")
	public Model theaterSelect(Model model, String movie, String theater, String date) {
		List movieListRate = null, movieListAsc = null, dateList = null, timeList = null; 
		if (movie==null && date!=null) {
			movieListRate = ticketService.getMovieRateByTD(theater,date);
			movieListAsc = ticketService.getMovieAscByTD(theater,date);
		} else if (movie!=null && date==null) {
			dateList = ticketService.getShowDateListByMT(movie, theater);
		} else if (movie==null && date==null) {
			movieListRate = ticketService.getMovieRateByT(theater);
			movieListAsc = ticketService.getMovieAscByT(theater);
			dateList = ticketService.getShowDateListByT(theater);
		} else if (movie!=null&&theater!=null&&date!=null) {
			timeList = ticketService.getTimeList(movie, theater, date);
		}
		List theaterSelectList = new ArrayList();
		theaterSelectList.add(movieListRate);
		theaterSelectList.add(movieListAsc);
		theaterSelectList.add(dateList);
		theaterSelectList.add(timeList);
		
		model.addAttribute("theaterSelectList", theaterSelectList);
		return model;
	}
	
	@RequestMapping("/dateSelect")
	public Model dateSelect(Model model, String movie, String theater, String date) {
		List movieListRate = null, movieListAsc = null, theaterList = null , timeList = null; 
		if (movie==null && theater!=null) {
			movieListRate = ticketService.getMovieRateByTD(theater,date);
			movieListAsc = ticketService.getMovieAscByTD(theater,date);
		} else if (movie!=null && theater==null) {
			theaterList = ticketService.getTheaterListByMD(movie,date);
		} else if (movie==null && theater==null) {
			movieListRate = ticketService.getMovieRateByD(date);
			movieListAsc = ticketService.getMovieAscByD(date);
			theaterList = ticketService.getTheaterListByD(date);
		} else if (movie!=null&&theater!=null&&date!=null) {
			timeList = ticketService.getTimeList(movie, theater, date);
		}
		List dateSelectList = new ArrayList();
		dateSelectList.add(movieListRate);
		dateSelectList.add(movieListAsc);
		dateSelectList.add(theaterList);
		dateSelectList.add(timeList);
		System.out.println(timeList);
		model.addAttribute("dateSelectList", dateSelectList);
		return model;
	}
	
	@RequestMapping("/choiceseat")
	public Model choiceseat(Model model, String movie, String theater, String date, String time) {
		int result = 0;
		String filmNumber = ticketService.getFilmNumberBy(movie);
		System.out.println(filmNumber);
		ticketVO.setFilmNumber(filmNumber);
		ticketVO.setTheaterName(theater);
		ticketVO.setDate(date);
		ticketVO.setRoomName(time.split(" ")[0]);
		ticketVO.setStartTime(time.split(" ")[1]);
		System.out.println(ticketVO.getFilmNumber());
		System.out.println(ticketVO.getTheaterName());
		System.out.println(ticketVO.getDate());
		System.out.println(ticketVO.getRoomName());
		System.out.println(ticketVO.getStartTime());
		if(result == 1) {
			model.addAttribute("result", "success");
		} else {
			model.addAttribute("result", "fail");
		}
		return model;
	}
}
