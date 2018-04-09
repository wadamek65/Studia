package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class MyPanel extends JPanel {

    private List<Shape> shapeList;

    MyPanel(List<Shape> list) {
        shapeList = list;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(Shape sh : shapeList) {
            if(sh.point_list.size() == 2) {
                System.out.println(sh.point_list.get(0).x);
                g.drawLine(sh.point_list.get(0).x, sh.point_list.get(0).y, sh.point_list.get(1).x, sh.point_list.get(1).y);
            }
        }
//        g.drawLine(shapeList.get(0).point_list.get(0).x, shapeList.get(0).point_list.get(0).y, shapeList.get(0).point_list.get(1).x, shapeList.get(0).point_list.get(1).y);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }
}
