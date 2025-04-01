package main.java.model;

import javax.swing.*;

/**
 * SpecialActionCard 类：表示特殊行动卡牌
 * 说明：
 * - 如 Helicopter Lift、Sandbags 等
 */
public class SpecialActionCard extends Card {
    private String actionType;  // 行动类型

    /**
     * 构造函数
     * @param image 卡牌图片
     * @param actionType 行动类型
     */
    public SpecialActionCard(ImageIcon image, String actionType) {
        super(image);
        this.actionType = actionType;
    }

    @Override
    public String getType() {
        return "SpecialActionCard: " + actionType;
    }

    public String getActionType() {
        return actionType;
    }
}
