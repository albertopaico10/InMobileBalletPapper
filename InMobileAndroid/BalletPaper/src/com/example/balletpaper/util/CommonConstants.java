package com.example.balletpaper.util;

public class CommonConstants {

	public class URLService {
		public static final String CREATE_USER = "http://192.168.1.34:8080/BallotPapperService/rest/user/create";
		public static final String VALIDATION_USER = "http://192.168.1.34:8080/BallotPapperService/rest/user/validation";
//		public static final String CREATE_USER = "http://10.0.2.2:8080/BallotPapperService/rest/user/create";
//		public static final String VALIDATION_USER = "http://10.0.2.2:8080/BallotPapperService/rest/user/validation";
	}
	
	public class GenericValues{
		public static final String MOBILE_RECORD = "MOBILE";
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
		public static final String KEY_VALUE_ENCRIPTED="inMobileKeyPublic";
	}
	public class CodeResponse{
		public static final String RESPONSE_SUCCESS_USER="SUCCESS_INSERT_USER";
		public final static String RESPONSE_FAIL_VALIDATION="FAIL_VALIDATION_USER";
		public final static String RESPONSE_SUCCESS_VALIDATION="SUCCESS_VALIDATION_USER";
		public static final String RESPONSE_EMAIL_EXIST="EMAIL_EXIST";
		public static final String RESPONSE_EMAIL_NOT_EXIST="EMAIL_NOT_EXIST";
	}

}
