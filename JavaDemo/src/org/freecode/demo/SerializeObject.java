package org.freecode.demo;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

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
		    String fName = "c:/Temp/Obj.ser";
		    so.saveObject(data, fName);
		    System.out.println("File saved");
		    
		    Object o = so.loadObject(fName);
		    if (o != null && o instanceof Map) {
		    	Map mapObj = (Map) o;
		    	so.printMap(mapObj);
		    }
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
	
	public void saveObject(Object o, String fName) throws IOException {
		FileOutputStream fo = new FileOutputStream(fName);
		ObjectOutputStream oos = new ObjectOutputStream(fo);
		
		oos.writeObject(o);
		oos.flush();
		oos.close();
	}
	
	public Object loadObject(String fName) throws IOException, ClassNotFoundException {
		FileInputStream fi = new FileInputStream(fName);
		ObjectInputStream ois = new ObjectInputStream(fi);
		
		Object o = ois.readObject();
		ois.close();
		
		return o;
	}
	
	public void printMap(Map map) {
		if (map != null) {
			map.put("Last entry", new Date());
			for (Iterator<Entry> it = map.entrySet().iterator(); it.hasNext();) {
				Entry itm = (Entry) it.next();
				if (itm != null) {
					System.out.println(itm.getKey() + ":" + itm.getValue());
				}
			}
		}
	}
}
