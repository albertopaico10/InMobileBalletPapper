package com.example.balletpaper.util;

public class CommonConstants {

	public class URLService {
		public static final String URL_SERVICE = "http://198.38.89.205:8080/";
//		public static final String URL_SERVICE = "http://192.168.1.34:8080/";
		public static final String SERVICE_NAME = "BallotPapperService";
		public static final String CREATE_USER = URL_SERVICE+SERVICE_NAME+"/rest/user/create";
		public static final String VALIDATION_USER = URL_SERVICE+SERVICE_NAME+"/rest/user/validation";
		public static final String REGISTER_COMPLAINT = URL_SERVICE+SERVICE_NAME+"/rest/complient/save";
		public static final String UPLOAD_IMAGE = URL_SERVICE+SERVICE_NAME+"/rest/image/save";
		public static final String GET_DISTRICT = URL_SERVICE+SERVICE_NAME+"/rest/list/district/127";
		
//		public static final String CREATE_USER = "http://10.0.2.2:8080/BallotPapperService/rest/user/create";
//		public static final String VALIDATION_USER = "http://10.0.2.2:8080/BallotPapperService/rest/user/validation";
	}
	
	public class GenericValues{
		public static final String MOBILE_RECORD = "MOBILE";
		public static final String CATEGORY_UPLOAD_IMAGE = "PHOTO_UPLOAD";
	}
	
	public class RequestUploadImage{
		public static final String CATEGORYIMAGE_REQUEST_IMAGE = "categoryImage";
		public static final String HEXIMAGE_REQUEST_IMAGE = "hexImage";
		public static final String IDUSER_REQUEST_IMAGE = "idUser";
		public static final String IDCOMPLIENT_REQUEST_IMAGE = "idComplient";
	}
	
	public class RequestSaveComplaint{
		public static final String IDUSER_REQUEST_COMPLAINT = "idUser";
		public static final String LONGITUDE_REQUEST_COMPLAINT = "longitude";
		public static final String LATITUDE_REQUEST_COMPLAINT = "latitude";
		public static final String FULLADDRESS_REQUEST_COMPLAINT = "addressFull";
		public static final String COMMENT_ADITIONAL_REQUEST_COMPLAINT = "commentsAdditional";
		public static final String NUMBER_PLATE_REQUEST_COMPLAINT = "numberPlate";
		public static final String HEX_PHOTO_1_REQUEST_COMPLAINT = "hexPhoto1";
		public static final String HEX_PHOTO_2_REQUEST_COMPLAINT = "hexPhoto2";
		public static final String HEX_PHOTO_3_REQUEST_COMPLAINT = "hexPhoto3";
		public static final String CATEGORY_IMAGE_REQUEST_COMPLAINT = "categoryImage";
		public static final String DISTRICT_REQUEST_COMPLAINT = "district";
		
	}
	
	public class RequestValueUser{
		public static final String EMAIL_REQUEST_USER = "email";
		public static final String PASSWORD_REQUEST_USER = "password";
		public static final String TYPECUSTOMER_REQUEST_USER = "typeCustomer";
		public static final String NAMEUSER_REQUEST_USER = "namesUser";
		public static final String LASTNAMEUSER_REQUEST_USER = "lastNameUser";
		public static final String DNIUSER_REQUEST_USER = "dniUser";
		public static final String RECORDINGDEVICE_REQUEST_USER = "recordingDevice";
	}
	
	public class RequestLoginUser{
		public static final String EMAIL_REQUEST_USER = "email";
		public static final String PASSWORD_REQUEST_USER = "password";
	}
	
	public class EncriptedValues{
		public static final String ALGORITHM_MD5="MD5";
		public static final String ALGORITHM_AES="AES";		
		public static final String KEY_VALUE_ENCRIPTED="inMobileKeyPublic";
	}
	public class CodeResponse{
		public static final String RESPONSE_SUCCESS_USER="SUCCESS_INSERT_USER";
		public final static String RESPONSE_FAIL_VALIDATION="FAIL_VALIDATION_USER";
		public final static String RESPONSE_SUCCESS_VALIDATION="SUCCESS_VALIDATION_USER";
		public static final String RESPONSE_EMAIL_EXIST="EMAIL_EXIST";
		public static final String RESPONSE_EMAIL_NOT_EXIST="EMAIL_NOT_EXIST";
		public final static String RESPONSE_SUCCESS_COMPLAINT="SUCCESS_COMPLAINT";
		public final static String RESPONSE_SUCCESS_IMAGE="SUCCESS_INSERT_IMAGE";
		public final static String RESPONSE_ERROR="ERROR";
	}

}
