package com.epam.prejap.tetris.game;

import com.epam.prejap.tetris.block.BlockFeed;
import org.testng.annotations.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Pattern;

import static org.testng.Assert.*;

public class PlayfieldTest {

    private final String SCORE_REGEX = "Score: \\d";

    private static PrintStream myPrintStream;
    private static ByteArrayOutputStream output;
    private static Pattern scorePattern;

    @BeforeGroups(groups = "Score")
    private void setUp() {
        output = new ByteArrayOutputStream();
        myPrintStream = new PrintStream(output);
        scorePattern = Pattern.compile(SCORE_REGEX);
    }

    @Test(groups = "Score")
    public void scoreShouldIncreaseWhenNewBlockAppears() {
        //given
        Playfield playfield = createSamplePlayfieldWithOutput(myPrintStream);
        int expectedScore = 1;

        //when
        playfield.nextBlock();
        int actualScore = playfield.getScore();

        //then
        assertEquals(actualScore, expectedScore);
    }

    @Test(groups = "Score")
    public void scoreShouldBeDisplayedWhenNewBlockAppears() {

        //given
        Playfield playfield = createSamplePlayfieldWithOutput(myPrintStream);

        //when
        playfield.nextBlock();
        String actualString = output.toString();

        //then
        assertTrue(scorePattern.matcher(actualString).find());
    }

    @Test(groups = "Score", dataProvider = "moveValues")
    public void scoreShouldBeDisplayedAfterMove(Move move) {

        //given
        Playfield playfield = createSamplePlayfieldWithOutput(myPrintStream);
        playfield.nextBlock();

        output.reset();

        //when
        playfield.move(move);
        String actualString = output.toString();

        //then
        assertTrue(scorePattern.matcher(actualString).find());
    }

    @DataProvider
    public Object[] moveValues() {
        return Move.values();
    }

    private Playfield createSamplePlayfieldWithOutput(PrintStream printStream) {
        int rows = 10;
        int columns = 20;
        BlockFeed feed = new BlockFeed();
        Printer printer = new Printer(printStream, new Timer(500));

        return new Playfield(rows, columns, feed, printer);
    }
}
