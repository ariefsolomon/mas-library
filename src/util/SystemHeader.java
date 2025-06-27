package util;

public class SystemHeader {
    public static void showHeader(String message, int width, int verticalPadding) {
        System.out.println("\n" + "=".repeat(width));

        for (int i = 0; i < verticalPadding; i++) {
            System.out.println("|" + " ".repeat(width - 2) + "|");
        }

        int space = width - 2;
        int paddingLeft = (space - message.length()) / 2;
        int paddingRight = space - message.length() - paddingLeft;
        String centeredLine = "|" + " ".repeat(paddingLeft) + message + " ".repeat(paddingRight) + "|";
        System.out.println(centeredLine);

        for (int i = 0; i < verticalPadding; i++) {
            System.out.println("|" + " ".repeat(width - 2) + "|");
        }

        System.out.println("=".repeat(width));
    }

    public static void showSubHeader(String message, int width) {
        int padding = (width - message.length()) / 2;

        String left = "-".repeat(padding);
        String right = "-".repeat(width - message.length() - padding);

        System.out.println("\n" + left + message + right);
    }
}
