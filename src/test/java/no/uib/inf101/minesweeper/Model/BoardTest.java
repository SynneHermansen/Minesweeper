package no.uib.inf101.minesweeper.Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import no.uib.inf101.minesweeper.model.Board;
import no.uib.inf101.minesweeper.model.Cell;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(15, 10);
    }

    @Test
    void testBoardDimensions() {
        assertEquals(15, board.rows(), "Antall rader skal være 15");
        assertEquals(10, board.cols(), "Antall kolonner skal være 10");
    }

    @Test
    void testCellInitialization() {
        for (int row = 0; row < board.rows(); row++) {
            for (int col = 0; col < board.cols(); col++) {
                Cell cell = board.getCell(row, col);
                assertNotNull(cell, "Cellen skal ikke være null");
                assertEquals('-', cell.getSymbol(), "Cellen skal ha standard symbol '-'");
            }
        }
    }

    @Test
    void testIteratorFunctionality() {
        int count = 0;
        for (Cell cell : board) {
            assertNotNull(cell, "Cellen skal ikke være null i iterasjonen");
            count++;
        }
        assertEquals(15 * 10, count, "Iteratoren skal gå gjennom alle cellene i brettet");
    }
}
