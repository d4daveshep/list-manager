package daveshep.gtd.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
 * If <dd> is greater than the number of days in the current or next month then the last of the month is selected.
 * 
 * <dd/MM> sets the date to <dd/MM> in the current year or next year.  E.g. 05/07, 5/12, 23/7 
 * Invalid dates are rejected e.g. 31/6.  Leap year (29/2) is also rejected (TODO fix this) 
 * 
 * <dd/MM/yy> and <dd/MM/yyyy> work as expected but dates in the past are rejected.
 * 
 * <EEE> selects the next day of the week.  E.g. mon, monday
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
	
	private static Pattern ddPattern = Pattern.compile("[0-9]{1,2}");
	private static Pattern ddMMPattern = Pattern.compile("[0-9]{1,2}/[0-9]{1,2}");
	private static Pattern ddMMyyPattern = Pattern.compile("[0-9]{1,2}/[0-9]{1,2}/[0-9]{2}");
	private static Pattern ddMMyyyyPattern = Pattern.compile("[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}");
	private static Pattern aaaPattern = Pattern.compile("[a-zA-Z]{3,}");
	private static Pattern yyyyPattern = Pattern.compile("[0-9]{4}");
	private static Pattern ndwmyPattern = Pattern.compile("[0-9]+[dwmy]");
	

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

		Calendar cal = Calendar.getInstance();
		cal.setLenient(false);
		cal.setTime(today);
		
		// match dd
		matcher = ddPattern.matcher(dueDateString);
		if (matcher.matches()) {
			
			int day = Integer.parseInt(dueDateString);
			while (true) {
				try {
					cal.set(Calendar.DAY_OF_MONTH, day);
					dueDate = cal.getTime();
					break;
				} catch (IllegalArgumentException e) {
					day--;
				}
			}
			
			if (dueDate.before(today)) {
				cal.add(Calendar.MONTH, 1);
			}
			dueDate = cal.getTime();
			
			return dueDate;
		}
		
		// match dd/MM
		matcher = ddMMPattern.matcher(dueDateString);
		if (matcher.matches()) {

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
			sdf.setLenient(false);
			
			Calendar tempCal = Calendar.getInstance();
			tempCal.setLenient(false);
			Date tempDate = sdf.parse(dueDateString);
			tempCal.setTime(tempDate);
			
			cal.set(Calendar.DATE, tempCal.get(Calendar.DATE));
			cal.set(Calendar.MONTH, tempCal.get(Calendar.MONTH));
			
			dueDate = cal.getTime();
			if (dueDate.before(today)) {
				cal.add(Calendar.YEAR, 1);
				dueDate = cal.getTime();
			}
			return dueDate;
			
		}
		
		// match dd/MM/yy
		matcher = ddMMyyPattern.matcher(dueDateString);
		if (matcher.matches()) {

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
			sdf.setLenient(false);
			dueDate = sdf.parse(dueDateString);
			
			if (dueDate.before(today)) {
				throw new ParseException("Date must not be in the past",0);
			}
			return dueDate;
			
		}
		
		// match dd/MM/yyyy
		matcher = ddMMyyyyPattern.matcher(dueDateString);
		if (matcher.matches()) {

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			sdf.setLenient(false);
			dueDate = sdf.parse(dueDateString);
			
			if (dueDate.before(today)) {
				throw new ParseException("Date must not be in the past",0);
			}
			return dueDate;
			
		}
		
		// match day of week or 1st of month
		matcher = aaaPattern.matcher(dueDateString);
		if (matcher.matches()) {

			SimpleDateFormat sdf;
			try {
				sdf = new SimpleDateFormat("EEE");
				sdf.setLenient(false);

				Calendar tempCal = Calendar.getInstance();
				tempCal.setLenient(false);
				Date tempDate = sdf.parse(dueDateString);
				tempCal.setTime(tempDate);

				cal.set(Calendar.DAY_OF_WEEK, tempCal.get(Calendar.DAY_OF_WEEK));

				dueDate = cal.getTime();

				if (dueDate.before(today)) {
					cal.add(Calendar.WEEK_OF_YEAR, 1);
					dueDate = cal.getTime();
				}
				return dueDate;
			} catch (ParseException e) {
				// continue
			}

			try {
				sdf = new SimpleDateFormat("MMM");
				sdf.setLenient(false);

				Calendar tempCal = Calendar.getInstance();
				tempCal.setLenient(false);
				Date tempDate = sdf.parse(dueDateString);
				tempCal.setTime(tempDate);

				cal.set(Calendar.MONTH, tempCal.get(Calendar.MONTH));
				cal.set(Calendar.DAY_OF_MONTH, 1);

				dueDate = cal.getTime();

				if (dueDate.before(today)) {
					cal.add(Calendar.YEAR, 1);
					dueDate = cal.getTime();
				}
				return dueDate;
			} catch (ParseException e) {
				throw e;
			}
		}
		

		// match 1st day of year
		matcher = yyyyPattern.matcher(dueDateString);
		if (matcher.matches()) {

			SimpleDateFormat sdf;
			sdf = new SimpleDateFormat("yyyy");
			sdf.setLenient(false);

			Calendar tempCal = Calendar.getInstance();
			tempCal.setLenient(false);
			dueDate = sdf.parse(dueDateString);

			if (dueDate.before(today)) {
				throw new ParseException("Date must not be in the past",0);
			}
			return dueDate;
		}

		// match 
		matcher = ndwmyPattern.matcher(dueDateString);
		if (matcher.matches()) {

			int multiplier = Integer.parseInt(dueDateString.substring(0, dueDateString.length()-1));
			char thingToMultiply = dueDateString.charAt(dueDateString.length()-1);
			
			
			switch (thingToMultiply) {
			
			case 'd':
				cal.add(Calendar.DAY_OF_MONTH, multiplier);
				break;
				
			case 'w':
				cal.add(Calendar.WEEK_OF_YEAR, multiplier);
				break;
				
			case 'm':
				cal.add(Calendar.MONTH, multiplier);
				break;
				
			case 'y':
				cal.add(Calendar.YEAR, multiplier);
				break;
				
			default:
				throw new ParseException("Can't understand how we got to this point", 0);
			
			}
			
			dueDate = cal.getTime();
			return dueDate;
		}

		throw new ParseException("Can't match date format", 0);
	}

}
