package org.freecode.demo;

import org.freecode.demo.entity.Student;
import org.freecode.demo.entity.StudentClass;
import org.freecode.demo.entity.StudyGroup;
import org.hibernate.Session;

public class HibernateMappingDemo {
  public static void main(String[] args)
  {
	  Session ses = HibernateUtil.getSessionFactory().openSession();
	  ses.beginTransaction();
	  
	  StudentClass aClass = new StudentClass();
	  aClass.setClassId(1234);
	  aClass.setClassName("Test Class 123");
	  ses.save(aClass);
	  
	  Student s1 = new Student(11, "John Smith");
	  s1.setStudentClass(aClass);
	  Student s2 = new Student(12, "Mary Rose");
	  s2.setStudentClass(aClass);
	  
	  ses.save(s1);
	  ses.save(s2);
	  
	  StudyGroup sg1 = new StudyGroup(911, "Math Group");
	  sg1.setStudentClass(aClass);
	  StudyGroup sg2 = new StudyGroup(811, "Art Group");
	  sg2.setStudentClass(aClass);
	  StudyGroup sg3 = new StudyGroup(711, "Drawing Group");
	  sg3.setStudentClass(aClass);
	  
	  ses.save(sg1);
	  ses.save(sg2);
	  ses.save(sg3);
	  
	  ses.getTransaction().commit();
	  ses.close();
  }
}
