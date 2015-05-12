package com.rest.service.inmobile.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.service.inmobile.facade.ReqRespManager;
import com.rest.service.inmobile.hibernate.ReqRespHibernate;
import com.rest.service.inmobile.hibernate.bean.RequestResponse;
import com.rest.service.inmobile.util.UtilMethods;

@Service
@Transactional
public class ReqRespManagerImpl implements ReqRespManager{

	@Autowired
	ReqRespHibernate reqRespHibernate;
	
	public Object saveOrUpdate(Object beanObject,String typeValue,int idUser,int idReqResp) {
		System.out.println("***** idUser : "+idUser+"**** idReqResp : "+idReqResp);
		Object returnObject=new Object();
		RequestResponse reqResp=new RequestResponse();
		//--Convert Object in String Json
		String strValue=UtilMethods.fromObjectToString(beanObject);
		try {
			if(idReqResp!=0){
				RequestResponse beanReqResp=reqRespHibernate.findById(idReqResp);
				reqResp.setId(beanReqResp.getId());
				reqResp.setResponse(strValue);
				reqResp.setRequest(beanReqResp.getRequest());
				reqResp.setTypeOperation(beanReqResp.getTypeOperation());
				reqResp.setIdUser(idUser);
			}else{
				reqResp.setRequest(strValue);	
				reqResp.setTypeOperation(typeValue);
				reqResp.setIdUser(idUser);
			}
			reqResp.setStatus(1);
			//--Safe in Data Base
			returnObject = reqRespHibernate.saveOrUpdateReqResp(reqResp);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return returnObject;
	}

	
	
}
