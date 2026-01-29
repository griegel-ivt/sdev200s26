package mod2;
import java.util.Scanner;

public class Ex12_9 {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Enter a binary number: ");
            String binaryString = input.nextLine();

            try {
                System.out.print("Decimal value: " + bin2Dec(binaryString));
            } catch (BinaryFormatException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
    public static int bin2Dec(String binaryString) throws BinaryFormatException {
        int decimalValue = 0;
        for (int i = 0; i < binaryString.length(); i++) {
            char ch = binaryString.charAt(i);
            if (ch == '1') {
                decimalValue += Math.pow(2, binaryString.length() - i - 1);
            } else if (ch == '0') {
                //Do nothing
            } else {
                throw new BinaryFormatException("String isn't binary. ");
            }
        }
        return decimalValue;
    }
}
class BinaryFormatException extends Exception {
    public BinaryFormatException(String message) {
        super(message);
    }
}