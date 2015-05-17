package com.rest.service.inmobile.util;

import com.rest.service.inmobile.bean.complient.ComplientRequest;
import com.rest.service.inmobile.bean.image.ImageRequest;
import com.rest.service.inmobile.bean.user.UserRequest;
import com.rest.service.inmobile.hibernate.bean.Complaint;
import com.rest.service.inmobile.hibernate.bean.ComplaintImage;
import com.rest.service.inmobile.hibernate.bean.Image;
import com.rest.service.inmobile.hibernate.bean.User;

public class ConvertClass {

	public static User convertUserRequestToDataBase(UserRequest beanRequest){
		User userDataBase=new User();
		userDataBase.setEmail(beanRequest.getEmail());
		userDataBase.setPasswordUser(beanRequest.getPassword());
		userDataBase.setTypeUser(Integer.parseInt(beanRequest.getTypeCustomer()));
		userDataBase.setNamesUser(beanRequest.getNamesUser());
		userDataBase.setLastNameUser(beanRequest.getLastNameUser());
		userDataBase.setDniUser(beanRequest.getDniUser());
		userDataBase.setRecordingDevice(beanRequest.getRecordingDevice());
		return userDataBase;
	}
	
	public static Image convertImageToDataBase(ImageRequest beanImageRequest){
		Image beanImage=new Image();
		beanImage.setCategoryImage(beanImageRequest.getCategoryImage());
		//--Get bytes
		byte[] imageByte=UtilMethods.hexStringToByteArray(beanImageRequest.getHexImage());
		beanImage.setImg(imageByte);
		return beanImage;
	}
	
	public static Complaint convertComplientToDataBase(ComplientRequest beanRequest){
		Complaint beanComplient=new Complaint();
		beanComplient.setComments(beanRequest.getCommentsAdditional());
		beanComplient.setCompleteAddress(UtilMethods.descriptValue(beanRequest.getAddressFull()));
		beanComplient.setIdUser(beanRequest.getIdUser());
		beanComplient.setLatitude(beanRequest.getLatitude());
		beanComplient.setLongitude(beanRequest.getLongitude());
		beanComplient.setNumberPlate(beanRequest.getNumberPlate());
		return beanComplient;
	}
	
	public static ComplaintImage convertComplientImageToDataBase(int idUser,int idImage,int idComplaint){
		ComplaintImage beanComplaintImage=new ComplaintImage();
		beanComplaintImage.setIdComplaint(idComplaint);
		beanComplaintImage.setIdImage(idImage);
		beanComplaintImage.setIdUser(idUser);
		return beanComplaintImage;
	}
}
