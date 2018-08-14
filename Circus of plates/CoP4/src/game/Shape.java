package game;

import observer.Observer;
import strategy.StartegyControlled;
import eg.edu.alexu.csd.oop.game.GameObject;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import strategy.NotControlled;

/**
 *
 * @author Youssef
 */
public abstract class Shape implements GameObject, Observer {

    private static final int MAX_MSTATE = 1;
    protected BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
    protected boolean falling = false;
    protected int x;
    protected int y;
    protected Color color;
    protected int barType;
    protected boolean visible;
    protected StartegyControlled controltype;
    private String className;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Shape(int x, int y, Color color, int barType) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.barType = barType;
        this.visible = true;
        this.controltype = new NotControlled();
//        draw();
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int mX) {
//        if (controltype.control()) {
//
//            int n = getX() - mX;
//
//            if (n > 0) {
//                if (mX > 70) {
//                    this.x = mX;
//                }
//            } else if (n < 0) {
//               if (mX < 870) {
//
//                    this.x = mX;
//                }
//            }
//        } else {
//
//            this.x = mX;
//        }
////        this.x = mX;
        this.x = mX;

    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int mY) {
        if (!controltype.control()) {
            this.y = mY;
        }
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
//        spriteImages[0].getGraphics().setColor(color);
        this.color = color;

        // spriteImages[0] = draw();
    }

    public void setControlType(StartegyControlled s) {
        this.controltype = s;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public void setBarType(int barType) {
        this.barType = barType;
    }

    public int getBarType() {
        return this.barType;
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return this.spriteImages;
    }

    @Override
    public int getWidth() {
        return spriteImages[0].getWidth();
    }

    public void draw() {
     
        if (className.equals("Plate")) {
            BufferedImage b = new BufferedImage(80, 30, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = b.createGraphics();
            g2.setColor(color);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            double x = 70 / 2;
            double y = 30 / 2;
            int x1 = (int) ((80 / 2.0) - x);
            int y1 = (int) ((30 / 2.0) - y);
            int x2 = (int) ((80 / 2.0) + x);
            int y2 = (int) ((30 / 2.0) + y);
            g2.setStroke(new BasicStroke(3));
            g2.fillOval(x1 + 3, y1 + 3, x2 - 6, y2 - 6);
            g2.setColor(getColor());
            g2.drawArc(x1 + 12, y1 + 8, x2 - 22, y2 - 22, 5, 80);
            g2.dispose();
            spriteImages[0] = b;
        } else {

            int width = 80;

            this.visible = true;
            // create a bunch of buffered images and place into an array, to be displayed sequentially
            spriteImages[0] = new BufferedImage(width, 20, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = spriteImages[0].createGraphics();
            g2.setColor(color);
            g2.setBackground(color);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setStroke(new BasicStroke(30));
            g2.drawLine(0, 0, getWidth(), 0);
            g2.dispose();
        }

    }

    @Override
    public int getHeight() {
        return spriteImages[0].getHeight();
    }

    @Override
    public void notifyObserver(int endOfBar) {

        if (this.barType == Bar.LEFT_DOWN || this.barType == Bar.LEFT_TOP) {
            if (this.isFalling()) {
                this.setY(this.getY() + 4);
            } else {
                this.setX(this.getX() + 2);
            }
            if (this.getX() > endOfBar) {
                this.setFalling(true);
            }
        } else {
            if (this.isFalling()) {
                this.setY(this.getY() + 4);
            } else {
                this.setX(this.getX() - 2);
            }
            if (this.getX() < endOfBar - 75) {
                this.setFalling(true);
            }
        }

    }
}
