package org.freecode.demo;

public class Teacher implements Staff {
    public void work() {
    	System.out.println("Teaching, coaching and other teacher duties.");
    }

	@Override
	public String toString() {
		return "Teacher []";
	}
    
}
