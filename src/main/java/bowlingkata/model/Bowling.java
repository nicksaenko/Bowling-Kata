package bowlingkata.model;

import java.util.HashMap;
import java.util.Map;

    /* Punktesystem:

    Strike: Wenn erste Runde ein Strike: 10 Punkte + X (erster Wurf aus Runde 2) + Y (zweiter Wurf aus Runde 2)
    Spare: Wenn erste Runde: 8 Pins umgeworfen, dann Spare: 8 + 2 + X (erster Wurf aus Runde 2)

    Wenn im 9. Frame ein Strike oder ein Spare geworfen wurde, dann wird das natürlich für die Würfe im
    10. Frame berücksichtigt. Ein Strike oder ein Spare innerhalb des 10. Frames aktivieren die Bedingung nicht. */

public class Bowling {

    Map<Integer, String[]> frames;
    boolean finished = false;
    String throwValue;
    int round = 1;
    int points = 0;
    boolean thrownStrike = false;
    boolean thrownStrikeInARow = false;
    boolean thrownSpare = false;

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

            if (currentThrow[0] != null && !currentThrow[0].isEmpty()) {
                System.out.println("Es ist nicht möglich, im zweiten Wurf einen Strike zu werfen!");
            } else {
                currentThrow[0] = throwValue;
                currentThrow[1] = "";
                round++;

                points += 10;

                if (thrownSpare) {
                    points += 10;
                    thrownSpare = false;
                }

                if (thrownStrike) {
                    points += 10;

                    if (thrownStrikeInARow) {
                        points += 10;
                    }
                }

                thrownStrikeInARow = thrownStrike;
                thrownStrike = true;

            }

        } else { // 10. Runde
            String[] currentThrow = frames.get(round);

            // Erster Wurf
            if (currentThrow[0] == null) {
                currentThrow[0] = throwValue;
                points += 10;

                if (thrownStrike) {
                    points += 10;

                    if (thrownStrikeInARow) {
                        points += 10;
                    }
                }

                thrownStrikeInARow = thrownStrike;

                // Überprüfung, ob im 9. Frame ein Strike geworfen wurde
                String[] lastThrows = frames.get(round - 1);
                if (lastThrows[0].equals("X") && currentThrow[0] != null) { // falls ja, muss Verdopplung bestehen bleiben
                    thrownStrike = true;
                } else {                          // falls nein, darf Verdopplung im letzten Frame nicht stattfinden
                    thrownStrike = false;
                }

                // Zweiter Wurf
            } else if (currentThrow[1] == null) {
                currentThrow[1] = throwValue;
                points += 10;

                if (thrownStrike) {
                    points += 10;
                }
                thrownStrikeInARow = false;

                // Dritter Wurf
            } else if (currentThrow[2] == null) {
                currentThrow[2] = throwValue;
                points += 10;
                finished = true;
            }
        }
    }

    private void processSpare(String throwValue) {
        if (round < 10) {
            String[] currentThrow = frames.get(round);

            // Erster Wurf
            if (currentThrow[0] == null) {
                System.out.println("Es ist nicht möglich, im ersten Wurf einen Spare zu werfen!");

                // Zweiter Wurf
            } else if (currentThrow[1] == null) {
                currentThrow[1] = throwValue;
                thrownSpare = true;

                if (thrownStrike) {
                    points += 2 * (10 - Integer.parseInt(currentThrow[0]));
                    thrownStrike = false;
                } else {
                    points += (10 - Integer.parseInt(currentThrow[0]));
                }

                round++;
            }

        } else { // 10. Runde
            String[] currentThrow = frames.get(round);

            // Erster Wurf
            if (currentThrow[0] == null) {
                System.out.println("Es ist nicht möglich, im ersten Wurf einen Spare zu werfen!");

                // Zweiter Wurf
            } else if (currentThrow[1] == null) {
                currentThrow[1] = throwValue;
                points += (10 - Integer.parseInt(currentThrow[0]));

                // Dritter Wurf (falls erlaubt)
            } else if (currentThrow[2] == null && (!currentThrow[1].equals("X")) && (!currentThrow[1].equals("/"))) { // Ist das hier richtig?
                points += (10 - Integer.parseInt(currentThrow[1]));
                finished = true;
            }
        }

    }

    private void processNumber(String throwValue) {
        if (round < 10) {
            String[] currentThrows = frames.get(round);
            int pins = Integer.parseInt(throwValue);

            // Erster Wurf
            if (currentThrows[0] == null) {
                currentThrows[0] = throwValue;

                if (thrownStrikeInARow) {
                    points += 3 * pins;
                    thrownStrikeInARow = false;

                } else if (thrownStrike || thrownSpare) {
                    points += 2 * pins;
                    thrownSpare = false;

                } else {
                    points += pins;
                }

                // Zweiter Wurf
            } else {

                if (pins + Integer.parseInt(currentThrows[0]) > 10) {
                    System.out.println("Es gibt doch nur 10 Pins ;)");

                } else {
                    currentThrows[1] = throwValue;

                    if (thrownStrike) {
                        points += 2 * Integer.parseInt(throwValue);
                        thrownStrike = false;
                    } else {
                        points += Integer.parseInt(throwValue);
                    }
                    round++;
                }
            }

        } else { // 10. Runde
            String[] currentThrows = frames.get(round);

            // Erster Wurf
            if (currentThrows[0] == null) {
                currentThrows[0] = throwValue;

                if (thrownSpare) {
                    points += 2 * Integer.parseInt(throwValue);
                } else if (thrownStrike) {
                    points += 2 * Integer.parseInt(throwValue);
                } else {
                    points += Integer.parseInt(throwValue);
                }

                // Zweiter Wurf
            } else if (currentThrows[1] == null) {
                currentThrows[1] = throwValue;

                if (thrownStrike) {
                    points += 2 * Integer.parseInt(throwValue);
                    thrownStrike = false;
                } else {
                    points += Integer.parseInt(throwValue);
                }


                if (!(currentThrows[0].equals("X")) && !(currentThrows[1].equals("/"))) {
                    finished = true;
                }

                // Dritter Wurf (falls erlaubt)
            } else if (currentThrows[2] == null && (currentThrows[0].equals("X") || currentThrows[1].equals("/"))) {
                currentThrows[2] = throwValue;
                points += Integer.parseInt(throwValue);
                finished = true;
            }
        }

    }

    public boolean getFinishedState() {
        return finished;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Frame\t\t");
        for (int i = 1; i <= 10; i++) {
            builder.append(i).append("\t\t");
        }

        builder.append("\n");

        builder.append("Würfe\t\t");
        for (int i = 1; i <= 10; i++) {
            String[] frame = frames.get(i);
            if (frame != null) {

                if (frame[0] != null) {
                    builder.append(frame[0]);
                } else {
                    builder.append("-");
                }

                builder.append(" ");

                if (frame.length > 1 && frame[1] != null) {
                    builder.append(frame[1]);
                } else {
                    builder.append("-");
                }

                if (i == 10 && frame.length == 3 && frame[2] != null) {
                    builder.append(" ").append(frame[2]);
                }
                builder.append("\t\t");
            }
        }
        builder.append("\nPunkte: " + points);

        return builder.toString();
    }

}