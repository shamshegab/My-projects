/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package difficulty;

import eg.edu.alexu.csd.oop.game.World;

/**
 *
 * @author Youssef
 */
public class Easy implements Difficulty{

    private World world;
    
    public Easy(World world){
        this.world = world;
    }            
    
    @Override
    public int getSpeed() {
        return 20;
    }

    @Override
    public int getControlSpeed() {
        return 30;
    }

    @Override
    public void generateObstacle() {        
        //do nothing
    }


    
}
