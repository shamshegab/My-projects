/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systemscompiler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Shams Sherif
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner in = new Scanner(System.in);
        CodeGenration c = new CodeGenration(in.nextLine().trim());
       
       // System.out.println(in.infixToPostfix("(Adsa+Bads)*(C+D)"));
        
    }
}
