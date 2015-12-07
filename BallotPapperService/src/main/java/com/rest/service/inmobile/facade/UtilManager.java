package com.rest.service.inmobile.facade;

import org.springframework.stereotype.Service;

@Service
public interface UtilManager {
	public boolean isSendEmailFromOtherAccount(String email);
}
