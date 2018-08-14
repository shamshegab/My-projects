/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.edu.alexu.csd.oop.db;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.XMLConstants;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stax.StAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author ahmed
 */
public class Parser {

    private static Parser instance = new Parser();

    private Parser() {
    }

    public static Parser getInstance() {
        return instance;
    }

    String PAthname="Tables//";

    public String getPAthname() {
        return PAthname;
    }

    public void setPAthname(String PAthname) {
        this.PAthname = PAthname;
    }
    public void insertElement(String[] queries) {

        ArrayList<String> insertedCol = new ArrayList<>();
        ArrayList<String> insertedValues = new ArrayList<>();
        int i;
        for (i = 3; i < queries.length; i++) {
            if (queries[i].equalsIgnoreCase("VALUES")) {
                break;
            } else {
                insertedCol.add(queries[i]);
            }
        }

        for (int j = i + 1; j < queries.length; j++) {
            insertedValues.add(queries[j]);
        }

        String Path = getPAthname();
        i = 0;
        String str = "";

        String tableName = queries[2];
        try {
            boolean flag = false;

            Path = Path.concat("NewTable.xml");
            XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();
            XMLStreamWriter xMLStreamWriter = xMLOutputFactory.createXMLStreamWriter(new FileWriter(Path));
            String filePath = getPAthname() + tableName + ".xml";

            File file = new File(filePath);

            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = factory.createXMLEventReader(new FileInputStream(new File(filePath)));

            xMLStreamWriter.writeStartDocument("utf-8", "1.0");
           
            xMLStreamWriter.writeStartElement(tableName);

            ArrayList<String> columns = new ArrayList<>();
            ArrayList<String> values = new ArrayList<>();
            ArrayList<String> columnValues = new ArrayList<>();
            String column = "";
            while (eventReader.hasNext()) {

                XMLEvent event = eventReader.nextEvent();

                if (event.getEventType() == XMLStreamConstants.START_ELEMENT) {
                    StartElement startElement = event.asStartElement();
                    String start = startElement.getName().getLocalPart();
                    if (i == 0) {
                        i = 1;
                    } else if (i == 1) {
                        str = start;
                        i = 2;
                    } else if (i == 2) {
                        column = start;
                    }
                } else if (event.getEventType() == XMLStreamConstants.CHARACTERS) {
                    Characters characters = event.asCharacters();
                    String data = characters.getData();
                    if (data.matches(".*\\w.*")) {
                        columns.add(column);
                        values.add(data);
                        if (flag == false) {
                            columnValues.add(column);
                          
                        }
                    }
                } else if (event.getEventType() == XMLStreamConstants.END_ELEMENT) {
                    EndElement endElement = event.asEndElement();
                    String start = endElement.getName().getLocalPart();
                    if (start == str) {
                        flag = true;
                        xMLStreamWriter.writeStartElement("root");
                        for (int j = 0; j < columns.size(); j++) {
                            xMLStreamWriter.writeStartElement(columns.get(j));
                            xMLStreamWriter.writeCharacters(values.get(j));
                            xMLStreamWriter.writeEndElement();

                        }
                        xMLStreamWriter.writeEndElement();
                        columns.clear();
                        values.clear();

                    }
                }

            }

            xMLStreamWriter.writeStartElement("root");
            int j = 0;
            for (i = 0; i < insertedCol.size();) {

                if (!columnValues.get(j).equalsIgnoreCase(insertedCol.get(i))) {
                    xMLStreamWriter.writeStartElement(columnValues.get(j));
                    xMLStreamWriter.writeCharacters("0");
                    xMLStreamWriter.writeEndElement();
                    j++;
                } else {

                    xMLStreamWriter.writeStartElement(insertedCol.get(i));
                    xMLStreamWriter.writeCharacters(insertedValues.get(i));
                    xMLStreamWriter.writeEndElement();
                    i++;
                    j++;
                }
            }

            for (int k = j; k < columnValues.size(); k++) {
                xMLStreamWriter.writeStartElement(columnValues.get(k));
                xMLStreamWriter.writeCharacters("0");
                xMLStreamWriter.writeEndElement();

            }
            xMLStreamWriter.writeEndElement();
            xMLStreamWriter.writeEndElement();
            xMLStreamWriter.writeEndDocument();
            xMLStreamWriter.flush();
            xMLStreamWriter.close();

            eventReader.close();

        } catch (XMLStreamException e) {
        } catch (IOException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.gc();
            File oldFile = new File(getPAthname() + tableName + ".xml");

            File newFile = new File(getPAthname()+"newTable.xml");

            oldFile.delete();
            newFile.renameTo(oldFile);

        }

    }

