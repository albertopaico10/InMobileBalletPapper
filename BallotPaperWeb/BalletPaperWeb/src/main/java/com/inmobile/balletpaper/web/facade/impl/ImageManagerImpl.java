package com.inmobile.balletpaper.web.facade.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.inmobile.balletpaper.web.bean.canonical.ImageDTO;
import com.inmobile.balletpaper.web.bean.canonical.image.ImageRequest;
import com.inmobile.balletpaper.web.bean.canonical.image.ListImageResponse;
import com.inmobile.balletpaper.web.facade.ImageManager;
import com.inmobile.balletpaper.web.util.CommonConstants;
import com.inmobile.balletpaper.web.util.ConvertClassFormat;
import com.inmobile.balletpaper.web.util.UtilMethods;

@Service
public class ImageManagerImpl implements ImageManager {

	public List<ImageDTO> getImageFromService(int idComplaint,String root) {
		List<ImageDTO> listImage=new ArrayList<ImageDTO>();
		RestTemplate restTemplate=new RestTemplate();
		System.out.println("Valor Request : "+UtilMethods.fromObjectToString(idComplaint));
		System.out.println("URL : "+CommonConstants.URLService.URL_LIST_IMAGE_COMPLAINT);
		ImageRequest beanRequestImage=ConvertClassFormat.convertWebToServiceImage(idComplaint, root);
		System.out.println("REQUEST : "+UtilMethods.fromObjectToString(beanRequestImage));
		ListImageResponse listImageResponse=restTemplate.postForObject(CommonConstants.URLService.URL_LIST_IMAGE_COMPLAINT,beanRequestImage, ListImageResponse.class);
		System.out.println("Valor Response : "+UtilMethods.fromObjectToString(listImageResponse));
		if(CommonConstants.Response.RESPONSE_SUCCESS_GET_IMAGE.equals(listImageResponse.getCodeResponse())){
			listImage=ConvertClassFormat.convertServiceToImageDTO(listImageResponse.getListImageResponse());
		}
		return listImage;
	}

}
