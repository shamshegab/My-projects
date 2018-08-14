/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapes;

import static game.Bar.SPRITE_HEIGHT;
import game.Shape;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 *
 * @author Shams Sherif
 */
public class Rectangle extends Shape {

    public Rectangle(int x, int y, Color color, int barType) {
        super(x, y, color, barType);        
    }

}
