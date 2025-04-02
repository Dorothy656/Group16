package main.java.view;

import main.java.model.IslandTile;
import main.java.model.Player;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

/**
 * IslandMapPanel 类：用于显示岛屿地图和玩家位置
 * 说明：
 * - 通过 setTiles() 设置 24 张岛屿瓦片，并在 paintComponent 中绘制
 * - 通过 setPlayers() 设置玩家列表，在地图上对应瓦片上绘制玩家的图标
 * - 此处采用简单的行列布局：瓦片分布为 6 行，瓦片数分别为 {2,4,6,6,4,2}，并根据该布局计算每个瓦片的绘制坐标
 */
public class IslandMapPanel extends JPanel {
    private List<IslandTile> tiles;
    private List<Player> players;
    private int tileSize = 90; // 每个瓦片 90*90 像素
    private int gap = 12;      // 每个瓦片之间间隔 12 像素

    // 每行瓦片数配置
    private int[] tilesPerRow = {2, 4, 6, 6, 4, 2};

    public void setTiles(List<IslandTile> tiles) {
        this.tiles = tiles;
        repaint();
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (tiles == null || tiles.size() == 0) return;

        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int numRows = tilesPerRow.length;
        int totalDrawingHeight = numRows * tileSize + (numRows - 1) * gap;
        int startY = (panelHeight - totalDrawingHeight) / 2;

        // 绘制岛屿瓦片：逐行绘制
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
                // 可以绘制边框（如需要）
                g.setColor(Color.BLACK);
                g.drawRect(x, y, tileSize, tileSize);
            }
        }

        if (players != null && tiles != null) {
            for (Player player : players) {
                // 获取玩家所在瓦片在 tiles 列表中的索引
                int index = tiles.indexOf(player.getPawn());
                if (index >= 0) {
                    // 根据 index 计算该瓦片的行、列以及绘制坐标
                    int row = 0;
                    int indexCounter = 0;
                    int tileX = 0, tileY = 0;
                    for (int r = 0; r < tilesPerRow.length; r++) {
                        int numTiles = tilesPerRow[r];
                        if (index < indexCounter + numTiles) {
                            int col = index - indexCounter;
                            int totalRowWidth = numTiles * tileSize + (numTiles - 1) * gap;
                            int startX = (panelWidth - totalRowWidth) / 2;
                            tileX = startX + col * (tileSize + gap);
                            tileY = startY + r * (tileSize + gap);
                            break;
                        }
                        indexCounter += numTiles;
                    }
                    // 获取玩家角色图标（使用 normal 状态图标），这里对图标进行缩放（示例缩小四分之一）
                    ImageIcon icon = player.getRoleObj().getNormalIcon();
                    int iconWidth = icon.getIconWidth() / 2 - 10;
                    int iconHeight = icon.getIconHeight() / 2 - 10;
                    int drawX = tileX + (tileSize - iconWidth) / 2;
                    int drawY = tileY + (tileSize - iconHeight) / 2;
                    g.drawImage(icon.getImage(), drawX, drawY, iconWidth, iconHeight, this);
                }
            }
        }
    }
}
