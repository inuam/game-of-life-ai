package org.aus;

/**
 * Represents the grid state for Conway's Game of Life simulation.
 * Manages cell states and implements the rules for generation advancement.
 */
public class GameGrid {
    private boolean[][] grid;
    private final int rows;
    private final int cols;

    /**
     * Constructs a new GameGrid with specified dimensions.
     *
     * @param rows number of rows in the grid
     * @param cols number of columns in the grid
     */
    public GameGrid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new boolean[rows][cols];
    }

    /**
     * Toggles the state of a cell between alive and dead.
     *
     * @param row the row index of the cell
     * @param col the column index of the cell
     */
    public void toggle(int row, int col) {
        if (isValid(row, col)) {
            grid[row][col] = !grid[row][col];
        }
    }

    /**
     * Gets the state of a cell.
     *
     * @param row the row index
     * @param col the column index
     * @return true if the cell is alive, false if dead or out of bounds
     */
    public boolean getCell(int row, int col) {
        if (isValid(row, col)) {
            return grid[row][col];
        }
        return false;
    }

    /**
     * Sets the state of a cell.
     *
     * @param row the row index
     * @param col the column index
     * @param alive true to set the cell alive, false to set it dead
     */
    public void setCell(int row, int col, boolean alive) {
        if (isValid(row, col)) {
            grid[row][col] = alive;
        }
    }

    /**
     * Clears all cells in the grid, setting them all to dead.
     */
    public void clear() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = false;
            }
        }
    }

    /**
     * Gets the grid state as a 2D boolean array.
     *
     * @return the grid state
     */
    public boolean[][] getGrid() {
        return grid;
    }

    /**
     * Gets the number of rows in the grid.
     *
     * @return the number of rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * Gets the number of columns in the grid.
     *
     * @return the number of columns
     */
    public int getCols() {
        return cols;
    }

    /**
     * Validates if coordinates are within grid bounds.
     *
     * @param row the row index
     * @param col the column index
     * @return true if coordinates are valid, false otherwise
     */
    private boolean isValid(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    /**
     * Counts the number of live neighbors for a cell.
     *
     * @param row the row index
     * @param col the column index
     * @return the count of live neighbors (0-8)
     */
    public int countLiveNeighbors(int row, int col) {
        int count = 0;
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) continue;
                int newRow = row + dr;
                int newCol = col + dc;
                if (isValid(newRow, newCol) && grid[newRow][newCol]) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Advances the grid to the next generation according to Conway's Game of Life rules.
     * Rules:
     * - A live cell with fewer than 2 live neighbors dies (underpopulation)
     * - A live cell with 2-3 live neighbors survives
     * - A live cell with more than 3 live neighbors dies (overpopulation)
     * - A dead cell with exactly 3 live neighbors becomes alive (reproduction)
     */
    public void nextGeneration() {
        boolean[][] newGrid = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int liveNeighbors = countLiveNeighbors(i, j);
                if (grid[i][j]) {
                    newGrid[i][j] = liveNeighbors == 2 || liveNeighbors == 3;
                } else {
                    newGrid[i][j] = liveNeighbors == 3;
                }
            }
        }
        this.grid = newGrid;
    }
}
