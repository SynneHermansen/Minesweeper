package no.uib.inf101.minesweeper.controller;

import no.uib.inf101.minesweeper.controller.GameController;
import no.uib.inf101.minesweeper.model.MinesweeperModel;
import no.uib.inf101.minesweeper.model.Cell;
import no.uib.inf101.minesweeper.view.MinesweeperView;
import no.uib.inf101.minesweeper.model.GameState;
import no.uib.inf101.grid.CellPosition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GameControllerTest {

    private MinesweeperModel model;
    private MinesweeperView view;
    private GameController controller;

    @BeforeEach
    public void setUp() {
    	
        model = new MinesweeperModel();
        view = new MinesweeperView(model); 
        controller = new GameController(model, view);
    }

    @Test
    public void testCheckGameStateWhenActive() {
        model.setGameState(GameState.ACTIVE_GAME);
        
        Cell cell = model.getBoard().getCell(0, 0); 
        controller.cellClicked(cell);
        
        assertEquals(GameState.ACTIVE_GAME, model.getGameState());
    }

    @Test
    public void testCheckGameStateWhenPaused() {
        model.setGameState(GameState.PAUSE);
        Cell cell = model.getBoard().getCell(0, 0); 
        
        controller.cellClicked(cell);
        assertEquals(GameState.PAUSE, model.getGameState());
    }

    @Test
    public void testFirstClick() {
        model.setGameState(GameState.ACTIVE_GAME);
        model.setFirstClick(true);


        Cell cell = model.getBoard().getCell(0, 0);
        controller.cellClicked(cell);

   
        assertFalse(model.isFirstClick()); 
    }


    @Test
    public void testGameOverOnMineClick() {
    	System.setProperty("java.awt.headless", "true");
        model.setGameState(GameState.ACTIVE_GAME);

        Cell cell = model.getBoard().getCell(0, 0);
        cell.setMine(true);
        
        controller.cellClicked(cell);
        
        Cell cell1 = model.getBoard().getCell(1, 0);
        cell1.setMine(true);
        controller.cellClicked(cell1);
        
        assertEquals(GameState.GAME_OVER, model.getGameState());
    }

}
