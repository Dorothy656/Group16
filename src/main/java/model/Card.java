package main.java.model;

import javax.swing.*;

/**
 * Card 类：卡牌基类
 * 说明：
 * - 表示所有卡牌的通用属性和方法
 */
public abstract class Card {
    protected ImageIcon image;  // 卡牌图片

    /**
     * 构造函数
     * @param image 卡牌图片
     */
    public Card(ImageIcon image) {
        this.image = image;
    }

    /**
     * 获取卡牌图片
     * @return ImageIcon 卡牌图片
     */
    public ImageIcon getImageIcon() {
        return image;
    }

    /**
     * 获取卡牌类型
     * @return String 卡牌类型
     */
    public abstract String getType();

    @Override
    public String toString() {
        return getType();
    }
}
