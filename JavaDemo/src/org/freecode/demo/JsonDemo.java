package org.freecode.demo;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JsonDemo {
	
	public static void main(String[] args) {
		
		JsonDemo demo = new JsonDemo();
		System.out.println("Student: \n" + demo.constructStudentJsonObj().toString());
	}

	/**
	 * Student JSON:
	 * {
	 *   "studentNumber": "1234567",
	 *   "firstName": "John",
	 *   "lastName": "Smith",
	 *   "courses": [
	 *     {
	 *       "courseNumber": "E1",
	 *       "courseName": "English",
	 *       "instructor": "Dave Lee"       
	 *     },
	 *     {
	 *       "courseNumber": "M9",
	 *       "courseName": "Math",
	 *       "instructor": "Liang Dong"       
	 *     }
	 *   ],
	 *   "address": {
	 *     "streetNumber": "123",
	 *     "streetName": "North Rd",
	 *     "city": "Coquitlam"
	 *   }
	 * }
	 * @return
	 */
	public JsonObject constructStudentJsonObj() {
		JsonObject courseEnglish = new JsonObject();
		courseEnglish.addProperty("courseNumber", "E1");
		courseEnglish.addProperty("courseName", "English");
		courseEnglish.addProperty("instructor", "Dave Lee");
		
		JsonObject courseMath = new JsonObject();
		courseMath.addProperty("courseNumber", "M9");
		courseMath.addProperty("courseName", "Math");
		courseMath.addProperty("instructor", "Liang Dong");
		
		JsonArray courses = new JsonArray();
		courses.add(courseEnglish);
		courses.add(courseMath);
		
		JsonObject address = new JsonObject();
		address.addProperty("streetNumber", "123");
		address.addProperty("streetName", "North Rd");
		address.addProperty("city", "Coquitlam");
		
		JsonObject student = new JsonObject();
		student.addProperty("studentNumber", "1234567");
		student.addProperty("firstName", "John");
		student.addProperty("lastName", "Smith");
		student.add("courses", courses);
		student.add("address", address);
		
		return student;
	}
}
