package com.example.pac_man;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HelloApplication extends Application {
    private static final int CELL_SIZE = 40;
    private static final int BOARD_WIDTH = 760;
    private static final int BOARD_HEIGHT = 760;
    int level = 1;

    @Override
    public void start(Stage primaryStage) {
        // Create maze , Pac-Man, ghosts
        MazeView mazeView = new MazeView(new Maze(BOARD_WIDTH,BOARD_HEIGHT,CELL_SIZE));
        PacMan pacman = new PacMan(1, 1,mazeView);
        Ghost[] ghosts = createGhosts(mazeView,level);

        //check if the pacman is alive
        Timeline positionChecker = new Timeline(new KeyFrame(Duration.millis(50), e->{
            for(Ghost ghost : ghosts)
                if(ghost.getCurrentColumn() == pacman.getCurrentColumn() && ghost.getCurrentRow() == pacman.getCurrentRow())
                    primaryStage.close();
        }));
        positionChecker.setCycleCount(-1);
        positionChecker.play();


        //game scene
        Scene scene = new Scene(mazeView, BOARD_WIDTH, BOARD_HEIGHT);

        // Set up event handling for key presses
        scene.setOnKeyPressed(e->{
            switch(e.getCode()){
                case UP :
                    pacman.moveUp();
                    pacman.rotateToTop();
                    break;
                case DOWN:
                    pacman.moveDown();
                    pacman.rotateToBottom();
                    break;
                case RIGHT:
                    pacman.moveRight();
                    pacman.reflectVerticallyToRight();
                    break;
                case LEFT:
                    pacman.moveLeft();
                    pacman.reflectVerticallyToLeft();
                    break;
            }
        });
        primaryStage.setScene(scene);
        primaryStage.setTitle("Pac-Man Game");
        primaryStage.show();
    }
    public Ghost[] createGhosts(MazeView mazeView,int level){
        Ghost[] ghosts = new Ghost[4];
        switch (level){
            case 1,2,3:
                 ghosts[0] = new Ghost(17,17,mazeView, level);
                 ghosts[0].setMode(1);
                 ghosts[1] = new Ghost(17,1,mazeView, level);
                 ghosts[1].setMode(2);
                 ghosts[2] = new Ghost(1,17,mazeView, level);
                 ghosts[2].setMode(3);
                 ghosts[3] = new Ghost(9,6,mazeView, level);
                break;

            case 4:
                ghosts[0] = new Ghost(17,17,mazeView, 2);
                ghosts[0].setMode(1);
                ghosts[1] = new Ghost(17,1,mazeView, 3);
                ghosts[1].setMode(2);
                ghosts[2] = new Ghost(1,17,mazeView, 3);
                ghosts[2].setMode(3);
                ghosts[3] = new Ghost(9,6,mazeView, level);
                break;
            case 5:
                ghosts[0] = new Ghost(17,17,mazeView, 3);
                ghosts[0].setMode(1);
                ghosts[1] = new Ghost(17,1,mazeView, 3);
                ghosts[1].setMode(2);
                ghosts[2] = new Ghost(1,17,mazeView, 3);
                ghosts[2].setMode(3);
                ghosts[3] = new Ghost(9,6,mazeView, level);
                break;


        }
        return ghosts;
    }

    public static void main(String[] args) {
        launch();
    }
}
//           case 3:
//ghosts[0] = new Ghost(17,17,mazeView, level);
//ghosts[0].setMode(1);
//ghosts[1] = new Ghost(17,1,mazeView, level);
//ghosts[1].setMode(2);
//ghosts[2] = new Ghost(1,17,mazeView, level);
//ghosts[2].setMode(3);
//ghosts[3] = new Ghost(9,6,mazeView, level);
//               break;