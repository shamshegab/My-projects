/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systemscompiler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Shams Sherif
 */
public class CodeGenration {

    private ArrayList<String[]> statments = new ArrayList<>();
    private ArrayList<String> reserved = new ArrayList<>();
    private Stack<String> arthi;
    int c = 0;
    File sic;
    String prgrmName;

    public CodeGenration(String file) throws FileNotFoundException, IOException {
        Lexical l = new Lexical(file);
        this.statments = l.getStatments();
        sic = new File("SIC-XE.txt");
        start();

    }

    void start() throws IOException {
        int i = 0;

        boolean t = true;
        while (i < statments.size() && t) {
            String statment[] = statments.get(i);
            for (int j = 0; j < statment.length; j++) {
                if ("PROGRAM".equals(statment[j])) {
                    prgrmName = statment[j + 1];
                    FileWriter wr = new FileWriter(sic);
                    BufferedWriter out = new BufferedWriter(wr);
                    out.write(prgrmName + "\t" + "START\t" + "0");
                    out.newLine();
                    out.close();
                    wr.close();
                    t = false;
                    break;
                }

            }
            i++;
        }
        genratebody(i);
    }
int n=0;
    void Arthi(int low, ArrayList<String> eq, BufferedWriter out) throws IOException {
        if (low == eq.size()) {
            return;
        }
        if (eq.get(low).contains("+")) {
            String b1 = eq.get(low - 1);
                String b2 = eq.get(low - 2);
            if (n == 0) {
                
                if (count(eq, low + 2) > 1) {
                    
                    out.write("\tLDA\t" + b1);
                    out.newLine();
                    out.write("\tADD\t" + b2);
                    out.newLine();
                    if (low + 1 != eq.size()) {
                        String temp = "T" + String.valueOf(c);
                        c++;
                        reserved.add(temp);
                        out.write("\tSTA\t" + temp);
                        out.newLine();
                        eq.set(low, temp);
                        if (low - 3 > -1) {
                            eq.set(low - 1, eq.get(low - 3));
                        }
                    }
                }else{
                    out.write("\tLDA\t" + b1);
                    out.newLine();
                    out.write("\tADD\t" + b2);
                    out.newLine();
                      if (low + 1 != eq.size()) {
                        String temp = "T" + String.valueOf(c);
                        c++;
                       
                        //out.write("\tSTA\t" + temp);
                        //out.newLine();
                        eq.set(low, temp);
                        if (low - 3 > -1) {
                            eq.set(low - 1, eq.get(low - 3));
                        }
                        n=1;
                    }
                }
            } else {
                if(b1.contains("T")){
                out.write("\tADD\t" + b2);
                }else{
                     out.write("\tADD\t" + b1);
                }
                out.newLine();
                n=0;
            }
            Arthi(low + 1, eq, out);

        } else if (eq.get(low).contains("*")) {
           String b1 = eq.get(low - 1);
                String b2 = eq.get(low - 2);
            if (n == 0) {
                
                if (count(eq, low + 2) > 1) {
            
                    out.write("\tLDA\t" + b1);
                    out.newLine();
                    out.write("\tMUL\t" + b2);
                    out.newLine();
                    if (low + 1 != eq.size()) {
                        String temp = "T" + String.valueOf(c);
                        c++;
                        reserved.add(temp);
                        out.write("\tSTA\t" + temp);
                        out.newLine();
                        eq.set(low, temp);
                        if (low - 3 > -1) {
                            eq.set(low - 1, eq.get(low - 3));
                        }
                    }
                }else{
                    out.write("\tLDA\t" + b1);
                    out.newLine();
                    out.write("\tMUL\t" + b2);
                    out.newLine();
                      if (low + 1 != eq.size()) {
                        String temp = "T" + String.valueOf(c);
                        c++;
                    
                        //out.write("\tSTA\t" + temp);
                        //out.newLine();
                        eq.set(low, temp);
                        if (low - 3 > -1) {
                            eq.set(low - 1, eq.get(low - 3));
                        }
                        n=1;
                    }
                }
            } else {
                if(b1.contains("T")){
                out.write("\tMUL\t" + b2);
                }else{
                     out.write("\tMUL\t" + b1);
                }
                out.newLine();
                n=0;
            }
            Arthi(low + 1, eq, out);
        } else {
            Arthi(low + 1, eq, out);
        }
    }

