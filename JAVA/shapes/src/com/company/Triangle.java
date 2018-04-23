package com.company;

public class Triangle extends Shape {
    Triangle(Point2D point1, Point2D point2, Point2D point3) {
        point_list.add(point1);
        point_list.add(point2);
        point_list.add(point3);
    }

    private float calculateArea(Point2D p1, Point2D p2, Point2D p3) {
        return Math.abs(((p1.x*(p2.y - p3.y)) + p2.x*(p3.y - p1.y) + p3.x*(p1.y - p2.y)) /2 );
    }

    @Override
    protected boolean contains(Integer px, Integer py) {
        Point2D p1 = point_list.get(0);
        Point2D p2 = point_list.get(1);
        Point2D p3 = point_list.get(2);
        Point2D clicked_p = new Point2D(px, py);

        float triangleArea = calculateArea(p1, p2, p3);
        float area1 = calculateArea(clicked_p, p2, p3);
        float area2 = calculateArea(p1, clicked_p, p3);
        float area3 = calculateArea(p1, p2, clicked_p);

        return (triangleArea == area1 + area2 + area3);
    }

    @Override
    protected void moveX(Integer x) {
        Point2D p1 = point_list.get(0);
        Point2D p2 = point_list.get(1);
        Point2D p3 = point_list.get(2);

        if(x >= 0) {
            if(p1.x < 400 && p2.x < 400 && p3.x < 400) {
                p1.x = p1.x + x;
                p2.x = p2.x + x;
                p3.x = p3.x + x;
            }
        } else {
            if(p1.x > 0 && p2.x > 0 && p3.x > 0) {
                p1.x = p1.x + x;
                p2.x = p2.x + x;
                p3.x = p3.x + x;
            }
        }
    }

    @Override
    protected void moveY(Integer x) {
        Point2D p1 = point_list.get(0);
        Point2D p2 = point_list.get(1);
        Point2D p3 = point_list.get(2);

        if(x >= 0) {
            if(p1.y < 400 && p2.y < 400 && p3.y < 400) {
                p1.y = p1.y + x;
                p2.y = p2.y + x;
                p3.y = p3.y + x;
            }
        } else {
            if(p1.y > 0 && p2.y > 0 && p3.y > 0) {
                p1.y = p1.y + x;
                p2.y = p2.y + x;
                p3.y = p3.y + x;
            }
        }
    }
}
