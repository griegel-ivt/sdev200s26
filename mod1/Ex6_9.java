package mod1;

public class Ex6_9 {
    public static double footToMeter(double foot) {
        double meters = 0.305 * foot;
        return meters;
    }
    public static double meterToFoot(double meter) {
        double feet = 3.279 * meter;
        return feet;
    }
    public static void main(String[] args) {
        double feetLeft = 1.0;
        double metersRight = 20.0;

        System.out.println("Feet    Meters              Meters      Feet");
        System.out.println("---------------------------------------------");
        for (int i = 0; i < 10; i++) {
            double metersLeft = footToMeter(feetLeft);
            double feetRight = meterToFoot(metersRight);

            System.out.printf("%4.1f%10.3f%20.1f%10.3f\n", feetLeft, metersLeft, metersRight, feetRight);
            feetLeft += 1.0;
            metersRight += 5.0;
        }
        System.out.println("");
    }
}