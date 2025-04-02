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
        // 尝试从 ImageIcon 的描述信息中获取文件名，并根据关键字确定宝藏类型
        String desc = image.getDescription();
        if (desc != null) {
            if (desc.contains("Crystal_of_Fire")) {
                this.treasureType = "Crystal of Fire";
            } else if (desc.contains("Earth_Stone")) {
                this.treasureType = "Earth Stone";
            } else if (desc.contains("Card_Oceans_Chalice")) {
                this.treasureType = "Ocean's Chalice";
            } else if (desc.contains("Statue_of_the_Wind")) {
                this.treasureType = "Statue of the Wind";
            } else if (desc.contains("Helicopter")) {
                this.treasureType = "Helicopter";
            } else if (desc.contains("Sand_Bag")) {
                this.treasureType = "Sand Bag";
            } else if (desc.contains("Waters_Rise")) {
                this.treasureType = "Waters Rise";
            } else {
                System.out.println("Unknown treasure type: " + desc);
            }
        } else {
            this.treasureType = "Default Treasure";
        }
    }

    @Override
    public String getType() {
        return "TreasureCard: " + treasureType;
    }

    public String getTreasureType() {
        return treasureType;
    }
}
