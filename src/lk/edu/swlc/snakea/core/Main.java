package lk.edu.swlc.snakea.core;

import lk.edu.swlc.snakea.core.game.SnakeGame;
import lk.edu.swlc.snakea.core.view.SnakeUI;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SnakeGame game = new SnakeGame(30, 30);

        SnakeUI ui = new SnakeUI(game, 20);
        SwingUtilities.invokeLater(ui);

        while (ui.getUpdatable() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("The drawing board hasn't been created yet.");
            }
        }
        game.setUpdatable(ui.getUpdatable());
        game.setUI(ui);
        game.start();
    }
}
