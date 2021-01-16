import lk.edu.swlc.snakea.core.modal.SnakePiece;
import org.junit.Test;
import static org.junit.Assert.*;

public class SnakePieceTest {

    SnakePiece snakePiece = new SnakePiece(1,1);

    @Test
    public void testGetX() {
        assertEquals(1,snakePiece.getX());
    }

    @Test
    public void testGetY() {
        assertEquals(1,snakePiece.getY());
    }
}
