package org.freecode.demo;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Jonathan
 * Date: Mar 20, 2007
 * Time: 2:26:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConvertType {
    public static void main(String args[]) {
      ArrayList rsList = new ArrayList();

        try {
            Class.forName("oracle.jdbc.OracleDriver");
        }
        catch(java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
        }
        try {
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ora9i","qatest", "qatest");
            Statement stmt = con.createStatement();
/*                        String sqlStr = "INSERT INTO TestTable (UserName, UserID, Age, Gender, DOB) VALUES"
                                        + " ('', 12, 99, 1, TO_DATE('2004-01-01', 'YYYY-MM-DD'))";
                        stmt.executeUpdate(sqlStr);
*/
                        String sqlStr = "SELECT UserName, UserID, Age, Gender, DOB FROM TestTable";
                        ResultSet rs = stmt.executeQuery(sqlStr);
                        ResultSetMetaData rsMeta = rs.getMetaData();
                        int colNum = rsMeta.getColumnCount();

                        while (rs.next()) {
                          for (int i = 0; i < colNum; ++i) {
                            rsList.add(rs.getObject(i + 1) );

                          }

/*
                          String uid = rs.getString(2);
                          String uname = rs.getString(1);
                          int age = rs.getInt(3);
                          boolean gender = rs.getBoolean(4);
                          String genderStr = (gender ? "Female" : "Male");
                          Date dob = rs.getDate(5);
                          System.out.println("UserID: " + uid + "\n"
                                             + "UserName: " + uname + "\n"
                                             + "Age: " + age + "\n"
                                             + "Gender: " + genderStr + "(" + gender + ")\n"
                                             + "Birthday: " + dob + "\n");
*/
                        }

            stmt.close();
            con.close();
        }
        catch(SQLException ex) {
                System.out.println("\n--- SQLException caught ---\n");
                while (ex != null) {
                    System.out.println("Message: " + ex.getMessage ());
                    System.out.println("SQLState: " + ex.getSQLState ());
                    System.out.println("ErrorCode: " + ex.getErrorCode ());
                    ex = ex.getNextException();
                    System.out.println("");
                }
        }

      for (int i = 0; i < rsList.size(); ++i) {
        Object obj = rsList.get(i);
        if (obj instanceof String) {
          System.out.print(obj.toString());
        } else if (obj instanceof Boolean) {
        	System.out.print("True/False");
        }
        else {
        	System.out.println("Something else");
        }
      }
    }

}
