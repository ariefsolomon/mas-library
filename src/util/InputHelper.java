package util;

import java.util.Scanner;

public class InputHelper {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getInputInterval(int min, int max) {
        String input = scanner.nextLine();
        try {
            int value = Integer.parseInt(input);
            if (value >= min && value <= max) {
                return value;
            }
            return -1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static String getInputString(boolean lowercase) {
        String input = scanner.nextLine();
        if (lowercase) {
            return input.toLowerCase();
        }
        return input;
    }
}
