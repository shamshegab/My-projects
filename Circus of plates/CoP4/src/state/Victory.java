/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import game.CircusOfPlates;
import game.LeaderBoard;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import scores.Caretaker;
import scores.Originator;

/**
 *
 * @author Youssef
 */
public class Victory implements GameState {

    private CircusOfPlates game;

    private boolean win = false;

    public Victory(CircusOfPlates game) {
    }

    @Override
    public int getScore() {
        return game.score;
    }

    @Override
    public void addScore() {
        if (!win) {
            JOptionPane dialog = new JOptionPane();
            JPanel p = new JPanel();
            JLabel lname = new JLabel("Name: ");
            JTextField name = new JTextField(15);
            p.add(lname);
            p.add(name);
            int value = JOptionPane.showConfirmDialog(dialog, p, "Enter Your Name", JOptionPane.OK_CANCEL_OPTION);
            if (value == JOptionPane.OK_OPTION) {
                Originator o = new Originator();
                Caretaker c = Caretaker.getInstance();
                o.set(name.getText(), String.valueOf(getScore()));
                c.add(o.store());

                this.showLeaderBoard();
            }
        }
        win = true;
    }

    @Override
    public void showLeaderBoard() {
        try {
            Caretaker c = Caretaker.getInstance();
            Caretaker d = c.getCareTaker();
            LeaderBoard l = new LeaderBoard(d);
            l.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(Victory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
