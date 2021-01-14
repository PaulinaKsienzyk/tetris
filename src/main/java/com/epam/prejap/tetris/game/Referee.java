package com.epam.prejap.tetris.game;

/**
 * Holds current number of points and awards new one for each block acquired.
 */
public class Referee implements PlayfieldObserver {

    private int currentScore;

    @Override
    public void newBlockAppeared() {
        awardPoints();
    }

    public String currentScore() {
        return String.valueOf(currentScore);
    }

    private void awardPoints() {
        currentScore += 1;
    }
}
