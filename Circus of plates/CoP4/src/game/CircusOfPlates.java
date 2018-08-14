/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import shapes.Plate;
import shapes.Rectangle;
import flyweight.FlyWeightFactory;
import static flyweight.FlyWeightFactory.LEFT_DOWN;
import static flyweight.FlyWeightFactory.LEFT_TOP;
import static flyweight.FlyWeightFactory.RIGHT_DOWN;
import static flyweight.FlyWeightFactory.RIGHT_TOP;
import difficulty.Difficulty;
import difficulty.Easy;
import difficulty.Hard;
import difficulty.Medium;
import state.GameState;
import eg.edu.alexu.csd.oop.game.GameObject;
import java.awt.Color;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import memento.GameCareTaker;
import memento.GameOriginator;
import scores.Caretaker;
import scores.Originator;
import state.Defeat;
//import state.ReturnToCheckPoint;
import state.Victory;
import strategy.Controlled;

/**
 *
 * @author Shams Sherif
 */
public class CircusOfPlates implements eg.edu.alexu.csd.oop.game.World {

    private int MAX_TIME = 1 * 60 * 1000;	// 1 minute       
    public long startTime;

    private Difficulty diff;

//    private GameOriginator originator;
//    private GameCareTaker careTaker;
    private boolean win = false;
    private GameState state;

    private int ShiftTime = 1 * 1000;
    private long startShift = System.currentTimeMillis();

    public int time;
    public static int width;
    public static int height;

    public int score;

    public List<GameObject> constant = new LinkedList<>();
    public List<GameObject> moving = new LinkedList<>();
    public List<GameObject> control = new LinkedList<>();

    int[] types = {LEFT_DOWN, LEFT_TOP, RIGHT_TOP, RIGHT_DOWN};
    private Bar[] bars = new Bar[4];
    private Random r = new Random();
    private ImageObject cane;
    private ImageObject clown;
    private final Color colors[] = {Color.red, Color.GREEN, Color.BLUE, Color.YELLOW};
    private static char c;

    public char getC() {
        return c;
    }

   public void setC(char c) {
        this.c = c;
        if (getC() == 'e') {
            //System.out.println(c);
            setDifficulty(new Easy(this));
                        startTime=System.currentTimeMillis();
        } else if(getC()=='m') {
            setDifficulty(new Medium(this));
            startTime=System.currentTimeMillis();

        }else{
            setDifficulty(new Hard(this));
            System.out.println("Hard");
                        startTime=System.currentTimeMillis();
        }
    }

    public CircusOfPlates(int screenWidth, int screenHeight) {

        FlyWeightFactory.classList.add(Plate.class);// replace with dynmicly load plate
        FlyWeightFactory.classList.add(Rectangle.class);// replace with dynmicly loaded Rectangle

        width = screenWidth;
        height = screenHeight;
        time = 0;

        clown = new Clown(screenWidth / 3, (int) (screenHeight * 0.75), "/clown.png");//1
        cane = new Cane((screenWidth / 3) - 20, (int) (screenHeight * 0.75) - 55, "/cane.png");//0

        control.add(clown);
        control.add(cane);

        bars[0] = new Bar(0, Bar.LONG_BAR_Y, Bar.LONG_BAR_WIDTH, Bar.LEFT_TOP);
        bars[1] = new Bar(width - Bar.LONG_BAR_WIDTH, Bar.LONG_BAR_Y, Bar.LONG_BAR_WIDTH, Bar.RIGHT_TOP);
        bars[2] = new Bar(0, Bar.SHORT_BAR_Y, Bar.SHORT_BAR_WIDTH, Bar.LEFT_DOWN);
        bars[3] = new Bar(width - Bar.SHORT_BAR_WIDTH, Bar.SHORT_BAR_Y, Bar.SHORT_BAR_WIDTH, Bar.RIGHT_DOWN);

        constant.add(bars[0]);
        constant.add(bars[1]);
        constant.add(bars[2]);
        constant.add(bars[3]);

    }

    private boolean intersectsPlates(GameObject o1, GameObject o2) {
        return (Math.abs((o1.getX() + o1.getWidth() / 2) - (o2.getX() + o2.getWidth() / 2)) <= o1.getWidth()) && (Math.abs((o1.getY() + o1.getHeight() / 2) - (o2.getY() + o2.getHeight() / 2)) <= o1.getHeight());
    }

    private boolean intersectsCane(GameObject o1, GameObject o2) {
        return (Math.abs((o1.getX() + o1.getWidth() / 2) - (o2.getX() + o2.getWidth() / 2)) <= o1.getWidth()) && (Math.abs((o1.getY() + o1.getHeight() / 2) - (o2.getY() + o2.getHeight() / 2)) <= o1.getHeight());
    }

    @Override
    public boolean refresh() {

        this.generateObstacle();

        if (this.state instanceof Victory) {
            this.addScore();
            return false;
        } else if (this.state instanceof Defeat) {
            return false;
        } else {
            updateScore();// check if top three with the same color and add score    
            getFlyWeight();// adds a plate every 1 sec
            update();//add observers
            for (int i = 0; i < moving.size(); i++) { //check if intersect or fall
                if (moving.get(i) != null) {
                    Shape p = (Shape) moving.get(i);
                    if (p.isFalling()) {
                        if (intersectsCane(p, cane) && control.size() == 2) {
                            this.catchShape(p);
                        } else if (intersectsPlates(p, control.get(control.size() - 1)) && control.size() > 2) {
                            this.catchShape(p);
                        }
                    }
                }
            }
            return true;
        }
    }

