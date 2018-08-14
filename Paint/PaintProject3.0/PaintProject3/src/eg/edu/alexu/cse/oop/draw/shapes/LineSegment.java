package eg.edu.alexu.cse.oop.draw.shapes;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.HashMap;
import java.util.Map;

public class LineSegment extends AbstractShape {

    private Map<String, Double> properties;
    private Double weight =0.0;
    private Double endX;
    private Double endY;

    
    public LineSegment() {
        properties = new HashMap();        
        properties.put("Y2", 0.0);
        properties.put("X2", 0.0);
        properties.put("weight", 0.0);
    }


    @Override
    public void setProperties(Map<String, Double> properties) {
        this.weight = properties.get("weight");
        this.endX = properties.get("X2");
        this.endY = properties.get("Y2");
        this.properties.put("weight", weight);
        this.properties.put("Y2", endY);
        this.properties.put("X2", endX);
    }

    @Override
    public Map<String, Double> getProperties() {
        return properties;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        LineSegment line = new LineSegment();
        line.setColor(color);
        line.setFillColor(color);
        line.setPosition(position);
        line.setProperties(properties);

        return line;
    }

    @Override
    public void draw(Graphics canvas) {
        Graphics2D g2d = (Graphics2D) canvas;
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(this.weight.intValue()));
        g2d.drawLine(position.x, position.y, endX.intValue(), endY.intValue());        
    }

}
