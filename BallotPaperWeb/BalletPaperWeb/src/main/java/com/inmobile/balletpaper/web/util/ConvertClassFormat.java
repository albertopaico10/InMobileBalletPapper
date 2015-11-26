package com.inmobile.balletpaper.web.util;

import java.util.ArrayList;
import java.util.List;

import com.inmobile.balletpaper.web.bean.BalletPaperDTO;
import com.inmobile.balletpaper.web.bean.RegisterUserDTO;
import com.inmobile.balletpaper.web.bean.canonical.ImageDTO;
import com.inmobile.balletpaper.web.bean.canonical.balletpaper.BalletPaperResponse;
import com.inmobile.balletpaper.web.bean.canonical.image.ImageRequest;
import com.inmobile.balletpaper.web.bean.canonical.image.ImageResponse;
import com.inmobile.balletpaper.web.bean.canonical.user.UserRequest;

public class ConvertClassFormat {
	
	public static UserRequest convertWebToServiceUser(RegisterUserDTO registerUser){
		UserRequest userRequest=new UserRequest();
		userRequest.setEmail(registerUser.getUser());
		userRequest.setPassword(UtilMethods.encriptValue(registerUser.getPassword()));
		userRequest.setTypeCustomer("USER_WEB");
		return userRequest;
	}
	
	public static List<BalletPaperDTO> convertListServiceToListBalletPaper(List<BalletPaperResponse> listComplaintResponse){
		List<BalletPaperDTO> webReturnList=new ArrayList<BalletPaperDTO>();
		for(BalletPaperResponse beanService:listComplaintResponse){
			BalletPaperDTO beanWeb=new BalletPaperDTO();
			beanWeb.setIdComplient(beanService.getIdComplient());
			beanWeb.setAddressComplaint(beanService.getAddressComplaint());
			beanWeb.setDateComplaint(beanService.getDateComplaint());
			beanWeb.setDistrictComplaint(beanService.getDistrictComplaint());
			beanWeb.setHourComplaint(beanService.getHourComplaint());
			beanWeb.setPlateComplaint(beanService.getPlateComplaint());
			beanWeb.setStatusComplaint(beanService.getStatusComplaint());
			beanWeb.setIdUser(beanService.getIdUser());
			beanWeb.setNameComplaint(beanService.getNameComplaint());
			webReturnList.add(beanWeb);
		}
		return webReturnList;
	}
	
	public static List<ImageDTO> convertServiceToImageDTO(List<ImageResponse> listImageResponse){
		List<ImageDTO> listImageDTO=new ArrayList<ImageDTO>();
		for(ImageResponse beanImageResponse:listImageResponse){
			ImageDTO beanImageDTO=new ImageDTO();
			beanImageDTO.setId(beanImageResponse.getIdImage());
			beanImageDTO.setNameFile(beanImageResponse.getNameFileImage());
			beanImageDTO.setRootFile(beanImageResponse.getRootImage());
			listImageDTO.add(beanImageDTO);
		}
		return listImageDTO;
	}
	
	public static ImageRequest convertWebToServiceImage(int idComplaint,String root){
		ImageRequest beanImage=new ImageRequest();
		beanImage.setIdComplient(idComplaint);
		beanImage.setRootProject(root);
		return beanImage;
	}
	
}
