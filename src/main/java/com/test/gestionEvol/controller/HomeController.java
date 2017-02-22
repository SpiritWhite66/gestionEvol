package com.test.gestionEvol.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@Value("${conference.name:World}")
	private String conference;

	@RequestMapping("/home")
	public String home()
	{
		return "ListEvol";
	}
}
