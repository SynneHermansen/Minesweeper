package no.uib.inf101.minesweeper.model;

import no.uib.inf101.grid.CellPosition;

/**
 * Represents a single cell on the Minesweeper board.
 * A cell can have a mine, can be revealed, and can be flagged.
 * It also stores the number of mines in its neighboring cells and its symbol for display.
 */
public class Cell {
    
    private boolean hasMine;      
    private boolean isRevealed;     
    private boolean isFlagged;          
    
    private int mineCount;
    private Character symbol;
    private CellPosition position;

    /**
     * Default constructor initializing a cell with no mine, not revealed, and not flagged.
     */
    public Cell() {
        this.hasMine = false;
        this.isRevealed = false;
        this.isFlagged = false;
    }

    /**
     * Constructor initializing the cell with a specific position and symbol.
     * 
     * @param pos The position of the cell on the board.
     * @param symbol The symbol to represent the cell.
     */
    public Cell(CellPosition pos, char symbol) {
        this.position = pos;
        this.symbol = symbol;
    }

    /**
     * Returns whether the cell contains a mine.
     * 
     * @return true if the cell has a mine, false otherwise.
     */
    public boolean hasMine() {
        return hasMine;
    }

    /**
     * Sets whether the cell contains a mine.
     * 
     * @param hasMine true if the cell should contain a mine, false otherwise.
     */
    public void setMine(boolean hasMine) {
        this.hasMine = hasMine;
    }

    /**
     * Returns whether the cell has been revealed.
     * 
     * @return true if the cell is revealed, false otherwise.
     */
    public boolean isRevealed() {
        return isRevealed;
    }

    /**
     * Reveals the cell and updates its symbol based on its state.
     * If the cell contains a mine, the symbol will be '*'.
     * If the cell does not contain a mine, the symbol will represent the mine count
     * of neighboring cells, or a space if there are no neighboring mines.
     */
    public void reveal() {
        if (isRevealed) {
            return;
        }
        this.isRevealed = true;
        
        if (this.hasMine()) {
            symbol = '*';
        } else if (getMineCount() >= 0) {
            symbol = Character.forDigit(getMineCount(), 10);
        } else {
            symbol = ' ';
        }
    }

    /**
     * Returns whether the cell is flagged.
     * 
     * @return true if the cell is flagged, false otherwise.
     */
    public boolean isFlagged() {
        return isFlagged;
    }

    /**
     * Sets whether the cell is flagged.
     * 
     * @param isFlagged true if the cell should be flagged, false otherwise.
     */
    public void setFlagged(boolean isFlagged) {
        this.isFlagged = isFlagged;
    }

    /**
     * Sets the mine count for the cell, indicating the number of mines in its neighboring cells.
     * 
     * @param count The number of mines in neighboring cells.
     */
    public void setMineCount(int count) {
        this.mineCount = count;
    }

    /**
     * Returns the number of neighboring mines for the cell.
     * 
     * @return The number of neighboring mines.
     */
    public int getMineCount() {
        return mineCount;
    }

    /**
     * Returns the symbol representing the cell, used for display purposes.
     * 
     * @return The symbol of the cell.
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * Returns the position of the cell on the board.
     * 
     * @return The position of the cell as a CellPosition object.
     */
    public CellPosition getPos() {
        return position;
    }
}
