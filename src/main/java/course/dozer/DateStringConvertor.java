package course.dozer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.dozer.DozerConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateStringConvertor extends DozerConverter<Date, String>{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private static final String DEFAULT_DATE_TEMPLATE = "yyyy-MM-dd HH:mm:ss";

	public DateStringConvertor() {
		super(Date.class, String.class);
	}

	@Override
	public String convertTo(Date source, String destination) {
		DateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_TEMPLATE);
		return dateFormat.format(source);
	}

	@Override
	public Date convertFrom(String source, Date destination) {
		DateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_TEMPLATE);
		Date date = null;
		try {
			date = dateFormat.parse(DEFAULT_DATE_TEMPLATE);
		} catch (ParseException e) {
			logger.error("Error happen when parsing source {}", source);
		}
		return date;
	}

}
