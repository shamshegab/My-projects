package eg.edu.alexu.cse.oop.draw.shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.HashMap;
import java.util.Map;

public class Square extends AbstractShape {

    private Map<String, Double> properties;
    private Double width;    
    
    public Square(){     

        properties = new HashMap();
        properties.put("width", 0.0);
    }

    @Override
    public void setProperties(Map<String, Double> properties) {               
        this.width = properties.get("width");
        this.properties.put("width", width);        
    }

    @Override
    public Map<String, Double> getProperties() {
        return properties;
    }
   
    
    @Override
    public Object clone() throws CloneNotSupportedException {        
        Square square = new Square();
        square.setColor(color);
        square.setFillColor(fillColor);
        square.setPosition(position);
        square.setProperties(properties);
        
        return square;
    }
    
    
    @Override
    public void draw(Graphics canvas) {   
        Graphics2D canva =(Graphics2D) canvas;
        canva.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
        canva.setColor(fillColor);
        canva.fillRect(this.position.x, this.position.y, this.width.intValue(),  this.width.intValue());
        canva.setColor(color);                        
        canva.drawRect(this.position.x, this.position.y, this.width.intValue(),  this.width.intValue());                                
    }
    
}
