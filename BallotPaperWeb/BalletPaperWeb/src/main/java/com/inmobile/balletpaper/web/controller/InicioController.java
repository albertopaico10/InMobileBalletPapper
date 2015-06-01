package com.inmobile.balletpaper.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inmobile.balletpaper.web.bean.RegisterUserDTO;
import com.inmobile.balletpaper.web.util.CommonConstants;

@Controller
public class InicioController {

	@RequestMapping("inicio.htm")
    public String show(final HttpServletRequest request,final ModelMap model) {
		System.out.println("Ruta : "+request.getSession().getServletContext().getRealPath(""));
		return CommonConstants.Page.REDIRECT_INIT_PAGE; 
	}
	
}
