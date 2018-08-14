package eg.edu.alexu.cse.oop.draw.shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.HashMap;
import java.util.Map;

public class Circle extends AbstractShape {
    
    private Map<String, Double> properties;
    private Double diameter;                
    
    public Circle(){             
        properties = new HashMap();
        properties.put("diameter", 0.0);
    }

    @Override
    public void setProperties(Map<String, Double> properties) {               
        this.diameter = properties.get("diameter");
        this.properties.put("diameter", diameter);        
    }

    @Override
    public Map<String, Double> getProperties() {
        return properties;
    }   
    
    @Override
    public Object clone() throws CloneNotSupportedException {        
        Circle circle = new Circle();
        
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
        canva.fillOval(position.x, position.y, diameter.intValue(), diameter.intValue());
        canva.setColor(color);
        canva.drawOval(position.x, position.y, diameter.intValue(), diameter.intValue());
    }

}
