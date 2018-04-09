package com.company;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Figures fig = new Figures();
        fig.add_shape(1, 2, 80, 40);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new MyPanel(fig.shapeList));

        frame.pack();
        frame.setVisible(true);
    }
}
