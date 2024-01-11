package cinema.userInteraction;

import java.util.Scanner;

public abstract class Menu {
    public static final String DEFAULT_COLOUR = "\u001B[0m";
    public static final String YELLOW_COLOUR = "\u001B[33m";
    public static final String RED_COLOUR = "\u001B[31m";
    public static final String GREEN_COLOUR = "\u001B[32m";
    public static final String BLUE_COLOUR = "\u001B[34m";

    String[] options;
    public static Scanner scanner = new Scanner(System.in);
    boolean shouldBeTerminated = false;

    public void executeMenu() {
        do {
            printMenu();
            String choice = getUserChoice();
            processCurrentChoice(choice);
        } while (!shouldBeTerminated);
    }

    private void printMenu() {
        System.out.println(YELLOW_COLOUR + "To choose any option enter it's number\n");
        for (String line : options) {
            System.out.println(line);
        }
        System.out.print("\nYour choice: " + DEFAULT_COLOUR);
    }

    private String getUserChoice() {
        return scanner.nextLine();
    }

    protected abstract void processCurrentChoice(String choice);


    public static void printlnYellowMessage(String message) {
        System.out.println(YELLOW_COLOUR + message + DEFAULT_COLOUR);
    }

    public static void printYellowMessage(String message) {
        System.out.print(YELLOW_COLOUR + message + DEFAULT_COLOUR);
    }

    public static void printlnRedMessage(String message) {
        System.out.println(RED_COLOUR + message + DEFAULT_COLOUR);
    }

    public static void printlnGreenMessage(String message) {
        System.out.println(GREEN_COLOUR + message + DEFAULT_COLOUR);
    }

    public static void printlnBlueMessage(String message) {
        System.out.println(BLUE_COLOUR + message + DEFAULT_COLOUR);
    }
}
