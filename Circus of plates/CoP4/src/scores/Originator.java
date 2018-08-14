/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scores;

import scores.Memento;

/**
 *
 * @author Shams Sherif
 */
public class Originator {
    private String b[]=new String[2];
    
    public void set(String name, String score){
      
        b[0]=name;
        b[1]=score;
       
         // System.out.println("Org1: "+b);
    }
    
    public Memento store(){
        //System.out.println("Org2:Store");
        return new Memento(b);
    }
    
    public String[] restore(Memento m){
        b= m.getSavedArt();
       // System.out.println("Org3:"+b );
        return b;
    }
}
