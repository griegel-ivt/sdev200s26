package mod1;
import java.util.Scanner;

public class Ex8_29 {
    public static boolean equals(int[][] m1, int[][] m2) {
        boolean equal = true;
        for (int i=0; i < 3; i++) {
            for (int j=0; j < 3; j++) {
                if (m1[i][j] != m2[i][j]) {
                    equal = false;
                }
            }
        }
        return equal;
    }
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Enter m1 (a 3 by 3 matrix) row by row:");
            String[] im1 = input.nextLine().split(" ");
            System.out.println("Enter m2 (a 3 by 3 matrix) row by row:");
            String[] im2 = input.nextLine().split(" ");
            
            int[][] m1 = new int[3][3];
            int[][] m2 = new int[3][3];

            int sel = 0;
            for (int i=0; i < 3; i++) {
                for (int j=0; j < 3; j++) {
                    m1[i][j] = Integer.parseInt(im1[sel]);
                    m2[i][j] = Integer.parseInt(im2[sel]);
                    sel++;
                }
            }
            
            if (equals(m1, m2)) {
                System.out.println("The two arrays are identical.");
            } else {
                System.out.println("The two arrays are not identical.");
            }
        }
    }
}