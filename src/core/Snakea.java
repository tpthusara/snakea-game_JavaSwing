package core;

import javax.swing.*;
import java.awt.*;

public class Snakea extends JFrame {

    public Snakea() {

        initUI();
    }

    private void initUI() {

        add(new SnakeBoard());

        setResizable(false);
        pack();

        setTitle("Snakea-Game");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            JFrame ex = new Snakea();
            ex.setVisible(true);
        });
    }
}
