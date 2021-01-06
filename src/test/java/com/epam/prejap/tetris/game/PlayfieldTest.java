package com.epam.prejap.tetris.game;

import com.epam.prejap.tetris.block.BlockFeed;
import org.testng.annotations.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Pattern;

import static org.testng.Assert.*;

public class PlayfieldTest {

    private final String SCORE_REGEX = "Score: \\d";

    private static PrintStream originalPrintStream;
    private static ByteArrayOutputStream output;

    @BeforeGroups(groups = "score")
    public void setUpStreams() {
        originalPrintStream = System.out;
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @Test(groups = "score")
    public void scoreShouldIncreaseWhenNewBlockAppears() {
        //given
        Playfield playfield = createSamplePlayfield();
        int expectedScore = 1;

        //when
        playfield.nextBlock();
        int actualScore = playfield.getScore();

        //then
        assertEquals(actualScore, expectedScore);
    }

    @Test(groups = "score")
    public void scoreShouldBeDisplayedWhenNewBlockAppears() {
        //given
        Playfield playfield = createSamplePlayfield();

        //when
        playfield.nextBlock();
        String actualString = output.toString();

        //then
        if (!Pattern.compile(SCORE_REGEX).matcher(actualString).find()) {
            fail();
        }
    }

    @Test(groups = "score", dataProvider = "moveValues")
    public void scoreShouldBeDisplayedAfterMove(Move move) {
        //given
        Playfield playfield = createSamplePlayfield();
        playfield.nextBlock();

        cleanUpStreams();

        //when
        playfield.move(move);
        String actualString = output.toString();

        //then
        if (!Pattern.compile(SCORE_REGEX).matcher(actualString).find()) {
            fail();
        }

    }

    @DataProvider
    public Object[] moveValues() {
        return Move.values();
    }

    @AfterGroups(groups = "score")
    public void cleanUpStreams() {
        System.setOut(originalPrintStream);
    }

    private Playfield createSamplePlayfield() {
        int rows = 10;
        int columns = 20;
        BlockFeed feed = new BlockFeed();
        Printer printer = new Printer(System.out);

        return new Playfield(rows, columns, feed, printer);
    }
}
