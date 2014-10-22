package com.levi9.app.webAuction.web.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * Converts string to Date object.
 * 
 * @author p.stanic
 */
public class StringToDateConverter implements Converter<String, Date> {

	private static final String DATE_FORMAT = "dd-MM-yyyy";

	/**
	 * Date format: dd-MM-yyyy
	 */
	private final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

	/**
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public Date convert(String dateString) {
		try {
			return dateFormat.parse(dateString);
		} catch (ParseException e) {
			return null;
		}
	}
}
