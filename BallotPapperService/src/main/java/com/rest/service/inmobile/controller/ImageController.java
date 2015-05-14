package com.rest.service.inmobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rest.service.inmobile.bean.image.ImageRequest;
import com.rest.service.inmobile.bean.image.ImageResponse;
import com.rest.service.inmobile.facade.ImageManager;
import com.rest.service.inmobile.util.CommonConstants;

@Controller
public class ImageController {
	
	@Autowired
	private ImageManager imageManager;
	
	@RequestMapping(value = CommonConstants.ValueRequestMapping.SAVE_IMAGE,	method = RequestMethod.POST)
	public @ResponseBody ImageResponse saveImage(@RequestBody ImageRequest beanRequest) {
		ImageResponse beanResponse=imageManager.saveImage(beanRequest);
		return beanResponse;
	}
}
