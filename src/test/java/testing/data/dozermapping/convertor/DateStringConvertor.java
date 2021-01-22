package testing.data.dozermapping.convertor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import course.dozer.CustomizeConvertor;

public class DateStringConvertor extends CustomizeConvertor<Date, String>{
	
	private String dateFormatStr = "yyyy-MM-dd HH:mm:ss";
	
	public DateStringConvertor() {
		super(Date.class, String.class);
	}

	@Override
	public String convertTo(Date source, String destination) {
		DateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
		return dateFormat.format(source);
	}

	@Override
	public Date convertFrom(String source, Date destination) {
		DateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
		Date date = null;
		try {
			date = dateFormat.parse(source);
		} catch (ParseException e) {
		}
		return date;
	}

}
