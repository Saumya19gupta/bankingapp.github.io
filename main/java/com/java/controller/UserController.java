package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.entity.Account;
import com.java.entity.User;
import com.java.service.AccountService;
import com.java.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("mybank/login")
public class UserController 
{
	@Autowired private UserService userService;
	@Autowired private AccountService accountService;
	
	@GetMapping("login-form")
	public String getLoginFormView(HttpSession ses)
	{
		if(ses.getAttribute("username")!=null)
			return "redirect:/mybank";
		return "login/user-login";
	}
	@GetMapping("registration-form")
	public String getRegistrationFormView()
	{
		return "login/user-registration";
	}
	@PostMapping("registerme")
	public String registerUser(User user,Model model)
	{
		long accountno=userService.saveUser(user);
		model.addAttribute("name",user.getName());
		model.addAttribute("accountno",accountno);
		return "login/registration-success";
	}
	@PostMapping("authenticate-user")
	public String authenticateUser(String userid,String password,Model model,HttpSession ses)
	{
		User user=userService.getUser(userid);
		if(user!=null)
		{
			if(user.getPassword().equals(password))
			{
				ses.setAttribute("username",user.getName());
				Account account=accountService.getAccountByUserId(userid);
				ses.setAttribute("accountno",account.getAccountno());
				return "redirect:/mybank";
			}
			model.addAttribute("uid",userid);
			model.addAttribute("msg","Entered password is wrong");
			return "login/user-login";
		}
		model.addAttribute("uid",userid);
		model.addAttribute("msg","Entered userid does not exist");
		return "login/user-login";
	}
	@GetMapping("logout")
	public String logoutUser(HttpSession ses,Model model)
	{
		model.addAttribute("username",ses.getAttribute("username"));
		ses.invalidate();
		return "login/logout";
	}
}
