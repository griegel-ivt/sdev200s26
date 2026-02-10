package mod4;

import java.io.*;
import java.util.*;

public class Ex21_3 {
    public static void main(String[] args) throws Exception {  
        File file = new File(args[0]);
        if (file.exists()) {
            System.out.println("The number of keywords in the program is " + countKeywords(file));
        }
        else {
            System.out.println("File does not exist");
        }    
    }

    public static int countKeywords(File file) throws Exception {  
        // Array of all Java keywords + true, false and null
        String[] keywordString = {"abstract", "assert", "boolean", 
        "break", "byte", "case", "catch", "char", "class", "const",
        "continue", "default", "do", "double", "else", "enum",
        "extends", "for", "final", "finally", "float", "goto",
        "if", "implements", "import", "instanceof", "int", 
        "interface", "long", "native", "new", "package", "private",
        "protected", "public", "return", "short", "static", 
        "strictfp", "super", "switch", "synchronized", "this",
        "throw", "throws", "transient", "try", "void", "volatile",
        "while", "true", "false", "null"};

        Set<String> keywordSet = new HashSet<>(Arrays.asList(keywordString));
        String code = "";
        try (Scanner input = new Scanner(file)) {
            while (input.hasNextLine()) {
                code += input.nextLine() + "\n";
            }
        }

        int count = 0;
        boolean inString = false;
        boolean inComment = false;
        String currentWord = "";
        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);
            char nextC;
            if (i + 1 < code.length()) {
                nextC = code.charAt(i + 1);
            } else {
                nextC = ' ';
            }

            if (!inString && !inComment) {
                if (c == '"') {
                    inString = true;
                    continue;
                }
                else if (c == '/' && nextC == '/') {
                    inComment = true;
                    i++;
                    continue;
                }
            }
            else if (inString && c == '"') {
                inString = false;
                continue;
            }
            else if (inComment && c == '\n') {
                inComment = false;
                continue;
            }

            if (!inString && !inComment) {
                if (Character.isLetter(c)) {
                    currentWord += c;
                } else {
                    if (currentWord.length() > 0) {
                        if (keywordSet.contains(currentWord)) count++;
                        currentWord = "";
                    }
                }
            }
        }
        return count;
    }
}