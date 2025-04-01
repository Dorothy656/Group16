package main.java.model;

import javax.swing.*;

/**
 * FloodCard 类：表示洪水卡牌
 * 说明：
 * - 包含对应的岛屿瓦片信息
 */
public class FloodCard extends Card {
    private IslandTile associatedTile;  // 对应的岛屿瓦片

    /**
     * 构造函数
     * @param image 卡牌图片
     * @param tile  对应的岛屿瓦片
     */
    public FloodCard(ImageIcon image, IslandTile tile) {
        super(image);
        this.associatedTile = tile;
    }

    @Override
    public String getType() {
        return "FloodCard";
    }

    public IslandTile getAssociatedTile() {
        return associatedTile;
    }
}
