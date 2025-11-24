package no.uib.inf101.minesweeper.view;

import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.minesweeper.model.Board;
import no.uib.inf101.minesweeper.model.Cell;
import no.uib.inf101.minesweeper.model.GameState;

/**
 * Interface representing the part of the Minesweeper model that the view can access.
 */
public interface ViewableMinesweeperModel {

    /**
     * Get the dimensions of the Minesweeper board (rows and columns).
     *
     * @return an object representing the grid's dimensions
     */
    GridDimension getDimension();

    /**
     * Get all the tiles (cells) on the board.
     * Each cell includes information such as whether it's revealed, flagged, a mine, or the number of neighboring mines.
     *
     * @return an iterable of Cell objects representing the current board state
     */
    Iterable<Cell> getTilesOnBoard();
    
    /**
     * Get the current state of the game.
     * Possible states include ACTIVE_GAME, GAME_OVER, PAUSE, and WIN.
     *
     * @return the current GameState
     */
    GameState getGameState();
    
    /**
     * Get the internal board representation.
     *
     * @return the Board object containing all cells
     */
    Board getBoard();
    
    /**
     * Get the total number of mines on the board.
     *
     * @return the number of mines
     */
    int getNumberOfMines();

    /**
     * Toggle the flagging mode.
     * When flag mode is active, clicking on a cell will place or remove a flag instead of revealing the cell.
     */
    void toggleFlagMode();

    /**
     * Check whether flagging mode is currently active.
     *
     * @return true if flagging mode is active, false otherwise
     */
    boolean isFlagMode();
    
    /**
     * Set the game state manually.
     *
     * @param state the new GameState to set
     */
    void setGameState(GameState state);

    /**
     * Pause the game, preventing further moves.
     */
    void pauseGame();

    /**
     * Resume the game if it was paused.
     */
    void resumeGame();

    /**
     * Update the number of remaining unflagged mines.
     *
     * @param i the new number of mines
     */
    void updateNumberOfMines(int i);

    /**
     * Check whether the next click will be the first click in the game.
     * Typically used to ensure the first click never hits a mine.
     *
     * @return true if it is the first click, false otherwise
     */
    boolean isFirstClick();

    /**
     * Set whether the next click is considered the first click.
     *
     * @param firstClick true if setting up for the first click, false otherwise
     */
    void setFirstClick(boolean firstClick);

 
}
