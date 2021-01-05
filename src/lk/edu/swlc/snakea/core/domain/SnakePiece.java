package lk.edu.swlc.snakea.core.domain;
public class SnakePiece {

    private int x;
    private int y;

    public SnakePiece(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    /**
     * if the coordinates of this snakePiece take the same values of another snakePiece,
     * it means the two pieces have made contact and the method returns true
     *
     * @param snakePiece
     * @return
     */
    public boolean runsInto(SnakePiece snakePiece) {
        if (this.x == snakePiece.getX() && this.y == snakePiece.getY()) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}
