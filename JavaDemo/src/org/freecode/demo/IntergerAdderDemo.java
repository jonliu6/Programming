package org.freecode.demo;

public class IntergerAdderDemo {
	private final static int int1 = 20;
	private final static int int2 = 25;
	
	public static void main(String[] args) {
		System.out.println(integerCalulate((a,b) -> int1 + int2));
		
	}
	
	public static int integerCalulate(IntegerAdder intAdder) {
		return intAdder.add(0, 0);
	}
}
