package org.freecode.demo;

public class Administrator implements Staff{
	
	public void work() {
		System.out.println("Reporting, printing, and other administration work.");
	}

	@Override
	public String toString() {
		return "Administrator []";
	}

	
}
