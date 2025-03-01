package bowlingkata.model;

import java.util.HashMap;
import java.util.Map;

public class Bowling {

    Map<Integer, String[]> frames;

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

}