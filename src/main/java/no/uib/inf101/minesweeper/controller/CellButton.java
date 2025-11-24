package no.uib.inf101.minesweeper.controller;

import javax.swing.JButton;
import java.awt.Dimension;
import no.uib.inf101.minesweeper.model.Cell;

public class CellButton extends JButton {
    private Cell cell;
    private GameController controller;

    public CellButton(Cell cell, GameController controller) {
        this.cell = cell;
        this.controller = controller;
        this.setPreferredSize(new Dimension(40, 40));
        this.addActionListener(e -> handleCellClick());
    }

    private void handleCellClick() {
        if (controller != null) {
            controller.cellClicked(cell); 
        }
    }

    public Cell getCell() {
        return cell;
    }
}
