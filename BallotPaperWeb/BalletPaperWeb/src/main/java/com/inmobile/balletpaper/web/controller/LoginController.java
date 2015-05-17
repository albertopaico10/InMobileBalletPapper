package com.inmobile.balletpaper.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.inmobile.balletpaper.web.bean.RegisterUserDTO;
import com.inmobile.balletpaper.web.bean.ReturnService;
import com.inmobile.balletpaper.web.facade.UserManager;
import com.inmobile.balletpaper.web.util.CommonConstants;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UserManager userManager;	
	
	@RequestMapping("validateUser.htm")
	public ModelAndView validateUserForm(
			@ModelAttribute RegisterUserDTO logueoBean,
			final BindingResult result, final SessionStatus status,
			final HttpServletRequest request,final ModelMap model) {
		ReturnService returnServiceBean = userManager.validateUser(logueoBean);
		
		model.addAttribute("loginUsuForm", logueoBean);
		request.setAttribute("messages", returnServiceBean.getMessages());
		request.setAttribute("messagesSpecific", returnServiceBean.getSpecificMessages());


		return new ModelAndView(returnServiceBean.getReturnPage());   
		
	}
}
