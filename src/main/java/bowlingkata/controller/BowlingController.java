package bowlingkata.controller;

import bowlingkata.model.Bowling;
import bowlingkata.view.BowlingView;

public class BowlingController {

    BowlingView bowlingView;
    Bowling bowling;

    public BowlingController() {
        this.bowlingView = new BowlingView();
    }

    public void greetingAndMenue(){
        bowlingView.printGreeting();
        int menueInput = bowlingView.showMenue();

        if (menueInput == 1) {
            startGame();
        }else if (menueInput == 0) {
            System.exit(0);
        }
    }

    public void startGame() {
        this.bowling = new Bowling();
    }

}