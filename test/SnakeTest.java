import lk.edu.swlc.snakea.core.enums.Moves;
import lk.edu.swlc.snakea.core.modal.Snake;
import lk.edu.swlc.snakea.core.modal.SnakePiece;
import org.junit.Test;
import static org.junit.Assert.*;

public class SnakeTest {

    Snake snake = new Snake(600 / 2, 600 / 2, Moves.DOWN);

    @Test
    public void getMovesTest(){
        Moves moves = Moves.DOWN;
//        Moves moves = Moves.UP;
//        Moves moves = Moves.LEFT;
//        Moves moves = Moves.RIGHT;
        assertEquals(moves, snake.getMoves());
    }

    @Test
    public void getLengthText(){
        assertEquals(1, snake.getLength());
    }

    @Test
    public void getHeadTest(){
        assertEquals(0, snake.getLength()-1);
    }

    @Test
    public void runsIntoTest(){
        SnakePiece snakePiece = new SnakePiece();
        assertEquals(false, snake.runsInto(snakePiece));
    }

    @Test
    public void runsIntoItselfTest(){
        assertEquals(false, snake.runsIntoItself());
    }
}