    public int deleteElements(String[] queries) {

        String Path = getPAthname();
        int numberOfDeletedRows = 0;
        String condition = queries[4];
        String conditionValue = queries[5];

        int i = 0;
        String str = "";
        String start = "";
        String tableName = queries[2];
        boolean flag = true;
        try {

            Path = Path.concat("NewTable.xml");
            XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();
            XMLStreamWriter xMLStreamWriter = xMLOutputFactory.createXMLStreamWriter(new FileWriter(Path));
            String filePath = getPAthname() + tableName + ".xml";
            File file = new File(filePath);
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = factory.createXMLEventReader(new FileInputStream(file));

            xMLStreamWriter.writeStartDocument("utf-8", "1.0");
         
            xMLStreamWriter.writeStartElement(tableName);

            ArrayList<String> columns = new ArrayList<>();
            ArrayList<String> values = new ArrayList<>();

            String column = "";
            while (eventReader.hasNext()) {

                XMLEvent event = eventReader.nextEvent();

                if (event.getEventType() == XMLStreamConstants.START_ELEMENT) {
                    StartElement startElement = event.asStartElement();
                    start = startElement.getName().getLocalPart();
                    if (i == 0) {
                        i = 1;
                    } else if (i == 1) {
                        str = start;
                        i = 2;
                    } else if (i == 2) {

                        column = start;
                    }
                } else if (event.getEventType() == XMLStreamConstants.CHARACTERS) {
                    Characters characters = event.asCharacters();
                    String data = characters.getData();
                    if (data.matches(".*\\w.*")) {

                        if (start.equals(condition) && data.equals(conditionValue)) {

                            numberOfDeletedRows++;
                            flag = false;
                        }
                        columns.add(column);
                        values.add(data);
                    }
                } else if (event.getEventType() == XMLStreamConstants.END_ELEMENT) {
                    EndElement endElement = event.asEndElement();
                    start = endElement.getName().getLocalPart();
                    if (start == str && flag == true) {

                        xMLStreamWriter.writeStartElement("root");
                        for (int j = 0; j < columns.size(); j++) {
                            xMLStreamWriter.writeStartElement(columns.get(j));
                            xMLStreamWriter.writeCharacters(values.get(j));
                            xMLStreamWriter.writeEndElement();

                        }
                        xMLStreamWriter.writeEndElement();
                        columns.clear();
                        values.clear();

                    } else if (start == str && flag == false) {
                        flag = true;
                        columns.clear();
                        values.clear();
                    }
                }
            }
            xMLStreamWriter.writeEndElement();
            xMLStreamWriter.writeEndDocument();
            xMLStreamWriter.flush();
            xMLStreamWriter.close();
            eventReader.close();

        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.gc();
            File oldFile = new File(getPAthname() + tableName + ".xml");

            File newFile = new File(getPAthname()+"newTable.xml");
            oldFile.delete();
            newFile.renameTo(oldFile);
        }
        return numberOfDeletedRows;

    }

