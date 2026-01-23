package mod1;

public class Ex9_9 {
    public static void main(String[] args) {
        RegularPolygon poly1 = new RegularPolygon(); 
        RegularPolygon poly2 = new RegularPolygon(5, 4); 
        RegularPolygon poly3 = new RegularPolygon(10, 4, 5.6, 7.8); 

        System.out.println("Poly 1 Perimeter: " + poly1.getPerimeter());
        System.out.println("Poly 1 Area: " + poly1.getArea());
        System.out.println("Poly 2 Perimeter: " + poly2.getPerimeter());
        System.out.println("Poly 2 Area: " + poly2.getArea());
        System.out.println("Poly 3 Perimeter: " + poly3.getPerimeter());
        System.out.println("Poly 3 Area: " + poly3.getArea());
    }
}

class RegularPolygon {
    private int n;
    private double side;
    private double x;
    private double y;

    public RegularPolygon() {
        n = 3;
        side = 1;
        x = 0;
        y = 0;
    }
    public RegularPolygon(int n, double side) {
        this.n = n;
        this.side = side;
        x = 0;
        y = 0;
    }
    public RegularPolygon(int n, double side, double x, double y) {
        this.n = n;
        this.side = side;
        this.x = x;
        this.y = y;
    }
    
    public int get_n() {
        return n;
    }
    public double get_side() {
        return side;
    }
    public double get_x() {
        return x;
    }
    public double get_y() {
        return y;
    }
    public void set_n(int n) {
        this.n = n;
    }
    public void set_side(double side) {
        this.side = side;
    }
    public void set_x(double x) {
        this.x = x;
    }
    public void set_y(double y) {
        this.y = y;
    }

    public double getPerimeter() {
        return n*side;
    }
    public double getArea() {
        return (n*side*side)/(4*Math.tan(Math.PI/n));
    }
}