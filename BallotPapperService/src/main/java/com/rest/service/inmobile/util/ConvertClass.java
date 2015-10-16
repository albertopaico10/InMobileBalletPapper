package com.rest.service.inmobile.util;

import java.util.ArrayList;
import java.util.List;

import com.rest.service.inmobile.bean.complient.ComplientRequest;
import com.rest.service.inmobile.bean.complient.ComplientResponse;
import com.rest.service.inmobile.bean.complient.ListComplaintResponse;
import com.rest.service.inmobile.bean.complient.TypeComplaintResponse;
import com.rest.service.inmobile.bean.image.ImageRequest;
import com.rest.service.inmobile.bean.image.ImageResponse;
import com.rest.service.inmobile.bean.systemparam.SystemParamResponse;
import com.rest.service.inmobile.bean.ubigeo.Ubigeo;
import com.rest.service.inmobile.bean.user.UserRequest;
import com.rest.service.inmobile.hibernate.TypeComplaintHibernate;
import com.rest.service.inmobile.hibernate.bean.Complaint;
import com.rest.service.inmobile.hibernate.bean.ComplaintImage;
import com.rest.service.inmobile.hibernate.bean.District;
import com.rest.service.inmobile.hibernate.bean.Image;
import com.rest.service.inmobile.hibernate.bean.SystemParam;
import com.rest.service.inmobile.hibernate.bean.TypeComplaint;
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
		beanComplient.setDistrinctName(beanRequest.getDistrict().toUpperCase());
		beanComplient.setTypeComplaint(9999);
		beanComplient.setAddress(beanRequest.getAddress());
		beanComplient.setCountry(beanRequest.getCountry());
		return beanComplient;
	}
	
	public static ComplaintImage convertComplientImageToDataBase(int idUser,int idImage,int idComplaint){
		ComplaintImage beanComplaintImage=new ComplaintImage();
		beanComplaintImage.setIdComplaint(idComplaint);
		beanComplaintImage.setIdImage(idImage);
		beanComplaintImage.setIdUser(idUser);
		return beanComplaintImage;
	}
	
	public static List<Ubigeo> convertDataBaseDistricttoUbigeo(List<District> listBeanDistrict){
		List<Ubigeo> listBeanUbigeo=new ArrayList<Ubigeo>();
		for(District beanDistrict:listBeanDistrict){
			Ubigeo ubigeoBean=new Ubigeo();
			ubigeoBean.setId(beanDistrict.getId());
			ubigeoBean.setName(beanDistrict.getDistrictName());
			listBeanUbigeo.add(ubigeoBean);
		}
		return listBeanUbigeo;
	}
	
	public static ListComplaintResponse convertFromDataBaseToListComplaint(List<Complaint> listComplaint,TypeComplaintHibernate typeComplaintHibernate){
		ListComplaintResponse beanListComplaintResponse=new ListComplaintResponse();
		List<ComplientResponse> listComplaintResponse=new ArrayList<ComplientResponse>();
		for(Complaint beanComplaint:listComplaint){
			ComplientResponse beanResponseComplaint=new ComplientResponse();
			beanResponseComplaint.setAddressComplaint(beanComplaint.getCompleteAddress());
			beanResponseComplaint.setDateComplaint(UtilMethods.convertFormatString(beanComplaint.getDateCreated(), CommonConstants.FormatDate.FORMAT_DATE_DD_MM_YYYY));
			beanResponseComplaint.setDistrictComplaint(beanComplaint.getDistrinctName());
			beanResponseComplaint.setHourComplaint(UtilMethods.convertFormatString(beanComplaint.getDateCreated(), CommonConstants.FormatDate.FORMAT_HOURS_hh_mm));
			beanResponseComplaint.setIdComplient(beanComplaint.getId());
			beanResponseComplaint.setPlateComplaint(beanComplaint.getNumberPlate());
			beanResponseComplaint.setStatusComplaint(UtilMethods.getStatusComplaint(beanComplaint.getStatus()));
			beanResponseComplaint.setIdUser(String.valueOf(beanComplaint.getIdUser()));
			try {
				if(beanComplaint.getTypeComplaint()!=0){
					TypeComplaint beanTypeComplaint=typeComplaintHibernate.getTypeComplaint(beanComplaint.getTypeComplaint());
					if(beanTypeComplaint!=null){
						beanResponseComplaint.setNameComplaint(beanTypeComplaint.getCategoryComplaint());
						beanResponseComplaint.setTypeComplaint(String.valueOf(beanComplaint.getTypeComplaint()));	
					}
					
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			listComplaintResponse.add(beanResponseComplaint);
		}
		beanListComplaintResponse.setListComplaintResponse(listComplaintResponse);
		return beanListComplaintResponse;
	}
	
	public static ComplientResponse convertFromDataBaseToCompleintResponse(Complaint beanComplaint,String nameTypeComplaint){
		ComplientResponse beanResponseComplaint=new ComplientResponse();
		
		beanResponseComplaint.setAddressComplaint(beanComplaint.getCompleteAddress());
		beanResponseComplaint.setDateComplaint(UtilMethods.convertFormatString(beanComplaint.getDateCreated(), CommonConstants.FormatDate.FORMAT_DATE_DD_MM_YYYY));
		beanResponseComplaint.setDistrictComplaint(beanComplaint.getDistrinctName());
		beanResponseComplaint.setHourComplaint(UtilMethods.convertFormatString(beanComplaint.getDateCreated(), CommonConstants.FormatDate.FORMAT_HOURS_hh_mm));
		beanResponseComplaint.setIdComplient(beanComplaint.getId());
		beanResponseComplaint.setPlateComplaint(beanComplaint.getNumberPlate());
		beanResponseComplaint.setStatusComplaint(UtilMethods.getStatusComplaint(beanComplaint.getStatus()));
		beanResponseComplaint.setIdUser(String.valueOf(beanComplaint.getIdUser()));
		beanResponseComplaint.setNameComplaint(nameTypeComplaint);
		beanResponseComplaint.setTypeComplaint(String.valueOf(beanComplaint.getTypeComplaint()));
		return beanResponseComplaint;
	}
	
	public static ImageResponse convertImageToImageResponse(Image beanImage,String rootFile)throws Exception{
		ImageResponse beanImageResponse=new ImageResponse();
		beanImageResponse.setIdImage(beanImage.getId());
		beanImageResponse.setRootImage(UtilMethods.saveFileInServer(beanImage.getImg(),
				beanImage.getId()+"_"+beanImage.getCategoryImage(),CommonConstants.ImageParameter.FORMAT_JPG,rootFile));
		beanImageResponse.setNameFileImage(beanImage.getId()+"_"+beanImage.getCategoryImage()+CommonConstants.ImageParameter.FORMAT_JPG);
		return beanImageResponse;
	}
	
	public static List<TypeComplaintResponse> convertDataBaseToTypeComplaint(List<TypeComplaint> listTypeComplaint){
		List<TypeComplaintResponse> listTypeComplaintResponse=new ArrayList<TypeComplaintResponse>();
		for(TypeComplaint beanTypeComplaint:listTypeComplaint){
			TypeComplaintResponse beanTypeComplaintResponse=new TypeComplaintResponse();
			beanTypeComplaintResponse.setId(String.valueOf(beanTypeComplaint.getId()));
			beanTypeComplaintResponse.setNameComplaint(beanTypeComplaint.getCategoryComplaint());
			listTypeComplaintResponse.add(beanTypeComplaintResponse);
		}
		return listTypeComplaintResponse;
	}
	
	public static SystemParamResponse convertFromDataBaseToSystemParamResponse(SystemParam beanSystemParam){
		SystemParamResponse beanSystemParamResponse=new SystemParamResponse();
		beanSystemParamResponse.setId(beanSystemParam.getId());
		beanSystemParamResponse.setNameParam(beanSystemParam.getNameParam());
		beanSystemParamResponse.setReasonParam(beanSystemParam.getReasonParam());
		beanSystemParamResponse.setValueParam(beanSystemParam.getValueParam());
		return beanSystemParamResponse;
	}
}
