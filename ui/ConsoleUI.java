package ui;
import java.io.Console;
public class ConsoleUI {

    // Reset
    public static void startupAnimation() {

    try {
        System.out.println(CYAN + BOLD);

        System.out.println("*************************************************************************");
        Thread.sleep(200);

        System.out.println("        S M A R T   E V   C H A R G E R  A L L O C A T I O N");
        Thread.sleep(500);
        System.out.println();
        System.out.println("                      S Y S T E M");
        Thread.sleep(500);

        System.out.println("*************************************************************************");

        System.out.println(RESET);

    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
}
    public static final String RESET = "\u001B[0m";

    // Colors
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    // Bold
    public static final String BOLD = "\u001B[1m";

    public static void title(String text) {

        System.out.println();
        System.out.println(CYAN + BOLD
                + "============================================================"
                + RESET);

        System.out.println(CYAN + BOLD
                + "              " + text
                + RESET);

        System.out.println(CYAN + BOLD
                + "============================================================"
                + RESET);
    }

    public static void section(String text) {

        System.out.println();
        System.out.println(BLUE + BOLD
                + "-------------------- " + text
                + " --------------------"
                + RESET);
    }

    public static void success(String text) {
        System.out.println(GREEN + "" + text + RESET);
    }

    public static void error(String text) {
        System.out.println(RED + "" + text + RESET);
    }

    public static void warning(String text) {
        System.out.println(YELLOW + "" + text + RESET);
    }
     public static void input(String text) {
        System.out.print(YELLOW + text + RESET);
    }

    public static void info(String text) {
        System.out.println(CYAN + text + RESET);
    }

    public static void line() {
        System.out.println(CYAN
                + "------------------------------------------------------------"
                + RESET);
    }
}
