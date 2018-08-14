/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systemscompiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Shams Sherif
 */
public class Lexical {

    private ArrayList<String> tokens = new ArrayList<>();
    private ArrayList<String> speci = new ArrayList<>();
    private ArrayList<String[]> statments = new ArrayList<>();
    private String eq = "";

    public ArrayList<String[]> getStatments() {
        return statments;
    }
    Pattern Program = Pattern.compile("(PROGRAM)\\s*(\\w+)", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    Pattern Begin = Pattern.compile("(BEGIN)\\s*", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    Pattern intMulti = Pattern.compile("(VAR)\\s*(((?:[a-z][a-z0-9_]*))\\s*(,)\\s*((?:[a-z][a-z0-9_]*)).*(;*))+", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    Pattern intIntial = Pattern.compile("(VAR)\\s+((?:[a-z][a-z0-0_]*))\\s*(:=)\\s*(\\d+)\\s*", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    Pattern intDeclare = Pattern.compile("(VAR)\\s+((?:[a-z][a-z0-0_]*))(\\s+)?(\\s+)?", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    Pattern intial = Pattern.compile("((?:[a-z][a-z0-0_]*))\\s*(:=)\\s*(\\d+)\\s*;*", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    Pattern arthimtic = Pattern.compile("(?i)\\s*((?:[a-z][a-z0-0_]*))\\s*:=(\\s*(\\w+)\\s*(([+*])\\s*(\\w+)\\s*)+)(;*)", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    Pattern condition = Pattern.compile("if\\s*\\(\\s*((?:[a-z][a-z0-9_]*|\\d+))\\s*(.)\\s*((?:[a-z][a-z0-9_]*|\\d+))\\s*(\\))", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    Pattern end = Pattern.compile("(END)(\\.)\\s*", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    Pattern var = Pattern.compile("(VAR)\\s*\\n*", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    Pattern write = Pattern.compile("(?i)\\s*WRITE\\s*\\((\\s*[a-zA-Z_]\\w*\\s*(,\\s*[a-zA-Z_]\\w*\\s*)*)\\)\\s*;*", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    Pattern read = Pattern.compile("(?i)\\s*READ\\s*\\((\\s*[a-zA-Z_]\\w*\\s*(,\\s*[a-zA-Z_]\\w*\\s*)*)\\)\\s*;*", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

    private void Read(String file) throws FileNotFoundException {

        File f = new File(file);
        Scanner in = new Scanner(f);
        while (in.hasNext()) {
            String line = in.nextLine();
            Matcher m = var.matcher(line);
            String d = line.trim();
            d = d.replaceAll(" ", "");
            if (d.equals("VAR")) {
                line += " " + in.nextLine() + ";";

            } else if (line.contains("READ") || line.contains("WRITE")) {

            } else if (line.contains("(")) {
                int j = 0;
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == '=') {
                        j = i + 1;
                        break;
                    }
                }
                eq = line.substring(j);
                line = line.replaceAll("\\(", "");
                line = line.replaceAll("\\)", "");

            }
            // System.out.println(line);
            chk(line);

        }
        
       
       
    }

    private void chk(String line) {
        int found = -1;
        Matcher m = Program.matcher(line);
        Pattern[] patterns = {Program, Begin, intMulti, intIntial, intDeclare, arthimtic, end, read, write,intial};
        for (int i = 0; i < patterns.length; i++) {
            m = patterns[i].matcher(line);
            if (m.find()) {
                found = i;
                break;
            }
        }

        switch (found) {
            case 0:
                String stat[] = new String[2];
                stat[0] = m.group(1);
                stat[1] = m.group(2);
                statments.add(stat);
                tokens.add(m.group(1));
                tokens.add(m.group(2));
                speci.add(null);
                speci.add(null);

                break;
            case 1:
                stat = new String[1];
                stat[0] = m.group(0);
                statments.add(stat);
                tokens.add(m.group(0));
                speci.add(null);

                break;
            case 2:
                tokens.add(m.group(1));
                speci.add(null);
                String t = m.group(2);

                t = t.trim();
                String[] s = t.split(",");
                stat = new String[s.length + 1];
                stat[0] = "Declare Multiple";
                for (int i = 0; i < s.length; i++) {
                    if (s[i].contains(",") || s[i].contains(";")) {
                        tokens.add(s[i]);
                        speci.add(null);
                    } else {
                        tokens.add("id");
                        speci.add(s[i]);
                    }

                    stat[i + 1] = s[i].trim();
                }
                statments.add(stat);
                break;
            case 3:
              
                stat = new String[3];
                stat[0] = "Declare&Intilize";
                stat[1] = m.group(2);
                stat[2] = m.group(4);
                statments.add(stat);
                tokens.add(m.group(1));
                tokens.add(m.group(2));
                tokens.add(m.group(3));
                tokens.add(m.group(4));

                break;
            case 4:

                stat = new String[2];
                stat[0] = "Declare one";
                stat[1] = m.group(2);
                statments.add(stat);
                tokens.add(m.group(1));
             
                tokens.add(m.group(2));

                break;

            case 5:
                speci.add(m.group(1));
                speci.add(null);
                tokens.add("id");
                tokens.add(":=");
               
                String l = m.group(2);
                if (eq != "") {
                    l = eq;
                    eq="";
                }

          
                l = l.trim();
                l = l.replaceAll(" ", "");
                s = l.split("\\+|\\*");

                stat = new String[3];
              
                stat[1] = m.group(1);
                stat[0] = "Arthimitic";
                stat[2] = l;
                for (int i = 0; i < s.length; i++) {
                    if (s[i].contains("+") || s[i].contains("*")) {
                        tokens.add(s[i]);
                        speci.add(null);
                    } else {
                        tokens.add("id");
                        speci.add(s[i]);
                    }

                }
                if (m.group(0).contains(";")) {
                    tokens.add(";");
                    speci.add(null);
                }
                statments.add(stat);
                break;
            case 6:
                stat = new String[1];
     
                stat[0] = "End";
                statments.add(stat);
                tokens.add(m.group(0));
                speci.add(null);
                break;
            case 7:

                String r[] = m.group(1).split(",");
                stat = new String[r.length + 1];
                stat[0] = "READ";
                for (int i = 0; i < r.length; i++) {
                    stat[i + 1] = r[i];
                }

                statments.add(stat);
               
                break;

            case 8:
                String w[] = m.group(1).split(",");
                stat = new String[w.length + 1];
                stat[0] = "WRITE";
                for (int i = 0; i < w.length; i++) {
                    stat[i + 1] = w[i];
                }

                statments.add(stat);
               

                break;
            case 9:
                 stat = new String[3];
                 stat[0]="Intial";
                 stat[1]=m.group(1);
                 stat[2]=m.group(2);
                 statments.add(stat);
                
                break;
            case -1:
                System.out.println("Grammer error in \""+line+"\"");
                System.exit(0);
                //  System.out.println("wrong grammer");
                break;

        }

    }

    public Lexical(String file) throws FileNotFoundException {

        Read(file);

    }

    public ArrayList<String> gettokens() {
        return tokens;
    }

    public ArrayList<String> getSpeci() {
        return speci;
    }

   

}
