package main.java.view;

import javax.swing.*;
import java.awt.*;

public class IslandMapPanel extends JPanel {

    public IslandMapPanel() {
        setPreferredSize(new Dimension(600, 600));
        setBackground(Color.LIGHT_GRAY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int tileSize = 50;
        int cols = getWidth() / tileSize;
        int rows = getHeight() / tileSize;
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                g.setColor(Color.WHITE);
                g.fillRect(i * tileSize, j * tileSize, tileSize, tileSize);
                g.setColor(Color.BLACK);
                g.drawRect(i * tileSize, j * tileSize, tileSize, tileSize);
            }
        }
    }
}
