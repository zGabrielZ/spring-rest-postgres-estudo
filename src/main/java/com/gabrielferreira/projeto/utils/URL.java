package com.gabrielferreira.projeto.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
public class URL {
	
	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	public static String decodeParamDate(Date d) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return URLDecoder.decode(sdf.format(d),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}
