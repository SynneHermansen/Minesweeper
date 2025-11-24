package no.uib.inf101.minesweeper.mine;

public interface MinePlacementStrategy {

	/*
	 * The strategy to place Mines based on number of input and the row and col to not place mines
	 * @return void
	 * 
	 */
	void placeMines(int saferow, int safeCol);


	
}