    public int updateElements(String[] queries) {

        ArrayList<String> updatedCol = new ArrayList<>();
        ArrayList<String> updatedValues = new ArrayList<>();
        int i;
        for (i = 3; i < queries.length; i++) {
            if (queries[i].equalsIgnoreCase("WHERE")) {
                break;
            } else if (i % 2 == 0) {
                updatedValues.add(queries[i]);
            } else {
                updatedCol.add(queries[i]);
            }
        }
        String condition = queries[i + 1];
        String conditionValue = queries[i + 2];

        String Path = getPAthname();
        i = 0;
        String str = "";
        String start = "";
        String tableName = queries[1];
        int numberOfUpdatedRows = 0;
        boolean flag = true;
        try {

            Path = Path.concat("NewTable.xml");
            XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();
            XMLStreamWriter xMLStreamWriter = xMLOutputFactory.createXMLStreamWriter(new FileWriter(Path));
            String filePath = getPAthname() + tableName + ".xml";
            File file = new File(filePath);
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = factory.createXMLEventReader(new FileInputStream(file));

            xMLStreamWriter.writeStartDocument("utf-8", "1.0");
       
            xMLStreamWriter.writeStartElement(tableName);

            ArrayList<String> columns = new ArrayList<>();
            ArrayList<String> values = new ArrayList<>();

            String column = "";
            while (eventReader.hasNext()) {

                XMLEvent event = eventReader.nextEvent();

                if (event.getEventType() == XMLStreamConstants.START_ELEMENT) {
                    StartElement startElement = event.asStartElement();
                    start = startElement.getName().getLocalPart();
                    if (i == 0) {
                        i = 1;
                    } else if (i == 1) {
                        str = start;
                        i = 2;
                    } else if (i == 2) {
                        column = start;
                    }
                } else if (event.getEventType() == XMLStreamConstants.CHARACTERS) {
                    Characters characters = event.asCharacters();
                    String data = characters.getData();
                    if (data.matches(".*\\w.*")) {

                        if (start.equals(condition) && data.equals(conditionValue)) {
                            numberOfUpdatedRows++;
                            flag = false;
                        }

                        columns.add(column);
                        values.add(data);
                    }
                } else if (event.getEventType() == XMLStreamConstants.END_ELEMENT) {
                    EndElement endElement = event.asEndElement();
                    start = endElement.getName().getLocalPart();
                    if (start == str && flag == true) {

                        xMLStreamWriter.writeStartElement("root");
                        for (int j = 0; j < columns.size(); j++) {
                            xMLStreamWriter.writeStartElement(columns.get(j));
                            xMLStreamWriter.writeCharacters(values.get(j));
                            xMLStreamWriter.writeEndElement();

                        }
                        xMLStreamWriter.writeEndElement();
                        columns.clear();
                        values.clear();

                    } else if (start == str && flag == false) {

                        for (int j = 0; j < updatedCol.size(); j++) {
                            for (int k = 0; k < columns.size(); k++) {
                                if (updatedCol.get(j).equals(columns.get(k))) {
                                    values.set(k, updatedValues.get(j));
                                    break;
                                }
                            }
                        }
                        xMLStreamWriter.writeStartElement("root");
                        for (int j = 0; j < columns.size(); j++) {
                            xMLStreamWriter.writeStartElement(columns.get(j));
                            xMLStreamWriter.writeCharacters(values.get(j));
                            xMLStreamWriter.writeEndElement();
                        }
                        xMLStreamWriter.writeEndElement();
                        columns.clear();
                        values.clear();
                        flag = true;
                    }
                }
            }

            xMLStreamWriter.writeEndElement();
            xMLStreamWriter.writeEndDocument();

            xMLStreamWriter.flush();
            xMLStreamWriter.close();

            eventReader.close();

        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.gc();
            File oldFile = new File(getPAthname() + tableName + ".xml");
            File newFile = new File(getPAthname()+"newTable.xml");

            oldFile.delete();
            newFile.renameTo(oldFile);
        }
        System.out.println("Updated");
        return numberOfUpdatedRows;
    }

