Conway's Game of Life - JavaFX Implementation

A JavaFX desktop application implementing Conway's Game of Life cellular automaton.

Prerequisites:
- Java 21 or higher
- Maven 3.8+

Dependencies:
- JavaFX 21.0.1 (included via Maven)
- JUnit 5 (included via Maven for testing)

Features:
- Interactive grid with click-to-toggle cells
- Start/Stop simulation controls
- Speed adjustment slider (0.5 - 10 generations/second)
- Clear and Reset buttons
- Dark navy background with blue live cells and yellow grid
- Real-time rendering

Installation:
1. Install Java 21: https://adoptium.net/
2. Install Maven: https://maven.apache.org/download.cgi

Build and Run:
```
cd conways-game-of-life
mvn clean compile
mvn javafx:run
```

Run Tests:
```
mvn test
```

Controls:
- Click cells to toggle between alive (blue) and dead (dark)
- Start: Begin the simulation
- Stop: Pause the simulation
- Clear: Clear all cells without stopping
- Reset: Stop and return to initial state
- Speed Slider: Adjust simulation speed from 0.5 to 10 generations/second

Conway's Game of Life Rules:
1. Any live cell with fewer than 2 live neighbors dies (underpopulation)
2. Any live cell with 2-3 live neighbors survives
3. Any live cell with more than 3 live neighbors dies (overpopulation)
4. Any dead cell with exactly 3 live neighbors becomes alive (reproduction)
