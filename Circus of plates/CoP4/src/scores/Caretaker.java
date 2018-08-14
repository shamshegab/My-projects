/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scores;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shams Sherif
 */
public class Caretaker {
   private ArrayList<Memento> saved =new ArrayList<Memento>();
    private static Caretaker caretaker;
   private Caretaker(){
       
   }
    public static Caretaker getInstance() {
        if (caretaker == null) {
            caretaker = new Caretaker();
        }
        return caretaker;
    }
    
    public void add(Memento m){
        
        File f = new File("scores.txt");
       try {
           FileWriter w = new FileWriter(f,true);
           PrintWriter out = new PrintWriter(w);
           System.out.println(m.getSavedArt()[0]+'-');
           out.println(m.getSavedArt()[0]);
           out.println(m.getSavedArt()[1]);
           out.close();
           w.close();
       } catch (IOException ex) {
           Logger.getLogger(Caretaker.class.getName()).log(Level.SEVERE, null, ex);
       }
        
    }
    public Memento getMemonto(int i){
        return saved.get(i);
    }
    public Caretaker getCareTaker() throws FileNotFoundException, IOException{
        
         File f = new File("scores.txt");
        FileReader r = new FileReader(f);
        Scanner in = new Scanner(r);
        
        while (in.hasNext()) {            
            Originator o = new Originator();
            o.set(in.nextLine(), in.nextLine());
            saved.add(o.store());
        }
        in.close();
        r.close();
        return this;
    }
    public int getSize(){
        return saved.size();
    }

    
}
