package eg.edu.alexu.cse.oop.draw.gui;

import eg.edu.alexu.cse.oop.draw.DrawingEngine;
import eg.edu.alexu.cse.oop.draw.Shape;
import eg.edu.alexu.cse.oop.draw.shapes.*;
import java.awt.Graphics;
import java.util.List;
import java.util.Stack;


public class Actions implements DrawingEngine {

    private Stack<Shape> shapesList = null;
    private Stack<Shape> recentlyUndone = null;
    private Stack<String> command = null;
    private Stack<String> undoCommand = null;
    private List<Class<? extends Shape>> classList = null;
    private static Actions actions;

    private Actions() {

        shapesList = new Stack();
        recentlyUndone = new Stack();
        command = new Stack();
        undoCommand = new Stack();
        classList = new Stack();
        classList.add(new Circle().getClass());
        classList.add(new Ellipse().getClass());
        classList.add(new Square().getClass());
        classList.add(new Rectangle().getClass());
        classList.add(new Triangle().getClass());
        classList.add(new LineSegment().getClass());
    }

    @Override
    public void addShape(Shape shape) {
        command.add("draw");
        shapesList.add(shape);
    }

    @Override
    public void removeShape(Shape shape) {
        recentlyUndone.add(shape);
        shapesList.remove(shape);
        command.add("delete");
    }

    @Override
    public Shape[] getShapes() {
        Shape[] shapes = new Shape[shapesList.size()];

        for (int i = 0; i < shapes.length; i++) {
            shapes[i] = shapesList.get(i);
        }
        return shapes;
    }

    @Override
    public void refresh(Graphics canvas) {
        for (int i = 0; i < shapesList.size(); i++) {
            shapesList.get(i).draw(canvas);
        }
    }

    @Override
    public List<Class<? extends Shape>> getSupportedShapes() {
        return this.classList;
    }

    @Override
    public void installPluginShape(Class<? extends Shape> shapeClass) {
        if (classList.size() == 7) {
            classList.remove(6);
        }
        classList.add(shapeClass);
    }

    @Override
    public void undo() {

        if (command.size() >= 20) {
            Stack<String> temp = new Stack();
            for (int i = 0; i < 19; i++) {
                temp.push(command.pop());
            }
            command.clear();    
            for (int i = 0; i < 19; i++) {
                command.push(temp.pop());
            }
        }
        if (command.peek().equals("draw")) {
            recentlyUndone.push(shapesList.peek());
            shapesList.pop();

            undoCommand.push("draw");
        } else {
            shapesList.push(recentlyUndone.peek());
            recentlyUndone.pop();

            undoCommand.push("delete");
        }
        command.pop();
    }

    @Override
    public void redo() {

        if (undoCommand.peek().equals("draw")) {
            shapesList.push(recentlyUndone.peek());
            recentlyUndone.pop();

            command.push("draw");

        } else {
            recentlyUndone.push(shapesList.peek());
            shapesList.pop();

            command.push("delete");
        }
        undoCommand.pop();
    }

    public static Actions getInstance() {
        if (actions == null) {
            actions = new Actions();
        }
        return actions;
    }
}
