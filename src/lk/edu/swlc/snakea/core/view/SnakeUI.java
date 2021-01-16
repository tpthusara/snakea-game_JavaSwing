package lk.edu.swlc.snakea.core.view;

import java.awt.*;
import javax.swing.*;

import lk.edu.swlc.snakea.core.gameLogic.SnakeGame;
import lk.edu.swlc.snakea.core.interfaces.Updatable;
import lk.edu.swlc.snakea.core.controller.KeyValueListener;

public class SnakeUI implements Runnable {

    private JFrame frame;
    private SnakeGame game;
    private int sideLength;
    private PlayPanel board;
    private JLabel label;
    private JLabel overLable;
    private int points;
    private Image frameLogo;

    public SnakeUI(SnakeGame game, int sideLength) {
        this.game = game;
        this.sideLength = sideLength;
        this.points = this.game.getPoints();
        this.frameLogo = new ImageIcon("src\\lk\\edu\\swlc\\snakea\\core\\assets\\snakea.png").getImage();
    }
/**
 * this method sets the main frame of the gameLogic
 */
    public void run() {
        this.frame = new JFrame("Game Snakea");
        int width = (this.game.getWidth() + 1) * sideLength + 10;
        int height = (this.game.getHeight() + 2) * sideLength + 10;
        this.frame.setPreferredSize(new Dimension(width, height));
        this.frame.setIconImage(this.frameLogo);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createComponents(this.frame.getContentPane());
        this.frame.pack();
        this.frame.setResizable(false);
        this.frame.setVisible(true);
        this.frame.setLocationRelativeTo(null);
        this.frame.setSize(625,700);
    }
/**
 * this method sets the components on the main frame:
 * 1. the board gameLogic
 * 2. the panel with the points information
 * @param container 
 */
    public void createComponents(Container container) {
        this.board = new PlayPanel(this.game, this.sideLength);
        this.board.setBorder(BorderFactory.createLineBorder(Color.RED));
        container.add(this.board);
        container.add(createPointsPanel(), BorderLayout.NORTH);
        this.frame.addKeyListener(new KeyValueListener(this.game.getSnake()));
    }
/**
 * this method contains the points board
 * @return 
 */
    public JPanel createPointsPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3));
        JLabel pointsName = new JLabel("Your Score - ", JLabel.CENTER);
        pointsName.setFont(new Font("Ink Free",Font.BOLD, 20));
        pointsName.setHorizontalAlignment(SwingConstants.CENTER);
        pointsName.setVerticalAlignment(SwingConstants.CENTER);
        JLabel empty = new JLabel("");
        this.label = new JLabel();
        this.label.setFont(new Font("Ink Free",Font.BOLD, 20));
        this.label.setText(Integer.toString(this.points));
        this.overLable = new JLabel();
        this.overLable.setFont(new Font("Ink Free",Font.BOLD, 20));
        this.overLable.setText("");
        panel.add(pointsName);
        panel.add(this.label);
        panel.setBackground(Color.yellow);
        panel.add(empty);
        panel.add(this.overLable);
        panel.setPreferredSize(new Dimension(-1, 50));
        return panel;
    }

    public JLabel gameOver(){
        this.overLable.setForeground(Color.RED);
        this.overLable.setText("Game Over!!!");
        return this.overLable;
    }

    public JLabel updateLabel() {
        this.label.setText(Integer.toString(this.game.getPoints()));
        return this.label;
    }

    public Updatable getUpdatable() {
        return this.board;
    }
}
