package com.rest.service.inmobile.facade.impl;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.service.inmobile.bean.complient.ComplientRequest;
import com.rest.service.inmobile.bean.complient.ComplientResponse;
import com.rest.service.inmobile.bean.image.ImageRequest;
import com.rest.service.inmobile.bean.image.ImageResponse;
import com.rest.service.inmobile.facade.ComplientManager;
import com.rest.service.inmobile.facade.ImageManager;
import com.rest.service.inmobile.facade.ReqRespManager;
import com.rest.service.inmobile.hibernate.ComplientHibernate;
import com.rest.service.inmobile.hibernate.UserHibernate;
import com.rest.service.inmobile.hibernate.bean.Complaint;
import com.rest.service.inmobile.hibernate.bean.RequestResponse;
import com.rest.service.inmobile.hibernate.bean.User;
import com.rest.service.inmobile.util.CommonConstants;
import com.rest.service.inmobile.util.ConvertClass;
import com.rest.service.inmobile.util.MailUtil;

@Service
@Transactional
public class ComplientManagerImpl implements ComplientManager {

	@Autowired
	private ReqRespManager reqRespManager;
	
	@Autowired
	private ImageManager imageManager;
	
	@Autowired
	private ComplientHibernate complientHibernate;
	
	@Autowired
	private UserHibernate userHibernate;
	
	public ComplientResponse saveComplient(ComplientRequest beanRequest) {
		ComplientResponse beanResponse=new ComplientResponse();
		RequestResponse valueReqResp = (RequestResponse) reqRespManager.saveOrUpdate(beanRequest,
				CommonConstants.TypeOperationReqResp.OPERATION_SAVE_COMPLIENT,beanRequest.getIdUser(), 0);
		System.out.println("ID Response : " + valueReqResp.getId());
		
		try {
			//--Save Complaint in Data Base
			Complaint beanComplient=ConvertClass.convertComplientToDataBase(beanRequest);
			beanComplient.setStatus(1);
			int idComplient=complientHibernate.saveComplient(beanComplient);
			
			if(beanRequest.getHexPhoto1()!=null&&!beanRequest.getHexPhoto1().isEmpty()){
				saveImageInDatase(beanRequest.getCategoryImage()+"_1",beanRequest.getHexPhoto1(),beanRequest.getIdUser(),idComplient);
			}
			if(beanRequest.getHexPhoto2()!=null&&!beanRequest.getHexPhoto2().isEmpty()){
				saveImageInDatase(beanRequest.getCategoryImage()+"_2",beanRequest.getHexPhoto2(),beanRequest.getIdUser(),idComplient);
			}
			if(beanRequest.getHexPhoto3()!=null&&!beanRequest.getHexPhoto3().isEmpty()){
				saveImageInDatase(beanRequest.getCategoryImage()+"_3",beanRequest.getHexPhoto3(),beanRequest.getIdUser(),idComplient);
			}
			beanResponse.setCodeResponse(CommonConstants.CodeResponse.CODE_RESPONSE_SUCCESS_COMPLAINT);
			beanResponse.setMessageResponse("Se grabó la primera parte de la denuncia con exito");
			beanResponse.setIdComplient(idComplient);
			
			//Send email to customer.
			User userBean=userHibernate.getUser(String.valueOf(beanRequest.getIdUser()));
			buidlEmailGeneratedComplaint(userBean.getEmail(), idComplient, beanComplient.getCompleteAddress(), beanRequest.getNumberPlate());
		} catch (Exception e) {
			beanResponse.setCodeResponse(CommonConstants.CodeResponse.CODE_RESPONSE_ERROR);
			beanResponse.setMessageResponse(e.getMessage());
		}
		
		reqRespManager.saveOrUpdate(beanResponse,CommonConstants.TypeOperationReqResp.OPERATION_SAVE_COMPLIENT,
				beanRequest.getIdUser(),valueReqResp.getId());
		return beanResponse;
	}
	
	public ImageResponse saveImageInDatase(String category,String hexImage,int idUser,int idComplaint)throws Exception{
		ImageRequest imageRequest=new ImageRequest();
		imageRequest.setCategoryImage(category);
		imageRequest.setHexImage(hexImage);
		imageRequest.setIdUser(idUser);
		imageRequest.setIdComplient(idComplaint);
		ImageResponse imageResponse=imageManager.saveImage(imageRequest);
		return imageResponse;
	}
	
	public void buidlEmailGeneratedComplaint(String emilTo,int idComplaint,String address,String numberPlate)throws MessagingException{
		String body="<html>"
				+ "<body>"
				+ "<p>"
				+ "<b>InMobile Generacion de Denuncia - Test Email</b>"
				+ "</p><br/>"
				+ "<p>Estimo Usario:</p><br/>"
				+ "<p>Se le agradece haber elegido la aplicación</p>"
				+ "<p>Su denuncia fue registrada con exito, aqui el detalle : </p>"
				+ "<p><b>Id Denuncia : </b>"+idComplaint+"</p>"
				+ "<p><b>Lugar de la Infracción : </b>"+address+"</p>"
				+ "<p><b>Placa del Vehiculo : </b>"+numberPlate+"</p>"
				+ "<p><b>Estados de Denuncia : ABIERTA</b></p>"
				+ "<p>Le estaremos informando del proceso de esta denuncia</p>"
				+ "<p><b>Gracias</b></p>"
				+ "</body>"
				+ "</html>";
		MailUtil.sendEmail(emilTo,CommonConstants.Email.SUBJECT_COMPLETE_COMPLAINT,body);
	}

	
}
