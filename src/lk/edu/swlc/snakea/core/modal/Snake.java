package lk.edu.swlc.snakea.core.modal;

import lk.edu.swlc.snakea.core.enums.Moves;

import java.util.List;
import java.util.ArrayList;

public class Snake {

    private int x;
    private int y;
    private Moves moves;
    private List<SnakePiece> piecesOfSnake;
    private boolean grows;

    public Snake(int originalX, int originalY, Moves moves) {
        this.x = originalX;
        this.y = originalY;
        this.moves = moves;
        this.piecesOfSnake = new ArrayList<SnakePiece>();
        this.piecesOfSnake.add(new SnakePiece(this.x, this.y));
        this.grows = false;
    }

    public Moves getMoves() {
        return this.moves;
    }

    public void setMoves(Moves moves) {
        this.moves = moves;
    }

    public int getLength() {
        return this.piecesOfSnake.size();
    }

    public List<SnakePiece> getPieces() {
        return this.piecesOfSnake;
    }

    public SnakePiece getHead() {
        return this.piecesOfSnake.get(this.getLength() - 1);
    }

    /**
     * the snake moves in the given moves. if the snake length is less than
     * 3 pieces, the snake grows one piece at the time from the front if the
     * snake length is 3 or more pieces, the only way for it to grow is by
     * eating apples.
     */
    public void move() {
        int moveX = this.getHead().getX();
        int moveY = this.getHead().getY();
        if (null != this.moves) {
            switch (this.moves) {
                case UP:
                    moveY--;
                    break;
                case DOWN:
                    moveY++;
                    break;
                case RIGHT:
                    moveX++;
                    break;
                case LEFT:
                    moveX--;
                    break;
                default:
                    break;
            }
        }
        if (this.getLength() > 2 && !this.grows) {
            this.piecesOfSnake.remove(0);
        }
        if (this.grows) {
            this.grows = false;
        }
        this.piecesOfSnake.add(new SnakePiece(moveX, moveY));
    }

    public void grows() {
        this.grows = true;
    }

    /**
     * @return true when the head of the snake comes in touch with any part of
     * the body
     */
    public boolean runsInto(SnakePiece snakePiece) {
        for (int i = 0; i < this.getLength(); i++) {
            if (this.piecesOfSnake.get(i).getX() == snakePiece.getX() && this.piecesOfSnake.get(i).getY() == snakePiece.getY()) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return true when the head of the snake comes in touch with any part of
     * the body and use this method on calculations of score points
     */

    public boolean runsIntoItself() {
        for (int i = 0; i < this.getLength() - 1; i++) {
            if (this.getHead().getX() == this.piecesOfSnake.get(i).getX() && this.getHead().getY() == this.piecesOfSnake.get(i).getY()) {
                return true;
            }
        }
        return false;
    }
}
