package lk.edu.swlc.snakea.core.game;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import lk.edu.swlc.snakea.core.enums.Moves;
import lk.edu.swlc.snakea.core.domain.SnakeMeal;
import lk.edu.swlc.snakea.core.view.SnakeUI;
import lk.edu.swlc.snakea.core.interfaces.Updatable;
import lk.edu.swlc.snakea.core.domain.Snake;

public class SnakeGame extends Timer implements ActionListener {

    private int width;
    private int height;
    private boolean continues;
    private Updatable updatable;
    private Snake snake;
    private SnakeMeal snakeMeal;
    private int points;
    private SnakeUI ui;
    private Scores scoreTable;
    
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
                this.snakeMeal = new SnakeMeal(appleX, appleY);
            } else {
                this.snakeMeal = new SnakeMeal(appleX / 2 + 1, appleY / 2 + 1);
            }
        }
    }
/**
 * controls whether the game goes on or stops
 * @return 
 */
    public boolean continues() {
        return continues;
    }
/**
 * this control is meant to provide a connection to the updatable JPanel that graphically traces the game
 * @param updatable 
 */
    public void setUpdatable(Updatable updatable) {
        this.updatable = updatable;
    }
    /**
     * this control connects the game to the User Interface
     * @param ui 
     */
    public void setUI(SnakeUI ui){
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
/**
 * define what happens whenever a piece (snakeMeal or body of the snake) or the border of the game is hit
 * @param ae 
 */
    public void actionPerformed(ActionEvent ae) {
        if (!continues) {
            return;
        }
        this.snake.move();
        if (this.snake.runsInto(this.snakeMeal)) {
            this.snake.grows();
            this.setPoints();
            this.snakeMeal = new SnakeMeal(new Random().nextInt(this.width), new Random().nextInt(this.height));
        }
        if (this.snake.runsIntoItself()) {
            this.scoreTable.addLastScore(this.points);
            this.continues = false;
        } else if (this.snake.getHead().getX() == this.width || this.snake.getHead().getX() < 0) {
            this.scoreTable.addLastScore(this.points);
            this.continues = false;
        } else if (this.snake.getHead().getY() == this.height || this.snake.getHead().getY() < 0) {
            this.scoreTable.addLastScore(this.points);
            this.continues = false;
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
