package snakeGame.GameLogic.domain;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameObjects {

    private final int CELL_SIZE = 25;
    private final int NUMBER_OF_FOOD = 3;
    private int boardWidth;
    private int boardHeight;
    private final ArrayList<Cell> food = new ArrayList<>();
    private final ArrayList<Snake> snakes = new ArrayList<>();
    private final ArrayList<Boolean> snakeAlive = new ArrayList<>();
    Random random;

    public GameObjects(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;

        createSnakes();
        random = new Random();

        initFoods();

    }

    private void initFoods() {
        for(int i = 0; i < NUMBER_OF_FOOD; i++) {
            food.add(new Cell(0, 0));
            placeFood(0,0);
        }
    }

    private void createSnakes() {
        snakes.add(new Snake(1,new Cell(5, 5), Color.GREEN, Keys.PLAYER1.keysAsArray(), ImagePaths.PLAYER1.getPath()));
        snakeAlive.add(true);
        snakes.add(new Snake(2,new Cell(10, 10), Color.BLUE, Keys.PLAYER2.keysAsArray(), ImagePaths.PLAYER2.getPath()));
        snakeAlive.add(true);
        snakes.add(new Snake(3,new Cell(15, 15), Color.YELLOW, Keys.PLAYER3.keysAsArray(), ImagePaths.PLAYER3.getPath()));
        snakeAlive.add(true);
    }

    public void placeFood(int oldX, int oldY) {

        for(Cell c : food){
            if(c.x == oldX && c.y == oldY){
                c.x = random.nextInt(boardWidth / CELL_SIZE);
                c.y = random.nextInt(boardHeight / CELL_SIZE);
                return;
            }
        }
    }

    public ArrayList<Cell> foods() {
        return food;
    }

    public ArrayList<Snake> snakes() {
        return snakes;
    }

    public boolean isSnakeAlive(int snakeId) {
        return snakeAlive.get(snakeId - 1);
    }

    public void markSnakeDead(int snakeId) {
        snakeAlive.set(snakeId - 1, false);
    }

    public boolean areAllSnakesDead() {
        for (Boolean alive : snakeAlive) {
            if (alive) {
                return false;
            }
        }
        return true;
    }

    public boolean areAllButOneSnakeDead() {
        int aliveCount = 0;
        for (Boolean alive : snakeAlive) {
            if (alive) {
                aliveCount++;
            }
        }
        return aliveCount == 1;
    }

    public String getWinner() {
        for (int i = 0; i < snakeAlive.size(); i++) {
            if (snakeAlive.get(i)) {
                return Integer.toString(i + 1);
            }
        }
        return "";
    }

    public Image foodImage(){
        return Toolkit.getDefaultToolkit().getImage("src/main/resources/food.png");
    }
}
