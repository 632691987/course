package course.dozer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.dozer.DozerConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalendarConvertor extends DozerConverter<Calendar, String>{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private static final String DEFAULT_DATE_TEMPLATE = "yyyy-MM-dd HH:mm:ss";

	public CalendarConvertor() {
		super(Calendar.class, String.class);
	}

	@Override
	public String convertTo(Calendar source, String destination) {
		DateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_TEMPLATE);
		return dateFormat.format(source.getTime());
	}

	@Override
	public Calendar convertFrom(String source, Calendar destination) {
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_TEMPLATE);
		try {
			cal.setTime(dateFormat.parse(source));
		} catch (ParseException e) {
			logger.error(String.format("Invalid value [%s] for Calendar", source));
			cal = null;
		}
		return cal;
	}
}
