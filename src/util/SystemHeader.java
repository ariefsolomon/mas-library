package util;

public class SystemHeader {
    public static void showHeader(String message) {
        int totalWidth = 40;
        int padding = (totalWidth - 2 - message.length()) / 2; // -2 untuk karakter '|'

        StringBuilder line = new StringBuilder();
        line.append("|");

        // Add space to the left
        for (int i = 0; i < padding; i++) {
            line.append(" ");
        }

        line.append(message);

        // Add space to the right
        int remainingSpace = totalWidth - 2 - padding - message.length();
        for (int i = 0; i < remainingSpace; i++) {
            line.append(" ");
        }

        line.append("|");

        // Print header
        System.out.println("\n" + "=".repeat(totalWidth));
        System.out.println(line.toString());
        System.out.println("=".repeat(totalWidth));
    }

    public static void showSubHeader(String message) {
        int totalWidth = 40;
        int padding = (totalWidth - message.length()) / 2;

        String left = "-".repeat(padding);
        String right = "-".repeat(totalWidth - message.length() - padding);

        System.out.println("\n" + left + message + right);
    }
}
