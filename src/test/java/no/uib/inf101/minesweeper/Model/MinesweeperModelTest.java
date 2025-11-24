package no.uib.inf101.minesweeper.Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import no.uib.inf101.minesweeper.mine.RandomMinePlacement;
import no.uib.inf101.minesweeper.mine.MinePlacementStrategy;
import no.uib.inf101.minesweeper.model.Board;
import no.uib.inf101.minesweeper.model.Cell;
import no.uib.inf101.minesweeper.model.GameState;
import no.uib.inf101.minesweeper.model.MinesweeperModel;



class MinesweeperModelTest {

    private MinesweeperModel model;

    @BeforeEach
    void setUp() {
        Board board = new Board(15, 10);
        MinePlacementStrategy placement = new RandomMinePlacement(board,40);
        model = new MinesweeperModel(board, placement);
    }

    @Test
    void testBoardInitialization() {
        assertNotNull(model.getBoard(), "Brettet skal ikke være null");
    }

    @Test
    void testGameStateInitialization() {
        assertEquals(GameState.ACTIVE_GAME, model.getGameState(), "Spillet skal starte med aktiv status");
    }



    @Test
    void testNeighbourMineCalculation() {
        for (Cell cell : model.getBoard()) {
            if (!cell.hasMine()) {
                int count = cell.getMineCount();
                assertTrue(count >= 0 && count <= 8, "Antall nabominer må være mellom 0 og 8");
            }
        }
    }

    @Test
    void testSetGameOver() {
        model.setGameOver();
        assertEquals(GameState.GAME_OVER, model.getGameState(), "Spillet skal settes til GAME_OVER");
    }
}
