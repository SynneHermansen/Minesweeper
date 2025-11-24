package no.uib.inf101.minesweeper.model;

import java.util.Iterator;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridDimension;
/**
 * Represents the game board in the Minesweeper game. 
 * The board consists of a 2D array of cells, each of which can be revealed, flagged, or contain a mine.
 */
public class Board implements GridDimension, Iterable<Cell> {
    private Cell[][] cells;

    /**
     * Constructor for initializing the game board with a specified number of rows and columns.
     * Each cell is initialized with a position (row, column) and a default symbol of '-'.
     * 
     * @param rows The number of rows in the board.
     * @param cols The number of columns in the board.
     */
    public Board(int rows, int cols) {
        cells = new Cell[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                cells[row][col] = new Cell(new CellPosition(row, col), '-'); // Initier celler med posisjon og symbol
            }
        }
    }
    /**
     * Returns the cell at the specified row and column.
     * 
     * @param row The row number of the cell.
     * @param col The column number of the cell.
     * @return The Cell object at the specified position.
     */
    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    @Override
    public int rows() {
        return cells.length;
    }

    @Override
    public int cols() {
        return cells[0].length;
    }

    @Override
    public Iterator<Cell> iterator() {
        return new Iterator<Cell>() {
            private int currentRow = 0;
            private int currentCol = 0;

            @Override
            public boolean hasNext() {
                return currentRow < cells.length && currentCol < cells[0].length;
            }

            @Override
            public Cell next() {
                Cell cell = cells[currentRow][currentCol];
                currentCol++;
                if (currentCol >= cells[0].length) {
                    currentCol = 0;
                    currentRow++;
                }
                return cell;
            }
        };
    }
}
