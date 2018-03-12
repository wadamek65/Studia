package com.company;

public class Circle extends Shape{
    protected Double radius;

    Circle(Point2D point, Double r) {
        point_list.add(point);
        radius = r;
    }
}
