package daveshep.gtd.domain;

/**
 * Class to encapsulate the various ways to specify repeating tasks
 * @author shephd
 *
 */
public class TaskRepeater {

	public enum RepeatType {
		NONE, DAILY, WEEKLY, BI_WEEKLY, MONTHLY, BI_MONTHLY, QUARTERLY, HALF_YEARLY, YEARLY, SPECIAL  
	}
	
	private RepeatType repeatType = RepeatType.NONE;
	private String specialText;
	
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
	 * the <first|second|third|forth|last> <day of week> of every month (e.g. the second Monday of every month)
	 *
	 * 
	 * @param text
	 * @throws IllegalArgumentException
	 */
	private void validate(String text) throws IllegalArgumentException {

		if (false) {
			
		}
		else {
			throw new IllegalArgumentException("special repeat text fails validation");
		}
		
	}

	
	
	
}
