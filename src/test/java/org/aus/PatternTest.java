package org.aus;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Pattern class and predefined patterns.
 */
public class PatternTest {

    @Test
    public void testGliderPattern() {
        Pattern glider = Pattern.glider();
        assertEquals("Glider", glider.getName());
        assertEquals(3, glider.getHeight());
        assertEquals(3, glider.getWidth());
        assertTrue(glider.getCells()[0][1]);
        assertTrue(glider.getCells()[1][2]);
        assertTrue(glider.getCells()[2][0]);
    }

    @Test
    public void testBlinkerPattern() {
        Pattern blinker = Pattern.blinker();
        assertEquals("Blinker", blinker.getName());
        assertEquals(1, blinker.getHeight());
        assertEquals(3, blinker.getWidth());
    }

    @Test
    public void testToadPattern() {
        Pattern toad = Pattern.toad();
        assertEquals("Toad", toad.getName());
        assertEquals(2, toad.getHeight());
        assertEquals(4, toad.getWidth());
    }

    @Test
    public void testBeaconPattern() {
        Pattern beacon = Pattern.beacon();
        assertEquals("Beacon", beacon.getName());
        assertEquals(4, beacon.getHeight());
        assertEquals(4, beacon.getWidth());
    }

    @Test
    public void testPulsarPattern() {
        Pattern pulsar = Pattern.pulsar();
        assertEquals("Pulsar", pulsar.getName());
        assertEquals(13, pulsar.getHeight());
        assertEquals(13, pulsar.getWidth());
    }

    @Test
    public void testLwssPattern() {
        Pattern lwss = Pattern.lwss();
        assertEquals("Lightweight Spaceship", lwss.getName());
        assertEquals(4, lwss.getHeight());
        assertEquals(5, lwss.getWidth());
    }

    @Test
    public void testPlacePatternOnGrid() {
        GameGrid grid = new GameGrid(10, 10);
        Pattern glider = Pattern.glider();
        glider.placeOn(grid, 3, 3);
        
        assertTrue(grid.getCell(3, 4));
        assertTrue(grid.getCell(4, 5));
        assertTrue(grid.getCell(5, 3));
    }

    @Test
    public void testPlacePatternAtOrigin() {
        GameGrid grid = new GameGrid(10, 10);
        Pattern blinker = Pattern.blinker();
        blinker.placeOn(grid, 0, 0);
        
        assertTrue(grid.getCell(0, 0));
        assertTrue(grid.getCell(0, 1));
        assertTrue(grid.getCell(0, 2));
    }

    @Test
    public void testAllPatterns() {
        Pattern[] patterns = Pattern.all();
        assertEquals(6, patterns.length);
        assertEquals("Glider", patterns[0].getName());
        assertEquals("Lightweight Spaceship", patterns[5].getName());
    }
}
