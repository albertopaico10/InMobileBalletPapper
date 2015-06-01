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
		request.setAttribute("idUserLogin", returnServiceBean.getIdUser());
		request.getSession().setAttribute("typeUser", returnServiceBean.getTypeUser());

		return new ModelAndView(returnServiceBean.getReturnPage());   
		
	}
	
	@RequestMapping("closeSession.htm")
	public String findUser(final HttpServletRequest request,final ModelMap model) {
		RegisterUserDTO logueoBean=new RegisterUserDTO();
		model.addAttribute("loginUsuForm", logueoBean);
		return CommonConstants.Page.REDIRECT_LOGIN_PAGE;
	}
	
	@RequestMapping("showLogin.htm")
    public String show(final HttpServletRequest request,final ModelMap model) {
		System.out.println("inside inicio htm");
		final RegisterUserDTO tableUser=new RegisterUserDTO();
		model.addAttribute("loginUsuForm", tableUser);
		return CommonConstants.Page.REDIRECT_LOGIN_PAGE; 
	}
}
