package org.aus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Conway's Game of Life rules.
 * Tests all four fundamental rules of the cellular automaton.
 */
public class GameGridTest {
    private GameGrid grid;

    /**
     * Sets up a 5x5 grid for each test.
     */
    @BeforeEach
    public void setUp() {
        grid = new GameGrid(5, 5);
    }

    /**
     * Tests rule 1: A live cell with fewer than 2 live neighbors dies.
     */
    @Test
    public void testUnderPopulation() {
        grid.setCell(2, 2, true);
        grid.setCell(2, 3, true);
        grid.nextGeneration();
        assertFalse(grid.getCell(2, 2), "Cell with < 2 neighbors should die");
        assertFalse(grid.getCell(2, 3), "Cell with < 2 neighbors should die");
    }

    /**
     * Tests rule 2: A live cell with 2-3 live neighbors survives.
     */
    @Test
    public void testSurvival() {
        grid.setCell(2, 1, true);
        grid.setCell(2, 2, true);
        grid.setCell(2, 3, true);
        grid.nextGeneration();
        assertTrue(grid.getCell(2, 2), "Cell with 2 neighbors should survive");
    }

    /**
     * Tests rule 3: A live cell with more than 3 live neighbors dies.
     */
    @Test
    public void testOverPopulation() {
        grid.setCell(1, 1, true);
        grid.setCell(1, 2, true);
        grid.setCell(2, 1, true);
        grid.setCell(2, 2, true);
        grid.setCell(3, 2, true);
        grid.nextGeneration();
        assertFalse(grid.getCell(2, 2), "Cell with > 3 neighbors should die");
    }

    /**
     * Tests rule 4: A dead cell with exactly 3 live neighbors becomes alive.
     */
    @Test
    public void testReproduction() {
        grid.setCell(2, 1, true);
        grid.setCell(2, 2, true);
        grid.setCell(2, 3, true);
        grid.nextGeneration();
        assertTrue(grid.getCell(1, 2), "Dead cell with 3 neighbors should become alive");
        assertTrue(grid.getCell(3, 2), "Dead cell with 3 neighbors should become alive");
    }

    /**
     * Tests toggling cell state.
     */
    @Test
    public void testToggleCell() {
        assertFalse(grid.getCell(0, 0), "Cell should start dead");
        grid.toggle(0, 0);
        assertTrue(grid.getCell(0, 0), "Cell should be alive after toggle");
        grid.toggle(0, 0);
        assertFalse(grid.getCell(0, 0), "Cell should be dead after second toggle");
    }

    /**
     * Tests clearing all cells.
     */
    @Test
    public void testClear() {
        grid.setCell(0, 0, true);
        grid.setCell(1, 1, true);
        grid.clear();
        assertFalse(grid.getCell(0, 0), "All cells should be dead after clear");
        assertFalse(grid.getCell(1, 1), "All cells should be dead after clear");
    }

    /**
     * Tests live neighbor counting algorithm.
     */
    @Test
    public void testCountLiveNeighbors() {
        grid.setCell(0, 0, true);
        grid.setCell(0, 1, true);
        grid.setCell(1, 1, true);
        assertEquals(3, grid.countLiveNeighbors(1, 0), "Should count 3 live neighbors");
    }

    @Test
    public void testGridDimensions() {
        assertEquals(5, grid.getRows(), "Grid should have 5 rows");
        assertEquals(5, grid.getCols(), "Grid should have 5 columns");
    }

    @Test
    public void testGetCellOutOfBounds() {
        assertFalse(grid.getCell(-1, 0), "Out of bounds cell should be dead");
        assertFalse(grid.getCell(0, -1), "Out of bounds cell should be dead");
        assertFalse(grid.getCell(5, 0), "Out of bounds cell should be dead");
    }

    @Test
    public void testSetCellOutOfBounds() {
        assertDoesNotThrow(() -> grid.setCell(-1, 0, true), "Setting out of bounds cell should not throw");
        assertDoesNotThrow(() -> grid.setCell(5, 0, true), "Setting out of bounds cell should not throw");
    }

    @Test
    public void testGetGrid() {
        boolean[][] rawGrid = grid.getGrid();
        assertNotNull(rawGrid, "getGrid() should return non-null");
        assertEquals(5, rawGrid.length, "Grid array should have correct dimensions");
    }
}
