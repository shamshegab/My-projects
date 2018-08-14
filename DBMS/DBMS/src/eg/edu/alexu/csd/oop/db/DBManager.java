/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.edu.alexu.csd.oop.db;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;


/**
 *
 * @author ahmed
 */
public class DBManager implements DataBase {

    private static DBManager instance = new DBManager();

    private DBManager() {
    }

    public static DBManager getInstance() {
        return instance;
    }

    @Override
    public boolean executeStructureQuery(String query) throws SQLException {
        String queries[] = query.split("[ (,)=]+|[ ( );]");
        String Path = "Tables\\";
        Parser p = Parser.getInstance();
        if (queries[0].equalsIgnoreCase("CREATE")) {
            return p.createTable(query);
        } else if (queries[0].equals("DROPTABLE")) {
            //String Path = "C:\\Users\\User\\Documents\\NetBeansProjects\\Project2\\Project2\\src\\eg\\edu\\alexu\\csd\\oop\\db\\";
            Path = Path.concat(queries[1]);
            boolean success = (new File(Path.concat(".xml"))).delete();
            if (success) {
                System.out.println("Table Successfully deleted");
                (new File(Path.concat(".xsd"))).delete();
                return true;
            } else {
                System.out.println("This table does not exist");
                return false;
            }
        }

        return false;
    }

    @Override
    public Object[][] executeRetrievalQuery(String query) throws SQLException {
       
        Pattern p1 = Pattern.compile("(SELECT)\\s+(\\w+\\s*,\\s*)*\\s*\\w*\\s*(FROM)\\s+\\w+\\s*;\\s*", Pattern.CASE_INSENSITIVE);
        Pattern p2 = Pattern.compile("(SELECT)\\s+(\\w+\\s*,\\s*)*\\s*\\w*\\s*(FROM)\\s+\\w+\\s+(WHERE)\\s+\\w+\\s*(=)\\s*\\s*\\w+\\s*\\s*;\\s*", Pattern.CASE_INSENSITIVE);
        Pattern p3 = Pattern.compile("(SELECT)\\s+(\\w+\\s*,\\s*)*\\s*\\w*\\s*(FROM)\\s+\\w+\\s+(WHERE)\\s+\\w+\\s*(>)\\s*\\s*\\w+\\s*\\s*;\\s*", Pattern.CASE_INSENSITIVE);
        Pattern p4 = Pattern.compile("(SELECT)\\s+(\\w+\\s*,\\s*)*\\s*\\w*\\s*(FROM)\\s+\\w+\\s+(WHERE)\\s+\\w+\\s*(<)\\s*\\s*\\w+\\s*\\s*;\\s*", Pattern.CASE_INSENSITIVE);
        Matcher m = p1.matcher(query);
        Matcher m2 = p2.matcher(query);
        Matcher m3 = p3.matcher(query);
        Matcher m4 = p4.matcher(query);

        if (m.matches() || m2.matches() || m3.matches() || m4.matches()) {
            //System.out.println("SELECT");

        } else {
            System.out.println("INVALID STATEMENT");
            return null;
        }

        Parser parser = Parser.getInstance();
        String Condition = null;
        String Sign = null;
        String COnditionValue = null;

        String queries[] = query.split("[ (,)]+|[ ( );]");
        if (query.contains("WHERE")) {
            for (int i = 0; i < queries.length; i++) {
                if (queries[i].equals("WHERE")) {
                    Condition = queries[i + 1];
                    Sign = queries[i + 2];
                    if (queries[i + 3].contains(";")) {
                        queries[i + 3] = queries[i + 3].replaceAll(";", "");
                    }
                    COnditionValue = queries[i + 3];
                }
            }
        }

        ArrayList<String> columnValues = new ArrayList<>();
        int i;
        for (i = 1; i < queries.length; i++) {  //get number of columns requested
            if (queries[i].equalsIgnoreCase("FROM")) {
                break;
            } else {

                columnValues.add(queries[i]);
            }
        }
        if (parser.validatefile(queries[i + 1])) {
         //   Object [][]outcome = parser.selectElements(columnValues, queries[i + 1], Condition, Sign, COnditionValue);
            
            String[][] result = (String[][])  parser.selectElements(columnValues, queries[i + 1], Condition, Sign, COnditionValue);
            return result;
        }
        return null;
    }

    @Override
    public int executeUpdateQuery(String query) throws SQLException {

        int choice = ValidateStatement(query);
        Parser parser = Parser.getInstance();
        query = query.replaceAll("\'", "");
        String queries[] = query.split("[ (,)=]+|[ ( );]");

        if (choice == 1 && parser.validate(queries)) {
            parser.insertElement(queries);
            return 1;
        } else if (choice == 2) {
            int numberOfDeletedRows = parser.deleteElements(queries);
            return numberOfDeletedRows;

        } else if (choice == 3) {
            int numberOfUpdatedRows = parser.updateElements(queries);
            return numberOfUpdatedRows;

        } else {
            System.out.println("----");
            return 0;
        }

    }

    private int ValidateStatement(String query) {

        Pattern p1 = Pattern.compile("(INSERT)\\s+(INTO)\\s+\\w+\\s*\\(\\s*(\\w+\\s*(\\,\\s*)*)+\\)\\s*(VALUES)\\s*\\(\\s*(\\'*\\w+\\'*\\s*(\\,\\s*)*)+\\)\\s*;\\s*", Pattern.CASE_INSENSITIVE);
        Pattern p2 = Pattern.compile("(DELETE)\\s+(FROM)\\s+\\w+\\s+(WHERE)\\s+\\w+\\s*(=)\\s*\\'*\\w+\\'*\\s*;\\s*", Pattern.CASE_INSENSITIVE);
        Pattern p3 = Pattern.compile("(UPDATE)\\s+\\w+\\s+(SET)\\s+(\\w+\\s*(=)\\s*\\'\\w+\\s*\\'\\,*)*\\s+(WHERE)\\s+(\\w+\\s*(=)\\s*\\'\\s*\\w+\\s*\\')\\s*;\\s*", Pattern.CASE_INSENSITIVE);

        Matcher m1 = p1.matcher(query);
        Matcher m2 = p2.matcher(query);
        Matcher m3 = p3.matcher(query);

        if (m1.matches()) {
            // System.out.println("INSERT");
            return 1;
        } else if (m2.matches()) {
            // System.out.println("DELETE");
            return 2;
        } else if (m3.matches()) {
            //  System.out.println("UPDATE");
            return 3;

        } else {
            System.out.println("INVALID STATEMENT");
            return 0;
        }

    }
}
