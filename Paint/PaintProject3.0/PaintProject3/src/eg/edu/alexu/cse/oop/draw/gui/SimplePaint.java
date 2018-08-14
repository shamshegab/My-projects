package eg.edu.alexu.cse.oop.draw.gui;

import eg.edu.alexu.cse.oop.draw.Shape;
import eg.edu.alexu.cse.oop.draw.shapes.*;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.EmptyStackException;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SimplePaint extends javax.swing.JFrame {

    public SimplePaint() {

        initComponents();
        refreshSupportedShapes();
        maxXLabel.setText("Max X: " + panel.getWidth());
        maxYLabel.setText("Max Y: " + panel.getHeight());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        undo = new javax.swing.JButton();
        redo = new javax.swing.JButton();
        lbl = new javax.swing.JLabel();
        shapesComboBox = new javax.swing.JComboBox<>();
        edit = new javax.swing.JButton();
        move = new javax.swing.JButton();
        colorize = new javax.swing.JButton();
        copy = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        panel = new javax.swing.JPanel();
        newShapeBtn = new javax.swing.JButton();
        maxXLabel = new javax.swing.JLabel();
        maxYLabel = new javax.swing.JLabel();
        btnsPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Vector Drawing Application");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        undo.setText("Undo");
        undo.setPreferredSize(new java.awt.Dimension(80, 23));
        undo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoActionPerformed(evt);
            }
        });

        redo.setText("Redo");
        redo.setPreferredSize(new java.awt.Dimension(80, 23));
        redo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redoActionPerformed(evt);
            }
        });

        lbl.setText("Select Shape");

        shapesComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose Shape" }));
        shapesComboBox.setToolTipText("");

        edit.setText("Edit");
        edit.setPreferredSize(new java.awt.Dimension(80, 23));
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });

        move.setText("Move");
        move.setPreferredSize(new java.awt.Dimension(80, 23));
        move.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveActionPerformed(evt);
            }
        });

        colorize.setText("Colorize");
        colorize.setPreferredSize(new java.awt.Dimension(80, 23));
        colorize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorizeActionPerformed(evt);
            }
        });

        copy.setText("Copy");
        copy.setPreferredSize(new java.awt.Dimension(80, 23));
        copy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyActionPerformed(evt);
            }
        });

        delete.setText("Delete");
        delete.setPreferredSize(new java.awt.Dimension(80, 23));
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        panel.setBackground(new java.awt.Color(255, 255, 255));
        panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1202, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        newShapeBtn.setText("+");
        newShapeBtn.setToolTipText("add a plugin shape");
        newShapeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newShapeBtnActionPerformed(evt);
            }
        });

        maxXLabel.setText("Max X:");

        maxYLabel.setText("Max Y:");

        btnsPanel.setPreferredSize(new java.awt.Dimension(200, 31));

        javax.swing.GroupLayout btnsPanelLayout = new javax.swing.GroupLayout(btnsPanel);
        btnsPanel.setLayout(btnsPanelLayout);
        btnsPanelLayout.setHorizontalGroup(
            btnsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        btnsPanelLayout.setVerticalGroup(
            btnsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 33, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(copy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(colorize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(lbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(shapesComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(move, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(maxXLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(maxYLabel)
                            .addGap(14, 14, 14)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(undo, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(redo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(newShapeBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1204, Short.MAX_VALUE)
                    .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(newShapeBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(undo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(redo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(shapesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(move, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(colorize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(copy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(maxXLabel)
                            .addComponent(maxYLabel))
                        .addGap(0, 568, Short.MAX_VALUE)))
                .addGap(5, 5, 5))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void newShapeActionPerformed(java.awt.event.ActionEvent evt) {

        Shape shape = null;
        Map<String, Double> properties = null;
        try {
            int shapeIndex = Actions.getInstance().getSupportedShapes().size() - 1;
            shape = (Shape) Actions.getInstance().getSupportedShapes().get(shapeIndex).newInstance();
            properties = shape.getProperties();
            if (this.showDialog(properties, shape)) {
                this.drawShape(shape);
            }
        } catch (InstantiationException ex) {
            showError("Could Not Draw Shape, Try Loading It Again");
        } catch (IllegalAccessException ex) {
            showError("Could Not Draw Shape, Try Loading It Again");
        } catch (NumberFormatException e) {
            showError("Only Enter Integer Values");
        }catch (NullPointerException e){
            showError("Could Not Draw Shape, Try Loading It Again");
        }

    }

    private void circleActionPerformed(java.awt.event.ActionEvent evt) {

        Shape shape = new Circle();
        Map<String, Double> properties = shape.getProperties();

        try {
            if (this.showDialog(properties, shape)) {
                this.drawShape(shape);
            }
        } catch (NumberFormatException e) {
            showError("Only Enter Integer Values");
        }

    }

    private void squareActionPerformed(java.awt.event.ActionEvent evt) {

        Shape shape = new Square();
        Map<String, Double> properties = shape.getProperties();

        try {
            if (this.showDialog(properties, shape)) {
                this.drawShape(shape);
            }
        } catch (NumberFormatException e) {
            showError("Only Enter Integer Values");
        }
    }

    private void rectangleActionPerformed(java.awt.event.ActionEvent evt) {

        Shape shape = new Rectangle();
        Map<String, Double> properties = shape.getProperties();

        try {
            if (this.showDialog(properties, shape)) {
                this.drawShape(shape);
            }
        } catch (NumberFormatException e) {
            showError("Only Enter Integer Values");
        }
    }

    private void ellipseActionPerformed(java.awt.event.ActionEvent evt) {

        Shape shape = new Ellipse();
        Map<String, Double> properties = shape.getProperties();

        try {
            if (this.showDialog(properties, shape)) {
                this.drawShape(shape);
            } else {
                showError("Please Don't Exceed Maximum Width Or Height");
            }
        } catch (NumberFormatException e) {
            showError("Only Enter Integer Values");
        }
    }

    private void lineActionPerformed(java.awt.event.ActionEvent evt) {

        Shape shape = new LineSegment();
        Map<String, Double> properties = shape.getProperties();

        try {
            if (this.showDialog(properties, shape)) {
                this.drawShape(shape);
            }
        } catch (NumberFormatException e) {
            showError("Only Enter Integer Values");
        }

    }

    private void triangleActionPerformed(java.awt.event.ActionEvent evt) {

        Shape shape = new Triangle();
        Map<String, Double> properties = shape.getProperties();

        try {
            if (this.showDialog(properties, shape)) {
                this.drawShape(shape);
            }
        } catch (NumberFormatException e) {
            showError("Only Enter Integer Values");
        }
    }

    private void redoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_redoActionPerformed

        try {
            Actions.getInstance().redo();
            panel.update(panel.getGraphics());
            Actions.getInstance().refresh(panel.getGraphics());
            this.refreshComboBox(new String[1000]);
        } catch (EmptyStackException e) {
        }

    }//GEN-LAST:event_redoActionPerformed


    private void undoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undoActionPerformed

        try {
            Actions.getInstance().undo();
            panel.update(panel.getGraphics());
            Actions.getInstance().refresh(panel.getGraphics());
            this.refreshComboBox(new String[1000]);
        } catch (EmptyStackException e) {
        }

    }//GEN-LAST:event_undoActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed

        Shape shape;
        try {
            shape = Actions.getInstance().getShapes()[shapesComboBox.getSelectedIndex()];
            Map<String, Double> properties = shape.getProperties();
            showDialog(properties, shape);
            panel.update(panel.getGraphics());
            shape.setProperties(properties);
            Actions.getInstance().refresh(panel.getGraphics());
        } catch (ArrayIndexOutOfBoundsException e) {
            showError("No Shape Selected");
        }
    }//GEN-LAST:event_editActionPerformed

    private void moveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveActionPerformed

        Shape moved;
        try {
            moved = Actions.getInstance().getShapes()[shapesComboBox.getSelectedIndex()];
            moveShape(moved);
            panel.update(panel.getGraphics());
            Actions.getInstance().refresh(panel.getGraphics());
        } catch (ArrayIndexOutOfBoundsException e) {
            showError("No Shape Selected");
        } catch (NumberFormatException e) {
            showError("Only Enter Integers");
        }
    }//GEN-LAST:event_moveActionPerformed

    private void copyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyActionPerformed

        Shape oldShape;
        Shape clone;
        try {
            oldShape = Actions.getInstance().getShapes()[shapesComboBox.getSelectedIndex()];
            clone = (Shape) oldShape.clone();
            Actions.getInstance().addShape(clone);
            if (!moveShape(clone)) {
                Actions.getInstance().removeShape(clone);
            }
            Actions.getInstance().refresh(panel.getGraphics());
            this.refreshComboBox(new String[1000]);
        } catch (CloneNotSupportedException ex) {
        } catch (ArrayIndexOutOfBoundsException e) {
            showError("No Shape Selected");
        } catch (NumberFormatException e) {
            showError("Only Enter Integers");
        }


    }//GEN-LAST:event_copyActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed

        try {
            Actions.getInstance().removeShape(Actions.getInstance().getShapes()[shapesComboBox.getSelectedIndex()]);
            panel.update(panel.getGraphics());
            Actions.getInstance().refresh(panel.getGraphics());
            this.refreshComboBox(new String[1000]);
        } catch (ArrayIndexOutOfBoundsException e) {
            showError("No Shape Selected");
        }
    }//GEN-LAST:event_deleteActionPerformed

    private void drawShape(Shape shape) {

        Actions.getInstance().addShape(shape);
        Actions.getInstance().refresh(panel.getGraphics());

        this.refreshComboBox(new String[1000]);
    }

    private void colorizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colorizeActionPerformed

        Shape colorized = null;
        try {
            colorized = Actions.getInstance().getShapes()[shapesComboBox.getSelectedIndex()];
            colorize(colorized);
            panel.update(panel.getGraphics());
            Actions.getInstance().refresh(panel.getGraphics());
        } catch (ArrayIndexOutOfBoundsException e) {
            showError("No Shape Selected");
        }
    }//GEN-LAST:event_colorizeActionPerformed

    private void newShapeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newShapeBtnActionPerformed

        JPanel pane = new JPanel();
        JLabel label1 = new JLabel();
        JTextField field1 = new JTextField();
        JButton findFile = new JButton("Find File");
        String className;

        label1.setText("Class Name: ");
        field1.setColumns(10);

        pane.add(label1);
        pane.add(field1);
        pane.add(findFile);

        JFileChooser f = new JFileChooser();

        findFile.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                f.showDialog(panel, "Choose Jar");
            }

        });

        int value = JOptionPane.showConfirmDialog(this, pane, "New Class", JOptionPane.OK_CANCEL_OPTION);
        if (value == JOptionPane.OK_OPTION) {
            className = field1.getText();

            ClassLoader child;
            Class classToLoad = null;
            try {

                child = new URLClassLoader(new URL[]{new URL("jar", "", "file:\\" + f.getSelectedFile() + "!/")}, SimplePaint.class.getClassLoader());

                classToLoad = Class.forName(className, true, child);

                Actions.getInstance().installPluginShape(classToLoad);

                refreshSupportedShapes();

                System.out.println("class loaded");

                

            } catch (ClassNotFoundException ex) {
                showError("Could Not Find Class " + className);
            } catch (MalformedURLException ex) {
            } catch (IllegalArgumentException ex) {
                showError("Error Loading Class " + className);
            } catch (SecurityException ex) {
                showError("Error Loading Class " + className);
            }
        }
    }//GEN-LAST:event_newShapeBtnActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

        Actions.getInstance().refresh(panel.getGraphics());
    }//GEN-LAST:event_formWindowActivated

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed

    }//GEN-LAST:event_formKeyPressed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SimplePaint.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SimplePaint.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SimplePaint.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SimplePaint.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new SimplePaint().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnsPanel;
    private javax.swing.JButton colorize;
    private javax.swing.JButton copy;
    private javax.swing.JButton delete;
    private javax.swing.JButton edit;
    private javax.swing.JLabel lbl;
    private javax.swing.JLabel maxXLabel;
    private javax.swing.JLabel maxYLabel;
    private javax.swing.JButton move;
    private javax.swing.JButton newShapeBtn;
    private javax.swing.JPanel panel;
    private javax.swing.JButton redo;
    private javax.swing.JComboBox<String> shapesComboBox;
    private javax.swing.JButton undo;
    // End of variables declaration//GEN-END:variables

    private boolean showDialog(Map<String, Double> properties, Shape shape) throws NumberFormatException {
        System.out.println("1");
        JPanel pane = new JPanel();
        JLabel l1 = new JLabel();
        l1.setText("X: ");
        pane.add(l1);
        JTextField field1 = new JTextField(10);
        field1.setText("0");
        pane.add(field1);
        JLabel l2 = new JLabel();
        l2.setText("Y: ");
        pane.add(l2);
        JTextField field2 = new JTextField(10);
        field2.setText("0");
        pane.add(field2);
        JLabel l3 = new JLabel();
        l3.setText("Color: ");
        pane.add(l3);

        JButton bColor = new JButton("Choose");

        bColor.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Color newsColor = JColorChooser.showDialog(null, "Choose a color", Color.BLACK);
                shape.setColor(newsColor);
            }
        });

        pane.add(bColor);

        JLabel l4 = new JLabel();
        l4.setText("Fill Color: ");
        pane.add(l4);
        JButton bFill = new JButton("Choose");
        bFill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color newsColor = JColorChooser.showDialog(null, "Choose a fill color", Color.BLACK);
                shape.setFillColor(newsColor);
            }
        });
        pane.add(bFill);
        if (shape.getColor() == null) {
            int value = JOptionPane.showConfirmDialog(this, pane, "Enter Properties", JOptionPane.OK_CANCEL_OPTION);
            if (value == JOptionPane.OK_OPTION) {

                if (Integer.parseInt(field1.getText()) > panel.getWidth() || Integer.parseInt(field1.getText()) < 0 || Integer.parseInt(field2.getText()) > panel.getHeight() || Integer.parseInt(field2.getText()) < 0) {
                    showError("Please Don't Exceed The Given Range");
                    return false;
                }
                shape.setPosition(new Point(Integer.parseInt(field1.getText()), Integer.parseInt(field2.getText())));
                if (shape.getColor() == null) {
                    shape.setColor(Color.BLACK);
                }
                if (shape.getFillColor() == null) {
                    shape.setFillColor(Color.WHITE);
                }
                field1.setText("0");
                System.out.println("2");
            } else {
                return false;
            }
        }
        int i = 0;
        while (i < properties.size()) {
            System.out.println("3");
            pane.removeAll();

            l1.setText(properties.keySet().toArray()[i].toString() + " :");

            pane.add(l1);
            pane.add(field1);
            int d = properties.get(properties.keySet().toArray()[i].toString()).intValue();
            field1.setText(String.valueOf(d));
            int value = JOptionPane.showConfirmDialog(this, pane, "Enter Properties", JOptionPane.OK_CANCEL_OPTION);
            if (value == JOptionPane.OK_OPTION) {
                properties.put(properties.keySet().toArray()[i].toString(), (double) Integer.parseInt(field1.getText()));
                i++;
            } else {
                return false;
            }
            field1.setText("0");

            // handle them
        }

        shape.setProperties(properties);
        return true;

    }

    private boolean moveShape(Shape moved) throws NumberFormatException {

        JPanel pane = new JPanel();
        JLabel l1 = new JLabel();
        l1.setText("X: ");
        pane.add(l1);
        JTextField field1 = new JTextField(10);
        field1.setText(String.valueOf(moved.getPosition().x));

        pane.add(field1);
        JLabel l2 = new JLabel();
        l2.setText("Y: ");
        pane.add(l2);
        JTextField field2 = new JTextField(10);
        field2.setText(String.valueOf(moved.getPosition().y));
        pane.add(field2);
        JTextField field3 = new JTextField(10);
        JTextField field4 = new JTextField(10);
        if (moved instanceof LineSegment) {
            JLabel l3 = new JLabel();
            l3.setText("X2: ");
            pane.add(l3);

            field3.setText(String.valueOf(moved.getProperties().get("X2").intValue()));

            pane.add(field3);
            JLabel l4 = new JLabel();
            l4.setText("Y2: ");
            pane.add(l4);

            field4.setText(String.valueOf(moved.getProperties().get("Y2").intValue()));
            pane.add(field4);
        }

        int value = JOptionPane.showConfirmDialog(this, pane, "Enter Properties", JOptionPane.OK_CANCEL_OPTION);
        if (value == JOptionPane.OK_OPTION) {
            if (Integer.parseInt(field1.getText()) > panel.getWidth() || Integer.parseInt(field1.getText()) < 0 || Integer.parseInt(field2.getText()) >= panel.getHeight() || Integer.parseInt(field2.getText()) < 0) {
                showError("Please Don't Exceed The Given Range");
                return false;
            }
            moved.setPosition(new Point(Integer.parseInt(field1.getText()), Integer.parseInt(field2.getText())));
            if (moved instanceof LineSegment) {
                Map<String, Double> properties = moved.getProperties();
                properties.replace("X2", Double.valueOf(field3.getText()));
                properties.replace("Y2", Double.valueOf(field4.getText()));
                moved.setProperties(properties);
            }
        } else {
            return false;
        }
        return true;
    }

    private void colorize(Shape colorized) {

        JPanel pane = new JPanel();
        JLabel l1 = new JLabel();
        l1.setText("Color: ");
        pane.add(l1);
        JButton bcolor = new JButton("Change");
        bcolor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color newsColor = JColorChooser.showDialog(null, "Choose a color", colorized.getColor());
                colorized.setColor(newsColor);
            }
        });
        pane.add(bcolor);
        JLabel l4 = new JLabel();
        l4.setText("Fill Color: ");
        pane.add(l4);
        JButton bfcolor = new JButton("Change");
        bfcolor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color newsColor = JColorChooser.showDialog(null, "Choose a fill color", colorized.getFillColor());
                colorized.setFillColor(newsColor);
            }
        });

        pane.add(bfcolor);
        int value = JOptionPane.showConfirmDialog(this, pane, "Enter Properties", JOptionPane.OK_CANCEL_OPTION);
        if (value == JOptionPane.OK_OPTION) {

        }

    }

    private void showError(String msg) {

        JPanel pane = new JPanel();
        JLabel errorLbl = new JLabel(msg);
        pane.add(errorLbl);

        JOptionPane.showConfirmDialog(this, pane, "Error", JOptionPane.OK_CANCEL_OPTION);
    }

    private void refreshComboBox(String[] comboBoxList) {
        int shapeCounter = 0;
        if (Actions.getInstance().getShapes().length == 0) {
            comboBoxList[0] = "Draw a Shape";
            shapeCounter++;
        }
        for (int i = 0; i < Actions.getInstance().getShapes().length; i++) {
            comboBoxList[i] = Actions.getInstance().getShapes()[i].getClass().getSimpleName() + shapeCounter;
            shapeCounter++;

        }
        shapesComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(comboBoxList));
    }

    private void refreshSupportedShapes() {
        btnsPanel.removeAll();
        JButton[] shapeBtns = new javax.swing.JButton[Actions.getInstance().getSupportedShapes().size()];
        btnsPanel.setLayout(new FlowLayout());
        for (int i = 0; i < Actions.getInstance().getSupportedShapes().size(); i++) {
            shapeBtns[i] = new javax.swing.JButton();
            shapeBtns[i].setText(Actions.getInstance().getSupportedShapes().get(i).getSimpleName());
            shapeBtns[i].setVisible(true);
            shapeBtns[i].setPreferredSize(new java.awt.Dimension(110, 25));
            btnsPanel.add(shapeBtns[i]);

            if (Actions.getInstance().getSupportedShapes().get(i).getSimpleName().equals("Circle")) {
                shapeBtns[i].addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        circleActionPerformed(e);
                    }
                });
            } else if (Actions.getInstance().getSupportedShapes().get(i).getSimpleName().equals("Square")) {
                shapeBtns[i].addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        squareActionPerformed(e);
                    }
                });
            } else if (Actions.getInstance().getSupportedShapes().get(i).getSimpleName().equals("Ellipse")) {
                shapeBtns[i].addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ellipseActionPerformed(e);
                    }
                });
            } else if (Actions.getInstance().getSupportedShapes().get(i).getSimpleName().equals("Rectangle")) {
                shapeBtns[i].addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        rectangleActionPerformed(e);
                    }
                });
            } else if (Actions.getInstance().getSupportedShapes().get(i).getSimpleName().equals("Triangle")) {
                shapeBtns[i].addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        triangleActionPerformed(e);
                    }
                });
            } else if (Actions.getInstance().getSupportedShapes().get(i).getSimpleName().equals("LineSegment")) {
                shapeBtns[i].addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        lineActionPerformed(e);
                    }
                });
            } else {
                shapeBtns[i].addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        newShapeActionPerformed(e);
                    }
                });
            }

        }
        btnsPanel.revalidate();
    }
}
