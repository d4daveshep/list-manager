package daveshep.gtd;

import java.util.HashSet;
import java.util.Set;

import daveshep.gtd.domain.Task;


public class ListManager {
	
	private Set tasks;
	
	public ListManager() {
		tasks = new HashSet();
	}
	
	public static void main( String args[] ) {
		System.out.println( "GTD List Manager" );
	}
	
	public void addTask( Task aTask ) {
		tasks.add(aTask);
	}
}