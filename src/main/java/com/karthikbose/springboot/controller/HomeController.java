package com.karthikbose.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@Autowired
	private ResourceBundleMessageSource messages;
	
	@GetMapping("/") 
	public String greet() {
		return messages.getMessage("greet", null, LocaleContextHolder.getLocale());
	}
}
