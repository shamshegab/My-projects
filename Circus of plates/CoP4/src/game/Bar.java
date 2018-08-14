/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import observer.Observer;
import observer.Observable;
import eg.edu.alexu.csd.oop.game.GameObject;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Shams Sherif
 */
public class Bar implements GameObject, Observable {

    public static int LEFT_TOP = 0;
    public static int RIGHT_TOP = 1;
    public static int LEFT_DOWN = 2;
    public static int RIGHT_DOWN = 3;
    
    public final static int LONG_BAR_Y = 70;
    public final static int SHORT_BAR_Y = 150;

    private List<Observer> observers = new ArrayList<>();
    private int type;
    public final static int LONG_BAR_WIDTH = 350;
    public final static int SHORT_BAR_WIDTH = 200;
    public static final int SPRITE_HEIGHT = 5;
    private static final int MAX_MSTATE = 1;
    // an array of sprite images that are drawn sequentially
    private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
    private int x;
    private int y;
    private int width;
    private int endOfBar;
    private boolean visible;

    public Bar(int posX, int posY, int width, int type) {
        this.type = type;
        this.x = posX;
        this.y = posY;
        this.width = width;
        if (type == LEFT_TOP) {
            this.endOfBar = this.width;
        } else if (type == RIGHT_TOP) {
            this.endOfBar = CircusOfPlates.width - this.width;
        } else if (type == LEFT_DOWN) {
            this.endOfBar = this.width;
        } else {
            this.endOfBar = CircusOfPlates.width - this.width;
        }
        this.visible = true;
        // create a bunch of buffered images and place into an array, to be displayed sequentially
        spriteImages[0] = new BufferedImage(width, SPRITE_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = spriteImages[0].createGraphics();
        g2.setColor(Color.BLACK);
        g2.setBackground(Color.BLACK);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(20));
        g2.drawLine(0, 0, this.getWidth(), 0);
        g2.dispose();
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int mX) {
        this.x = mX;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int mY) {
        this.y = mY;
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return spriteImages;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return SPRITE_HEIGHT;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public void addObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyAllObservers() {
        java.util.Iterator<Observer> iter = observers.iterator();
        while(iter.hasNext()){
            iter.next().notifyObserver(this.endOfBar);
        }
    }

    public int getType() {
        return this.type;
    }
}
