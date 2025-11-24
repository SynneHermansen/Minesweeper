package no.uib.inf101.minesweeper.model;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.minesweeper.mine.MinePlacementStrategy;
import no.uib.inf101.minesweeper.mine.RandomMinePlacement;
import no.uib.inf101.minesweeper.view.ViewableMinesweeperModel;


public class MinesweeperModel implements ViewableMinesweeperModel {
	
	private Board board;
	private MinePlacementStrategy placement;
	private GameState state;
	private int numberOfMines=50;
    private boolean flagMode = false;
    private boolean firstClick = true;
    private int revealedCells = 0;
    private boolean minesPlaced = false;

    /**
     * Initializes the board and the mineplacement
     */
	public MinesweeperModel() {
		board=new Board(15,10);
		placement=new RandomMinePlacement(board,numberOfMines);
		this.state=GameState.ACTIVE_GAME;
        
    }
		
	/**
	 * Sets the field variables board and placement
	 * @param board
	 * @param placement
	 */
	public MinesweeperModel(Board board, MinePlacementStrategy placement) {
		this.board=board;
		this.placement=placement;
		this.state=GameState.ACTIVE_GAME;
				
				
	}
	/**
	 * The method that calculates number of mines that are next to the cell
	 * 
	 */
	public void NeighbourMines() {
		
		for(int i=0; i<board.rows(); i++) {
			for(int j=0; j<board.cols(); j++) {
				Cell cell = board.getCell(i, j);
				if(!cell.hasMine()) {
				int mineCount=0;
				for(int x=-1; x<=1; x++) {
					for(int y=-1; y<=1; y++) {
						int ni=i+x;
						int nj=j+y;
						if(ni>=0 && ni< board.rows()) {
							if(nj>=0 && nj<board.cols()) {
								Cell cell2=board.getCell(ni, nj);
								if(cell2.hasMine()) {
									mineCount++;
								}
							}
						}
					}
			
				}
				cell.setMineCount(mineCount);
			}
		}
	}
		
	}
	
	@Override
    public void toggleFlagMode() {
        flagMode = !flagMode;
       
	}
    public boolean isFlagMode() {
        return flagMode;
    }



	public GridDimension getDimension() {
		return board;
	}


	@Override
	public Iterable<Cell> getTilesOnBoard() {
		return board;
	}
	

	@Override
	public Board getBoard() {
		return board;
	}
	
	public void setGameOver() {
		state = GameState.GAME_OVER;
	}


	@Override
	public GameState getGameState() {
		return state;
	}
	

	@Override
	public void setGameState(GameState state) {
		this.state= state;
	}
	@Override
	public int getNumberOfMines() {
		return numberOfMines;
	}
	@Override
	public void updateNumberOfMines(int i) {
		numberOfMines=i;
	}
	
	@Override
    public void pauseGame() {
        if (state == GameState.ACTIVE_GAME) {
            state = GameState.PAUSE;
        }
    }
    
    @Override
    public void resumeGame() {
        if (state == GameState.PAUSE) {
            state = GameState.ACTIVE_GAME;
        }
    }
    
    @Override
    public boolean isFirstClick() {
        return firstClick;
    }
    
    @Override
    public void setFirstClick(boolean firstClick) {
    	this.firstClick = firstClick;
}
    /**
     * Reveals the safe connecting cells to the row and col pressed by the player.
     * It reveals based on the number of mines around
     * @param row Rownumber of the cell that is checked
     * @param col Columnnumber of the cell that is checked
     * @param depth Regulates how many cells that are revealed
     */
    public void revealSafeZone(int row, int col,int depth) {
    	if (revealedCells > 10) return;
    	if (depth > 1) return;
    	
    	if (row < 0 || row >= board.rows() || col < 0 || col >= board.cols()) {
    	    return; 
    	}
    	
    	Cell cell=board.getCell(row, col);
    	if (cell.isRevealed() || cell.isFlagged() ){
            return; 
        }
    	if (cell.hasMine()) {
    	    return;
    	}
    	 cell.reveal();
    	 revealedCells++;
    	
    	 if (cell.getMineCount() <= 4) {
    	        for (int r = -1; r <= 1; r++) {
    	            for (int c = -1; c <= 1; c++) {
    	                if (r != 0 || c != 0) {
    	                    revealSafeZone(row + r, col + c, depth + 1);
    	               }
    	            }
    	           }
    	        }
    	 
    	
    }
    /**
     * Places mines on the board on the first click and makes sure that there are no mines where the player
     * presses the first time
     * @param safeRow Rownumber of the first cell the player presses
     * @param safeCol Columnnumber of the first cell the player presses
     */
    public void placeMinesFirstClick(int safeRow, int safeCol) {
    	if (!minesPlaced) {
            placement.placeMines(safeRow, safeCol); 
            minesPlaced = true;
       
    		}
   
    	}
    public boolean isGameWon() {
        int revealedCells = 0;
        int safeCells = 0; 

       
        for (int i = 0; i < board.rows(); i++) {
            for (int j = 0; j < board.cols(); j++) {
                Cell cell = board.getCell(i, j);
                if (cell.hasMine()) {
                    continue; 
                }
                safeCells++; 
                if (cell.isRevealed()) {
                    revealedCells++; 
                }
            }
        }

      
        return revealedCells == safeCells;
    }

    }
