package daveshep.gtd.domain;

import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * Class to encapsulate
 * - the various ways to specify repeating tasks
 * - the calculation of the next due date from a given date
 * @author shephd
 *
 */
public class TaskRepeater {

	public enum RepeatType {
		NONE, SIMPLE, SPECIAL  
	}
	
	public enum RepeatInterval {
		DAILY, WEEKLY, BI_WEEKLY, MONTHLY, BI_MONTHLY, QUARTERLY, HALF_YEARLY, YEARLY  
	}
	
	public enum RepeatFrom {
		DUE_DATE, COMPLETED_DATE
	}
	
	private RepeatType repeatType = RepeatType.NONE;
	private RepeatInterval repeatInterval;
	private String specialText;
	private int repeatDayOfMonth;
	private int repeatDayOfWeek;
	private int repeatWeekOfMonth = 0;
	private int repeatMultiplier = 0;
	private RepeatFrom repeatFrom;
	private int specialType = 0;
	
	public TaskRepeater() {
	}

	public String getSpecialText() {
		return specialText;
	}

	public void setSpecialText(String specialText) throws IllegalArgumentException {
		
		if (this.repeatType!=RepeatType.SPECIAL) {
			throw new IllegalArgumentException("Need to set repeat type to SPECIAL before specifying special text");
		}
		
		this.specialType = 0;
		this.repeatWeekOfMonth = 0;
		this.repeatMultiplier = 0;
		this.repeatInterval = null;
		
		validate(specialText);
		this.specialText = specialText;
	}
	
