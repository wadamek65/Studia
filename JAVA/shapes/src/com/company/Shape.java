package com.company;
import java.util.List;
import java.util.ArrayList;

abstract class Shape {
    protected List<Point2D> point_list = new ArrayList<>();
    protected Integer radius;
    protected abstract boolean contains(Integer px, Integer py);
    protected abstract void moveX(Integer x);
    protected abstract void moveY(Integer y);
}
