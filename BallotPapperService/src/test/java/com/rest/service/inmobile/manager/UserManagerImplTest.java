package com.rest.service.inmobile.manager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.rest.service.inmobile.facade.UserManager;

@ContextConfiguration(locations = {"/dispatcher-servlet.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserManagerImplTest {
	
	@Autowired
	private UserManager userManager;

	
	@Test
	public void buidlEmailCreationUser()throws Exception{
		userManager.buidlEmailCreationUser("alberto_j10@hotmail.com");
	}
}
