package daveshep.gtd.domain;

import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * Class to encapsulate the various ways to specify repeating tasks
 * @author shephd
 *
 */
public class TaskRepeater {

	public enum RepeatType {
		NONE, DAILY, WEEKLY, BI_WEEKLY, MONTHLY, BI_MONTHLY, QUARTERLY, HALF_YEARLY, YEARLY, SPECIAL  
	}
	
	public enum RepeatInterval {
		DAYS, WEEKS, MONTHS, YEARS  
	}
	
	private RepeatType repeatType = RepeatType.NONE;
	private String specialText;
	private int repeatDayOfMonth;
	private int repeatDayOfWeek;
	private int repeatWeekOfMonth;
	private RepeatInterval repeatInterval;
	private int repeatMultiplier;
	
	public TaskRepeater() {
	}

	public RepeatType getType() {
		return repeatType;
	}

	public void setType(RepeatType type) {
		this.repeatType = type;
	}

	public String getSpecialText() {
		return specialText;
	}

	public void setSpecialText(String specialText) throws IllegalArgumentException {
		
		if (repeatType!=RepeatType.SPECIAL) {
			throw new IllegalArgumentException("Need to set repeat type to SPECIAL before specifying special text");
		}
		validate(specialText);
		this.specialText = specialText;
	}
	
	/**
	 * accepts the form of
	 * every <W> where W is <day of week|weekday|weekend> (e.g. every Monday)
	 * every <X> <T> where T = <days|weeks|months|years> (e.g. every 2 days, every 8 weeks)
	 * the <N> <W> of every month where N = <first|second|third|forth|last> and W = <day of week> (e.g. the second Monday of every month)
	 * the <D> of every month where D = <day of month> (e.g. the 20th of every month)
	 * 
	 * @param text
	 * @throws IllegalArgumentException
	 */
	private void validate(String text) throws IllegalArgumentException {

		StringTokenizer tokens = new StringTokenizer(text);
		
		String token = tokens.nextToken(); // get first token
		if (token.equalsIgnoreCase("every")) {
			token = tokens.nextToken(); // get the second token
			if (token.equalsIgnoreCase("monday")) {
				this.repeatDayOfWeek = Calendar.MONDAY;
			}
			else if (token.equalsIgnoreCase("tuesday")) {
				this.repeatDayOfWeek = Calendar.TUESDAY;
			}
			else if (token.equalsIgnoreCase("wednesday")) {
				this.repeatDayOfWeek = Calendar.WEDNESDAY;
			}
			else if (token.equalsIgnoreCase("thursday")) {
				this.repeatDayOfWeek = Calendar.THURSDAY;
			}
			else if (token.equalsIgnoreCase("friday")) {
				this.repeatDayOfWeek = Calendar.FRIDAY;
			}
			else if (token.equalsIgnoreCase("saturday")) {
				this.repeatDayOfWeek = Calendar.SATURDAY;
			}
			else if (token.equalsIgnoreCase("sunday")) {
				this.repeatDayOfWeek = Calendar.SUNDAY;
			}
			else try{ 
				this.repeatMultiplier = Integer.parseInt(token);
				
				token = tokens.nextToken(); // get the third token
				if (token.equalsIgnoreCase("days")) {
					this.repeatInterval = RepeatInterval.DAYS;
				} else if (token.equalsIgnoreCase("weeks")) {
					this.repeatInterval = RepeatInterval.WEEKS;
				} else if (token.equalsIgnoreCase("months")) {
					this.repeatInterval = RepeatInterval.MONTHS;
				} else if (token.equalsIgnoreCase("years")) {
					this.repeatInterval = RepeatInterval.YEARS;
				} else {
					throw new IllegalArgumentException("special repeat text fails validation");
				}
				
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException("special repeat text fails validation");
			}
		}
		else if (token.equalsIgnoreCase("the")) {
			token = tokens.nextToken(); // get the second token
			if (token.equalsIgnoreCase("first")) {
				this.repeatWeekOfMonth = 1;
			}
			else if (token.equalsIgnoreCase("second")) {
				this.repeatWeekOfMonth = 2;
			}
			else if (token.equalsIgnoreCase("third")) {
				this.repeatWeekOfMonth = 3;
			}
			else if (token.equalsIgnoreCase("fourth")) {
				this.repeatWeekOfMonth = 4;
			}
			else if (token.equalsIgnoreCase("last")) {
				this.repeatWeekOfMonth = -1;
			} else {
				throw new IllegalArgumentException("special repeat text fails validation");
			}

			token = tokens.nextToken(); // get the third token
			if (token.equalsIgnoreCase("monday")) {
				this.repeatDayOfWeek = Calendar.MONDAY;
			}
			else if (token.equalsIgnoreCase("tuesday")) {
				this.repeatDayOfWeek = Calendar.TUESDAY;
			}
			else if (token.equalsIgnoreCase("wednesday")) {
				this.repeatDayOfWeek = Calendar.WEDNESDAY;
			}
			else if (token.equalsIgnoreCase("thursday")) {
				this.repeatDayOfWeek = Calendar.THURSDAY;
			}
			else if (token.equalsIgnoreCase("friday")) {
				this.repeatDayOfWeek = Calendar.FRIDAY;
			}
			else if (token.equalsIgnoreCase("saturday")) {
				this.repeatDayOfWeek = Calendar.SATURDAY;
			}
			else if (token.equalsIgnoreCase("sunday")) {
				this.repeatDayOfWeek = Calendar.SUNDAY;
			} else {
				throw new IllegalArgumentException("special repeat text fails validation");
			}

			token = tokens.nextToken(); // get the fourth token
			if (!token.equalsIgnoreCase("of")) {
				throw new IllegalArgumentException("special repeat text fails validation");
			}
			
			token = tokens.nextToken(); // get the fifth token
			if (!token.equalsIgnoreCase("every")) {
				throw new IllegalArgumentException("special repeat text fails validation");
			}
			
			token = tokens.nextToken(); // get the sixth token
			if (!token.equalsIgnoreCase("month")) {
				throw new IllegalArgumentException("special repeat text fails validation");
			}
			
			
		}
		
		else {
			throw new IllegalArgumentException("special repeat text fails validation");
		}
		
	}

	
	
	
}
