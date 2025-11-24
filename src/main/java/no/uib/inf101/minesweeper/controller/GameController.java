package no.uib.inf101.minesweeper.controller;

import no.uib.inf101.minesweeper.model.MinesweeperModel;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.minesweeper.model.Cell;
import no.uib.inf101.minesweeper.model.GameState;
import no.uib.inf101.minesweeper.view.MinesweeperView;

public class GameController {
	
    private MinesweeperModel model;
    private MinesweeperView view;

    /**
     * Check the current game state and handle accordingly
     * @param model
     */

    public GameController(MinesweeperModel model, MinesweeperView view) {
        this.model = model;
        this.view = view;
        checkGameState();
        
        
    }
    /**
     * Check the current game state and handle accordingly
     */
    private void checkGameState() {
    	 GameState state = model.getGameState();

         if (state == GameState.PAUSE) {
             view.drawPauseBoard();
             
         } 
         else if (state == GameState.GAME_OVER) {
        	 model.setGameOver();
        	 view.gameOver();
             return;
         } 
		
	}

    /**
     * Handles cell click actions
     * @param cell
     */
	public void cellClicked(Cell cell) {
		if (model.isFirstClick()) {
			CellPosition pos=cell.getPos();
			int row=pos.row();
			int col=pos.col();
			
			model.placeMinesFirstClick(row, col); 
	        model.setFirstClick(false);
	        
	        model.NeighbourMines();
	        model.revealSafeZone(row, col,0); 
	        view.updateGrid();
		   
		}
		
		else {
           GameState state = model.getGameState();

           if (state == GameState.PAUSE) {
        	   view.drawPauseBoard();
                
            } 
           
            else if (state == GameState.GAME_OVER) {
            	view.gameOver();
                return;
            } 
            
            
           	if (cell.isRevealed()) {
           		return;
           	}
        
           	if (model.isFlagMode()) {
           		if (cell.isFlagged()) {
           			cell.setFlagged(false); 
                    int count=model.getNumberOfMines();
                    model.updateNumberOfMines(count+1);
                    view.updateMineCounterLabel();
           		} 
           		else {
           			if (!cell.isFlagged() && model.getNumberOfMines() == 0) {
                    		    return;
                    	                    		}
                         cell.setFlagged(true);
                         int count=model.getNumberOfMines();
                         model.updateNumberOfMines(count-1);
                         view.updateMineCounterLabel();
                     }
                     view.updateGrid();
                     return;
           	}
            	else {
            		if (cell.hasMine()) {
            			model.setGameOver();
            			cell.reveal();
            			view.gameOver();
        
            		}else {
            			cell.reveal();
            		}
            		if(model.getGameState()==GameState.ACTIVE_GAME) {
            		 if (model.isGameWon()) {
            	           model.setGameState(GameState.WIN);  // Sett spillstatus til 'Vunnet'
            	           view.showWinMessage(); // Vis en melding i GUI-en
            	        }
            	} }
        view.updateGrid();
           
           
    }
	}
}