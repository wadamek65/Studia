import java.util.Random;
import java.lang.*;

class ComplexNumber{
    double a;
    double b;

    ComplexNumber(){
        Random generate = new Random();
        a = generate.nextInt(20) - 10;
        b = generate.nextInt(20) - 10;
    }
    ComplexNumber(double a_value, double b_value){
        a = a_value;
        b = b_value;
    }

    void displayComplexNumber(){
        if(b<0) System.out.println(a+""+b+"i");
        else System.out.println(a+"+"+b+"i");
    }

    double module(){
        return Math.sqrt(a*a+b*b);
    }

    ComplexNumber conjugate(){
        return new ComplexNumber(a, -b);
    }

    ComplexNumber add(ComplexNumber val){
        return new ComplexNumber(a+val.a, b+val.b);
    }

    ComplexNumber subtract(ComplexNumber val){
        return new ComplexNumber(a-val.a, b-val.b);
    }

    ComplexNumber multiply(ComplexNumber val){
        return new ComplexNumber(a*val.a-b*val.b, a*val.b+b*val.a);
    }
}

public class Complex {
    public static void main(String[] args) {
        //Random generator = new Random();
        ComplexNumber example = new ComplexNumber(3, 4);
        ComplexNumber example2 = new ComplexNumber(4, 3);
        example.displayComplexNumber();
        System.out.println("");
        example2.displayComplexNumber();
        System.out.println("");

        System.out.println("Moduł1 wynosi: "+example.module());
        System.out.println("");

        System.out.println("Moduł2 wynosi: "+example2.module());

        System.out.println("");
        System.out.println("Sprzężenie");
        example.conjugate().displayComplexNumber();

        System.out.println("");
        System.out.println("Dodawanie");
        example.add(example2).displayComplexNumber();

        System.out.println("");
        System.out.println("Odejmowanie");
        example.subtract(example2).displayComplexNumber();

        System.out.println("");
        System.out.println("Mnozenie");
        example.multiply(example2).displayComplexNumber();

        int n = 50; // ilość liczb w tablicy
        ComplexNumber[] arr = new ComplexNumber[n];
        for(int i=0; i<n; i++){
            arr[i] = new ComplexNumber();
            arr[i].displayComplexNumber();
        }
        //System.out.println("Hello JAVA");
    }
}
