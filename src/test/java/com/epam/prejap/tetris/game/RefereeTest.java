package com.epam.prejap.tetris.game;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class RefereeTest {

    @Test(groups = "Score")
    public void scoreShouldIncreaseWhenNewBlockAppears() {
        //given
        Referee referee = new Referee();
        referee.newBlockAppeared();
        int expectedScore = 1;

        //when
        int actualScore = referee.currentScore();

        //then
        assertEquals(actualScore, expectedScore);
    }
}
