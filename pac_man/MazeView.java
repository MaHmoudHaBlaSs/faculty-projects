package com.example.pac_man;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;

public class MazeView extends Pane {
    private static final int CELL_SIZE = 40; // Controls the screen Dimensions (be Careful)
    private Maze maze;
    public MazeView(Maze maze) {
        this.maze = maze;
        drawMaze();
    }
    public Maze getMaze(){
        return maze;
    }

    private void drawMaze() {
        int rows = maze.getRows();
        int cols = maze.getCols();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (maze.isWall(i, j))
                {
                    //                Coordinates : X(Rows*Width), Y(Cols*Height)      Dimensions
                    Rectangle wall = new Rectangle(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE-2, CELL_SIZE-2);
                    wall.setFill(Color.rgb(36,36,50)); // Adjust wall color
                    wall.setStroke(Color.BLACK);
                    wall.setStrokeWidth(3);
                    wall.setArcHeight(10);
                    wall.setArcWidth(10);
                    getChildren().add(wall);
                }
                else if (maze.isPellet(i, j))
                {
                    //Coordinates : X(Rows*Width+.5*CELL) -> Center of the Cell....               Radius
                    Circle pellet = new Circle(j * CELL_SIZE + CELL_SIZE / 2, i * CELL_SIZE + CELL_SIZE / 2, 4);
                    pellet.setFill(Color.YELLOW); // Adjust pellet color
                    pellet.setStroke(Color.BLACK);
                    pellet.setStrokeWidth(1);
                    getChildren().add(pellet);
                }
                else if (maze.isPowerPellet(i, j))
                {
                    // Same as pellet but with larger Radius
                    Circle powerPellet = new Circle(j * CELL_SIZE + CELL_SIZE / 2, i * CELL_SIZE + CELL_SIZE / 2, 5);
                    powerPellet.setFill(Color.ORANGE); // Adjust power pellet color
                    powerPellet.setStrokeWidth(1.3);
                    powerPellet.setStroke(Color.RED);
                    getChildren().add(powerPellet);
                }
                else{
                    //                Coordinates : X(Rows*Width), Y(Cols*Height)      Dimensions
                    Rectangle emptyCell = new Rectangle(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                    emptyCell.setFill(Color.WHITE); // Adjust wall color
                    getChildren().add(emptyCell);
                }
                // Add more conditions to handle Pacman and ghosts
            }
        }
    }
}
