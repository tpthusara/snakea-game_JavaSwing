package lk.edu.swlc.snakea.core.view;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

import lk.edu.swlc.snakea.core.domain.SnakePiece;
import lk.edu.swlc.snakea.core.game.SnakeGame;
import lk.edu.swlc.snakea.core.interfaces.Updatable;

public class PlayPanel extends JPanel implements Updatable {

    private SnakeGame game;
    private int pieceLength;

    public PlayPanel(SnakeGame game, int pieceLength) {
        this.game = game;
        this.pieceLength = pieceLength;
        setBackground(Color.BLACK);
    }
/**
 * defines the colors of the game
 * @param graphics 
 */
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        for (SnakePiece snakePiece : this.game.getSnake().getPieces()) {
            graphics.setColor(Color.WHITE);
            graphics.fill3DRect(snakePiece.getX() * this.pieceLength, snakePiece.getY() * this.pieceLength, this.pieceLength, this.pieceLength, true);
        }
        graphics.setColor(Color.RED);
        graphics.fillOval(this.game.getSnakeMeal().getX() * this.pieceLength, this.game.getSnakeMeal().getY() * this.pieceLength, this.pieceLength, this.pieceLength);
    }

    public void update() {
        this.repaint();
    }
}
