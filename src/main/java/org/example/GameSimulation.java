package org.example;

import javafx.animation.AnimationTimer;

/**
 * Controls the simulation timing and execution of Conway's Game of Life.
 * Manages play/pause state and generation speed.
 */
public class GameSimulation {
    private GameGrid grid;
    private AnimationTimer timer;
    private long lastUpdate = 0;
    private long updateInterval = 500_000_000;
    private Runnable onUpdate;
    private boolean running = false;

    /**
     * Constructs a GameSimulation with the given grid.
     *
     * @param grid the GameGrid to simulate
     */
    public GameSimulation(GameGrid grid) {
        this.grid = grid;
        this.onUpdate = () -> {};
    }

    /**
     * Sets the update interval in nanoseconds.
     *
     * @param nanos the interval between generations in nanoseconds
     */
    public void setUpdateInterval(long nanos) {
        this.updateInterval = nanos;
    }

    /**
     * Sets the simulation speed in generations per second.
     *
     * @param generationsPerSecond the speed of the simulation
     */
    public void setSpeed(double generationsPerSecond) {
        this.updateInterval = (long) (1_000_000_000 / generationsPerSecond);
    }

    /**
     * Sets the callback to be invoked after each generation update.
     *
     * @param callback the callback function
     */
    public void setOnUpdate(Runnable callback) {
        this.onUpdate = callback;
    }

    /**
     * Starts the simulation. Does nothing if already running.
     */
    public void start() {
        if (running) return;
        running = true;

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= updateInterval) {
                    grid.nextGeneration();
                    onUpdate.run();
                    lastUpdate = now;
                }
            }
        };
        timer.start();
    }

    /**
     * Stops the simulation.
     */
    public void stop() {
        running = false;
        if (timer != null) {
            timer.stop();
        }
    }

    /**
     * Resets the simulation with a new grid.
     *
     * @param newGrid the new grid to simulate
     */
    public void reset(GameGrid newGrid) {
        stop();
        this.grid = newGrid;
        lastUpdate = 0;
    }

    /**
     * Checks if the simulation is currently running.
     *
     * @return true if running, false otherwise
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Gets the current grid being simulated.
     *
     * @return the GameGrid
     */
    public GameGrid getGrid() {
        return grid;
    }
}
