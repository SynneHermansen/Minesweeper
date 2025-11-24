package no.uib.inf101.minesweeper.view;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import java.awt.*;
import java.net.URL;

import no.uib.inf101.minesweeper.controller.CellButton;
import no.uib.inf101.minesweeper.controller.GameController;
import no.uib.inf101.minesweeper.model.Cell;
import no.uib.inf101.minesweeper.model.GameState;

/**
 * The MinesweeperView class represents the visual part of the Minesweeper game,
 * including the main panel, the grid of cells, the timer, and game status indicators.
 */
public class MinesweeperView extends JPanel {
    
    private final ViewableMinesweeperModel model;
    private final Color backgroundColor = Color.LIGHT_GRAY;

    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel gridPanel;
    private JPanel pausePanel;

    private JLabel timerLabel;
    private JLabel mineLabel;
    private JButton flagButton;
    private JButton pauseButton;

    private Timer timer;
    private int secondsElapsed;
    private int rows;
    private int cols;
    private int numberOfMines;
   

    /**
     * Constructor for initializing the MinesweeperView.
     * 
     * @param model The model representing the game state.
     */
    public MinesweeperView(ViewableMinesweeperModel model) {
        this.model = model;
        this.rows = model.getDimension().rows();
        this.cols = model.getDimension().cols();
        setupMainPanel(); 
        
    }

    /**
     * Sets up the main panel containing the top panel and the grid panel.
     */
    public void setupMainPanel() {
        setLayout(new BorderLayout());
        mainPanel = new JPanel(new BorderLayout());
        gridPanel = new JPanel(new GridLayout(rows, cols));
        topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
        topPanel.setBackground(backgroundColor);
        topPanel.setLayout(new GridBagLayout());
        
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(gridPanel, BorderLayout.CENTER);
        this.add(mainPanel, BorderLayout.CENTER);
        
        drawPauseButton();
        drawNumberOfMines();
        drawSmiley();
        setupFlagButton();
        setupTimer();
        timer.start();
    }

