package com.rest.service.inmobile.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.service.inmobile.bean.image.ImageRequest;
import com.rest.service.inmobile.bean.image.ImageResponse;
import com.rest.service.inmobile.facade.ImageManager;
import com.rest.service.inmobile.facade.ReqRespManager;
import com.rest.service.inmobile.hibernate.ComplaintImageHibernate;
import com.rest.service.inmobile.hibernate.ImageHibernate;
import com.rest.service.inmobile.hibernate.bean.Complaint;
import com.rest.service.inmobile.hibernate.bean.ComplaintImage;
import com.rest.service.inmobile.hibernate.bean.Image;
import com.rest.service.inmobile.hibernate.bean.RequestResponse;
import com.rest.service.inmobile.util.CommonConstants;
import com.rest.service.inmobile.util.ConvertClass;

@Service
@Transactional
public class ImageManagerImpl implements ImageManager {
	
	@Autowired
	private ReqRespManager reqRespManager;
	
	@Autowired
	private ImageHibernate imageHibernate;
	
	@Autowired
	private ComplaintImageHibernate complaintImageHibernate;

	public ImageResponse saveImage(ImageRequest beanRequest) {
		ImageResponse beanResponse = new ImageResponse();
		// --Save Json in Data Base
		RequestResponse valueReqResp = (RequestResponse) reqRespManager.saveOrUpdate(beanRequest,
				CommonConstants.TypeOperationReqResp.OPERATION_UPLOAD_IMAGE_MOBILE,beanRequest.getIdUser(), 0);
		System.out.println("ID Response : " + valueReqResp.getId());

		try {
			//--Save Image
			Image image=ConvertClass.convertImageToDataBase(beanRequest);
			int idImage=imageHibernate.saveImageId(image);
			image.setStatus(1);

			//-- Save in table with complaint
			ComplaintImage beanComplaintImage=ConvertClass.convertComplientImageToDataBase(beanRequest.getIdUser(), idImage, beanRequest.getIdComplient());
			int idComplientImage=complaintImageHibernate.saveComplientImage(beanComplaintImage);
		
			beanResponse.setCodeResponse(CommonConstants.CodeResponse.CODE_RESPONSE_SUCCESS_IMAGE);
			beanResponse.setMessageResponse("Se grabó la imagen con exito image id : "+idImage+" ** Complaint Id : "+idComplientImage);
			beanResponse.setIdImage(idImage);
			
		} catch (Exception e) {
			beanResponse.setCodeResponse(CommonConstants.CodeResponse.CODE_RESPONSE_ERROR);
			beanResponse.setMessageResponse(e.getMessage());
		}
		
		reqRespManager.saveOrUpdate(beanResponse,CommonConstants.TypeOperationReqResp.OPERATION_UPLOAD_IMAGE_MOBILE,
				beanRequest.getIdUser(),valueReqResp.getId());
		return beanResponse;
	}

}
