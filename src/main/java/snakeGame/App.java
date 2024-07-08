package snakeGame;

import snakeGame.GameLogic.GameLogic;
import snakeGame.Menus.Menu;

import javax.swing.*;

public class App {

    public static final int BOARD_WIDTH = 700;
    public static final int BOARD_HEIGHT = 700;

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Snake");
        frame.setVisible(true);
        frame.setSize(BOARD_WIDTH, BOARD_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setIconImage(new ImageIcon("src/main/resources/icon.png").getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Menu menu = new Menu();

        while (true) {

            frame.add(menu);
            frame.pack();
            menu.drawMenu();

            while (menu.gameMode() == 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {}
            }

            System.out.println("Game mode: " + menu.gameMode());

            GameLogic gameLogic = new GameLogic();
            frame.add(gameLogic);
            frame.pack();
            gameLogic.requestFocusInWindow();

            while (gameLogic.isRunning()) {
                Thread.sleep(100);
            }
            
            Thread.sleep(1000);
            menu.restartGameMode();
        }

    }
}
