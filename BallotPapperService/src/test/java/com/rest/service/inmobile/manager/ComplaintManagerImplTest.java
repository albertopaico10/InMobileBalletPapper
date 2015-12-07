package com.rest.service.inmobile.manager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.rest.service.inmobile.facade.ComplientManager;

@ContextConfiguration(locations = {"/dispatcher-servlet.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ComplaintManagerImplTest {

	@Autowired
	private ComplientManager complientManager;
	
	@Test
	public void testBuildEmail()throws Exception{
//		complientManager.buidlEmailGeneratedComplaint("alberto_j10@hotmail.com", 60, "Av. No se", "AXF 3620");
		complientManager.buidlEmailGeneratedComplaint("albertopaico10@gmail.com", 60, "Av. No se", "AXF 3620");
	}

}
