package snakeGame.GameLogic;

import snakeGame.GameLogic.domain.GameObjects;
import snakeGame.GameLogic.domain.Cell;
import snakeGame.GameLogic.domain.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static snakeGame.App.BOARD_HEIGHT;
import static snakeGame.App.BOARD_WIDTH;

public class GameLogic extends JPanel implements ActionListener, KeyListener {

    private final int CELL_SIZE = 25;
    private final int SCORE_INDENTATION = 150;

    private boolean hasFinished = false;
    GameObjects gameObjects;
    Timer gameLoop;

    public GameLogic() {

        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);

        gameObjects = new GameObjects(BOARD_WIDTH, BOARD_HEIGHT);

        gameLoop = new Timer(130, this);
        gameLoop.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }


    public void draw(Graphics g) {

        //Draw grid
        //drawGrid(g);

        //Draw snakes
        for (Snake snake : gameObjects.snakes()) {
            if(gameObjects.isSnakeAlive(snake.snakeId())){
                drawSnake(g, snake);
            }
        }

        //Draw food
        drawFoods(g);

        //Draw scores
        for (Snake snake : gameObjects.snakes()) {
            drawScores(g, snake);
        }

        if (gameObjects.areAllButOneSnakeDead()) {
            g.setFont(new Font("Arial", Font.PLAIN, 40));
            g.setColor(Color.WHITE);
            g.drawString("Player " + gameObjects.getWinner() + " wins!", BOARD_WIDTH / 2 - 100, BOARD_HEIGHT / 2);
        }
    }

    private void drawGrid(Graphics g) {
        for (int i = 0; i < BOARD_WIDTH / CELL_SIZE; i++) {
            g.drawLine(i * CELL_SIZE, 0, i * CELL_SIZE, BOARD_HEIGHT);
            g.drawLine(0, i * CELL_SIZE, BOARD_WIDTH, i * CELL_SIZE);
        }
    }

    private void drawFoods(Graphics g) {
        for (Cell c : gameObjects.foods()) {
            g.setColor(Color.RED);
            g.fillRect(c.x * CELL_SIZE, c.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            g.drawImage(gameObjects.foodImage(), c.x * CELL_SIZE, c.y * CELL_SIZE, CELL_SIZE, CELL_SIZE, null);
        }
    }

    private void drawSnake(Graphics g, Snake snake) {
        g.setColor(snake.color());
        //g.fillRect(snake.head().x * CELL_SIZE, snake.head().y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        g.drawImage(snake.headImage(), snake.head().x * CELL_SIZE, snake.head().y * CELL_SIZE, CELL_SIZE, CELL_SIZE, null);

        for (Cell c : snake.body()) {
            g.fillRect(c.x * CELL_SIZE, c.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }
    }

    private void drawScores(Graphics g, Snake snake) {
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        if (!gameObjects.isSnakeAlive(snake.snakeId())) {
            g.setColor(Color.RED);
            g.drawString("Game Over: " + snake.size(), CELL_SIZE - 16 + (SCORE_INDENTATION * (snake.snakeId() - 1)), CELL_SIZE);
        } else {
            g.setColor(Color.WHITE);
            g.drawString("Score: " + snake.size(), CELL_SIZE - 16 + (SCORE_INDENTATION * (snake.snakeId() - 1)), CELL_SIZE);
        }
    }

    public void move() {

        for (Snake snake : gameObjects.snakes()) {

            for (Cell food : gameObjects.foods()) {
                if (collision(snake.head(), food)) {
                    snake.grow(food.x, food.y);
                    gameObjects.placeFood(food.x,food.y);
                }
            }

            snake.updateSnake();

            for (Cell c : snake.body()) {
                if (collision(snake.head(), c)) {
                    gameObjects.markSnakeDead(snake.snakeId());
                }
                for(Snake otherSnake : gameObjects.snakes()) {
                    if (otherSnake != snake) {
                        if (collision(otherSnake.head(), c) || collision(otherSnake.head(), snake.head())){
                            gameObjects.markSnakeDead(otherSnake.snakeId());
                        }
                    }
                }
            }

            if (snake.head().x * CELL_SIZE >= BOARD_WIDTH || snake.head().x < 0 ||
                    snake.head().y * CELL_SIZE >= BOARD_HEIGHT || snake.head().y < 0) {
                gameObjects.markSnakeDead(snake.snakeId());
            }
        }
    }

    public boolean hasFinished() {
        return hasFinished;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if (gameObjects.areAllButOneSnakeDead()) {
            gameLoop.stop();
            hasFinished = true;
        }
    }

    public boolean collision(Cell c1, Cell c2) {
        return c1.x == c2.x && c1.y == c2.y;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        for(Snake snake : gameObjects.snakes()) {
            if (snake.containsKey(key)) {
                snake.changeDirection(key);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public boolean isRunning() {
        return gameLoop.isRunning();
    }
}
