package com.company;

class Circle extends Shape{
    Circle(Point2D point, Integer r) {
        point_list.add(point);
        radius = r;
    }

    @Override
    protected boolean contains(Integer px, Integer py) {
        Integer rx = point_list.get(0).x;
        Integer ry = point_list.get(0).y;
        Integer res = (px - rx)*(px - rx) + (py - ry)*(py - ry);
        return (res <= radius*radius);
    }

    @Override
    protected void moveX(Integer x) {
        Point2D p1 = point_list.get(0);

        if(x >= 0) {
            if(p1.x + radius < 400) {
                p1.x = p1.x + x;
            }
        } else {
            if(p1.x > 0) {
                p1.x = p1.x + x;
            }
        }
    }

    @Override
    protected void moveY(Integer x) {
        Point2D p1 = point_list.get(0);

        if(x >= 0) {
            if(p1.y + radius < 400) {
                p1.y = p1.y + x;
            }
        } else {
            if(p1.y > 0) {
                p1.y = p1.y + x;
            }
        }
    }

}
