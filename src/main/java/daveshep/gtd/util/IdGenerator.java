package daveshep.gtd.util;

import java.util.Random;

public class IdGenerator {
	
	private static IdGenerator instance = new IdGenerator();
	
	private Random random = new Random();
	
	public static IdGenerator getInstance() {
		return instance;
	}
	
	public Long generateId() {
		return Long.valueOf(random.nextLong());
	}

}
