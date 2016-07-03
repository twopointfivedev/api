package com.propswip.api.utils;

import java.util.UUID;

import com.propswip.api.utils.ApiConstants;

public class PropswipUtils {

	public static String getPath(String propertyRef, int flatType, String source) {
		int subFolder = Math.abs(propertyRef.hashCode()%100);
		String s = ApiConstants.THUMBNAIL_IMG_DIR+source +"\\"+flatType+"\\"+subFolder+"\\"+propertyRef;
		return s;
	}
	
	public static String getGeneratedString() {
		UUID temp = UUID.randomUUID();
		String uuidString = Long.toHexString(temp.getMostSignificantBits())
		     + Long.toHexString(temp.getLeastSignificantBits());
		return uuidString;
	}

}
