package com.rest.service.inmobile.util;

public class CommonConstants {

	public class ValueRequestMapping{
		public static final String CREATE_USER = "/rest/user/create";
		public static final String VALIDATE_USER = "/rest/user/validation";
	}
	
	public class TypeOperationReqResp{
		public static final String OPERATION_CREATE_USER="CREATE_USER";
		public static final String OPERATION_VALIDATE_USER="VALIDATE_USER";
		public static final String OPERATION_SAVE_RESTAURANT="SAVE_RESTAURANT";
		public static final String OPERATION_UPLOAD_LOGO_RESTAURANT="UPLOAD_LOGO_RESTAURANT";
		public static final String OPERATION_SAVE_PROVIDER = "SAVE_PROVIDER";
	}
	
	public class CodeResponse{
		public final static String CODE_RESPONSE_ERROR="ERROR";
		public final static String CODE_RESPONSE_SUCCESS_USER="SUCCESS_INSERT_USER";
		public final static String CODE_RESPONSE_SUCCESS_VALIDATION="SUCCESS_VALIDATION_USER";
		public final static String CODE_RESPONSE_FAIL_VALIDATION="FAIL_VALIDATION_USER";
		public final static String CODE_RESPONSE_EXITS_USER="EMAIL_EXIST";
		public final static String CODE_RESPONSE_NOT_EXITS_USER="EMAIL_NOT_EXIST";
		public final static String CODE_RESPONSE_IS_PROVIDER="IS_PROVIDER";
		public final static String CODE_RESPONSE_IS_RESTAURANT="IS_RESTAURANT";
		public final static String CODE_RESPONSE_ACCOUNT_INACTIVE="ACCOUNT_INACTIVE";
		public final static String CODE_RESPONSE_ACCOUNT_PENDING_VALDATION="ACCOUNT_PENDING_VALDATION";
		public final static String CODE_RESPONSE_SUCCESS_DEPARTMENT="SUCCESS_DEPARTMENT";
		public final static String CODE_RESPONSE_SUCCESS_PROVINCE="SUCCESS_PROVINCE";
		public final static String CODE_RESPONSE_SUCCESS_DISTRICT="SUCCESS_DISTRICT";
		public final static String CODE_RESPONSE_SUCCESS_IMAGE="SUCCESS_INSERT_IMAGE";
		public final static String CODE_RESPONSE_SUCCESS_RESTAURANT="SUCCESS_INSERT_RESTAURANT";
		public final static String CODE_RESPONSE_SUCCESS_PROVIDE="SUCCESS_INSERT_PROVIDER";
	}
	
	public class Email{
		public final static String EMAIL_FROM="albertopaico10@gmail.com";
		public final static String PASSWORD_FROM="Pa55w0rd4097";
		public final static String SUBJECT_CREATION_USER="InMobile - Creation User";
		public final static String EMAIL_TRUE="true";
		public final static String EMAIL_SMTP_GMAIL="smtp.gmail.com";
		public final static String EMAIL_PORT_GMAIL="587";
	}
	
}
