/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memento;

import eg.edu.alexu.csd.oop.game.GameObject;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Youssef
 */
public class GameMemento implements Cloneable {

    private List<GameObject> moving;
    private List<GameObject> constant;
    private List<GameObject> control;
    private int score;
    private int time;       

    public GameMemento() { 
        moving = new LinkedList<>();
        constant = new LinkedList<>();
        control = new LinkedList<>();        
    }

    public void setMoving(List<GameObject> moving) {
        for (int i = 0; i < moving.size(); i++) {
            this.moving.add(moving.get(i));
        }
    }

    public void setConstant(List<GameObject> constant) {
        for (int i = 0; i < constant.size(); i++) {
            this.constant.add(constant.get(i));
        }
    }

    public void setControl(List<GameObject> control) {
        for (int i = 0; i < control.size(); i++) {
            this.control.add(control.get(i));
        }
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public GameMemento clone() {
        GameMemento memento = null;
        try {
            memento = (GameMemento) super.clone();
        } catch (CloneNotSupportedException ex) {            
        }
        return memento;
    }

    public List<GameObject> getConstant() {
        return constant;
    }

    public List<GameObject> getMoving() {
        return moving;
    }

    public List<GameObject> getControl() {
        return control;
    }   

    public int getTime() {
        return time;
    }

    public int getScore() {
        return score;
    }
}
