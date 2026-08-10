package org.gol;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * Canvas-based view for rendering the game grid and handling user interactions.
 * Displays alive cells in blue on a dark navy background with yellow grid lines.
 */
public class GameGridView extends Canvas {
    private final GameGrid grid;
    private final int cellSize;
    private Runnable onCellToggle;

    /**
     * Constructs a GameGridView for displaying the game grid.
     *
     * @param grid the GameGrid to display
     * @param cellSize the size of each cell in pixels
     */
    public GameGridView(GameGrid grid, int cellSize) {
        this.grid = grid;
        this.cellSize = cellSize;
        this.onCellToggle = () -> {};

        setWidth(grid.getCols() * cellSize);
        setHeight(grid.getRows() * cellSize);

        setOnMouseClicked(this::handleMouseClick);
    }

    /**
     * Sets the callback to be invoked when a cell is toggled.
     *
     * @param callback the callback function
     */
    public void setOnCellToggle(Runnable callback) {
        this.onCellToggle = callback;
    }

    /**
     * Handles mouse click events to toggle cells.
     *
     * @param event the mouse event
     */
    private void handleMouseClick(MouseEvent event) {
        int col = (int) (event.getX() / cellSize);
        int row = (int) (event.getY() / cellSize);
        grid.toggle(row, col);
        onCellToggle.run();
        draw();
    }

    /**
     * Redraws the grid on the canvas with current cell states.
     */
    public void draw() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.setFill(Color.web("#032147"));
        gc.fillRect(0, 0, getWidth(), getHeight());

        Color aliveColor = Color.web("#209dd7");
        Color gridColor = Color.web("#ecad0a");

        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getCols(); j++) {
                int x = j * cellSize;
                int y = i * cellSize;

                if (grid.getCell(i, j)) {
                    gc.setFill(aliveColor);
                    gc.fillRect(x, y, cellSize, cellSize);
                }

                gc.setStroke(gridColor);
                gc.setLineWidth(0.5);
                gc.strokeRect(x, y, cellSize, cellSize);
            }
        }
    }
}
