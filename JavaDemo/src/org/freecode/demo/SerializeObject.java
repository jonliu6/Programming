package org.freecode.demo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SerializeObject {

	public static void main(String[] args) {
		SerializeObject so = new SerializeObject();
		Map<String, Date> data = new HashMap<String, Date>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		for (int i = 0; i < 50000; i++) {
			String uid = "User " + i;
			cal.add(Calendar.MINUTE, i);
			data.put(uid, cal.getTime());
		}
		try {
		    System.out.println("The size of the object is: " + so.sizeOf(data) + " bytes");
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public int sizeOf(Object o) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		
		oos.writeObject(o);
		oos.flush();
		oos.close();
		
		return baos.toByteArray().length;
	}
}
