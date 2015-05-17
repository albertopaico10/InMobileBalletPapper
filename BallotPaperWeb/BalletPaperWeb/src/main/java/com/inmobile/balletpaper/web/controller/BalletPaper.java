package com.inmobile.balletpaper.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BalletPaper {
	
	@RequestMapping("formBalletPaper.htm")
	public ModelAndView validateUserForm(final HttpServletRequest request,final ModelMap model) {
		
		
		
		return null;
	}
}
