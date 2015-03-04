package com.catb.common.web;

import javax.servlet.http.HttpServletRequest;

public class Util {

	public static String getIpAddress(HttpServletRequest request) {
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		
		return ipAddress;
	}
}
