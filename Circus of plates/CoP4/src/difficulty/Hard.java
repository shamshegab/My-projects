/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package difficulty;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import game.CircusOfPlates;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import load.Loadable;
import load.ProxyAlien;
import state.Defeat;

/**
 *
 * @author Youssef
 */
public class Hard implements Difficulty {

    GameObject o1;
    GameObject o2;
    boolean alienOnScreen;
    CircusOfPlates world;
    int alienStart;
    int alienEnd;

    ProxyAlien proxyAlien;

    Loadable alien;

    public Hard(CircusOfPlates world) {

        this.world = world;
        proxyAlien = new ProxyAlien(world);
        world.getConstantObjects().add(proxyAlien);
        o2 = world.getControlableObjects().get(0);
        alienOnScreen = false;
        alienStart = 10 + new Random().nextInt(10);
        alienEnd = alienStart + 10;
    }

    @Override
    public int getSpeed() {
        return 10;
    }

    @Override
    public int getControlSpeed() {
        return 50;
    }

    @Override
    public void generateObstacle() {
        if (world.time < this.alienEnd && world.time > this.alienStart) {
            if (!alienOnScreen) {
                proxyAlien.drawImage(3);
                alienOnScreen = true;
            }
            if (proxyAlien.intersects(o2)) {
                world.setGameState(new Defeat(world));
            }
        } else if (world.time > this.alienEnd) {
            proxyAlien.removeImage();
        }
    }

}
