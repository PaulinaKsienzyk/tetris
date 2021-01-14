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

    public int currentScore() {
        return currentScore;
    }

    private void awardPoints() {
        currentScore += 1;
    }

    @Override
    public String toString() {
        return "Score: " + currentScore;
    }
}
