package daveshep.gtd.domain;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import daveshep.gtd.util.DateUtils;

/**
 * This class provides a parser for due dates.  Due dates are never in the past (i.e. present or future)  
 * 
 * The date can be specified in a number of forms:
 * 
 * <dd> sets the date to <dd> in the current month or next month.  E.g. 10
 * If 29, 30, 31 is entered then the next month with that date will be selected
 * 
 * <dd/MM> sets the date to <dd/MM> in the current year or next year.  E.g. 05/07, 5/12, 23/7 
 * Invalid dates are rejected e.g. 31/6.  Leap year (29/2) is valid and will select the next leap year 
 * 
 * <dd/MM/yy> and <dd/MM/yyyy work as expected
 * 
 * <EEE> selects the next day of the week.  E.g. mo, mon, monday
 * 
 * <MMM> selects the first of the next month.  E.g. mar, november
 * 
 * <yyyy> selects the first of Jan of the next year.  E.g. 2010
 * 
 * <n[d,w,m,y]> selects multiple <n> of <days, weeks, months or years> in the future.  E.g. 2d, 3w, 6m
 * 
 * @author david
 *
 */
public class DueDateParser {

	private Date today;
	
	private static Pattern ddPattern = Pattern.compile("[0-9]{2}");
	private static Pattern ddMMPattern = Pattern.compile("[0-9]{2}/[0-9]{2}");

	public DueDateParser() {
		today = DateUtils.today();
	}

	
	/**
	 * For testing purposes only
	 * @param today
	 */
	void setToday(Date today) {
		this.today = today;
	}
	
	public Date parse(String dueDateString) throws ParseException {
		
		Date dueDate = null;
		Matcher matcher;
		
		// match dd
		matcher = ddPattern.matcher(dueDateString);
		if (matcher.matches()) {
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(today);
			cal.set(Calendar.DATE, Integer.parseInt(dueDateString));
			
			dueDate = cal.getTime();
			return dueDate;
		}
		
		// match dd/MM
		matcher = ddMMPattern.matcher(dueDateString);
		if (matcher.matches()) {

			Calendar cal = Calendar.getInstance();
			cal.setTime(today);
			cal.set(Calendar.DATE, Integer.parseInt(dueDateString));
			
			dueDate = cal.getTime();
			return dueDate;
			
		}
		
		return DateUtils.today();
	}
	
	
}
