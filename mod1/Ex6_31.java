package mod1;
import java.util.Scanner;

public class Ex6_31 {
    public static boolean isValid(long number) {
        return (sumOfDoubleEvenPlace(number) + sumOfOddPlace(number)) % 10 == 0;
    }

    public static int sumOfDoubleEvenPlace(long number) {
        int sum = 0;
        number = number / 10;

        while (number > 0) {
            int digit = (int) (number % 10);
            sum += getDigit(digit*2);
            number = number / 100;
        }
        return sum;
    }

    public static int getDigit(int number) {
        if (number < 10) {
            return number;
        } else {
            return (number / 10 + number % 10);
        }
    }

    public static int sumOfOddPlace(long number) {
        int sum = 0;
        while (number > 0) {
            int digit = (int) (number % 10);
            sum += getDigit(digit);
            number = number / 100;
        }
        return sum;
    }

    public static boolean prefixMatched(long number, int d) {
        return getPrefix(number, getSize(d)) == d;
    }

    public static int getSize(long d) {
        return Long.toString(d).length();
    }

    public static long getPrefix(long number, int k) {
        if (getSize(number) > k) {
            return number / (long) (Math.pow(10, getSize(number) - k));
        }
        return number;
    }

    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            System.out.print("Enter Card Number: ");
            long cardNumber = scan.nextLong();
            if (isValid(cardNumber)) {
                System.out.println("Card Number Valid.");
            } else {
                System.out.println("Card Number Invalid.");
            }
        }
    }
}