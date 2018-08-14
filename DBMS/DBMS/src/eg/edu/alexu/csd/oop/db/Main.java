/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.edu.alexu.csd.oop.db;

import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Shams Sherif
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        DBManager manager = DBManager.getInstance();
        String query = "";
        Scanner s = new Scanner(System.in);
       
        String a = "";
        boolean flag = true;
        while (flag) {
            System.out.println("Enter query or (exit;) : ");
            query = "";
            boolean flag2 = true;
            while (flag2) {
                query += s.nextLine();
                if (query.contains(";")) {
                    flag2 = false;
                }
            }

            if (query.toUpperCase().contains("SELECT")) {
                String result[][] = (String[][]) manager.executeRetrievalQuery(query);
                for (String[] strings : result) {
                    for (int i = 0; i < strings.length; i++) {
                        System.out.printf("%10s\t", "" + strings[i]);
                    }
                    System.out.println("");
                }

            } else if (query.toUpperCase().contains("CREATE") || query.toUpperCase().contains("DROP")) {
                if (manager.executeStructureQuery(query)) {
                    System.out.println("Success");
                } else {
                    System.out.println("Failed");
                }

            } else if (query.toUpperCase().contains("INSERT") || query.toUpperCase().contains("DELETE") || query.toUpperCase().contains("UPDATE")) {
                int i = manager.executeUpdateQuery(query);
                if (i > 0) {
                    System.out.println(i + " rows updated");
                } else {
                    System.out.println("Failed");
                }

            } else if (query.contains("exit")) {
                flag = false;
            }
        }
    }
}
