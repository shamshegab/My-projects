package eg.edu.alexu.cse.oop.draw.shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.HashMap;
import java.util.Map;

public class Ellipse extends AbstractShape {

    private Map<String, Double> properties;
    private Double width;  
    private Double height;
    
    public Ellipse(){             
        properties = new HashMap();
        properties.put("width", 0.0);
        properties.put("height", 0.0);
    }
   
    @Override
    public void setProperties(Map<String, Double> properties) {               
        this.width = properties.get("width");
        this.height = properties.get("height");
        this.properties.put("width", width);        
        this.properties.put("height", height);
    }

    @Override
    public Map<String, Double> getProperties() {
        return properties;
    }    
    
    @Override
    public Object clone() throws CloneNotSupportedException {        
        Ellipse circle = new Ellipse();
        circle.setColor(color);
        circle.setFillColor(fillColor);
        circle.setPosition(position);
        circle.setProperties(properties);
        
        return circle;
    }

    @Override
    public void draw(Graphics canvas) {
        Graphics2D canva =(Graphics2D) canvas;
        canva.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
        canva.setColor(fillColor);
        canva.fillOval(position.x, position.y, width.intValue(), height.intValue());
        canva.setColor(color);
        canva.drawOval(position.x, position.y, width.intValue(), height.intValue());
    }
}
