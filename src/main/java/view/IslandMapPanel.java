package main.java.view;

import main.java.model.IslandTile;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * IslandMapPanel 类：用于根据给定的岛屿瓦片绘制地图（带图片）
 */
public class IslandMapPanel extends JPanel {
    private List<IslandTile> tiles;  // 当前要绘制的岛屿瓦片列表

    public void setTiles(List<IslandTile> tiles) {
        this.tiles = tiles;
        repaint();  // 更新界面
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (tiles == null || tiles.size() == 0) return;

        // 每行瓦片数配置
        int[] tilesPerRow = {2, 4, 6, 6, 4, 2};
        int tileSize = 90;
        int gap = 12;

        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int numRows = tilesPerRow.length;
        int totalDrawingHeight = numRows * tileSize + (numRows - 1) * gap;
        int startY = (panelHeight - totalDrawingHeight) / 2;

        int tileIndex = 0;
        for (int row = 0; row < numRows; row++) {
            int numTiles = tilesPerRow[row];
            int totalRowWidth = numTiles * tileSize + (numTiles - 1) * gap;
            int startX = (panelWidth - totalRowWidth) / 2;
            int y = startY + row * (tileSize + gap);

            for (int col = 0; col < numTiles; col++) {
                if (tileIndex >= tiles.size()) return;
                IslandTile tile = tiles.get(tileIndex++);
                int x = startX + col * (tileSize + gap);

                ImageIcon icon = tile.getTileImage();
                if (icon != null) {
                    g.drawImage(icon.getImage(), x, y, tileSize, tileSize, this);
                } else {
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(x, y, tileSize, tileSize);
                }

            }
        }
    }
}
