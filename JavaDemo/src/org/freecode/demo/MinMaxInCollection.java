package org.freecode.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinMaxInCollection {
	
	public static void main(String[] args) {
		List<String> strLst = new ArrayList<String>();
		strLst.add("2017-08-02");
		strLst.add("2017-07-28");
		strLst.add("2017-07-27");
		strLst.add("2017-07-29");
		strLst.add("2017-07-31");
		strLst.add("2017-07-30");
		strLst.add("2017-08-01");
		
		String minStr = Collections.min(strLst);
		String maxStr = Collections.max(strLst);
		
		System.out.println("MAX: " + maxStr + "\n" + "MIN: " + minStr);
	}

}
