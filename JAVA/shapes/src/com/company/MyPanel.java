package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

class MyPanel extends JPanel {

    private List<Shape> shapeList;
    private Integer lastClicked = -1;
    private Integer step = 5;

    MyPanel(List<Shape> list) {
        shapeList = list;
        setFocusable(true);
        requestFocus();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                for(int i = 0; i < shapeList.size(); i++) {
                    if(shapeList.get(i).contains(e.getX(), e.getY())) {
                        lastClicked = i;
                        break;
                    }
                }
                repaint();
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(lastClicked != -1) {
                    Shape lastShape = shapeList.get(lastClicked);
                    if (e.getKeyCode() == 38) { // arrow up
                        lastShape.moveY(-step);
                        repaint();
                    }
                    if (e.getKeyCode() == 39) { // arrow right
                        lastShape.moveX(step);
                        repaint();
                    }
                    if (e.getKeyCode() == 40) { // arrow down
                        lastShape.moveY(step);
                        repaint();
                    }
                    if (e.getKeyCode() == 37) { // arrow left
                        lastShape.moveX(-step);
                        repaint();
                    }
                    if (e.getKeyCode() == 127) { // delete
                        if(lastClicked != -1) {
                            shapeList.remove(shapeList.get(lastClicked));
                            lastClicked = -1;
                            repaint();
                        }
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i = 0; i < shapeList.size(); i++) {
            Shape sh = shapeList.get(i);
            g.setColor(Color.BLUE);
            if(lastClicked == i) g.setColor(Color.GREEN);
            if(sh.point_list.size() == 1) {
                g.fillOval(sh.point_list.get(0).x, sh.point_list.get(0).y, sh.radius, sh.radius);
            } else if(sh.point_list.size() == 2) {
                g.fillRect(sh.point_list.get(0).x, sh.point_list.get(0).y, sh.point_list.get(1).x, sh.point_list.get(1).y);
            } else if(sh.point_list.size() == 3) {
                int[] xArray = {sh.point_list.get(0).x, sh.point_list.get(1).x, sh.point_list.get(2).x};
                int[] yArray = {sh.point_list.get(0).y, sh.point_list.get(1).y, sh.point_list.get(2).y};
                g.fillPolygon(xArray, yArray, 3);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }

}