    public Object[][] selectElements(ArrayList<String> targetValues, String tableName, String Condition, String Sign, String COnditionValue) throws SQLException {

        int n = 0;
        String filePath = getPAthname() + tableName + ".xml";
        Map s = new HashMap();
        s = this.getTypes(tableName);
        ArrayList<String[]> rows = new ArrayList<>();
        String row[] = new String[targetValues.size()];
        Map<String, String> map = new HashMap<>();
        boolean flag = false;
        String col = "";

        try {
            File file = new File(filePath);
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = factory.createXMLEventReader(new FileInputStream(file));

            while (eventReader.hasNext()) {

                XMLEvent event = eventReader.nextEvent();
                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT: {
                        StartElement startElement = event.asStartElement();
                        String start = startElement.getName().getLocalPart();
                        if (s.containsKey(start)) {
                            flag = true;
                            col = start;
                           
                        }
                        break;
                    }

                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        String data = characters.getData();
                        if (flag && data != null && !data.isEmpty() && !data.trim().isEmpty()) {
                            map.put(col, data);
                           
                            flag = false;
                            n++;
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT: {
                        EndElement startElement = event.asEndElement();
                        String start = startElement.getName().getLocalPart();
                        if (!(n < s.size())) {
                            if (Condition == null) {
                                for (int i = 0; i < targetValues.size(); i++) {
                                    row[i] = map.get(targetValues.get(i));
                                }
                                rows.add(row);

                            } else {
                                switch (Sign) {
                                    case "=":
                                        if (map.get(Condition).equals(COnditionValue)) {
                                            for (int i = 0; i < targetValues.size(); i++) {
                                                row[i] = map.get(targetValues.get(i));
                                            }
                                            rows.add(row);
                                        }
                                        break;
                                    case ">":
                                        if (Integer.parseInt(map.get(Condition)) > Integer.parseInt(COnditionValue)) {
                                            for (int i = 0; i < targetValues.size(); i++) {
                                                row[i] = map.get(targetValues.get(i));
                                            }
                                            rows.add(row);
                                        }
                                        break;
                                    case "<":
                                        if (Integer.parseInt(map.get(Condition)) < Integer.parseInt(COnditionValue)) {
                                            for (int i = 0; i < targetValues.size(); i++) {
                                                row[i] = map.get(targetValues.get(i));
                                            }
                                            rows.add(row);
                                        }
                                        break;
                                }
                            }
                            n = 0;
                            row = new String[targetValues.size()];

                        }
                        break;
                    }
                    default:
                        break;
                }

            }

        } catch (XMLStreamException e) {
        } catch (IOException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        String[][] result = new String[rows.size() + 1][targetValues.size()];
        for (int j = 0; j < targetValues.size(); j++) {
            result[0][j] = targetValues.get(j);
        }
        int m = 1;

        for (String a[] : rows) {
            for (int i = 0; i < a.length; i++) {
                result[m][i] = a[i];

            }
            m++;
        }

//        for (int j = 0; j < rows.size() + 1; j++) {
//            for (int k = 0; k < targetValues.size(); k++) {
//                System.out.printf("%10s\t", result[j][k]);
//            }
//
//            System.out.println();
//
//        }
        return result;
    }

    public Map getTypes(String tableName) {
        Map<String, String> datatypes = new HashMap();
        try {

            String filePath = getPAthname() + tableName + ".xsd";
            File file = new File(filePath);
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = factory.createXMLEventReader(new FileInputStream(file));
            while (eventReader.hasNext()) {

                XMLEvent event = eventReader.nextEvent();
                if (event.getEventType() == XMLStreamConstants.START_ELEMENT) {
                    StartElement startElement = event.asStartElement();
                    String start = startElement.getName().getLocalPart();

                    //System.out.println("Strat Element:  "+start);
                    if (String.valueOf(event).contains("type")) {

                        String colname = "";
                        String eventtype[] = String.valueOf(event).split("[ (,)=]+|[ ( );]");
                        for (int i = 0; i < eventtype.length; i++) {
                            if (eventtype[i].equals("name")) {
                                String col = eventtype[i + 1];
                                col = col.replace("'", "");
                                datatypes.put(col, "");
                                colname = col;
                            } else if (eventtype[i].equals("type")) {
                                String type = eventtype[i + 1];
                                type = type.replaceAll("'", "");
                                type = type.replaceAll("xs:", "");
                                type = type.replaceAll(">", "");

                                datatypes.put(colname, type);

                            }
                        }
                    }

                }

            }
        } catch (XMLStreamException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datatypes;
    }

    boolean validate(String[] queries) {
        ArrayList<String> insertedCol = new ArrayList<>();
        ArrayList<String> insertedValues = new ArrayList<>();
        int i;
        for (i = 3; i < queries.length; i++) {
            if (queries[i].equalsIgnoreCase("VALUES")) {
                break;
            } else {
                insertedCol.add(queries[i]);
            }
        }

        for (int j = i + 1; j < queries.length; j++) {
            insertedValues.add(queries[j]);
        }

        Map data = this.getTypes(queries[2]);
        for (int j = 0; j < insertedCol.size(); j++) {
            if (data.get(insertedCol.get(j)).equals("int")) {
                if (!(insertedValues.get(j).matches("[0-9]+"))) {
                    return false;
                }
            }
        }

        return true;
    }

    boolean validatefile(String filename) {
        try {
            String xml = getPAthname() + filename + ".xml";
            String xsd = getPAthname() + filename + ".xsd";
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(new FileInputStream(xml));

            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsd));

            javax.xml.validation.Validator validator = schema.newValidator();

            validator.validate(new StAXSource(reader));
        } catch (XMLStreamException ex) {
            System.out.println(ex);
            return false;
            
        } catch (SAXException ex) {
            System.out.println(ex);
            return false;
        } catch (IOException ex) {
            System.out.println(ex);
            return false;
        }
        return true;
    }

