package com.hnb.global;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandFactory {
	private static final Logger logger = LoggerFactory.getLogger(CommandFactory.class);

	public static Command list(String pageNo){
		logger.info("페이지 = {}", pageNo);
		return new Command(pageNo);
	}
	
	public static Command search(String column, String keyword, String pageNo){
		logger.info("컬  럼 = {}", column);
		logger.info("검색어 = {}", keyword);
		logger.info("페이지 = {}", pageNo);
		return new Command(column, keyword, pageNo);
	}
}
