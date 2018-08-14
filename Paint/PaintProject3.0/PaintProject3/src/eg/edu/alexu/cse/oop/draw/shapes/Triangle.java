package eg.edu.alexu.cse.oop.draw.shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.HashMap;
import java.util.Map;

public class Triangle extends AbstractShape {

    private Double width;
    private Double height;

    private Map<String, Double> properties;

    public Triangle(){
        properties = new HashMap();
        properties.put("base", 0.0);
        properties.put("height", 0.0);
    }

    @Override
    public void setProperties(Map<String, Double> properties) {                  
        this.width = properties.get("base");
        this.height = properties.get("height");
        this.properties.put("base", width);
        this.properties.put("height", height);        
    }

    @Override
    public Map<String, Double> getProperties() {
        return properties;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {        
        Triangle triangle = new Triangle();
        triangle.setColor(color);
        triangle.setFillColor(fillColor);
        triangle.setPosition(position);
        triangle.setProperties(properties);
        
        return triangle;        
    }
    
    @Override
    public void draw(Graphics canvas) {
        Graphics2D canva =(Graphics2D) canvas;
        canva.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
        canva.setColor(fillColor);           
        int x2 = (int) (position.x + width);         
        int x3 = (int) (position.x + width / 2.0);
        int y2 = position.y;
        int y3 =(int) position.y - height.intValue();
        canva.fillPolygon(new int[]{x3, x2 , position.x }, new int[]{y3, y2 , position.y }, 3);
        canva.setColor(color);
        canva.drawPolygon(new int[]{x3, x2 , position.x }, new int[]{y3, y2 , position.y }, 3);       
    }

}
