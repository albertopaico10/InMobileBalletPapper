package com.rest.service.inmobile.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.rest.service.inmobile.bean.email.EmailBean;

@ContextConfiguration(locations = {"/dispatcher-servlet.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class MailUtilTest {

	@Test
	public void sendEmail2()throws Exception{
		EmailBean beanEmailBean=new EmailBean();
		beanEmailBean.setEmailFrom("registro@ojovial.com");
		beanEmailBean.setPasswordFrom("ojovial.2015");
//		beanEmailBean.setEmailFrom("ojovial@inmobile.pe");
//		beanEmailBean.setPasswordFrom("ojo1234vial");
//		beanEmailBean.setToEmail("alberto_j10@hotmail.com");
		beanEmailBean.setToEmail("albertopaico10@gmail.com");
		beanEmailBean.setSubjectEmail("InMobile - Creation User");
		beanEmailBean.setTransportProtocol("smtp");
		beanEmailBean.setEmailSmtp("mail.ojovial.com");
		beanEmailBean.setEmailPort("25");
		beanEmailBean.setEmailTrue("true");
		beanEmailBean.setBodyEmail("<html><body><p><b>InMobile Bienvenido - Test Email</b></p><br/><p>Estimo Usario:</p><br/><p>Se le agradece haber elegido la aplicaci?n</p><p>Su cuenta ha sido creada con exito</p><p><b>Gracias</b></p></body></html>");
		MailUtil.sendEmail(beanEmailBean);
	}
	
	@Test
	public void sendEmail()throws Exception{
		EmailBean beanEmailBean=new EmailBean();
		beanEmailBean.setEmailFrom("registro@ojovial.com");
		beanEmailBean.setPasswordFrom("ojovial.2015");
//		beanEmailBean.setEmailFrom("ojovial@inmobile.pe");
//		beanEmailBean.setPasswordFrom("ojo1234vial");
//		beanEmailBean.setToEmail("alberto_j10@hotmail.com");
		beanEmailBean.setToEmail("albertopaico10@gmail.com");
		beanEmailBean.setSubjectEmail("InMobile - Creation User");
		beanEmailBean.setTransportProtocol("smtp");
		beanEmailBean.setEmailSmtp("smtp.gmail.com");
		beanEmailBean.setEmailPort("465");
		beanEmailBean.setEmailTrue("true");
		beanEmailBean.setBodyEmail("<html><body><p><b>InMobile Bienvenido - Test Email</b></p><br/><p>Estimo Usario:</p><br/><p>Se le agradece haber elegido la aplicaci?n</p><p>Su cuenta ha sido creada con exito</p><p><b>Gracias</b></p></body></html>");
		MailUtil.sendEmail2(beanEmailBean);
	}
	
	@Test
	public void sendEmailGmail()throws Exception{
		EmailBean beanEmailBean=new EmailBean();
		beanEmailBean.setEmailFrom("albertopaico10@gmail.com");
		beanEmailBean.setPasswordFrom("3444343");
		beanEmailBean.setToEmail("alberto_j10@hotmail.com");
		beanEmailBean.setSubjectEmail("InMobile - Creation User");
		beanEmailBean.setBodyEmail("<html><body><p><b>InMobile Bienvenido - Test Email</b></p><br/><p>Estimo Usario:</p><br/><p>Se le agradece haber elegido la aplicaci?n</p><p>Su cuenta ha sido creada con exito</p><p><b>Gracias</b></p></body></html>");
		MailUtil.sendEmail(beanEmailBean);
	}
	
}
