package com.tcs.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tcs.business.MyCustomService;

@Controller
public class LoginController {
	
	@Autowired
	private MyCustomService customService;
	
	@RequestMapping(value = "/")
	public ModelAndView start(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView model = new ModelAndView("redirect:/hello");
		return model;
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView admin(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView model = new ModelAndView("admin");
		model.addObject("title", "Spring Security - Sample Override");
		model.addObject("message", "This page is only for the Admin guys !!");
		return model;
	}
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public ModelAndView hello(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView model = new ModelAndView("hello");
		model.addObject("title", "Spring Security - Sample Override");
		model.addObject("message", "This page is for both the Users and the Admins !");
		return model;
	}
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public ModelAndView welcome(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView model = new ModelAndView("welcome");
		model.addObject("title", "Spring Security - Sample Override");
		model.addObject("message", "This Welcome page is for both Users and Admin guys !");
		return model;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginGet(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "logout", required = false) String logout,
			@RequestParam(value = "error", required = false) String error)
	{
		ModelAndView model = new ModelAndView("login");
		if(logout != null)
		{
			model.addObject("logout", "You have logged out successfully !!");
		}
		if(error != null)
		{
			model.addObject("error", "Invalid Username and Password !!");
		}
		return model;
	}
	
	/*@RequestMapping(value = "/login_check", method = RequestMethod.POST)
	public ModelAndView loginPost(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password)
	{
		ModelAndView model = null;
		System.out.println("In Controller");
		UserDetails user = customService.loadUserByUsername(username);
		System.out.println("User is : " + user);
		if(user.getPassword().equals(password))
		{
			System.out.println("Validated");
			model = new ModelAndView("redirect:/hello");
		}
		else
		{
			model = new ModelAndView("redirect:/login");
		}  
		return model;
	}  */
	
/*	@RequestMapping(value = "/login/error", method = RequestMethod.GET)
	public ModelAndView loginError(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView model = new ModelAndView("login");
		model.addObject("error", "Invalid Username and Password !!");
		return model;
	}  */
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView error(HttpServletRequest request, HttpServletResponse response)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails user = (UserDetails) auth.getPrincipal();
		String username = user.getUsername();
		ModelAndView model = new ModelAndView("403");
		model.addObject("username", username);
		return model;
	}


}
