/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import eg.edu.alexu.csd.oop.game.GameEngine;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 *
 * @author Shams Sherif
 */
public class Game {

    /**
     * @param args the command line arguments
     */
    static JOptionPane p = new JOptionPane();
    public static void main(String[] args) {
    
         CircusOfPlates c = new CircusOfPlates(1000,600);
        JPanel pane = new JPanel();
        JLabel l1= new JLabel("Difficulty");
        pane.add(l1);
        String s[]={"Easy","Medium","Hard"};
        JComboBox combo = new JComboBox(s);
        pane.add(combo);
         int value = JOptionPane.showConfirmDialog(p, pane, "Enter Properties", JOptionPane.OK_CANCEL_OPTION);
        if (value == JOptionPane.OK_OPTION) {
            if(combo.getSelectedIndex()==0){
                c.setC('e');
            }else if(combo.getSelectedIndex()==1){
                 c.setC('m');
            }else{
                 c.setC('h');
            }
        }
        
        
        JMenuBar  menuBar = new JMenuBar();;
		JMenu menu = new JMenu("File");
		JMenuItem  exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menu.add(exitMenuItem);
		menuBar.add(menu);
//		GameEngine.start("Very Simple Game in 99 Line of Code", new eg.edu.alexu.csd.oop.game.sample.world.StarWar(800, 600), menuBar, Color.BLACK);

		/* -------------------------------------------------------------------- */
		/* allow pause, resume, and restart multiple worlds in the same frame */
		
		JMenuItem newMenuItem = new JMenuItem("New");
		JMenuItem pauseMenuItem = new JMenuItem("Pause");
		JMenuItem resumeMenuItem = new JMenuItem("Resume");
		menu.add(newMenuItem);
		menu.addSeparator();
		menu.add(pauseMenuItem);
		menu.add(resumeMenuItem);
		menuBar.add(menu);
                final GameEngine.GameController gameController = GameEngine.start("3afota", c,menuBar); 
                newMenuItem.addActionListener(new ActionListener() {
		@Override public void actionPerformed(ActionEvent e) {
				gameController.changeWorld(new CircusOfPlates(1000, 600));
                                c.setC('m');
			}
		});
		pauseMenuItem.addActionListener(new ActionListener() {
		@Override public void actionPerformed(ActionEvent e) {
				gameController.pause();
			}
		});
		resumeMenuItem.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				gameController.resume();
			}
		});
        
      
    }

}