    @Override
    public int getSpeed() {
        return diff.getSpeed();
    }

    @Override
    public int getControlSpeed() {
        return diff.getControlSpeed();
    }

    @Override
    public List<GameObject> getConstantObjects() {
        return constant;
    }

    @Override
    public List<GameObject> getMovableObjects() {
        return moving;
    }

    @Override
    public List<GameObject> getControlableObjects() {
        return control;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public String getStatus() {
        return "Score=" + getScore() + "   |   Time=" + this.getElapsedTime();	// update status
    }

    private void getFlyWeight() {

        for (int i = 0; i < types.length; i++) {

            Shape p = (Shape) FlyWeightFactory.getShape(types[i]);
            if (p.getY() > height) {
                if (p.getBarType() == LEFT_DOWN) {
                    p.setX(0);
                    p.setY(125);
                } else if (p.getBarType() == LEFT_TOP) {
                    p.setX(0);
                    p.setY(45);
                } else if (p.getBarType() == RIGHT_TOP) {
                    p.setX(width);
                    p.setY(45);
                } else if (p.getBarType() == RIGHT_DOWN) {
                    p.setX(width);
                    p.setY(125);
                }
                p.setColor(colors[r.nextInt(4)]);

                p.draw();

                p.setFalling(false);

            } else if (!moving.contains(p)) {
                int bartype = p.getBarType();
                FlyWeightFactory.removeShape(bartype);
                Shape s = (Shape) FlyWeightFactory.getShape(bartype);
                if (bartype == LEFT_DOWN) {
                    bars[2].addObserver(s);
                } else if (bartype == LEFT_TOP) {
                    bars[0].addObserver(s);
                } else if (bartype == RIGHT_TOP) {
                    bars[1].addObserver(s);
                } else if (bartype == RIGHT_DOWN) {
                    bars[3].addObserver(s);
                }
                moving.add(s);

            }
        }
    }

    private void update() {
        for (Bar bar : bars) {
            bar.notifyAllObservers();
        }
    }

    public void updateScore() {
        Object a[] = control.toArray();

        for (int i = 2; i < a.length; i++) {
            Shape p = (Shape) a[i];
            if (i + 2 < a.length) {

                Shape p2 = (Shape) a[i + 1];
                if (p.getColor().equals(p2.getColor())) {
                    Shape p3 = (Shape) a[i + 2];
                    if (p2.getColor().equals(p3.getColor())) {
                        score++;
                        control.remove(p);
                        control.remove(p2);
                        control.remove(p3);
                    }
                }
            }
        }
    }

    public void catchShape(Shape shape) {
        control.add(shape);
        moving.remove(shape);
        shape.setControlType(new Controlled());
        bars[0].removeObserver(shape);
        bars[1].removeObserver(shape);
        bars[2].removeObserver(shape);
        bars[3].removeObserver(shape);
        if (control.size() > 10) {
            setGameState(new Defeat(this));
        }
//        } else if (control.size() > 10 && this.getElapsedTime() > 5) {
//            this.moving = originator.restoreMoving(careTaker.getMemento());
//            this.constant = originator.restoreConstants(careTaker.getMemento());
//            this.control = originator.restoreControl(careTaker.getMemento());
//            this.startTime = originator.restoreTime(careTaker.getMemento());
//            this.score = originator.restoreScore(careTaker.getMemento());
//
//        }
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public int getElapsedTime() {
        int elapsedTime = (int) (System.currentTimeMillis() - startTime) / 1000;
//        if (elapsedTime == 5 || elapsedTime == 30 || elapsedTime == 45) {
//            try {
//                originator = new GameOriginator((CircusOfPlates) this.clone());
//                careTaker = new GameCareTaker();
//                careTaker.preserveState(originator.storeInMemento());
//            } catch (CloneNotSupportedException ex) {/*do nothing*/
//            }
//        }
        if (elapsedTime > 59) {
            this.setGameState(new Victory(this));
        }
        this.time = elapsedTime;
        return elapsedTime;
    }

    private void addScore() {
        if (!win) {
            JOptionPane dialog = new JOptionPane();
            JPanel p = new JPanel();
            JLabel lname = new JLabel("Name: ");
            JTextField name = new JTextField(15);
            p.add(lname);
            p.add(name);
            int value = JOptionPane.showConfirmDialog(dialog, p, "Enter Your Name", JOptionPane.OK_CANCEL_OPTION);
            if (value == JOptionPane.OK_OPTION) {
                Originator o = new Originator();
                Caretaker c = Caretaker.getInstance();
                o.set(name.getText(), String.valueOf(getScore()));
                c.add(o.store());

                showLeaderBoard();
            }
        }
        win = true;
    }

    public void setDifficulty(Difficulty diff) {
        this.diff = diff;
    }

    public void setGameState(GameState state) {
        this.state = state;
    }

    public void generateObstacle() {
        this.diff.generateObstacle();
    }

    public void showLeaderBoard() {
        try {
            Caretaker c = Caretaker.getInstance();
            Caretaker d = c.getCareTaker();
            LeaderBoard l = new LeaderBoard(d);
            l.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(Victory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
