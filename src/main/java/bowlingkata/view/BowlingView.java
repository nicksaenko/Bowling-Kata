package bowlingkata.view;

import java.util.Scanner;

public class BowlingView {

    private Scanner scanner = new Scanner(System.in);
    private int menueInput = -1;

    public void printGreeting(){
        System.out.println("Hi! Willkommen beim Bowling Kata!");
    }

    public int showMenue(){
        System.out.println("\n--- Eingabe ---");
        System.out.println("(1) Starte das Spiel");
        System.out.println("(0) Beenden");

        if (scanner.hasNextInt()) {
            menueInput = scanner.nextInt();
            validateMenueInput(menueInput);
        }else{
            errorOutput();
        }
        return menueInput;
    }

    private void validateMenueInput(int menueInput){
        if (menueInput != 1 && menueInput != 0){
            errorOutput();
            showMenue();
        }
    }

    private void errorOutput(){
        System.out.println("Bitte trage eine 1 oder 0 ein.");
    }

}