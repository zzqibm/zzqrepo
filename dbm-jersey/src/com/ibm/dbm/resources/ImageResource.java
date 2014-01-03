package com.ibm.dbm.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.ibm.dbm.bean.Image;
import com.ibm.dbm.mock.Mock;
import com.ibm.dbm.util.ImageHelper;

@Path("images")
public class ImageResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Image> getImages(@QueryParam("mock") boolean mock) {
		ImageHelper helper = new ImageHelper();
		List<Image> images = mock ? Mock.getImages() : helper.syncDirectorImages();
		
		return images;
	}
	
}
