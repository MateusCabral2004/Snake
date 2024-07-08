package snakeGame.GameLogic.domain;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public enum Keys {

    PLAYER1(KeyEvent.VK_LEFT, KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN),
    PLAYER2(KeyEvent.VK_A , KeyEvent.VK_W, KeyEvent.VK_D, KeyEvent.VK_S),
    PLAYER3(KeyEvent.VK_J, KeyEvent.VK_I, KeyEvent.VK_L, KeyEvent.VK_K);

    private final int left;
    private final int up;
    private final int right;
    private final int down;

    Keys(int left, int up, int right, int down) {
        this.left = left;
        this.up = up;
        this.right = right;
        this.down = down;
    }

    public ArrayList<Integer> keysAsArray() {
        ArrayList<Integer> keys = new ArrayList<>();
        keys.add(left);
        keys.add(up);
        keys.add(right);
        keys.add(down);
        return keys;
    }
}
