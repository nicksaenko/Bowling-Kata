package bowlingkata.model;

import java.util.HashMap;
import java.util.Map;

public class Bowling {

    Map<Integer, String[]> frames;
    boolean finished = false;

    public Bowling() {
        frames = new HashMap<>();
        for (int i = 1; i <= 10; i++) {

            if (i == 10){
                frames.put(i, new String[3]);
            }else {
                frames.put(i, new String[2]);
            }
        }
    }

    public void processThrow(String throwInput){
        // TODO: Bowling-Punktesystem-Logik implementieren
        System.out.println("User hat " + throwInput + "eingeben.");
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