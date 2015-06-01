package com.inmobile.balletpaper.web.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.inmobile.balletpaper.web.bean.ReturnService;
import com.inmobile.balletpaper.web.bean.canonical.balletpaper.ListBalletPaperResponse;
import com.inmobile.balletpaper.web.facade.BalletPaperManager;
import com.inmobile.balletpaper.web.util.CommonConstants;
import com.inmobile.balletpaper.web.util.ConvertClassFormat;
import com.inmobile.balletpaper.web.util.UtilMethods;

@Service
public class BalletPaperManagerImpl implements BalletPaperManager {

	public ReturnService listComplaintByUser(int idUser) {
		ReturnService beanReturn=new ReturnService();
		RestTemplate restTemplate=new RestTemplate();
		System.out.println("Valor Request : "+UtilMethods.fromObjectToString(idUser));
		System.out.println("URL : "+CommonConstants.URLService.URL_LIST_COMPLAINT_BY_USER);
		ListBalletPaperResponse beanList=restTemplate.getForObject(CommonConstants.URLService.URL_LIST_COMPLAINT_BY_USER+idUser, ListBalletPaperResponse.class);
		if(CommonConstants.Response.RESPONSE_SUCCESS_LIST_COMPLAINT.equals(beanList.getCodeResponse())){
			beanReturn.setReturnPage(CommonConstants.Page.REDIRECT_COMPLAINT_BY_USER);
			beanReturn.setListBalletPapper(ConvertClassFormat.convertListServiceToListBalletPaper(beanList.getListComplaintResponse()));
			System.out.println("Cantidad de filas : "+beanReturn.getListBalletPapper().size());
		}else{
			beanReturn.setReturnPage(CommonConstants.Page.REDIRECT_COMPLAINT_BY_USER);
		}
		beanReturn.setMessages(beanList.getCodeResponse());
		beanReturn.setIdUser(idUser);
		return beanReturn;
	}
	
	public ReturnService listComplaintByDistrict(int idUser) {
		ReturnService beanReturn=new ReturnService();
		RestTemplate restTemplate=new RestTemplate();
		System.out.println("Valor Request : "+UtilMethods.fromObjectToString(idUser));
		System.out.println("URL : "+CommonConstants.URLService.URL_LIST_COMPLAINT_BY_DISTRICT);
		ListBalletPaperResponse beanList=restTemplate.getForObject(CommonConstants.URLService.URL_LIST_COMPLAINT_BY_DISTRICT+idUser, ListBalletPaperResponse.class);
		if(CommonConstants.Response.RESPONSE_SUCCESS_LIST_COMPLAINT.equals(beanList.getCodeResponse())){
			beanReturn.setReturnPage(CommonConstants.Page.REDIRECT_COMPLAINT_BY_DISTRICT);
			beanReturn.setListBalletPapper(ConvertClassFormat.convertListServiceToListBalletPaper(beanList.getListComplaintResponse()));
			System.out.println("Cantidad de filas : "+beanReturn.getListBalletPapper().size());
		}else{
			beanReturn.setReturnPage(CommonConstants.Page.REDIRECT_COMPLAINT_BY_DISTRICT);
		}
		beanReturn.setMessages(beanList.getCodeResponse());
		beanReturn.setIdUser(idUser);
		return beanReturn;
	}

}
