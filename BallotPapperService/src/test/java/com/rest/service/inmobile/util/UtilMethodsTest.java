package com.rest.service.inmobile.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = {"/dispatcher-servlet.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UtilMethodsTest {

	@Test
	public void descriptValue(){
		String value="4a7220436869636c61796f20343136392c204c696d612c2050657275";
		String valorDescript="";
		try {
			valorDescript = UtilMethods.descriptValue(value);
			System.out.println("Valor : "+valorDescript);
		} catch (Exception e) {
			System.out.println("Error : "+e.getMessage());
		}
		
	}
	
	@Test
	public void encriptValue(){
		String value="Jr Chiclayo 4169, Lima, Peru";
		String encriptValue="";
		try {
			encriptValue=UtilMethods.encriptValue(value);
			System.out.println("Valor : "+encriptValue);
		} catch (Exception e) {
			System.out.println("Error : "+e.getMessage());
		}
	}
	
}
