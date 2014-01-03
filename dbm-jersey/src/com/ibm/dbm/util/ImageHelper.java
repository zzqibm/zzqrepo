package com.ibm.dbm.util;

import java.util.ArrayList;
import java.util.List;

import com.ibm.dbm.bean.Image;

public class ImageHelper {
	
	public List<Image> syncDirectorImages() {
		List<Image> images = new ArrayList<Image>();
		
		RemoteShellInvoke rsi = new RemoteShellInvoke();
		rsi.setCommand("vmc_ls_image.sh");
		
		int result = -1;
		try {
			result = rsi.exec();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(result == 0){
			String imagesString = rsi.getReturnMsg();
			imagesString = imagesString.trim();
			String[] imagesArray = imagesString.split(":");
			
			Image image = null;
			for (String imageName : imagesArray) {
				image = new Image();
				image.setName(imageName);
			}
		}
		
		return images;
	}
	
}
