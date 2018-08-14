/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package difficulty;

import eg.edu.alexu.csd.oop.game.World;

/**
 *
 * @author Shams Sherif
 */
public class Medium implements Difficulty{
    private World world;
    
    public Medium(World world){
        this.world = world;
    }            
    
    @Override
    public int getSpeed() {
        return 10;
    }

    @Override
    public int getControlSpeed() {
        return 40;
    }

    @Override
    public void generateObstacle() {        
        //do nothing
    }
    
}
