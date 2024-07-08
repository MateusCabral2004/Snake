package snakeGame.Menus;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;

import static snakeGame.App.BOARD_HEIGHT;
import static snakeGame.App.BOARD_WIDTH;

public class Menu extends JPanel implements MouseListener {

    Rectangle singlePlayer = new Rectangle(250, 100, 200, 100);
    Rectangle multiPlayer = new Rectangle(250, 300, 200, 100);
    Rectangle quitButton = new Rectangle(250, 500, 200, 100);

    private int gameMode = 0;

    public Menu() {

        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setBackground(Color.BLACK);
        addMouseListener(this);
        setFocusable(true);

    }

    public int gameMode() {
        return gameMode;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {

        g.drawRect(singlePlayer.x, singlePlayer.y, singlePlayer.width, singlePlayer.height);
        g.drawRect(multiPlayer.x, multiPlayer.y, multiPlayer.width, multiPlayer.height);
        g.drawRect(quitButton.x, quitButton.y, quitButton.width, quitButton.height);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 40));
        g.drawString("Start", singlePlayer.x + 50, singlePlayer.y + 50);
        g.drawString("Quit", quitButton.x + 50, quitButton.y + 50);
        g.drawString("Multiplayer", multiPlayer.x + 10, multiPlayer.y + 50);
    }

    public void drawMenu() {
        repaint();
    }


    /**
     * Mouse controller
     */

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        if (singlePlayer.contains(mouseX, mouseY)) {
            gameMode = 1;
        } else if (multiPlayer.contains(mouseX, mouseY)) {
            gameMode = 2;
        } else if (quitButton.contains(mouseX, mouseY)) {
            System.exit(0);
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void restartGameMode() {
        gameMode = 0;
    }
}
