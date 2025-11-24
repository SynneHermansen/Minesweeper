package no.uib.inf101.minesweeper.Mine;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.uib.inf101.minesweeper.mine.RandomMinePlacement;
import no.uib.inf101.minesweeper.model.*;

public class RandomMinePlacementTest {
    private Board board;
    private RandomMinePlacement minePlacement;
    private final int numberOfMines = 50;

    @BeforeEach
    public void setUp() {
        board = new Board(10, 10);
        minePlacement = new RandomMinePlacement(board, numberOfMines);
        minePlacement.placeMines(1,0);
    }

    @Test
    public void testCorrectNumberOfMinesPlaced() {
        int mineCount = 0;

        for (int row = 0; row < board.rows(); row++) {
            for (int col = 0; col < board.cols(); col++) {
                if (board.getCell(row, col).hasMine()) {
                    mineCount++;
                }
            }
        }

        assertEquals(numberOfMines, mineCount);
    }
}
