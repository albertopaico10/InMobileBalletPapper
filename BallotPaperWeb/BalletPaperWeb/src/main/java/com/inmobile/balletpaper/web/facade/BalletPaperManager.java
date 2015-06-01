package com.inmobile.balletpaper.web.facade;

import org.springframework.stereotype.Service;

import com.inmobile.balletpaper.web.bean.ReturnService;

@Service
public interface BalletPaperManager {

	public ReturnService listComplaintByUser(int idUser);
	public ReturnService listComplaintByDistrict(int idUser);
}
