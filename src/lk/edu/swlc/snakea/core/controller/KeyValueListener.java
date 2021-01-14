package lk.edu.swlc.snakea.core.controller;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import lk.edu.swlc.snakea.core.enums.Moves;
import lk.edu.swlc.snakea.core.modal.Snake;


public class KeyValueListener implements KeyListener {

    private Snake snake;

    public KeyValueListener(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                this.snake.setMoves(Moves.UP);
                break;
            case KeyEvent.VK_DOWN:
                this.snake.setMoves(Moves.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                this.snake.setMoves(Moves.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                this.snake.setMoves(Moves.RIGHT);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
