/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memento;

import eg.edu.alexu.csd.oop.game.GameObject;
import game.CircusOfPlates;
import java.util.List;

/**
 *
 * @author Youssef
 */
public class GameOriginator {

    private CircusOfPlates world;

    public GameOriginator(CircusOfPlates world) {
        this.world = world;
    }

    public GameMemento storeInMemento() {
        GameMemento memento = new GameMemento();
        memento.setConstant(world.constant);
        memento.setControl(world.control);
        memento.setMoving(world.moving);
        memento.setScore(world.score);        
        memento.setTime(world.time);
        return memento;
    }

    public List<GameObject> restoreConstants(GameMemento memento) {
        return memento.getConstant();
    }

    public List<GameObject> restoreControl(GameMemento memento) {
        return memento.getControl();
    }

    public List<GameObject> restoreMoving(GameMemento memento) {
        return memento.getMoving();
    }

    public int restoreTime(GameMemento memento) {
        return (int) (System.currentTimeMillis() - memento.getTime() * 1000);
    }

    public int restoreScore(GameMemento memento) {
        return memento.getScore();
    }

}
