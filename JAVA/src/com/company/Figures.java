package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Figures{

    public List<Shape> shapeList = new ArrayList<>();

    public void add_shape(Integer x1, Integer y1, Integer x2, Integer y2) {
        shapeList.add(new Square(new Point2D(x1, y1), new Point2D(x2, y2)));
    }

}
