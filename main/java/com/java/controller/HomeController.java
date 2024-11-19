package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("mybank")
public class HomeController 
{
	@GetMapping("")
	public String getHomeView(HttpSession ses)
	{
		if(ses.getAttribute("username")==null)
		{
			return "login/user-login";
		}
		return "home";
	}
}