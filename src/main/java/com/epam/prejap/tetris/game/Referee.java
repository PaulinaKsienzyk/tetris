package com.epam.prejap.tetris.game;

class Referee {

    private int currentScore = 0;

    int awardPoints() {
        return currentScore++;
    }
}
