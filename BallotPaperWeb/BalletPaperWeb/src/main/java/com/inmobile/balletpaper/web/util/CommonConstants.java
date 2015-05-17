package com.inmobile.balletpaper.web.util;

public class CommonConstants {

	public class Page {
		public static final String REDIRECT_INIT_PAGE = "initPage";
		public static final String REDIRECT_LOGIN_PAGE = "loginPage";
		public static final String REDIRECT_WELCOME_PAGE = "welcomePage";
	}

	public class EncriptedValues {
		public static final String ALGORITHM_MD5 = "MD5";
		public static final String ALGORITHM_AES = "AES";
		public static final String KEY_VALUE_ENCRIPTED = "inMobileKeyPublic";
	}

	public class URLService {
		public static final String URL_VALIDATION_USER = "http://localhost:8080/BallotPapperService/rest/user/validation";
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
	}
}
