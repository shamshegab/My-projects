/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import game.CircusOfPlates;

/**
 *
 * @author Youssef
 */
public class Defeat implements GameState {

    private CircusOfPlates game;
    
    public Defeat(CircusOfPlates game) {
        this.game = game;                
    }

    @Override
    public int getScore() {
        return 0;
    }


    @Override
    public void addScore() {
    }

    @Override
    public void showLeaderBoard() {        
    }

}