    void genratebody(int i) throws IOException {
        String extr = "";
        int f = 0;
        while (f < statments.size()) {
            String ch[] = statments.get(f);
            if (ch[0].contains("WRITE")) {
                if (!(extr.contains("XWRITE"))) {
                    extr += ",XWRITE";
                }

            }

            if (ch[0].contains("READ")) {
                if (!(extr.contains("XREAD"))) {

                    extr += "XREAD";
                }
            }
            f++;
        }
        FileWriter wr = new FileWriter(sic, true);
        BufferedWriter out = new BufferedWriter(wr);
        if (extr.length() > 1) {
            out.write("\tEXTREF\t" + extr);
            out.newLine();

        }
        out.close();
        wr.close();
        while (i < statments.size()) {
            String statment[] = statments.get(i);

            switch (statment[0]) {
                case "Declare Multiple":
                    for (int j = 1; j < statment.length; j++) {
                        if (statment[j].contains(";")) {
                            statment[j] = statment[j].replaceAll(";", "");
                        }
                        reserved.add(statment[j]);
                    }
                    break;
                case "Arthimitic":
                    wr = new FileWriter(sic, true);
                    out = new BufferedWriter(wr);
                    String dest = statment[1];
                    String equ = statment[2];
                    //.println(equ);
                    String finalresult = "";
                    ArrayList<String> s = getoperands(equ);
                    String srcA = "";
                    String srcB = "";
                    int k = 0;
                    Arthi(0, s, out);
                    out.write("\tSTA\t" + dest);
                    out.newLine();
                    out.close();
                    wr.close();

                    break;

                case "Declare one":
                    if (statment[1].contains(";")) {
                        statment[1] = statment[1].replaceAll(";", "");
                    }
                    reserved.add(statment[1]);
                    break;
                case "READ":
                    wr = new FileWriter(sic, true);
                    out = new BufferedWriter(wr);
                    out.write("\t+JSUB\tXREAD");
                    out.newLine();
                    out.write("\tWORD\t" + String.valueOf(statment.length - 1));
                    out.newLine();
                    for (int j = 1; j < statment.length; j++) {
                        out.write("\tWORD\t" + statment[j]);
                        out.newLine();
                    }
                    out.close();
                    wr.close();
                    break;

                case "WRITE":
                    wr = new FileWriter(sic, true);
                    out = new BufferedWriter(wr);
                    out.write("\t+JSUB\tXWRITE");
                    out.newLine();
                    out.write("\tWORD\t" + String.valueOf(statment.length - 1));
                    out.newLine();
                    for (int j = 1; j < statment.length; j++) {
                        out.write("\tWORD\t" + statment[j]);
                        out.newLine();
                    }
                    out.close();
                    wr.close();
                    break;

                case "Intial":
                    wr = new FileWriter(sic, true);
                    out = new BufferedWriter(wr);
                    out.write("\tLDA\t#" + statment[2]);
                    out.newLine();
                    out.write("\tSTA\t" + statment[1]);
                    out.newLine();
                    out.close();
                    wr.close();
                    break;

                default:

                    break;
            }
            i++;
        }
        genrateReserved();
    }

    void genrateReserved() throws IOException {
        FileWriter wr = new FileWriter(sic, true);
        BufferedWriter out = new BufferedWriter(wr);
        for (String reserv : reserved) {
            out.write(reserv + "\tRESW\t1");
            out.newLine();
        }
        out.write("\tEND\t" + prgrmName);

        out.close();

        wr.close();
        System.out.println("Compiled Succesfully");
    }

    ArrayList<String> getoperands(String equ
    ) {
        String postfix = Infix2Pstfix.infixToPostfix(equ);
        
        ArrayList<String> equat = new ArrayList<>();
        String s = "";
        int j = 0;
        for (int i = 0; i < postfix.length(); i++) {
            if (postfix.charAt(i) == '+' || postfix.charAt(i) == '*') {

                String both = postfix.substring(j, i);

                String var1 = getVar(both);
                String var2 = getVar(both.replaceAll(var1, ""));
                String remain = both.replaceAll(var1, "");
                String var3 = "";
                if (remain.length() > 0) {
                    var3 = remain.replace(var2, "");
                }

                j = i + 1;
                if (var1 != "") {
                    equat.add(var1);
                    //System.out.println(var1);
                }
                if (var2 != "") {
                    equat.add(var2);
                    // System.out.println(var2);
                }
                if (var3.length() > 0) {
                    equat.add(var3);
                    // System.out.println(var2);
                }
                equat.add(String.valueOf(postfix.charAt(i)));

            }
        }

        return equat;
    }

    private String getVar(String substring) {
        String var = "";
        for (int i = 0; i < substring.length(); i++) {
            String comp = substring.substring(0, substring.length() - i);
            //  System.out.println(comp);
            for (String res : reserved) {
                if (res.equals(comp)) {

                    return comp;
                }

            }
        }
        return var;
    }

    private int count(ArrayList<String> s, int j) {
        int count = 0;
        for (int i = j; i < s.size(); i++) {
            if (s.contains("+") || s.contains("*")) {
                count++;

            }
        }

        return count;
    }

}
