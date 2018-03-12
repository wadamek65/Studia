package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Figures {
    private JPanel panel = new JPanel();
    private JFrame frame = new JFrame();


    protected List<Square> squares = new ArrayList<>();

    public void add_square(Double x1, Double y1, Double x2, Double y2) {
        squares.add(new Square(new Point2D(x1, y1), new Point2D(x2, y2)));
    }

    public void run_frame(){
        frame.setBounds(250, 200, 400, 400);
        frame.add(panel);
        frame.setVisible(true);
        frame.setSize(400, 400);
        Graphics graphs = panel.getGraphics();
        frame.getContentPane().add(new JFrameGraphics());
        graphs.setColor(Color.DARK_GRAY);
        graphs.drawLine(1,20,300,49);

    }
}
