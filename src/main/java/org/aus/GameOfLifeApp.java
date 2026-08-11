package org.aus;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main application class for Conway's Game of Life.
 * Launches a JavaFX window with an interactive game grid and controls.
 */
public class GameOfLifeApp extends Application {
    private static final int GRID_ROWS = 50;
    private static final int GRID_COLS = 80;
    private static final int CELL_SIZE = 10;

    private GameGrid grid;
    private GameSimulation simulation;
    private GameGridView gridView;
    private Button playPauseBtn;
    private Label generationLabel;

    /**
     * Initializes and displays the application window.
     *
     * @param stage the primary stage for the application
     */
    @Override
    public void start(Stage stage) {
        grid = new GameGrid(GRID_ROWS, GRID_COLS);
        simulation = new GameSimulation(grid);
        gridView = new GameGridView(grid, CELL_SIZE);

        gridView.setOnCellToggle(gridView::draw);
        simulation.setOnUpdate(() -> {
            gridView.draw();
            updateGenerationLabel();
        });

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #032147;");

        root.setCenter(gridView);
        root.setBottom(createControlPanel());

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(e -> handleKeyPress(e.getCode()));
        stage.setTitle("Conway's Game of Life");
        stage.setScene(scene);
        stage.show();

        gridView.draw();
    }

    /**
     * Creates the control panel with buttons and speed slider.
     *
     * @return the control panel VBox
     */
    private VBox createControlPanel() {
        VBox panel = new VBox(10);
        panel.setPadding(new Insets(15));
        panel.setStyle("-fx-background-color: #032147;");

        HBox buttonBox = new HBox(10);
        buttonBox.setStyle("-fx-alignment: center;");

        playPauseBtn = new Button("Start");
        playPauseBtn.setStyle("-fx-padding: 8; -fx-font-size: 12; -fx-background-color: #753991; -fx-text-fill: white;");
        playPauseBtn.setOnAction(e -> toggleSimulation());

        Button resetBtn = new Button("Reset");
        resetBtn.setStyle("-fx-padding: 8; -fx-font-size: 12; -fx-background-color: #753991; -fx-text-fill: white;");
        resetBtn.setOnAction(e -> resetSimulation());

        Button clearBtn = new Button("Clear");
        clearBtn.setStyle("-fx-padding: 8; -fx-font-size: 12; -fx-background-color: #753991; -fx-text-fill: white;");
        clearBtn.setOnAction(e -> clearGrid());

        buttonBox.getChildren().addAll(playPauseBtn, resetBtn, clearBtn);

        HBox patternBox = new HBox(10);
        patternBox.setStyle("-fx-alignment: center;");

        Label patternLabel = new Label("Pattern:");
        patternLabel.setStyle("-fx-text-fill: #ecad0a; -fx-font-size: 12;");

        ComboBox<String> patternCombo = new ComboBox<>();
        for (Pattern p : Pattern.all()) {
            patternCombo.getItems().add(p.getName());
        }
        patternCombo.setPromptText("Select pattern");
        patternCombo.setStyle("-fx-font-size: 12;");

        Button loadPatternBtn = new Button("Load Pattern");
        loadPatternBtn.setStyle("-fx-padding: 8; -fx-font-size: 12; -fx-background-color: #753991; -fx-text-fill: white;");
        loadPatternBtn.setOnAction(e -> {
            String selected = patternCombo.getValue();
            if (selected != null) {
                loadPattern(selected);
            }
        });

        patternBox.getChildren().addAll(patternLabel, patternCombo, loadPatternBtn);

        HBox speedBox = new HBox(10);
        speedBox.setStyle("-fx-alignment: center;");

        Label speedLabel = new Label("Speed:");
        speedLabel.setStyle("-fx-text-fill: #ecad0a; -fx-font-size: 12;");

        Slider speedSlider = new Slider(0.5, 10, 2);
        speedSlider.setPrefWidth(200);
        speedSlider.setStyle("-fx-control-inner-background: #209dd7;");
        speedSlider.valueProperty()
                .addListener((ObservableValue<? extends Number> obs, Number oldVal, Number newVal) ->
                        simulation.setSpeed(newVal.doubleValue()));

        speedBox.getChildren().addAll(speedLabel, speedSlider);

        HBox infoBox = new HBox(20);
        infoBox.setStyle("-fx-alignment: center;");

        generationLabel = new Label("Generation: 0");
        generationLabel.setStyle("-fx-text-fill: #209dd7; -fx-font-size: 12; -fx-font-weight: bold;");

        Label gridSizeLabel = new Label("Grid: " + GRID_ROWS + "x" + GRID_COLS);
        gridSizeLabel.setStyle("-fx-text-fill: #888888; -fx-font-size: 11;");

        infoBox.getChildren().addAll(generationLabel, gridSizeLabel);

        Label instructionLabel = new Label("Click cells to toggle. Space: Start/Stop, R: Reset, C: Clear");
        instructionLabel.setStyle("-fx-text-fill: #888888; -fx-font-size: 11;");

        panel.getChildren().addAll(buttonBox, patternBox, speedBox, infoBox, instructionLabel);
        return panel;
    }

    /**
     * Toggles the simulation between running and paused states.
     */
    private void toggleSimulation() {
        if (simulation.isRunning()) {
            simulation.stop();
            playPauseBtn.setText("Start");
        } else {
            simulation.start();
            playPauseBtn.setText("Stop");
        }
    }

    /**
     * Resets the simulation and clears the grid.
     */
    private void resetSimulation() {
        grid.clear();
        simulation.reset(grid);
        playPauseBtn.setText("Start");
        gridView.draw();
        updateGenerationLabel();
    }

    /**
     * Clears the grid without resetting generation counter.
     */
    private void clearGrid() {
        grid.clear();
        gridView.draw();
    }

    /**
     * Loads a predefined pattern at the center of the grid.
     *
     * @param patternName the name of the pattern to load
     */
    private void loadPattern(String patternName) {
        Pattern pattern = null;
        for (Pattern p : Pattern.all()) {
            if (p.getName().equals(patternName)) {
                pattern = p;
                break;
            }
        }

        if (pattern != null) {
            int startRow = (GRID_ROWS - pattern.getHeight()) / 2;
            int startCol = (GRID_COLS - pattern.getWidth()) / 2;
            pattern.placeOn(grid, startRow, startCol);
            gridView.draw();
        }
    }

    /**
     * Updates the generation counter label.
     */
    private void updateGenerationLabel() {
        generationLabel.setText("Generation: " + simulation.getGeneration());
    }

    /**
     * Handles keyboard shortcuts.
     *
     * @param code the key code pressed
     */
    private void handleKeyPress(javafx.scene.input.KeyCode code) {
        switch (code) {
            case SPACE:
                toggleSimulation();
                break;
            case R:
                resetSimulation();
                break;
            case C:
                clearGrid();
                break;
            default:
                break;
        }
    }

    /**
     * Application entry point.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
