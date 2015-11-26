package com.inmobile.balletpaper.web.util;

public class CommonConstants {

	public class Page {
		public static final String REDIRECT_INIT_PAGE = "initPage";
		public static final String REDIRECT_LOGIN_PAGE = "loginPage";
		public static final String REDIRECT_WELCOME_PAGE = "welcomePage";
		public static final String REDIRECT_COMPLAINT_BY_USER = "complaintUserPage";
		public static final String REDIRECT_COMPLAINT_BY_DISTRICT = "complaintDistrictPage";
		public static final String REDIRECT_COMPLAINT_BY_PLATE = "complaintPlatePage";
	}

	public class EncriptedValues {
		public static final String ALGORITHM_MD5 = "MD5";
		public static final String ALGORITHM_AES = "AES";
		public static final String KEY_VALUE_ENCRIPTED = "inMobileKeyPublic";
	}

	public class URLService {
		public static final String URL_IP_SERVER = "http://162.248.54.10/";
//		public static final String URL_IP_SERVER = "http://localhost:8080/";
		public static final String URL_SERVICE_PROJECT = "BallotPapperService";
		public static final String URL_VALIDATION_USER = URL_IP_SERVER+URL_SERVICE_PROJECT+"/rest/user/validation";
		public static final String URL_LIST_COMPLAINT_BY_USER = URL_IP_SERVER+URL_SERVICE_PROJECT+"/rest/list/complaintbyuser/";
		public static final String URL_LIST_COMPLAINT_BY_DISTRICT = URL_IP_SERVER+URL_SERVICE_PROJECT+"/rest/list/complaintbydistrict/";
		public static final String URL_LIST_IMAGE_COMPLAINT = URL_IP_SERVER+URL_SERVICE_PROJECT+"/rest/list/imagebycomplaint";
	}
	
	public class Logger{
		public static final String LOGGER_START="****START****";
		public static final String LOGGER_END="****END****";
	}
	
	public class Response{
		public final static String RESPONSE_SUCCESS_VALIDATION="SUCCESS_VALIDATION_USER";
		public final static String RESPONSE_FAIL_VALIDATION="FAIL_VALIDATION_USER";
		public final static String RESPONSE_NOT_EXITS_USER="EMAIL_NOT_EXIST";
		public static final String RESPONSE_ERROR="ERROR";
		public final static String RESPONSE_SUCCESS_LIST_COMPLAINT="SUCCESS_LIST_COMPLAINT";
		public final static String RESPONSE_SUCCESS_GET_IMAGE="SUCCESS_GET_IMAGE";
	}
	
	public class ImageValues{
		public final static String NAME_IMAGE_FILE="download";
	}
	
}
