
package eg.edu.alexu.cse.oop.draw.shapes;

import eg.edu.alexu.cse.oop.draw.Shape;
import java.awt.Color;
import java.awt.Point;

public abstract class AbstractShape implements Shape {

    protected Point position;
    protected Color color;
    protected Color fillColor;

    @Override
    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setFillColor(Color color) {
        this.fillColor = color;
    }

    @Override
    public Color getFillColor() {
        return fillColor;
    }

    @Override
    public abstract Object clone() throws CloneNotSupportedException;    
    
}
