import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.Double;
import java.util.*;

class DistanceComparator implements Comparator<point1D> {
    public int compare(point1D p1, point1D p2) {
        if (p1.distance() == p2.distance()){
            return 0;
        }
        return p1.distance() < p2.distance() ? -1 : 1;
    }
}


class point1D{
    protected double x;


    public point1D(){
        x = 0;
    }

    public point1D(double x_value){
        x = x_value;
    }

    public double distance(){
        return x;
    }

    public void print_point(){
        System.out.print(x+" ");
    }

}

class point2D extends point1D{
    protected double y;

    public point2D(){
        x = 0;
        y = 0;
    }

    public point2D(double x_value, double y_value){
        x = x_value;
        y = y_value;
    }

    public double distance(){
        return Math.sqrt(x*x + y*y);
    }

    public void print_point(){
        super.print_point();
        System.out.print(y+" ");
    }
}

class point3D extends point2D{
    protected double z;

    public point3D(){
        x = 0;
        y = 0;
        z = 0;
    }

    public point3D(double x_value, double y_value, double z_value){
        x = x_value;
        y = y_value;
        z = z_value;
    }

    public double distance(){
        return Math.sqrt(x*x + y*y + z*z);
    }

    public void print_point(){
        super.print_point();
        System.out.print(z);
    }
}


public class Points {

    public static List<point1D> read_points(String file_name){

        try {
            FileReader file_reader = new FileReader(file_name);
            BufferedReader buffer = new BufferedReader(file_reader);

            String line;
            try {
                List<point1D> point_list = new ArrayList<>();

                while ((line = buffer.readLine()) != null) {
                    String[] split = line.split("\\s+");
                    if (split.length == 3) {
                        double x_val = Double.parseDouble(split[0]);
                        double y_val = Double.parseDouble(split[1]);
                        double z_val = Double.parseDouble(split[2]);
                        point_list.add(new point3D(x_val, y_val, z_val));
                    } else if (split.length == 2) {
                        double x_val = Double.parseDouble(split[0]);
                        double y_val = Double.parseDouble(split[1]);
                        point_list.add(new point2D(x_val, y_val));
                    } else if (split.length == 1) {
                        double x_val = Double.parseDouble(split[0]);
                        point_list.add(new point1D(x_val));
                    }
                }
                return point_list;
            } catch(IOException e) {
                e.printStackTrace();
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {

        List<point1D> points_list = read_points("D:\\REPOS\\Studia\\JAVA\\3d_points\\src\\points.txt");
        for (int i=0; i < points_list.size(); i++) {
            points_list.get(i).print_point();
            System.out.println();
        }

        System.out.println();

        Collections.sort(points_list, new DistanceComparator());
        for (int i=0; i < points_list.size(); i++) {
            points_list.get(i).print_point();
            System.out.println();
        }
    }
}
