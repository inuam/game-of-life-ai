package org.aus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for GameSimulation class.
 * Tests simulation state management and configuration.
 */
public class GameSimulationTest {
    private GameSimulation simulation;
    private GameGrid grid;

    @BeforeEach
    public void setUp() {
        grid = new GameGrid(5, 5);
        simulation = new GameSimulation(grid);
    }

    @Test
    public void testSimulationStartsInStoppedState() {
        assertFalse(simulation.isRunning(), "Simulation should start in stopped state");
    }

    @Test
    public void testSimulationStopsSuccessfully() {
        simulation.stop();
        assertFalse(simulation.isRunning(), "Simulation should be stopped after stop()");
    }

    @Test
    public void testResetChangesGrid() {
        GameGrid newGrid = new GameGrid(10, 10);
        simulation.reset(newGrid);
        assertEquals(newGrid, simulation.getGrid(), "Grid should be changed after reset");
    }

    @Test
    public void testResetStopsSimulation() {
        GameGrid newGrid = new GameGrid(5, 5);
        simulation.reset(newGrid);
        assertFalse(simulation.isRunning(), "Reset should stop simulation");
    }

    @Test
    public void testGetGridReturnsCurrentGrid() {
        assertEquals(grid, simulation.getGrid(), "getGrid() should return current grid");
    }

    @Test
    public void testSetSpeedWithDifferentValues() {
        assertDoesNotThrow(() -> simulation.setSpeed(0.5), "Should accept minimum speed");
        assertDoesNotThrow(() -> simulation.setSpeed(10.0), "Should accept maximum speed");
        assertDoesNotThrow(() -> simulation.setSpeed(5.0), "Should accept mid-range speed");
    }

    @Test
    public void testSetUpdateIntervalDirectly() {
        long interval = 1_000_000_000;
        assertDoesNotThrow(() -> simulation.setUpdateInterval(interval), "Should set interval");
    }

    @Test
    public void testOnUpdateCallbackCanBeSet() {
        final boolean[] called = {false};
        simulation.setOnUpdate(() -> called[0] = true);
        assertFalse(called[0], "Callback should not be called when setting");
    }

    @Test
    public void testGridIsNotNull() {
        assertNotNull(simulation.getGrid(), "Grid should not be null");
    }

    @Test
    public void testCanResetMultipleTimes() {
        GameGrid grid1 = new GameGrid(5, 5);
        GameGrid grid2 = new GameGrid(10, 10);
        simulation.reset(grid1);
        assertEquals(grid1, simulation.getGrid(), "First reset should work");
        simulation.reset(grid2);
        assertEquals(grid2, simulation.getGrid(), "Second reset should work");
    }

    @Test
    public void testStopWhenNotRunning() {
        assertFalse(simulation.isRunning(), "Not running initially");
        simulation.stop();
        assertFalse(simulation.isRunning(), "Should remain stopped");
    }

    @Test
    public void testGridDimensionsPreservedAfterReset() {
        GameGrid newGrid = new GameGrid(20, 30);
        simulation.reset(newGrid);
        assertEquals(20, simulation.getGrid().getRows(), "Rows should match");
        assertEquals(30, simulation.getGrid().getCols(), "Columns should match");
    }
}