    /**
     * Sets up the grid of CellButtons.
     * 
     * @param controller The GameController to handle button actions.
     */
    public void setUpGrid(GameController controller) {
        gridPanel.removeAll(); 
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Cell cell = model.getBoard().getCell(row, col);
                CellButton button = new CellButton(cell, controller);
                button.setFocusPainted(false);
             
                button.setOpaque(true);
                button.setBackground(backgroundColor);
                gridPanel.add(button);
            }
        }
        gridPanel.revalidate();
        gridPanel.repaint();
    }

    /**
     * Updates the visual state of the grid based on the model.
     */
    public void updateGrid() {
    	for (Component comp : gridPanel.getComponents()) {
            if (comp instanceof CellButton) {
                CellButton button = (CellButton) comp;
                Cell cell = button.getCell();
                updateCellButton(button, cell);
            }
        }
        gridPanel.revalidate();
        gridPanel.repaint();
    }
        
    private void updateCellButton(CellButton button, Cell cell) {
    	if (cell.isFlagged()) {
    		setFlagIcon(button);

    	} else if (cell.isRevealed()) {
            revealCell(button, cell);
            
        } else {
            resetButton(button);
            }
        
        }
        
     private void setFlagIcon(CellButton button) {
            URL url = getClass().getResource("/flag.png");
            if (url != null) {
                ImageIcon icon = new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
                button.setIcon(icon);
            }
            button.setText("");
            button.setEnabled(true);
            button.setContentAreaFilled(false);
            button.setOpaque(true);
            button.setBackground(backgroundColor);
        }
     
     private void revealCell(CellButton button, Cell cell) {
         button.setEnabled(false);
         button.setContentAreaFilled(true);
         if (cell.hasMine()) {
             button.setText("💣");
             UIManager.put("Button.disabledText", new ColorUIResource(Color.GRAY));
         } else {
             button.setText(String.valueOf(cell.getSymbol()));
             button.setFont(new Font("Arial", Font.BOLD, 10));
             UIManager.put("Button.disabledText", new ColorUIResource(Color.RED));
         }
         button.setBackground(backgroundColor);
     }
     
     private void resetButton(CellButton button) {
         button.setIcon(null);
         button.setText(" ");
         button.setEnabled(true);
         button.setBackground(backgroundColor);
     }

    
    private void drawPauseButton() {
    	   pauseButton = createIconButton("/tannhjul.png", 60);
           pauseButton.addActionListener(e -> {
               if (model.getGameState() == GameState.ACTIVE_GAME) {
                   model.pauseGame();
                   drawPauseBoard();
                   timer.stop();
               }
           });
           addComponentToTopPanel(pauseButton, 0);
       }
    	

    /** 
     * Draws the label for number of mines 
     */
    public void drawNumberOfMines() {
    	  mineLabel = createDigitalLabel();
          numberOfMines = model.getNumberOfMines();
          mineLabel.setText(String.valueOf(numberOfMines));
          addComponentToTopPanel(mineLabel, 1);
      }
    
    /**
     * Updates the mine counter based on model state.
     */
    public void updateMineCounterLabel() {
        mineLabel.setText(String.valueOf(model.getNumberOfMines()));
    }
    
    /** 
     * draws the smiley button
     */
    public void drawSmiley() {
    	
    	if(model.getGameState()==GameState.GAME_OVER) {
    	for (Component comp : topPanel.getComponents()) {
    	    if (comp instanceof JButton && "smiley".equals(comp.getName())) {
    	        topPanel.remove(comp);
    	        
    	        break;
    	    }
    	   String path = model.getGameState() == GameState.ACTIVE_GAME ? "/smiley.png" : "/Sad-face.png";
    	   JButton smileyButton = createIconButton(path, 60);
    	   smileyButton.setName("smiley"); 

           addComponentToTopPanel(smileyButton, 2);
           topPanel.revalidate();
           topPanel.repaint();
    	}
    	}
    	
    	else {
    	
    	   String path = model.getGameState() == GameState.ACTIVE_GAME ? "/smiley.png" : "/Sad-face.png";
    	   JButton smileyButton = createIconButton(path, 60);
    	   smileyButton.setName("smiley"); 

           addComponentToTopPanel(smileyButton, 2);
           topPanel.revalidate();
           topPanel.repaint();
    	}
       }
    
      
    /**
     * Draws the flag button for toggling flag mode
     */
    private void setupFlagButton() {
            flagButton = createDigitalButton("🚩");
            flagButton.addActionListener(e -> {
                model.toggleFlagMode();
                flagButton.setBackground(model.isFlagMode() ? Color.DARK_GRAY : Color.BLACK);
            });
            addComponentToTopPanel(flagButton, 3);
        }
    
     /** 
      * Sets up the timer label
      */
    public void setupTimer() {
        secondsElapsed = 0;
        timerLabel = createDigitalLabel();
        addComponentToTopPanel(timerLabel, 4);
        timer = new Timer(1000, e -> {
            secondsElapsed++;
            timerLabel.setText(String.format( "%03d", secondsElapsed)+ " "); 
        });
    }

    /** 
     * Handles the game logic when the game is lost
     */
    public void gameOver() {
    	//reveal the board
    	   for (int i = 0; i < model.getBoard().rows(); i++) {
    	        for (int j = 0; j < model.getBoard().cols(); j++) {
    	            Cell cell = model.getBoard().getCell(i, j);
    	            cell.reveal();
    	        }
    	    }
    	    updateGrid();
    	
    	//Stop the timer and stop the use of the buttons
        for (int i = 0; i < gridPanel.getComponentCount(); i++) {
            CellButton button = (CellButton) gridPanel.getComponent(i);
            button.setEnabled(false);
            if (timer != null) {
                timer.stop();
            }
        }
        //change the smiley to the sad version
       
        drawSmiley();
        repaint(); 
    }
    /**
     * sets up the pause panel
     */
    public void drawPauseBoard() {
    	// temporarily stop the use of buttons
    	for (Component comp : gridPanel.getComponents()) {
    	    if (comp instanceof CellButton) {
    	        CellButton button = (CellButton) comp;
    	        Cell cell = button.getCell();


    	        if (!cell.isRevealed()) {
    	            button.setEnabled(false);
    	        }
    	    }
    	}
    	pausePanel = new JPanel();
    	pausePanel.setLayout(new BorderLayout());
    	pausePanel.setPreferredSize(new Dimension(400, 500));
    	pausePanel.setBackground( new Color(255, 182, 193)); 

  
    	JLabel pauseLabel = new JLabel("PAUSE<3", JLabel.CENTER);
    	pauseLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
    	pausePanel.add(pauseLabel, BorderLayout.CENTER);

        JButton resumeButton = new JButton("Resume Game");
        resumeButton.setPreferredSize(new Dimension(200, 80));
        resumeButton.setFont(new Font("SansSerif", Font.BOLD, 24));
        resumeButton.addActionListener(e -> {
        	if (pausePanel != null) {
                pausePanel.setVisible(false);
                
            }
            remove(pausePanel);
            model.resumeGame();
            timer.start();
            revalidate();
            repaint();
            
            
            for (Component comp : gridPanel.getComponents()) {
                if (comp instanceof CellButton) {
                    CellButton button = (CellButton) comp;
                    Cell cell = button.getCell();

                   
                    if (!cell.isRevealed()) {
                        button.setEnabled(true);
                    }
                }
            }
           
        });
        pausePanel.add(resumeButton, BorderLayout.SOUTH);
        mainPanel.add(pausePanel, BorderLayout.WEST);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
    
    /**
     * Meldingen som vises når spilleren har vunnet spillet.
     */
    public void showWinMessage() {
        JOptionPane.showMessageDialog(null, "Du har vunnet!", "Spillet er vunnet" , JOptionPane.INFORMATION_MESSAGE);
        
    }
    /** Helper: Creates an icon button */
    private JButton createIconButton(String path, int size) {
        URL url = getClass().getResource(path);
        ImageIcon icon = new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));
        JButton button = new JButton(icon);
        button.setPreferredSize(new Dimension(size, size));
        button.setOpaque(true);
        button.setBackground(backgroundColor);
        button.setFocusPainted(false);
        return button;
    }
    
    /** Helper: Creates a styled button for flags */
    private JButton createDigitalButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(60, 60));
        button.setForeground(Color.RED);
        button.setOpaque(true);
        button.setBackground(Color.BLACK);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setFont(new Font("Digital-7", Font.BOLD, 30));
        return button;
    }
    
    
    /** Helper: Creates a digital-style label */
    private JLabel createDigitalLabel() {
        JLabel label = new JLabel("000");
        label.setPreferredSize(new Dimension(60, 60));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(Color.RED);
        label.setOpaque(true);
        label.setBackground(Color.BLACK);
        label.setFont(new Font("Digital-7", Font.BOLD, 30));
        return label;
    }
    
    /** Helper: Adds a component to topPanel using GridBagConstraints */
    private void addComponentToTopPanel(JComponent comp, int gridx) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 5, 0, 5);
        topPanel.add(comp, gbc);
    }
}
    
    	

