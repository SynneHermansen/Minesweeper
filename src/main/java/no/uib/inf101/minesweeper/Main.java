package no.uib.inf101.minesweeper;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import no.uib.inf101.minesweeper.model.MinesweeperModel;
import no.uib.inf101.minesweeper.view.MinesweeperView;
import no.uib.inf101.minesweeper.controller.GameController;


public class Main {
	
	public static final String WINDOW_TITLE = "INF101 Minesweeper";

	public static void main(String[] args) {
		MinesweeperModel model= new MinesweeperModel();
		MinesweeperView view = new MinesweeperView(model);
		GameController controller= new GameController(model,view);
		
		view.setUpGrid(controller);
        JFrame frame = new JFrame("Minesweeper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(view);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true); 
       
		
	}

}
