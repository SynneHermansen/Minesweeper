package no.uib.inf101.minesweeper.mine;
import java.util.Random;
import no.uib.inf101.minesweeper.model.*;

public class RandomMinePlacement implements MinePlacementStrategy{
	private Board board;
	private int numberOfMines;
	
    public RandomMinePlacement(Board board, int numberOfMines) {
        this.board = board;
        this.numberOfMines=numberOfMines;
    }

    public void placeMines(int safeRow, int safeCol) {
    	Random random = new Random();
        int placedMines = 0;

        while (placedMines < numberOfMines) {
            int row = random.nextInt(board.rows());
            int col = random.nextInt(board.cols());

           
            if ((row != safeRow || col != safeCol) && !board.getCell(row, col).hasMine()) {
                board.getCell(row, col).setMine(true);
                placedMines++;
            }
        }
    }
    

}

