package com.hnb.main;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller // servlet은 더이상 사용하지 않고 컨트롤러가 자동으로 매핑함
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {  // 모델은 request와 response를 모두 포함
		logger.info("메인컨트롤러 home 메소드 진입 {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "global/default.tiles"; // dispatcher와 response.getWriter().print()를 포함
					   // home은 경로를 의미함
	}
}
