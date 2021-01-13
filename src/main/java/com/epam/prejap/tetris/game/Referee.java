package com.epam.prejap.tetris.game;

public class Referee {

    private int currentScore;

    void  awardPoints() {
        currentScore += 1;
    }

    public int getScore() {
        return currentScore;
    }
}
