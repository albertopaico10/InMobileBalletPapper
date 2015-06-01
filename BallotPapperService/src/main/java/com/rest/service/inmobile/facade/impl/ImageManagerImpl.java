package com.rest.service.inmobile.facade.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.service.inmobile.bean.image.ImageRequest;
import com.rest.service.inmobile.bean.image.ImageResponse;
import com.rest.service.inmobile.bean.image.ListImageResponse;
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
		try {
			//--Save Image
			Image image=ConvertClass.convertImageToDataBase(beanRequest);
			image.setStatus(1);
			int idImage=imageHibernate.saveImageId(image);

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
		return beanResponse;
	}

	public ListImageResponse getImageFromIdComplaint(ImageRequest beanRequest){
		System.out.println("Ruta del Web : "+beanRequest.getRootProject());
		ListImageResponse beanListImageResponse=new ListImageResponse();
		RequestResponse valueReqResp = (RequestResponse) reqRespManager.saveOrUpdate(beanRequest.getIdComplient(),
				CommonConstants.TypeOperationReqResp.OPERATION_GET_IMAGES,beanRequest.getIdComplient(), 0);
		System.out.println("ID Response : " + valueReqResp.getId());
		try {
			List<ImageResponse> listImageResponse=new ArrayList<ImageResponse>();
			int totalImage=0;
			List<ComplaintImage> listComplaintImage=complaintImageHibernate.getListImage(beanRequest.getIdComplient());
			for(ComplaintImage beanComplaintImage:listComplaintImage){
				Image beanImage=imageHibernate.getImage(beanComplaintImage.getIdImage());
				ImageResponse beanImageResponse=ConvertClass.convertImageToImageResponse(beanImage,beanRequest.getRootProject());
				listImageResponse.add(beanImageResponse);
			}
			totalImage=listImageResponse.size();
			beanListImageResponse.setListImageResponse(listImageResponse);
			beanListImageResponse.setCodeResponse(CommonConstants.CodeResponse.CODE_RESPONSE_SUCCESS_GET_IMAGE);
			beanListImageResponse.setMessageResponse("Se recuperaron con exito las images : "+totalImage);
		} catch (Exception e) {
			beanListImageResponse.setCodeResponse(CommonConstants.CodeResponse.CODE_RESPONSE_ERROR);
			beanListImageResponse.setMessageResponse(e.getMessage());
		}
		reqRespManager.saveOrUpdate(beanListImageResponse,CommonConstants.TypeOperationReqResp.OPERATION_GET_IMAGES,
				beanRequest.getIdComplient(),valueReqResp.getId());
		return beanListImageResponse;
	}

}
