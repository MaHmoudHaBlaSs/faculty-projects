package com.example.pac_man;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.List;

public class Ghost extends GifCharacter {
    MazeView mazeView;
    Maze maze;
    PacMan pacMan ;
    private enum Direction {
        UP ,DOWN,RIGHT,LEFT;
    }
    private Direction direction;
    private int mode = 1;
    private int moves =0;
    private int counter =0;
    private int[][] steps;
    public Ghost(int startingI, int startingJ, MazeView mazeView,int level) {
        this.mazeView = mazeView;
        maze = mazeView.getMaze();
        currentRow = startingI;
        currentColumn = startingJ;

        // get the pacman
        for(Node node:mazeView.getChildren()){
            if(node instanceof PacMan) {
                pacMan = (PacMan) node;
            }
        }

        // Create the ImageView , stick it to the maze
        ImageView gif = switch (level) {
            case 1,2,3 -> new ImageView("ghost01.gif");
            case 4,5 -> new ImageView("ghost00.gif");
            default -> null;
        };
        mazeView.getChildren().add(gif);
        setGif(gif);

        // Set the initial position for the ghost
        setPosition();

        // Set the size (adjust as needed)
        gif.setFitWidth(CELL_SIZE-5);
        gif.setFitHeight(CELL_SIZE-5);

        //animate the ghost
        Timeline animation;
        switch(level){
            case 1:
                animation = new Timeline(new KeyFrame(Duration.millis(500),e->moveGhostRandomly()) );
                animation.setCycleCount(-1);
                animation.play();
                break;
            case 2:
                animation = new Timeline(new KeyFrame(Duration.millis(400),e->moveGhostRandomly()) );
                animation.setCycleCount(-1);
                animation.play();
                break;
            case 3:
                animation = new Timeline(new KeyFrame(Duration.millis(300),e-> moveGhostRandomly()));
                animation.setCycleCount(-1);
                animation.play();
                break;
            case 4:
                animation = new Timeline(new KeyFrame(Duration.millis(400),e-> moveDirectedGhost()) );
                animation.setCycleCount(-1);
                animation.play();
                break;
            case 5:
                animation = new Timeline(new KeyFrame(Duration.millis(300),e->moveDirectedGhost()));
                animation.setCycleCount(-1);
                animation.play();
                break;



        }



    }
    public void setMode(int mode){
        this.mode = mode;
    }
    private void setPath(){
        //start: ghost location , end: pacMan location
        int start = maze.indicesToCell(this.currentRow,this.currentColumn);
        int end = maze.indicesToCell(pacMan.getCurrentRow(), pacMan.getCurrentColumn());

        //find the shortest path, set the steps
        List<Integer> list = maze.findShortestPath(start,end);
        int length = list.size();
        steps = new int[length][2];
        for(int i=0;i<length;i++){
            steps[i] = maze.cellToIndices(list.get(i));
        }

        //return counter to 0
        counter=0;
    }
    private void moveDirectedGhost(){
        if(counter%9 ==0 )
           setPath();

        if (counter<steps.length){
            currentRow = steps[counter][0];
            currentColumn = steps[counter][1];
            setPosition();
       }
       counter++;
    }
    private void moveGhostRandomly(){
            switch(mode)
            {
                case 1:
                    // TOP - RIGHT - BOTTOM - LEFT
                    if(moves == 15) {mode = 2; moves = 0;}
                    if(!maze.isWall(currentRow-1,currentColumn) && ((direction != Direction.DOWN) || isStuck(currentRow,currentColumn))) // Check top side
                    {
                        moveUp();
                    }
                    else if(!maze.isWall(currentRow,currentColumn+1) && ((direction != Direction.LEFT) || (isStuck(currentRow, currentColumn)))) // Check right side
                    {
                        moveRight();
                    }
                    else if(!maze.isWall(currentRow+1,currentColumn) && ((direction != Direction.UP) || isStuck(currentRow,currentColumn))) // Check bottom side
                    {
                        moveDown();
                    }
                    else if(!maze.isWall(currentRow, currentColumn-1) && ((direction != Direction.RIGHT) || (isStuck(currentRow, currentColumn)))) // Check left side
                    {
                        moveLeft();
                    }
                    moves++;
                    break;
                case 2:
                    // BOTTOM - LEFT -TOP - RIGHT
                    if(moves == 13) {mode = 3; moves = 0;}
                    if(!maze.isWall(currentRow+1,currentColumn) && ((direction != Direction.UP) || isStuck(currentRow,currentColumn))) // Check Bottom side
                    {
                        moveDown();
                    }
                    else if(!maze.isWall(currentRow, currentColumn-1) && ((direction != Direction.RIGHT) || (isStuck(currentRow, currentColumn)))) // Check lef side
                    {
                        moveLeft();
                    }
                    else if(!maze.isWall(currentRow-1,currentColumn) && ((direction != Direction.DOWN) || isStuck(currentRow,currentColumn))) // Check top side
                    {
                        moveUp();
                    }
                    else if(!maze.isWall(currentRow,currentColumn+1) && ((direction != Direction.LEFT) || (isStuck(currentRow, currentColumn)))) // Check right side
                    {
                        moveRight();
                    }
                    moves++;
                    break;
                case 3:
                    // LEFT - TOP - BOTTOM - RIGHT
                    if(moves == 12) {mode = 4; moves = 0;}
                    if(!maze.isWall(currentRow, currentColumn-1) && ((direction != Direction.RIGHT) || (isStuck(currentRow, currentColumn)))) // Check lef side
                    {
                        moveLeft();
                    }
                    else if(!maze.isWall(currentRow-1,currentColumn) && ((direction != Direction.DOWN) || isStuck(currentRow,currentColumn))) // Check top side
                    {
                        moveUp();
                    }
                    else if(!maze.isWall(currentRow+1,currentColumn) && ((direction != Direction.UP) || isStuck(currentRow,currentColumn))) // Check Bottom side
                    {
                        moveDown();
                    }
                    else if(!maze.isWall(currentRow,currentColumn+1) && ((direction != Direction.LEFT) || (isStuck(currentRow, currentColumn)))) // Check right side
                    {
                        moveRight();
                    }
                    moves++;
                    break;
                case 4:
                    // RIGHT - BOTTOM - TOP - LEFT
                    if(moves == 14) {mode = 1; moves = 0;}
                    if(!maze.isWall(currentRow,currentColumn+1) && ((direction != Direction.LEFT) || (isStuck(currentRow, currentColumn)))) // Check right side
                    {
                        moveRight();
                    }
                    else if(!maze.isWall(currentRow+1,currentColumn) && ((direction != Direction.UP) || isStuck(currentRow,currentColumn))) // Check Bottom side
                    {
                        moveDown();
                    }
                    else if(!maze.isWall(currentRow-1,currentColumn) && ((direction != Direction.DOWN) || isStuck(currentRow,currentColumn))) // Check top side
                    {
                        moveUp();
                    }
                    else if(!maze.isWall(currentRow, currentColumn-1) && ((direction != Direction.RIGHT) || (isStuck(currentRow, currentColumn)))) // Check lef side
                    {
                        moveLeft();
                    }
                    moves++;
                    break;
            }
    }
    public boolean isStuck(int i, int j){
        int walls = 0;
        if(maze.isWall(i-1,j)) {walls++;} // Check top side
        if(maze.isWall(i,j+1)) {walls++;} // Check right side
        if(maze.isWall(i+1,j)) {walls++;} // Check bottom side
        if(maze.isWall(i, j-1)) {walls++;}// Check left side
        return walls == 3;
    }
    public void moveRight(){
        currentColumn += 1;
        setPosition();
        direction = Direction.RIGHT;
    }
    public void moveLeft(){
        currentColumn -= 1;
        setPosition();
        direction = Direction.LEFT;
    }
    public void moveUp(){
        currentRow -= 1;
        setPosition();
        direction = Direction.UP;
    }
    public void moveDown(){
        currentRow += 1;
        setPosition();
        direction = Direction.DOWN;
    }
}