	/**
	 * accepts the form of
	 * 1. every <W> where W is <day of week|weekday|weekend> (e.g. every Monday)
	 * 2. every <X> <T> where T = <days|weeks|months|years> (e.g. every 2 days, every 8 weeks)
	 * 3. the <N> <W> of every month where N = <first|second|third|forth|last> and W = <day of week> (e.g. the second Monday of every month)
	 * 4. the <D> of every month where D = <day of month> (e.g. the 20th of every month)
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
				this.specialType=1;
			}
			else if (token.equalsIgnoreCase("tuesday")) {
				this.repeatDayOfWeek = Calendar.TUESDAY;
				this.specialType=1;
			}
			else if (token.equalsIgnoreCase("wednesday")) {
				this.repeatDayOfWeek = Calendar.WEDNESDAY;
				this.specialType=1;
			}
			else if (token.equalsIgnoreCase("thursday")) {
				this.repeatDayOfWeek = Calendar.THURSDAY;
				this.specialType=1;
			}
			else if (token.equalsIgnoreCase("friday")) {
				this.repeatDayOfWeek = Calendar.FRIDAY;
				this.specialType=1;
			}
			else if (token.equalsIgnoreCase("saturday")) {
				this.repeatDayOfWeek = Calendar.SATURDAY;
				this.specialType=1;
			}
			else if (token.equalsIgnoreCase("sunday")) {
				this.repeatDayOfWeek = Calendar.SUNDAY;
				this.specialType=1;
			}
			else try{ 
				this.repeatMultiplier = Integer.parseInt(token); // get the second token as a number
				
				token = tokens.nextToken(); // get the third token
				if (token.equalsIgnoreCase("days")) {
					this.repeatInterval = RepeatInterval.DAILY;
				} else if (token.equalsIgnoreCase("weeks")) {
					this.repeatInterval = RepeatInterval.WEEKLY;
				} else if (token.equalsIgnoreCase("months")) {
					this.repeatInterval = RepeatInterval.MONTHLY;
				} else if (token.equalsIgnoreCase("years")) {
					this.repeatInterval = RepeatInterval.YEARLY;
				} else {
					throw new IllegalArgumentException("special repeat text fails validation");
				}
				this.specialType=2;
				
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
			this.specialType=3;
			
			
		}
		
		else {
			throw new IllegalArgumentException("special repeat text fails validation");
		}
		
	}

	public RepeatInterval getRepeatInterval() {
		return repeatInterval;
	}

	public void setRepeatInterval(RepeatInterval repeatInterval) {
		this.repeatInterval = repeatInterval;
	}

	public RepeatType getRepeatType() {
		return repeatType;
	}

	public void setRepeatType(RepeatType repeatType) {
		this.repeatType = repeatType;
	}

	public Date calculateNextDueDate(Date dueDate) {
		Date nextDueDate = null;

		Calendar cal = Calendar.getInstance();
		cal.setTime(dueDate);
		
		switch (this.repeatType) {
		
		case NONE:
			nextDueDate = dueDate;
			break;
		
		case SIMPLE:
			
			switch (this.repeatInterval) {

			case DAILY:
				cal.add(Calendar.DATE, 1);
				nextDueDate = cal.getTime();
				break;

			case WEEKLY:
				cal.add(Calendar.WEEK_OF_YEAR, 1);
				nextDueDate = cal.getTime();
				break;

			case BI_WEEKLY:
				cal.add(Calendar.WEEK_OF_YEAR, 2);
				nextDueDate = cal.getTime();
				break;

			case MONTHLY:
				cal.add(Calendar.MONTH, 1);
				nextDueDate = cal.getTime();
				break;

			case BI_MONTHLY:
				cal.add(Calendar.MONTH, 2);
				nextDueDate = cal.getTime();
				break;

			case QUARTERLY:
				cal.add(Calendar.MONTH, 3);
				nextDueDate = cal.getTime();
				break;

			case HALF_YEARLY:
				cal.add(Calendar.MONTH, 6);
				nextDueDate = cal.getTime();
				break;

			case YEARLY:
				cal.add(Calendar.YEAR, 1);
				nextDueDate = cal.getTime();
				break;
			
			}
			break;
			
		case SPECIAL:
			switch (this.specialType) {
			case 1: // every <W> where W is <day of week|weekday|weekend> (e.g. every Monday)
				
				cal.set(Calendar.DAY_OF_WEEK, this.repeatDayOfWeek);
				nextDueDate = cal.getTime();
				
				if (!nextDueDate.after(dueDate)) {
					cal.add(Calendar.WEEK_OF_YEAR, 1);
					nextDueDate = cal.getTime();
				}
				break;

			case 2: // every <X> <T> where T = <days|weeks|months|years> (e.g. every 2 days, every 8 weeks)
				
				switch (this.repeatInterval) {
				case DAILY:
					cal.add(Calendar.DATE, this.repeatMultiplier);
					nextDueDate = cal.getTime();
					break;
				case WEEKLY:
					cal.add(Calendar.WEEK_OF_YEAR, this.repeatMultiplier);
					nextDueDate = cal.getTime();
					break;
				case MONTHLY:
					cal.add(Calendar.MONTH, this.repeatMultiplier);
					nextDueDate = cal.getTime();
					break;
				case YEARLY:
					cal.add(Calendar.YEAR, this.repeatMultiplier);
					nextDueDate = cal.getTime();
					break;
				default:
					throw new IllegalStateException("Invalid RepeatInterval");
						
				}
				break;
				
			case 3: // the <N> <W> of every month where N = <first|second|third|forth|last> and W = <day of week> (e.g. the second Monday of every month)
				System.out.println("start");

//				cal.set(Calendar.DAY_OF_WEEK, this.repeatDayOfWeek );
//				nextDueDate = cal.getTime();
//				System.out.println("next: " + nextDueDate);
//
//				if (!nextDueDate.after(dueDate)) {
//					cal.add(Calendar.WEEK_OF_YEAR, 1);
//					nextDueDate = cal.getTime();
//					System.out.println("next: " + nextDueDate);
//				}

				cal.set(Calendar.WEEK_OF_MONTH, this.repeatWeekOfMonth);
				nextDueDate = cal.getTime();
				System.out.println("next: " + nextDueDate);

				if (!nextDueDate.after(dueDate)) {
					cal.add(Calendar.WEEK_OF_YEAR, 1);
					nextDueDate = cal.getTime();
				}
				System.out.println("next: " + nextDueDate);

				cal.set(Calendar.DAY_OF_WEEK, this.repeatDayOfWeek );
				nextDueDate = cal.getTime();
				System.out.println("next: " + nextDueDate);

				if (!nextDueDate.after(dueDate)) {
					cal.add(Calendar.WEEK_OF_YEAR, 4);
					nextDueDate = cal.getTime();
				}
				System.out.println("next: " + nextDueDate);
				break;
				
				
			}
			
		}
		
		return nextDueDate;
	}
	
	
}
