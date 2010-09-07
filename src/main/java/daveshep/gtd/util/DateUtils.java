package daveshep.gtd.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static final SimpleDateFormat xmlDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat displayDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	public static Date today() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}
	
	public static Date now() {
		return Calendar.getInstance().getTime();
	}
	
}
