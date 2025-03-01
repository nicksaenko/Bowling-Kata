package bowlingkata.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BowlingView {

    private Scanner scanner = new Scanner(System.in);
    private int menueInput = -1;
    String throwInput;

    public void printGreeting() {
        System.out.println("Hi! Willkommen beim Bowling Kata!");
    }

    public int showMenue() {
        System.out.println("\n--- Eingabe ---");
        System.out.println("(1) Starte das Spiel");
        System.out.println("(0) Beenden");

        if (scanner.hasNextInt()) {
            menueInput = scanner.nextInt();
            validateMenueInput(menueInput);
        } else {
            errorOutput();
        }
        return menueInput;
    }

    public String userThrowInput() {
        System.out.println("\nBitte trage dein Wurfergebnis ein (0-9), (X), (/): ");

        if (scanner.hasNextLine()) {
            throwInput = scanner.next();
            validateThrowInput(throwInput);
        } else {
            errorOutput();
        }
        return throwInput;
    }

    private void validateThrowInput(String throwInput) {
        List<String> validInputs = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "X", "/");
        if (!validInputs.contains(throwInput)) {
            errorOutput();
            userThrowInput();
        }
    }

    private void validateMenueInput(int menueInput) {
        if (menueInput != 1 && menueInput != 0) {
            errorOutput();
            showMenue();
        }
    }

    private void errorOutput() {
        System.out.println("Ungueltiger Eintrag!");
    }

}