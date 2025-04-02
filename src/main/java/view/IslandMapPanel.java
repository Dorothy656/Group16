package main.java.view;

import main.java.model.IslandTile;
import main.java.model.Player;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

public class IslandMapPanel extends JPanel {
    private List<IslandTile> tiles;
    private List<Player> players;
    private int tileSize = 90; // 每个瓦片90*90像素
    private int gap = 12;      // 间隔12像素
    // 假设瓦片布局：6行，瓦片数分别为{2,4,6,6,4,2}

    // 模拟宝藏状态，未来可由游戏逻辑动态更新
    private boolean earthStoneCaptured = false;
    private boolean oceanChaliceCaptured = false;
    private boolean crystalFireCaptured = false;
    private boolean statueWindCaptured = false;

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
        int[] tilesPerRow = {2, 4, 6, 6, 4, 2};
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
                // 可以绘制边框
                g.setColor(Color.BLACK);
                g.drawRect(x, y, tileSize, tileSize);
            }
        }

        // 绘制玩家图标，计算玩家所在瓦片的中心位置
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

        // 绘制四个宝藏图标，若宝藏被取走则显示 captured 图标
        String earthStonePath = earthStoneCaptured ? "/images/Icons/Treasure_Icon_Earth_Stone_captured@2x.png"
                : "/images/Icons/Treasure_Icon_Earth_Stone@2x.png";
        String oceanChalicePath = oceanChaliceCaptured ? "/images/Icons/Treasure_Icon_Ocean_s_Chalice_captured@2x.png"
                : "/images/Icons/Treasure_Icon_Ocean_s_Chalice@2x.png";
        String crystalFirePath = crystalFireCaptured ? "/images/Icons/Treasure_Icon_Crystal_of_Fire_captured@2x.png"
                : "/images/Icons/Treasure_Icon_Crystal_of_Fire@2x.png";
        String statueWindPath = statueWindCaptured ? "/images/Icons/Treasure_Icon_Statue_of_the_Wind_captured@2x.png"
                : "/images/Icons/Treasure_Icon_Statue_of_the_Wind@2x.png";

        ImageIcon earthStoneIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(earthStonePath)));
        ImageIcon oceanChaliceIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(oceanChalicePath)));
        ImageIcon crystalFireIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(crystalFirePath)));
        ImageIcon statueWindIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(statueWindPath)));

        int w1 = earthStoneIcon.getIconWidth(), h1 = earthStoneIcon.getIconHeight();
        int w2 = oceanChaliceIcon.getIconWidth(), h2 = oceanChaliceIcon.getIconHeight();
        int w3 = crystalFireIcon.getIconWidth(), h3 = crystalFireIcon.getIconHeight();
        int w4 = statueWindIcon.getIconWidth(), h4 = statueWindIcon.getIconHeight();

        // 绘制宝藏图标在四个角落
        // 左上角：Earth Stone
        g.drawImage(earthStoneIcon.getImage(), 0, 0, w1, h1, this);
        // 右上角：Ocean's Chalice
        g.drawImage(oceanChaliceIcon.getImage(), getWidth() - w2, 0, w2, h2, this);
        // 左下角：Crystal of Fire
        g.drawImage(crystalFireIcon.getImage(), 0, getHeight() - h3, w3, h3, this);
        // 右下角：Statue of the Wind
        g.drawImage(statueWindIcon.getImage(), getWidth() - w4, getHeight() - h4, w4, h4, this);
    }
}
