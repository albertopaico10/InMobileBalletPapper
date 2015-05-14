package com.rest.service.inmobile.facade;

import com.rest.service.inmobile.bean.image.ImageRequest;
import com.rest.service.inmobile.bean.image.ImageResponse;

public interface ImageManager {

	public ImageResponse saveImage(ImageRequest beanRequest);
	
}
