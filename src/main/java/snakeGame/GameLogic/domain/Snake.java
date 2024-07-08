package snakeGame.GameLogic.domain;

import java.awt.*;
import java.util.ArrayList;

public class Snake {

    private final Cell head;
    private final ArrayList<Cell> snakeBody = new ArrayList<>();
    private final ArrayList<Integer> keys;
    private final Color color;
    private final int snakeId;
    private int velX = 0;
    private int velY = 0;
    private final String imagePath;

    public Snake(int snakeID, Cell head, Color color, ArrayList<Integer> keys, String path) {
        this.head = head;
        this.color = color;
        this.keys = keys;
        this.snakeId = snakeID;
        this.imagePath = path;
    }

    public Cell head () {
        return head;
    }

    private void updateHeadPosition(int x, int y) {
        head.x += x;
        head.y += y;
    }

    private void updateBodyPosition() {
        for (int i = size() - 1; i >= 0; i--) {
            if (i == 0) {
                body().get(i).x = head().x;
                body().get(i).y = head().y;
            } else {
                body().get(i).x = body().get(i - 1).x;
                body().get(i).y = body().get(i - 1).y;
            }
        }
    }

    public void updateSnake() {
        updateBodyPosition();
        updateHeadPosition(velX, velY);
    }

    public ArrayList<Cell> body() {
        return snakeBody;
    }

    public int size() {
        return snakeBody.size();
    }

    public int snakeId() {
        return snakeId;
    }

    public void grow(int x, int y) {
        snakeBody.add(new Cell(x, y));
    }

    public Color color() {
        return color;
    }

    public boolean containsKey(int key){
        return keys.contains(key);
    }

    public void changeDirection(int key){
        //Keys 0 - left, 1 - up, 2 - right, 3 - down
        if (key == keys.get(0) && velX != 1) {
            velX = -1;
            velY = 0;
        } else if (key == keys.get(1) && velY != 1) {
            velX = 0;
            velY = -1;
        } else if (key == keys.get(2) && velX != -1) {
            velX = 1;
            velY = 0;
        } else if (key == keys.get(3) && velY != -1) {
            velX = 0;
            velY = 1;
        }
    }

    public Image headImage() {
        return Toolkit.getDefaultToolkit().getImage(imagePath);
    }
}
