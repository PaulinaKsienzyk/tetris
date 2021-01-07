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
    private static PrintStream myPrintStream;
    private static ByteArrayOutputStream output;

    @Test(groups = "score")
    public void scoreShouldIncreaseWhenNewBlockAppears() {
        //given
        Playfield playfield = createSamplePlayfieldWithOutput(System.out);
        int expectedScore = 1;

        //when
        playfield.nextBlock();
        int actualScore = playfield.getScore();

        //then
        assertEquals(actualScore, expectedScore);
    }

    @Test(groups = "score")
    public void scoreShouldBeDisplayedWhenNewBlockAppears() {
        setUpStreams();

        //given
        Playfield playfield = createSamplePlayfieldWithOutput(myPrintStream);

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
        setUpStreams();

        //given
        Playfield playfield = createSamplePlayfieldWithOutput(myPrintStream);
        playfield.nextBlock();

        output.reset();

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

    private void setUpStreams() {
        originalPrintStream = System.out;
        output = new ByteArrayOutputStream();
        myPrintStream = new PrintStream(output);
        System.setOut(myPrintStream);
    }

    @AfterGroups(groups = "score")
    private void cleanUpStreams() {
        System.setOut(originalPrintStream);
    }

    private Playfield createSamplePlayfieldWithOutput(PrintStream printStream) {
        int rows = 10;
        int columns = 20;
        BlockFeed feed = new BlockFeed();
        Printer printer = new Printer(printStream);

        return new Playfield(rows, columns, feed, printer);
    }
}
