package main.java.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Deck 类：卡牌牌堆泛型类
 * 说明：
 * - 用于存储和管理卡牌
 * - 提供添加卡牌、抽卡、洗牌等功能
 */
public class Deck<T extends Card> {
    private List<T> cards;

    public Deck() {
        cards = new ArrayList<>();
    }

    /**
     * 添加一张卡牌到牌堆
     * @param card 卡牌
     */
    public void addCard(T card) {
        cards.add(card);
    }

    /**
     * 从牌堆中抽取一张卡牌
     * @return T 抽取的卡牌，如果牌堆为空则返回 null
     */
    public T draw() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(0);
    }

    /**
     * 洗牌
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    public int size() {
        return cards.size();
    }

    public List<T> getCards() {
        return cards;
    }
}
