import lk.edu.swlc.snakea.core.gameLogic.SnakeGame;
import org.junit.Test;
import static org.junit.Assert.*;

public class SnakeGameTest {

    SnakeGame snakeGame = new SnakeGame(6,6);

    @Test
    public void getHeightText(){
        assertEquals(6, snakeGame.getHeight());
    }

    @Test
    public void getWidthText(){
        assertEquals(6, snakeGame.getWidth());
    }

    @Test
    public void getPointsText(){
        assertEquals(0, snakeGame.getPoints());

    }

    @Test
    public void getSnakeText(){
        assertEquals(true, snakeGame.getSnake() != null);
    }

    @Test
    public void getSnakeMealText(){
        assertEquals(false, snakeGame.getSnakeMeal() != null);
    }
}
