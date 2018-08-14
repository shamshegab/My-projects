/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flyweight;

import game.CircusOfPlates;
import game.Shape;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Shams Sherif
 */
public class FlyWeightFactory {

    public static List<Class<? extends Shape>> classList = new ArrayList<>();
    public static int LEFT_TOP = 0;
    public static int RIGHT_TOP = 1;
    public static int LEFT_DOWN = 2;
    public static int RIGHT_DOWN = 3;
    int[] types = {LEFT_DOWN, LEFT_TOP, RIGHT_DOWN, RIGHT_TOP};
    private static final Color colors[] = {Color.red, Color.GREEN, Color.BLUE, Color.YELLOW};
    static HashMap<Integer, Shape> shapes = new HashMap<Integer, Shape>();
    static Random r = new Random();

    public static Shape getShape(int mode) {
        Shape s = (Shape) shapes.get(mode);

        if (s == null) {
            if (mode == LEFT_DOWN) {
                s = (Shape) new Shape(0, 125, colors[r.nextInt(4)], mode) {
                };

                if (r.nextInt(2) == 0) {
                    s.setClassName(classList.get(0).getSimpleName());

                } else {
                    s.setClassName(classList.get(1).getSimpleName());
                }
                s.draw();
            } else if (mode == LEFT_TOP) {
                s = (Shape) new Shape(0, 45, colors[r.nextInt(4)], mode) {
                };
                if (r.nextInt(2) == 0) {
                    s.setClassName(classList.get(0).getSimpleName());
                } else {
                    s.setClassName(classList.get(1).getSimpleName());
                }
                s.draw();
            } else if (mode == RIGHT_TOP) {
                s = (Shape) new Shape(CircusOfPlates.width, 45, colors[r.nextInt(4)], mode) {
                };
                if (r.nextInt(2) == 0) {
                    s.setClassName(classList.get(0).getSimpleName());
                } else {
                    s.setClassName(classList.get(1).getSimpleName());
                }
                s.draw();
            } else if (mode == RIGHT_DOWN) {
                s = (Shape) new Shape(CircusOfPlates.width, 125, colors[r.nextInt(4)], mode) {
                };
                if (r.nextInt(2) == 0) {
                    s.setClassName(classList.get(0).getSimpleName());
                } else {
                    s.setClassName(classList.get(1).getSimpleName());
                }
                s.draw();
            }
            shapes.put(mode, s);

        }

        return s;
    }

    public static void removeShape(int mode) {
        shapes.remove(mode);
    }
}
