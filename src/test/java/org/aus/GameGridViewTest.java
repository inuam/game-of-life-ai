package org.aus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for GameGridView class.
 * Tests rendering and user interaction handling.
 */
public class GameGridViewTest {
    private GameGridView gridView;
    private GameGrid grid;
    private static final int CELL_SIZE = 10;

    @BeforeEach
    public void setUp() {
        grid = new GameGrid(5, 5);
        gridView = new GameGridView(grid, CELL_SIZE);
    }

    @Test
    public void testGridViewInitializesWithCorrectDimensions() {
        assertEquals(grid.getCols() * CELL_SIZE, gridView.getWidth(), 0.1, "Width should match grid columns * cell size");
        assertEquals(grid.getRows() * CELL_SIZE, gridView.getHeight(), 0.1, "Height should match grid rows * cell size");
    }

    @Test
    public void testGridViewCanSetCellToggleCallback() {
        final boolean[] called = {false};
        gridView.setOnCellToggle(() -> called[0] = true);
        assertFalse(called[0], "Callback should not be called when setting");
    }

    @Test
    public void testDrawMethodDoesNotThrowException() {
        assertDoesNotThrow(() -> gridView.draw(), "draw() should not throw exception");
    }

    @Test
    public void testDrawWithEmptyGrid() {
        grid.clear();
        assertDoesNotThrow(() -> gridView.draw(), "draw() should handle empty grid");
    }

    @Test
    public void testDrawWithAllCellsAlive() {
        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getCols(); j++) {
                grid.setCell(i, j, true);
            }
        }
        assertDoesNotThrow(() -> gridView.draw(), "draw() should handle all cells alive");
    }

    @Test
    public void testGridViewUsesCorrectCellSize() {
        GameGridView largeView = new GameGridView(grid, 20);
        assertEquals(grid.getCols() * 20, largeView.getWidth(), 0.1, "Large cell size should scale properly");
    }

    @Test
    public void testGridViewCanBeRedrawnMultipleTimes() {
        assertDoesNotThrow(() -> {
            gridView.draw();
            gridView.draw();
            gridView.draw();
        }, "draw() should be callable multiple times");
    }

    @Test
    public void testGridViewRespondsToGridChanges() {
        grid.setCell(0, 0, true);
        assertDoesNotThrow(() -> gridView.draw(), "draw() should handle grid changes");
        assertTrue(grid.getCell(0, 0), "Grid should reflect changes");
    }

    @Test
    public void testGridViewWithDifferentSizes() {
        GameGrid largeGrid = new GameGrid(100, 100);
        GameGridView largeView = new GameGridView(largeGrid, 5);
        assertEquals(500.0, largeView.getWidth(), 0.1, "Large grid should calculate width correctly");
        assertEquals(500.0, largeView.getHeight(), 0.1, "Large grid should calculate height correctly");
    }

    @Test
    public void testGridViewWithSmallCellSize() {
        GameGridView smallView = new GameGridView(grid, 1);
        assertEquals(5.0, smallView.getWidth(), 0.1, "Small cell size should work");
    }
}
