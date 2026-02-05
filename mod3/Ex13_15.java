package mod3;

import java.math.BigInteger;
import java.util.Scanner;

public class Ex13_15 {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            System.out.print("Enter rational r1 with numerator and denominator separated by a space: ");
            BigInteger n1 = input.nextBigInteger();
            BigInteger d1 = input.nextBigInteger();
            System.out.print("Enter rational r2 with numerator and denominator separated by a space: ");
            BigInteger n2 = input.nextBigInteger();
            BigInteger d2 = input.nextBigInteger();

            Rational r1 = new Rational(n1, d1);
            Rational r2 = new Rational(n2, d2);

            System.out.println(r1 + " + " + r2 + " = " + r1.add(r2));
            System.out.println(r1 + " - " + r2 + " = " + r1.subtract(r2));
            System.out.println(r1 + " * " + r2 + " = " + r1.multiply(r2));
            System.out.println(r1 + " / " + r2 + " = " + r1.divide(r2));
            System.out.println(r2 + " is " + r2.doubleValue());
        }
    }
}

class Rational extends Number implements Comparable<Rational> {
    // Data fields for numerator and denominator
    private BigInteger numerator = BigInteger.ZERO;
    private BigInteger denominator = BigInteger.ONE;

    /** Construct a rational with default properties */
    public Rational() {
        this(BigInteger.ZERO, BigInteger.ONE);
    }

    /** Construct a rational with specified numerator and denominator */
    public Rational(BigInteger numerator, BigInteger denominator) {
        BigInteger gcd = numerator.gcd(denominator);
        BigInteger sign = (denominator.signum() > 0) ? BigInteger.ONE : new BigInteger("-1");
        this.numerator = numerator.divide(gcd).multiply(sign);
        this.denominator = denominator.abs().divide(gcd);
    }

    /** Find GCD of two numbers */
    /* Unused since BigInteger contains a gcd() method
    private static long gcd(long n, long d) {
        long n1 = Math.abs(n);
        long n2 = Math.abs(d);
        int gcd = 1;
        
        for (int k = 1; k <= n1 && k <= n2; k++) {
            if (n1 % k == 0 && n2 % k == 0) 
                gcd = k;
        }

        return gcd;
    }
    */

    /** Return numerator */
    public BigInteger getNumerator() {
        return numerator;
    }

    /** Return denominator */
    public BigInteger getDenominator() {
        return denominator;
    }

    /** Add a rational number to this rational */
    public Rational add(Rational secondRational) {
        BigInteger n = numerator.multiply(secondRational.getDenominator())
        .add(denominator.multiply(secondRational.getNumerator()));
        BigInteger d = denominator.multiply(secondRational.getDenominator());
        return new Rational(n, d);
    }

    /** Subtract a rational number from this rational */
    public Rational subtract(Rational secondRational) {
        BigInteger n = numerator.multiply(secondRational.getDenominator())
        .subtract(denominator.multiply(secondRational.getNumerator()));
        BigInteger d = denominator.multiply(secondRational.getDenominator());
        return new Rational(n, d);
    }

    /** Multiply a rational number to this rational */
    public Rational multiply(Rational secondRational) {
        BigInteger n = numerator.multiply(secondRational.getNumerator());
        BigInteger d = denominator.multiply(secondRational.getDenominator());
        return new Rational(n, d);
    }

    /** Divide a rational number from this rational */
    public Rational divide(Rational secondRational) {
        BigInteger n = numerator.multiply(secondRational.getDenominator());
        BigInteger d = denominator.multiply(secondRational.getNumerator());
        return new Rational(n, d);
    }

    @Override  
    public String toString() {
        if (denominator.equals(BigInteger.ONE))
            return numerator.toString();
        else
            return numerator.toString() + "/" + denominator.toString();
    }

    @Override // Override the equals method in the Object class 
    public boolean equals(Object other) {
        if (((Rational)other).subtract(this).getNumerator().equals(BigInteger.ZERO))
            return true;
        else
            return false;
    }

    @Override // Implement the abstract intValue method in Number 
    public int intValue() {
        return (int)doubleValue();
    }

    @Override // Implement the abstract floatValue method in Number 
    public float floatValue() {
        return (float)doubleValue();
    }

    @Override // Implement the doubleValue method in Number 
    public double doubleValue() {
        return numerator.doubleValue() / denominator.doubleValue();
    }

    @Override // Implement the abstract longValue method in Number
    public long longValue() {
        return (long)doubleValue();
    }

    @Override // Implement the compareTo method in Comparable
    public int compareTo(Rational o) {
        BigInteger diff = this.subtract(o).getNumerator();
        return diff.signum();
    }
}