package org.aus;

/**
 * Represents a predefined pattern for Conway's Game of Life.
 * Contains pattern data and metadata for classic Conway patterns.
 */
public class Pattern {
    private final String name;
    private final boolean[][] cells;
    private final int width;
    private final int height;

    /**
     * Constructs a Pattern with the given name and cell configuration.
     *
     * @param name the name of the pattern
     * @param cells the 2D array representing alive (true) and dead (false) cells
     */
    public Pattern(String name, boolean[][] cells) {
        this.name = name;
        this.cells = cells;
        this.height = cells.length;
        this.width = cells.length > 0 ? cells[0].length : 0;
    }

    public String getName() {
        return name;
    }

    public boolean[][] getCells() {
        return cells;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * Places this pattern onto a grid at the specified position.
     *
     * @param grid the GameGrid to place the pattern on
     * @param startRow the top-left row position
     * @param startCol the top-left column position
     */
    public void placeOn(GameGrid grid, int startRow, int startCol) {
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                if (cells[r][c]) {
                    grid.setCell(startRow + r, startCol + c, true);
                }
            }
        }
    }

    /**
     * Gets the Glider pattern.
     * A small pattern that travels diagonally across the grid.
     *
     * @return the Glider pattern
     */
    public static Pattern glider() {
        boolean[][] cells = {
            {false, true, false},
            {false, false, true},
            {true, true, true}
        };
        return new Pattern("Glider", cells);
    }

    /**
     * Gets the Blinker pattern.
     * A period-2 oscillator that alternates between horizontal and vertical.
     *
     * @return the Blinker pattern
     */
    public static Pattern blinker() {
        boolean[][] cells = {
            {true, true, true}
        };
        return new Pattern("Blinker", cells);
    }

    /**
     * Gets the Toad pattern.
     * A period-2 oscillator.
     *
     * @return the Toad pattern
     */
    public static Pattern toad() {
        boolean[][] cells = {
            {false, true, true, true},
            {true, true, true, false}
        };
        return new Pattern("Toad", cells);
    }

    /**
     * Gets the Beacon pattern.
     * A period-2 oscillator.
     *
     * @return the Beacon pattern
     */
    public static Pattern beacon() {
        boolean[][] cells = {
            {true, true, false, false},
            {true, true, false, false},
            {false, false, true, true},
            {false, false, true, true}
        };
        return new Pattern("Beacon", cells);
    }

    /**
     * Gets the Pulsar pattern.
     * A period-3 oscillator.
     *
     * @return the Pulsar pattern
     */
    public static Pattern pulsar() {
        boolean[][] cells = new boolean[13][13];
        
        int[][] coords = {
            {0,2},{0,3},{0,4},{0,8},{0,9},{0,10},
            {2,0},{2,5},{2,7},{2,12},
            {3,0},{3,5},{3,7},{3,12},
            {4,0},{4,5},{4,7},{4,12},
            {5,2},{5,3},{5,4},{5,8},{5,9},{5,10},
            {7,2},{7,3},{7,4},{7,8},{7,9},{7,10},
            {8,0},{8,5},{8,7},{8,12},
            {9,0},{9,5},{9,7},{9,12},
            {10,0},{10,5},{10,7},{10,12},
            {12,2},{12,3},{12,4},{12,8},{12,9},{12,10}
        };
        
        for (int[] coord : coords) {
            cells[coord[0]][coord[1]] = true;
        }
        
        return new Pattern("Pulsar", cells);
    }

    /**
     * Gets the Lightweight Spaceship (LWSS) pattern.
     * A pattern that travels horizontally across the grid.
     *
     * @return the LWSS pattern
     */
    public static Pattern lwss() {
        boolean[][] cells = {
            {false, true, false, false, true},
            {true, false, false, false, false},
            {true, false, false, false, true},
            {true, true, true, true, false}
        };
        return new Pattern("Lightweight Spaceship", cells);
    }

    /**
     * Gets all predefined patterns.
     *
     * @return array of all patterns
     */
    public static Pattern[] all() {
        return new Pattern[] {
            glider(),
            blinker(),
            toad(),
            beacon(),
            pulsar(),
            lwss()
        };
    }
}
