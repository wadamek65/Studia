package com.company;

public class Square extends Shape {
    Square(Point2D point1, Point2D point2) {
        point_list.add(point1);
        point_list.add(point2);
    }

    @Override
    protected boolean contains(Integer px, Integer py) {
        Integer x1 = point_list.get(0).x;
        Integer x2 = point_list.get(1).x;
        Integer y1 = point_list.get(0).y;
        Integer y2 = point_list.get(1).y;

        if (px < x1 && px < x1 + x2) return false;
        if (py < y1 && py < y1 + y2) return false;
        if (px > x1 && px > x2 && px > x1 + x2) return false;
        if (py > y1 && py > y2 && py > y1 + y2) return false;
        return true;
    }

    @Override
    protected void moveX(Integer x) {
        Point2D p1 = point_list.get(0);
        Point2D p2 = point_list.get(1);

        if(x >= 0) {
            if(p1.x < 400 && p1.x + p2.x < 400) {
                p1.x = p1.x + x;
            }
        } else {
            if(p1.x > 0 && p2.x > 0) {
                p1.x = p1.x + x;
            }
        }
    }

    @Override
    protected void moveY(Integer y) {
        Point2D p1 = point_list.get(0);
        Point2D p2 = point_list.get(1);

        if(y >= 0) {
            if(p1.y < 400 && p1.y + p2.y < 400) {
                p1.y = p1.y + y;
            }
        } else {
            if(p1.y > 0 && p2.y > 0) {
                p1.y = p1.y + y;
            }
        }
    }
}
