package com.example.pac_man;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static final int CELL_SIZE = 40;
    private static final int BOARD_WIDTH = 760;
    private static final int BOARD_HEIGHT = 760;
    private static int ROWS = BOARD_HEIGHT / CELL_SIZE;
    private static int COLS = BOARD_HEIGHT / CELL_SIZE;

    @Override
    public void start(Stage primaryStage) {
        // Create Pac-Man
        PacMan pacman = new PacMan(500, 400);
        MazeView mazeView = new MazeView(new Maze(ROWS,COLS));
        Pane root = new Pane();
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);

        root.getChildren().addAll(mazeView.getMazeGroup(),pacman.getView());

        // Set up event handling for key presses
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    pacman.movePacman(0, -CELL_SIZE/3);
                    pacman.rotateToTop();
                    break;
                case DOWN:
                    pacman.movePacman(0, CELL_SIZE/3);
                    pacman.rotateToBottom();
                    break;
                case LEFT:
                    pacman.movePacman(-CELL_SIZE/3, 0);
                    pacman.reflectVerticallyToLeft();
                    break;
                case RIGHT:
                    pacman.movePacman(CELL_SIZE/3, 0);
                    pacman.reflectVerticallyToRight();
                    break;
                default:
                    break;
            }
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("Pac-Man Game");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}