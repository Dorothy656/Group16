package main.java.view;

import javax.swing.*;
import java.awt.*;

/**
 * IslandMapPanel 类：绘制自定义岛屿布局
 * 说明：
 * - 布局为六行方块，方块数量依次为：2, 4, 6, 6, 4, 2
 * - 整体绘图在面板中央显示（水平和垂直居中）
 * - 每个方块尺寸增大，同时在方块之间增加间距
 */
public class IslandMapPanel extends JPanel {
    private Image backgroundImage;

    public IslandMapPanel() {
        setPreferredSize(new Dimension(1000, 500));
        backgroundImage = new ImageIcon(getClass().getResource("/images/Back_Game.jpg")).getImage();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        // 定义每个方块的尺寸和间距（单位：像素）
        int tileSize = 70;   // 方块尺寸增大到70像素
        int gap = 10;        // 方块间距为10像素

        // 每行方块数量依次为：2, 4, 6, 6, 4, 2
        int[] tilesPerRow = {2, 4, 6, 6, 4, 2};
        int numRows = tilesPerRow.length;  // 总行数为6

        // 计算整个岛屿绘图的总高度（包括间距）
        int totalDrawingHeight = numRows * tileSize + (numRows - 1) * gap;
        int panelHeight = getHeight();
        int panelWidth = getWidth();

        // 计算绘图在面板中垂直居中的起始Y坐标
        int startY = (panelHeight - totalDrawingHeight) / 2;

        // 遍历每一行进行绘制
        for (int row = 0; row < numRows; row++) {
            int numTiles = tilesPerRow[row];  // 当前行方块数量
            // 计算当前行总宽度 = (方块宽度 * 数量) + (间距 * (数量 - 1))
            int totalRowWidth = numTiles * tileSize + (numTiles - 1) * gap;
            // 计算当前行在面板中水平居中的起始X坐标
            int startX = (panelWidth - totalRowWidth) / 2;
            // 当前行的Y坐标 = 起始Y + 行号 * (方块尺寸 + 间距)
            int y = startY + row * (tileSize + gap);

            // 遍历当前行中的每个方块
            for (int col = 0; col < numTiles; col++) {
                // 当前方块的X坐标 = 起始X + 列号 * (方块尺寸 + 间距)
                int x = startX + col * (tileSize + gap);
                // 绘制白色填充的方块
                g.setColor(Color.WHITE);
                g.fillRect(x, y, tileSize, tileSize);
                // 绘制黑色边框
                g.setColor(Color.BLACK);
                g.drawRect(x, y, tileSize, tileSize);
            }
        }
    }
}