    boolean createTable(String query) {
        String startComplexType = "<xs:complexType>\n";
        String endComplexType = "</xs:complexType>\n";
        String startSequence = "<xs:sequence>\n";
        String endSequence = "</xs:sequence>\n";
        String queries[] = query.split("[ (,)=]+|[ ( );]");
        String Path = getPAthname();
        try {

            Pattern p = Pattern.compile("(create)\\s+(table)\\s+\\w+\\s*\\(\\s*(\\w+\\s+(varchar|int)(\\,)*)+\\);", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(query);
            if (m.matches()) {
                System.out.println("TABLE CREATED");
            } else {
                System.out.println("INVALID STATEMENT");
                return false;
            }

            // String Path = "C:\\Users\\User\\Documents\\NetBeansProjects\\Project2\\Project2\\src\\eg\\edu\\alexu\\csd\\oop\\db\\";
            Path = Path.concat(queries[2]);
            XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();
            XMLStreamWriter xMLStreamWriter = xMLOutputFactory.createXMLStreamWriter(new FileWriter(Path.concat(".xml")));

            FileWriter fw = new FileWriter(Path.concat(".xsd"));
            BufferedWriter bw = new BufferedWriter(fw);

            String fileName = queries[2];

            xMLStreamWriter.writeStartDocument("utf-8", "1.0");

            xMLStreamWriter.writeStartElement(queries[2]);
          
            int x = queries.length;

            bw.write("<?xml version = \"1.0\"?>\n");
            bw.write("<xs:schema xmlns:xs = \"http://www.w3.org/2001/XMLSchema\">\n");
            bw.write("<xs:element name = " + "\'" + queries[2] + "\'/>\n");
            bw.write(startComplexType);
            bw.write(startSequence);
            bw.write("<xs:element name = " + "\'" + "root" + "\'/>\n");
            bw.write(startComplexType);
            bw.write(startSequence);

            for (int i = 3; i < x; i += 2) {
                if (queries[i + 1].equals("varchar")) {
                    bw.write("<xs:element name=\"" + queries[i] + "\" type=\"xs:" + "string" + "\"" + " minOccurs=\"0\" maxOccurs =\"unbounded\"/>\n");
                } else {
                    bw.write("<xs:element name=\"" + queries[i] + "\" type=\"xs:" + queries[i + 1] + "\"" + " minOccurs=\"0\" maxOccurs =\"unbounded\"/>\n");
                }
            }
            bw.write(endSequence);
            bw.write(endComplexType);

            bw.write(endSequence);
            bw.write(endComplexType);

            bw.write("</xs:schema>");
            String elements = "(";
            for (int i = 3; i < x; i += 2) {
               
                if (i != x - 2) {
                    elements = elements.concat(",");
                }
            }
            elements = elements.concat(")");
            //   System.out.println(elements);

//                bw.write("<!ELEMENT " + queries[1] + " " + elements + ">\n");
//                for (int i = 2; i < x; i += 2) {
//                    bw.write("<!ELEMENT " + queries[i] + " EMPTY>\n");
            // }
         
            xMLStreamWriter.writeEndElement();
            xMLStreamWriter.writeEndDocument();

            xMLStreamWriter.flush();
            xMLStreamWriter.close();
            bw.flush();
            bw.close();
            System.gc();
            return true;

        } catch (XMLStreamException | IOException e) {
            return false;
        }

    }

}
