/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.sql.SQLException;

/**
 *
 * @author Shams Sherif
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, SQLException, IllegalAccessException {
     
        //jdbc:mysql://localhost/sakila
        //C:\\Users\Youssef\Documents\NetBeansProjects\tables\
        java.sql.Driver driver = (java.sql.Driver) Class.forName("jdbc.Driver").newInstance();
//        Properties p = new Properties();
//        p.setProperty("user", "root");
//        p.setProperty("password", "12345678");

        try (java.sql.Connection con = driver.connect("C:\\\\Users\\Shams Hegab\\Desktop\\Project2\\Tables\\", null);
                java.sql.Statement statement = con.createStatement();) {
           
            java.sql.ResultSet res = statement.executeQuery("select Name, Course FROM zombo4;");
//            boolean success = statement.execute(" SELECT course_name, credit_hours FROM courses ");
//            ResultSet res = null;
//            if (success) {
//                res = statement.getResultSet();
//            }

            java.sql.ResultSetMetaData resMeta = res.getMetaData();
          
            System.out.printf("%-20s  %-20s", resMeta.getColumnName(0), resMeta.getColumnName(1));
            System.out.println("");
           
     
            while (res.next()) {
                System.out.printf("%-20s  %-20s", res.getString(0), res.getString(1));
                System.out.println("");
            }
            res.close();
        }
    
    }
}
