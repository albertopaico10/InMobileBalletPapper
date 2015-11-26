package com.rest.service.inmobile.util;

public class CommonConstants {

	public class ValueRequestMapping{
		public static final String CREATE_USER = "/rest/user/create";
		public static final String VALIDATE_USER = "/rest/user/validation";
		public static final String SAVE_IMAGE = "/rest/image/save";
		public static final String SAVE_COMPLIENT = "/rest/complient/save";
		public static final String LIST_DISTRICT = "/rest/list/district/{id}";
		public static final String LIST_COMPLAINT_BY_DISTRICT = "/rest/list/complaintbydistrict/{idUser}";
		public static final String LIST_COMPLAINT_BY_USER = "/rest/list/complaintbyuser/{idUser}";
		public static final String LIST_COMPLAINT_BY_PLATE = "/rest/list/numberplate/{numberPlate}";
		public static final String LIST_IMAGE_BY_COMPLAINT = "/rest/list/imagebycomplaint";
		public static final String UPDATE_TYPE_COMPLAINT = "/rest/update/typecomplaint/{idComplaint}/{typeComplaint}";
	}
	
	public class EncriptedValues{
		public static final String ALGORITHM_MD5="MD5";
		public static final String ALGORITHM_AES="AES";
		public static final String KEY_VALUE_ENCRIPTED="inMobileKeyPublic";
	}
	
	public class TypeOperationReqResp{
		public static final String OPERATION_CREATE_USER="CREATE_USER";
		public static final String OPERATION_VALIDATE_USER="VALIDATE_USER";
		public static final String OPERATION_UPLOAD_IMAGE_MOBILE="UPLOAD_IMAGE_MOBILE";
		public static final String OPERATION_SAVE_COMPLIENT = "SAVE_COMPLIENT";
		public static final String OPERATION_LIST_COMPLAINT_DISTRICT = "LIST_COMPLAINT_DISTRICT";
		public static final String OPERATION_LIST_COMPLAINT_USER = "LIST_COMPLAINT_USER";
		public static final String OPERATION_COMPLAINT_PLATE = "COMPLAINT_NUMBER_PLATE";
		public static final String OPERATION_GET_IMAGES = "GET_IMAGES";
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
		public final static String CODE_RESPONSE_SUCCESS_COMPLAINT="SUCCESS_COMPLAINT";
		public final static String CODE_RESPONSE_SUCCESS_GET_IMAGE="SUCCESS_GET_IMAGE";
		public final static String CODE_RESPONSE_EMPTY_LIST_COMPLAINT="EMPTY_LIST_COMPLAINT";
		public final static String CODE_RESPONSE_SUCCESS_LIST_COMPLAINT="SUCCESS_LIST_COMPLAINT";
		public final static String CODE_RESPONSE_SUCCESS_GET_COMPLAINT="SUCCESS_GET_COMPLAINT";
		public final static String CODE_RESPONSE_SUCCESS_UPDATE_COMPLAINT="SUCCESS_UPDATE_COMPLAINT";
	}
	
	public class Email{
//		public final static String EMAIL_FROM="ojovial@inmobile.pe";
//		public final static String PASSWORD_FROM="ojo1234vial";
//		public final static String SUBJECT_CREATION_USER="InMobile - Creation User";
//		public final static String SUBJECT_COMPLETE_COMPLAINT="InMobile - Registro Denuncia";
//		public final static String EMAIL_TRUE="true";
//		public final static String EMAIL_SMTP_GMAIL="smtp.gmail.com";
//		public final static String EMAIL_PORT_GMAIL="587";
		public final static String TYPE_OPERATION_CREATE_USER="CREATE_USER";
		public final static String TYPE_OPERATION_REGISTER_COMPLAINT="REGISTER_COMPLAINT";
	
		public final static String SYSTEM_PARAM_GENERAL_EMAIL="EMAIL";
		public final static String SYSTEM_PARAM_EMAIL_FROM_COMPLAINT="EMAIL_FROM_COMPLAINT";
		public final static String SYSTEM_PARAM_EMAIL_FROM_COMPLAINT_GMAIL="EMAIL_FROM_COMPLAINT_GMAIL";
		public final static String SYSTEM_PARAM_EMAIL_FROM_CREATION_USER="EMAIL_FROM_CREATION_USER";
		public final static String SYSTEM_PARAM_EMAIL_FROM_CREATION_USER_GMAIL="EMAIL_FROM_CREATION_USER_GMAIL";
		public final static String SYSTEM_PARAM_PASSWORD_FROM="PASSWORD_FROM";
		public final static String SYSTEM_PARAM_PASSWORD_FROM_GMAIL="PASSWORD_FROM_GMAIL";
		public final static String SYSTEM_PARAM_SUBJECT_CREATION_USER="SUBJECT_CREATION_USER";
		public final static String SYSTEM_PARAM_SUBJECT_COMPLETE_COMPLAINT="SUBJECT_COMPLETE_COMPLAINT";
		public final static String SYSTEM_PARAM_EMAIL_TRUE="EMAIL_TRUE";
		public final static String SYSTEM_PARAM_EMAIL_SMTP_GMAIL="EMAIL_SMTP_GMAIL";
		public final static String SYSTEM_PARAM_EMAIL_PORT_GMAIL="EMAIL_PORT_GMAIL";
		public final static String SYSTEM_PARAM_EMAIL_SMTP_OJOVIAL="EMAIL_SMTP_OJOVIAL";
		public final static String SYSTEM_PARAM_EMAIL_PORT_OJOVIAL="EMAIL_PORT_OJOVIAL";
		public final static String SYSTEM_PARAM_BODY_EMAIL_CREATION_USER="BODY_EMAIL_CREATION_USER";
		public final static String SYSTEM_PARAM_BODY_EMAIL_COMPLETE_COMPLAINT="BODY_EMAIL_COMPLETE_COMPLAINT";
		public final static String SYSTEM_PARAM_TRANSPORT_PROTOCOL_OJOVIAL="TRANSPORT_PROTOCOL_OJOVIAL";
		public final static String ID_COMPLAINT="${idComplaint}";
		public final static String COMPLETE_NAME="${completeName}";
		public final static String ADDRESS="${address}";
		public final static String NUMBER_PLATE="${numberPlate}";
		public final static String HOTMAIL_DOMAIN="@hotmail.com";
		public final static String OUTLOOK_DOMAIN="@outlook.com";
	}
	
	
	public class FormatDate{
		public static final String FORMAT_DATE_DD_MM_YYYY="dd/MM/yyyy";
		public static final String FORMAT_HOURS_hh_mm="hh:mm:ss";
	}
	
	public class StatusComplaint{
		public static final String STATUS_OPEN="Generada";//--1
		public static final String STATUS_CLOSE="Cerrada";//--2
		public static final String STATUS_DUPLICATED="Duplicada";//--3
		public static final String STATUS_PROGESS="En Proceso";//--4
		public static final String STATUS_WITTHOUTSTATUS="Sin Estado";//--??
	}
	
	public class ImageParameter{
//		public static final String IMAGE_DIRECTORY="C:\\opt/inmobile/ojovial/photos/";
		public static final String IMAGE_DIRECTORY="/opt/inmobile/ojovial/photos/";
//		public static final String IMAGE_DIRECTORY="C:\\Prueba\\";
		public static final String FORMAT_JPG=".jpg";
		public static final String FORMAT_PNG="png";
	}
	
	public class SystemParam{
		public final static String SYSTEM_PARAM_DOWNLOAD_FILE="DOWNLOAD_FILE";
	}
	
}
