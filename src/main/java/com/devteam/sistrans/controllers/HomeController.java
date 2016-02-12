package com.devteam.sistrans.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class HomeController {

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(HttpServletRequest request) {
		System.out.println("Home URI: /");
		return "home";
	}

}