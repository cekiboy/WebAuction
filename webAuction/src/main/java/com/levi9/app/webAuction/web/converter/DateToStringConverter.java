package com.levi9.app.webAuction.web.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * Formats Date object to string.
 * 
 * @author p.stanic
 */
public class DateToStringConverter implements Converter<Date, String> {

	private static final String DATE_FORMAT = "dd-MM-yyyy";

	/**
	 * Date format: dd-MM-yyyy
	 */
	private final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

	/**
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public String convert(Date date) {
		return dateFormat.format(date);
	}
}
