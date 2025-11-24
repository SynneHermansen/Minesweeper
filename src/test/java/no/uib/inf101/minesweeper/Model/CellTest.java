package no.uib.inf101.minesweeper.Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.minesweeper.model.Cell;

class CellTest {

    private Cell cell;
    private Cell mineCell;

    @BeforeEach
    void setUp() {
        cell = new Cell(new CellPosition(2, 3), '-');
        mineCell = new Cell(new CellPosition(1, 1), '-');
        mineCell.setMine(true);
    }

    @Test
    void testCellInitialization() {
        assertFalse(cell.hasMine(), "Cellen skal ikke ha en mine ved begynnelsen");
        assertFalse(cell.isRevealed(), "Cellen skal ikke være avslørt heller");
        assertFalse(cell.isFlagged(), "Cellen skal ikke være flagget");
        assertEquals('-', cell.getSymbol(), "Cellen skal ha standard symbol '-'.");
    }

    @Test
    void testSetMine() {
        cell.setMine(true);
        assertTrue(cell.hasMine(), "Cellen skal ha en mine etter setMine(true).");
    }

    @Test
    void testRevealCellWithoutMine() {
        cell.setMineCount(2);
        cell.reveal();
        assertTrue(cell.isRevealed(), "Cell skal være avslørt etter reveal().");
        assertEquals('2', cell.getSymbol(), "Cell skal vise antall nabominer etter reveal().");
    }

    @Test
    void testRevealCellWithMine() {
        mineCell.reveal();
        assertTrue(mineCell.isRevealed(), "MineCell skal være avslørt etter reveal().");
        assertEquals('*', mineCell.getSymbol(), "MineCell skal vise '*' etter reveal().");
    }

    @Test
    void testFlaggingCell() {
        cell.setFlagged(true);
        assertTrue(cell.isFlagged(), "Cellen skal være flagget etter setFlagged(true).");
    }

    @Test
    void testMineCount() {
        cell.setMineCount(3);
        assertEquals(3, cell.getMineCount(), "Cellen skal ha korrekt mineCount.");
    }

    @Test
    void testGetPosition() {
        assertEquals(new CellPosition(2, 3), cell.getPos(), "Cellen skal ha riktig posisjon.");
    }
}
