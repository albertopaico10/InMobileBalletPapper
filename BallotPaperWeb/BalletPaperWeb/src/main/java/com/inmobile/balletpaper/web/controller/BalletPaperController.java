package com.inmobile.balletpaper.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inmobile.balletpaper.web.bean.ReturnService;
import com.inmobile.balletpaper.web.bean.canonical.ImageDTO;
import com.inmobile.balletpaper.web.facade.BalletPaperManager;
import com.inmobile.balletpaper.web.facade.ImageManager;
import com.inmobile.balletpaper.web.util.CommonConstants;
import com.inmobile.balletpaper.web.util.UtilMethods;

@Controller
public class BalletPaperController {
	
	@Autowired
	private BalletPaperManager balletPaperManager;
	
	@Autowired
	private ImageManager imageManager;
	
	@RequestMapping("formBalletPaperUser.htm")
	public String showPage(final HttpServletRequest request,final ModelMap model) {
		System.out.println("Redirigir pagina web");
		final int idUser = Integer.parseInt(request.getParameter("idUser"));
		request.setAttribute("messages", "");
		request.setAttribute("idUserLogin", idUser);
		HttpSession session = request.getSession();
		request.setAttribute("typeUser", session.getAttribute("typeUser"));
		return CommonConstants.Page.REDIRECT_COMPLAINT_BY_USER;
	}
	
	@RequestMapping("findByUser.htm")
	public String findUser(final HttpServletRequest request,final ModelMap model) {
		System.out.println("Consultar papeletas");
		final int idUser = Integer.parseInt(request.getParameter("idUser"));
		
		ReturnService returnServiceBean=balletPaperManager.listComplaintByUser(idUser);
		System.out.println("Valor  : "+returnServiceBean.getMessages());
		request.setAttribute("messages", returnServiceBean.getMessages());
		request.setAttribute("listComplaint", returnServiceBean.getListBalletPapper());
		request.setAttribute("idUserLogin", idUser);
		
		HttpSession session = request.getSession();
		request.setAttribute("typeUser", session.getAttribute("typeUser"));
		
		return CommonConstants.Page.REDIRECT_COMPLAINT_BY_USER;
	}
	
	@RequestMapping("formBalletPaperDistrict.htm")
	public String showPageDistrict(final HttpServletRequest request,final ModelMap model) {
		System.out.println("Redirigir pagina web - Distrito");
		final int idUser = Integer.parseInt(request.getParameter("idUser"));
		request.setAttribute("messages", "");
		request.setAttribute("idUserLogin", idUser);
		
		HttpSession session = request.getSession();
		request.setAttribute("typeUser", session.getAttribute("typeUser"));
		
		return CommonConstants.Page.REDIRECT_COMPLAINT_BY_DISTRICT;
	}
	
	@RequestMapping("findByDistrict.htm")
	public String findDistrict(final HttpServletRequest request,final ModelMap model) {
		System.out.println("Consultar papeletas - Distrito");
		final int idUser = Integer.parseInt(request.getParameter("idUser"));
		
		ReturnService returnServiceBean=balletPaperManager.listComplaintByDistrict(idUser);
		System.out.println("Valor  : "+returnServiceBean.getMessages());
		request.setAttribute("messages", returnServiceBean.getMessages());
		request.setAttribute("listComplaint", returnServiceBean.getListBalletPapper());
		request.setAttribute("idUserLogin", idUser);
		
		return returnServiceBean.getReturnPage();
	}
	
	
	@RequestMapping(value="/getImageByComplaint.htm",method=RequestMethod.GET)
	public @ResponseBody String getListImageFromComplaintId(@RequestParam String id,final HttpServletRequest request){
		System.out.println("getListDistrict-->Id Complaint : "+id+"***"+request.getSession().getServletContext().getRealPath(""));
		String rootProject=request.getSession().getServletContext().getRealPath("");
		List<ImageDTO> listSpecificDistrict=imageManager.getImageFromService(Integer.parseInt(id),rootProject);
		System.out.println("Cantidad de Distritos : "+listSpecificDistrict.size());
		return UtilMethods.fromObjectToString(listSpecificDistrict);
	}
	
	
}
