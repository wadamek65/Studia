package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Figures fig = new Figures();
        Random generator = new Random();

        fig.add_shape(40, 50, 120, 150);
        fig.add_shape(0, 0, 50);
        fig.add_shape(350, 370, 220, 200, 250, 350);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFocusable(false);
        frame.add(new MyPanel(fig.shapeList));
        frame.pack();
        frame.setVisible(true);
    }
}
