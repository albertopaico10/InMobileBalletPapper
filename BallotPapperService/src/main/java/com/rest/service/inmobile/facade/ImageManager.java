package com.rest.service.inmobile.facade;

import com.rest.service.inmobile.bean.image.ImageRequest;
import com.rest.service.inmobile.bean.image.ImageResponse;
import com.rest.service.inmobile.bean.image.ListImageResponse;

public interface ImageManager {

	public ImageResponse saveImage(ImageRequest beanRequest);
	
	public ListImageResponse getImageFromIdComplaint(ImageRequest beanRequest);
}
