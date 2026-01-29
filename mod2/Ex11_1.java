package mod2;
import java.util.Scanner;

public class Ex11_1 {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Enter side 1: ");
            double side1 = Double.parseDouble(input.nextLine());
            System.out.println("Enter side 2: ");
            double side2 = Double.parseDouble(input.nextLine());
            System.out.println("Enter side 3: ");
            double side3 = Double.parseDouble(input.nextLine());
            System.out.println("Enter color: ");
            String color = input.nextLine();
            System.out.println("Enter filled status: ");
            boolean filled = Boolean.parseBoolean(input.nextLine());
            
            Triangle tri = new Triangle(side1, side2, side3);
            tri.setColor(color);
            tri.setFilled(filled);

            System.out.println("Area: " + tri.getArea());
            System.out.println("Perimeter: " + tri.getPerimeter());
            System.out.println("Color: " + tri.getColor());
            System.out.println("Filled: " + tri.isFilled());
        }
    }    
}

// GeometricObject.java: The abstract GeometricObject class
abstract class GeometricObject {
  private String color = "white";
  private boolean filled;

  /**Default construct*/
  protected GeometricObject() {
  }

  /**Construct a geometric object*/
  protected GeometricObject(String color, boolean filled) {
    this.color = color;
    this.filled = filled;
  }

  /**Getter method for color*/
  public String getColor() {
    return color;
  }

  /**Setter method for color*/
  public void setColor(String color) {
    this.color = color;
  }

  /**Getter method for filled. Since filled is boolean,
     so, the get method name is isFilled*/
  public boolean isFilled() {
    return filled;
  }

  /**Setter method for filled*/
  public void setFilled(boolean filled) {
    this.filled = filled;
  }

  /**Abstract method findArea*/
  public abstract double getArea();

  /**Abstract method getPerimeter*/
  public abstract double getPerimeter();
}

class Triangle extends GeometricObject {
    private final double side1;
    private final double side2;
    private final double side3;

    public Triangle() {
        side1 = 1.0;
        side2 = 1.0;
        side3 = 1.0;
    }
    public Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }
    public double getSide1() {
        return side1;
    }
    public double getSide2() {
        return side2;
    }
    public double getSide3() {
        return side3;
    }
    @Override public double getArea() {
        double sVar = (side1+side2+side3)/2;
        return Math.sqrt(sVar*(sVar-side1)*(sVar-side2)*(sVar-side3));
    }
    @Override public double getPerimeter() {
        return side1 + side2 + side3;
    }
    @Override public String toString() {
        return "Triangle: side1 = " + side1 + " side2 = " + side2 + " side3 = " + side3;
    }
}
