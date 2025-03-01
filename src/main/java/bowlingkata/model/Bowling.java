package bowlingkata.model;

import java.util.HashMap;
import java.util.Map;

public class Bowling {

    Map<Integer, String[]> frames;
    boolean finished = false;
    String throwValue;
    int round = 1;

    public Bowling() {
        frames = new HashMap<>();
        for (int i = 1; i <= 10; i++) {

            if (i == 10) {
                frames.put(i, new String[3]);
            } else {
                frames.put(i, new String[2]);
            }
        }
    }

    public void processThrow(String throwInput) {
        // TODO: Bowling-Punktesystem-Logik implementieren
        this.throwValue = throwInput;

        switch (throwValue) {
            case "X":
                processStrike(throwValue);
                break;
            case "/":
                processSpare(throwValue);
                break;
            default:
                processNumber(throwValue);
                break;
        }
    }

    private void processStrike(String throwValue) {
        if (round < 10) {
            String[] currentThrow = frames.get(round);

            if (currentThrow[0] == null) {
                currentThrow[0] = throwValue;
                currentThrow[1] = "";
                round++;
            } else { // der erste Wurf ist 1-9
                System.out.println("Es ist nicht möglich, im zweiten Wurf einen Strike zu werfen!");
            }

        } else { // 10. Runde
            String[] currentThrow = frames.get(round);

            if (currentThrow[0] == null) {
                currentThrow[0] = throwValue;

            } else if (currentThrow[1] == null) {
                System.out.println("Es ist nicht möglich, im zweiten Wurf einen Strike zu werfen!");

            } else if (currentThrow[2] == null && (currentThrow[0].equals(throwValue) || currentThrow[1].equals("/"))) {
                currentThrow[2] = throwValue;
            }
            finished = true;
        }

    }

    private void processSpare(String throwValue) {
        if (round < 10) {
            String[] currentThrow = frames.get(round);

            if (currentThrow[0] == null) {
                System.out.println("Es ist nicht möglich, im ersten Wurf einen Spare zu werfen!");

            } else if (currentThrow[2] == null) {
                currentThrow[2] = throwValue;
                round++;
            }

        } else { // 10. Runde
            String[] currentThrow = frames.get(round);

            if (currentThrow[0] == null) {
                System.out.println("Es ist nicht möglich, im ersten Wurf einen Spare zu werfen!");
            } else if (currentThrow[2] == null) {
                currentThrow[2] = throwValue;
            } else {
                System.out.println("Es ist nicht möglich, im dritten Wurf einen Spare zu werfen!");
            }

        }
    }

    private void processNumber(String throwValue) {
        if (round < 10) {
            String[] currentThrows = frames.get(round);

            if (currentThrows[0] == null) {
                currentThrows[0] = throwValue;
            } else {
                currentThrows[1] = throwValue;
                round++;
            }

        } else { // 10. Runde
            String[] currentThrows = frames.get(round);

            if (currentThrows[0] == null) {
                currentThrows[0] = throwValue;
            } else if (currentThrows[1] == null && !(currentThrows[0].equals("X"))) {
                currentThrows[1] = throwValue;
            } else if (currentThrows[2] == null && (currentThrows[0].equals("X") || currentThrows[1].equals("/"))) {
                currentThrows[2] = throwValue;
            }
            finished = true;
        }

    }

    public boolean getFinishedState() {
        return finished;
    }

    @Override
    public String toString() {
        //TODO: Punktesystem-Ansicht implementieren
        return null;
    }

}