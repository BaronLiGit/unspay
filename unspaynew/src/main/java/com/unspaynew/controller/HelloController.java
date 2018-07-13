package com.unspaynew.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
	public static final Logger log = LoggerFactory.getLogger(HelloController.class);
	@Value("#{configProperties['accountId']}")
	private String accountId;
	
	@Value("${first}")
	private String first;
	
	@Value("${key}")
	private String key;

	@RequestMapping(value="/hello")
	public ModelAndView spring(){
		log.info("预支付接口（首次）"+accountId);
		log.error(key);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("hello");
		mv.addObject("msg", "my:"+accountId+key);
		return mv;
	}
}
