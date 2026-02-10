package mod4;

import java.util.*;
import java.io.*;

public class Ex20_11 {
    public static void main(String[] args) {
        File file = new File(args[0]);
        if (!file.exists()) {
            System.out.println("File not found");
        }
        Stack<Character> symbols = new Stack<>();
        boolean correct = true;

        try (Scanner input = new Scanner(file)) {
            while (input.hasNext() && correct) {
                String line = input.nextLine();
                for (int i = 0; i < line.length(); i++) {
                    if (correct) {
                        char ch = line.charAt(i);
                        if (ch == '(' || ch == '{' || ch == '[') {
                            symbols.push(ch);
                        } else if (ch == ')' || ch == '}' || ch == ']') {
                            if (symbols.isEmpty() || !isMatching(symbols.pop(), ch)) {
                                correct = false;
                            }
                        }
                    } else {
                        break;
                    }
                }
            }
            if (correct && symbols.isEmpty()) {
                System.out.println("Correct grouping pairs");
            } else {
                System.out.println("Incorrect grouping pairs");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static boolean isMatching(char ch1, char ch2) {
        return (ch1 == '(' && ch2 == ')') || (ch1 == '{' && ch2 == '}') || (ch1 == '[' && ch2 == ']');
    }
}