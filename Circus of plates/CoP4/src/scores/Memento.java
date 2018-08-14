/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scores;

/**
 *
 * @author Shams Sherif
 */
public class Memento {
   String s[]= new String[2];
    
    public Memento( String a[]){
        s[0]=a[0];
        s[1]=a[1];
       
    }
    
    public String[] getSavedArt(){
        return s;
    }
}
