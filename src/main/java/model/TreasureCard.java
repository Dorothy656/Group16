package main.java.model;

import javax.swing.*;

/**
 * TreasureCard 类：表示宝藏卡牌
 * 说明：
 * - 包含宝藏类型（如 The Earth Stone、The Statue of the Wind 等）
 */
public class TreasureCard extends Card {
    private String treasureType;  // 宝藏类型

    /**
     * 构造函数
     * @param image 卡牌图片
     */
    public TreasureCard(ImageIcon image) {
        super(image);
        // TODO: 根据 image 或其他参数确定具体宝藏类型
        this.treasureType = "Default Treasure";
    }

    @Override
    public String getType() {
        return "TreasureCard: " + treasureType;
    }

    public String getTreasureType() {
        return treasureType;
    }
}
