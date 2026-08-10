package org.gol;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        simulation.setOnUpdate(gridView::draw);

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #032147;");

        root.setCenter(gridView);
        root.setBottom(createControlPanel());

        Scene scene = new Scene(root);
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
        resetBtn.setOnAction(e -> {
            grid.clear();
            simulation.reset(grid);
            playPauseBtn.setText("Start");
            gridView.draw();
        });

        Button clearBtn = new Button("Clear");
        clearBtn.setStyle("-fx-padding: 8; -fx-font-size: 12; -fx-background-color: #753991; -fx-text-fill: white;");
        clearBtn.setOnAction(e -> {
            grid.clear();
            gridView.draw();
        });

        buttonBox.getChildren().addAll(playPauseBtn, resetBtn, clearBtn);

        HBox speedBox = new HBox(10);
        speedBox.setStyle("-fx-alignment: center;");

        Label speedLabel = new Label("Speed:");
        speedLabel.setStyle("-fx-text-fill: #ecad0a; -fx-font-size: 12;");

        Slider speedSlider = new Slider(0.5, 10, 2);
        speedSlider.setPrefWidth(200);
        speedSlider.setStyle("-fx-control-inner-background: #209dd7;");
        speedSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            simulation.setSpeed(newVal.doubleValue());
        });

        speedBox.getChildren().addAll(speedLabel, speedSlider);

        Label instructionLabel = new Label("Click cells to toggle them. Use controls to start/stop simulation.");
        instructionLabel.setStyle("-fx-text-fill: #888888; -fx-font-size: 11;");

        panel.getChildren().addAll(buttonBox, speedBox, instructionLabel);
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
     * Application entry point.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
