package com.inmobile.balletpaper.web.facade;

import java.util.List;

import org.springframework.stereotype.Service;

import com.inmobile.balletpaper.web.bean.canonical.ImageDTO;

@Service
public interface ImageManager {

	public List<ImageDTO> getImageFromService(int idComplaint,String root);
	
}
