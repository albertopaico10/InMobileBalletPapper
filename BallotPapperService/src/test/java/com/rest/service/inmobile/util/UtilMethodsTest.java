package com.rest.service.inmobile.util;

import java.util.ArrayList;
import java.util.List;

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
	
	@Test
	public void testSomething(){
		List<String> names=new ArrayList<String>();
		names.add("toto");
		names.add("Lala");
		names.add("papa");
		int index = names.indexOf("papaaaa"); // index = 2
		System.out.println("Index : "+index);
	}
	
	@Test
	public void testGson(){
		String strValue=UtilMethods.fromObjectToString(24);
		System.out.println("GSON : "+strValue);
	}
	
	@Test
	public void replaceTextForEmai(){
		String newValue=UtilMethods.getFinalValuesForEmail("<html><body><p><b>InMobile Generacion de Denuncia - Test Email</b></p><br/><p>Estimo Usario:</p><br/><p>Se le agradece haber elegido la aplicación</p><p>Su denuncia fue registrada con exito, aqui el detalle : </p><p><b>Id Denuncia : </b>${idComplaint}</p><p><b>Lugar de la Infracción : </b>${address}</p><p><b>Placa del Vehiculo : </b>${numberPlate}</p><p><b>Estados de Denuncia : ABIERTA</b></p><p>Le estaremos informando del proceso de esta denuncia</p><p><b>Gracias</b></p></body></html>;",
				CommonConstants.Email.ID_COMPLAINT, "20");
//		String newValue=UtilMethods.getFinalValuesForEmail("HOLA a todo es momento de salir idComplaint",
//				CommonConstants.Email.ID_COMPLAINT, "20");
		String newValue2=UtilMethods.getFinalValuesForEmail(newValue,CommonConstants.Email.ADDRESS, "AV. Peru 4097");
		String newValue3=UtilMethods.getFinalValuesForEmail(newValue2,CommonConstants.Email.NUMBER_PLATE, "AXF-675");
		System.out.println("FINAL EMAIL : "+newValue3);
	}
	
}
