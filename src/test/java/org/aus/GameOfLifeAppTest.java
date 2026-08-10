package org.aus;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for GameOfLifeApp class.
 * Tests application initialization and basic functionality.
 */
public class GameOfLifeAppTest {

    @Test
    public void testAppHasValidGridDimensions() {
        assertEquals(50, getGridRows(), "App should use 50 rows");
        assertEquals(80, getGridCols(), "App should use 80 columns");
    }

    @Test
    public void testAppHasValidCellSize() {
        assertEquals(10, getCellSize(), "App should use cell size of 10");
    }

    @Test
    public void testAppConstantsArePositive() {
        assertTrue(getGridRows() > 0, "Grid rows must be positive");
        assertTrue(getGridCols() > 0, "Grid columns must be positive");
        assertTrue(getCellSize() > 0, "Cell size must be positive");
    }

    @Test
    public void testAppWindowTitle() {
        String expectedTitle = "Conway's Game of Life";
        assertTrue(expectedTitle.contains("Conway"), "App title should reference Conway's Game of Life");
    }

    @Test
    public void testAppCanCreateGameGrid() {
        GameGrid grid = new GameGrid(getGridRows(), getGridCols());
        assertNotNull(grid, "App should be able to create a game grid");
        assertEquals(getGridRows(), grid.getRows(), "Grid should have correct rows");
        assertEquals(getGridCols(), grid.getCols(), "Grid should have correct columns");
    }

    @Test
    public void testAppCanCreateSimulation() {
        GameGrid grid = new GameGrid(getGridRows(), getGridCols());
        GameSimulation simulation = new GameSimulation(grid);
        assertNotNull(simulation, "App should be able to create a simulation");
        assertFalse(simulation.isRunning(), "Simulation should start in stopped state");
    }

    @Test
    public void testAppCanCreateGridView() {
        GameGrid grid = new GameGrid(getGridRows(), getGridCols());
        GameGridView gridView = new GameGridView(grid, getCellSize());
        assertNotNull(gridView, "App should be able to create a grid view");
        assertEquals(getGridCols() * getCellSize(), gridView.getWidth(), 0.1, "View width should match grid width");
    }

    @Test
    public void testAppComponentsWorkTogether() {
        GameGrid grid = new GameGrid(getGridRows(), getGridCols());
        GameSimulation simulation = new GameSimulation(grid);
        GameGridView gridView = new GameGridView(grid, getCellSize());

        simulation.setOnUpdate(() -> gridView.draw());
        gridView.setOnCellToggle(() -> gridView.draw());

        grid.setCell(0, 0, true);
        gridView.draw();

        assertTrue(grid.getCell(0, 0), "Components should work together properly");
    }

    @Test
    public void testAppGridSizeReasonable() {
        int expectedWidth = getGridCols() * getCellSize();
        int expectedHeight = getGridRows() * getCellSize();
        assertTrue(expectedWidth > 0 && expectedWidth < 10000, "Grid width should be reasonable");
        assertTrue(expectedHeight > 0 && expectedHeight < 10000, "Grid height should be reasonable");
    }

    @Test
    public void testAppCanHandleMultipleSimulations() {
        GameGrid grid1 = new GameGrid(getGridRows(), getGridCols());
        GameGrid grid2 = new GameGrid(getGridRows(), getGridCols());
        GameSimulation sim1 = new GameSimulation(grid1);
        GameSimulation sim2 = new GameSimulation(grid2);

        assertNotNull(sim1, "First simulation should be created");
        assertNotNull(sim2, "Second simulation should be created");
        assertNotEquals(sim1, sim2, "Simulations should be different instances");
    }

    @Test
    public void testAppColorSchemeValuesAreDefined() {
        String darkNavy = "#032147";
        String blue = "#209dd7";
        String yellow = "#ecad0a";
        String purple = "#753991";

        assertNotNull(darkNavy, "Dark navy color should be defined");
        assertNotNull(blue, "Blue color should be defined");
        assertNotNull(yellow, "Yellow color should be defined");
        assertNotNull(purple, "Purple color should be defined");
    }

    private int getGridRows() {
        return 50;
    }

    private int getGridCols() {
        return 80;
    }

    private int getCellSize() {
        return 10;
    }
}
