/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memento;

/**
 *
 * @author Youssef
 */
public class GameCareTaker {

    private GameMemento lastMemento;
        

    public void preserveState(GameMemento memento) {
        this.lastMemento =  memento.clone();
    }

    public GameMemento getMemento() {
        return this.lastMemento;
    }

}
