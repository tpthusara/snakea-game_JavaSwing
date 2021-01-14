package lk.edu.swlc.snakea.core.game;

import lk.edu.swlc.snakea.core.enums.Moves;
import lk.edu.swlc.snakea.core.interfaces.Updatable;
import lk.edu.swlc.snakea.core.modal.Snake;
import lk.edu.swlc.snakea.core.modal.SnakeMeal;
import lk.edu.swlc.snakea.core.view.SnakeUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SnakeGame extends Timer implements ActionListener, Runnable {

    private int width;
    private int height;
    private boolean continues;
    private Updatable updatable;
    private Snake snake;
    private SnakeMeal snakeMeal;
    private int points;
    private SnakeUI ui;
    private Scores scoreTable;
    private Thread thread;


    static final int SCREEN_WIDTH = 1280;
    static final int SCREEN_HEIGHT = 720;

    public SnakeGame(int width, int height) {
        super(1000, null);
        this.width = width;
        this.height = height;
        this.continues = true;
        this.snake = new Snake(this.width / 2, this.height / 2, Moves.DOWN);
        this.points = 0;
        this.scoreTable = new Scores();
        addActionListener(this);
        setInitialDelay(2000);
        int appleX = new Random().nextInt(this.width);
        int appleY = new Random().nextInt(this.height);
        for (int i = 0; i < this.snake.getLength(); i++) {
            if (appleX != this.snake.getPieces().get(i).getX() && appleY != this.snake.getPieces().get(i).getY() && appleX >= 0 && appleY >= 0) {
                startGame();
            } else {
                startGame();
            }
        }
    }

    /**
     * controls whether the game goes on or stops
     *
     * @return
     */
    public boolean continues() {
        return continues;
    }

    /**
     * this control is meant to provide a connection to the updatable JPanel that graphically traces the game
     *
     * @param updatable
     */
    public void setUpdatable(Updatable updatable) {
        this.updatable = updatable;
    }

    /**
     * this control connects the game to the User Interface
     *
     * @param ui
     */
    public void setUI(SnakeUI ui) {
        this.ui = ui;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints() {
        this.points++;
        this.ui.updateLabel();
    }

    public void renderMeal() {
        this.snakeMeal = new SnakeMeal(new Random().nextInt(this.width), new Random().nextInt(this.height));
    }

    /**
     * rendering the meal in a random place
     */
    @Override
    public void run() {
        try {
            for (int i = 5; i > 0; i--) {
                renderMeal();
                Thread.sleep(10000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * defines a new thread when ever game starts
     */
    public void startGame() {
        try {
            thread = new Thread(this);
            thread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * define what happens whenever a piece (snakeMeal or body of the snake) or the border of the game is hit
     *
     * @param ae
     */
    public void actionPerformed(ActionEvent ae) {
        if (!continues) {
            return;
        }
        this.snake.move();
        if (this.snake.runsInto(this.snakeMeal)) {
            this.snake.grows();
            thread.stop();
            this.startGame();
            this.setPoints();
        }
        if (this.snake.runsIntoItself()) {
            this.scoreTable.addLastScore(this.points);
            this.continues = false;
            this.ui.gameOver();
            thread.stop();
        } else if (this.snake.getHead().getX() == this.width || this.snake.getHead().getX() < 0) {
            this.scoreTable.addLastScore(this.points);
            this.continues = false;
            this.ui.gameOver();
            thread.stop();
        } else if (this.snake.getHead().getY() == this.height || this.snake.getHead().getY() < 0) {
            this.scoreTable.addLastScore(this.points);
            this.continues = false;
            this.ui.gameOver();
            thread.stop();
        }
        this.updatable.update();
        this.setDelay(1000 / this.snake.getLength());
    }

    public Snake getSnake() {
        return this.snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public SnakeMeal getSnakeMeal() {
        return this.snakeMeal;
    }

    public void setSnakeMeal(SnakeMeal snakeMeal) {
        this.snakeMeal = snakeMeal;
    }
}
