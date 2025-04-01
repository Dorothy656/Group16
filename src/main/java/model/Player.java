package main.java.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Player 类：表示游戏中的玩家
 * 说明：
 * - 包含玩家名称、角色、当前位置（pawn）以及手牌列表
 * - 提供抽卡、移动、加固、交换卡牌、获取宝藏等方法（部分功能用 TODO 标记待实现）
 */
public class Player {
    private String name;          // 玩家名称
    private String role;          // 玩家角色（例如 Explorer, Pilot 等）
    private IslandTile pawn;      // 玩家当前所在的岛屿瓦片
    private List<Card> hand;      // 玩家手牌列表

    /**
     * 构造函数
     * @param name 玩家名称
     * @param role 玩家角色
     * @param pawn 玩家初始位置
     */
    public Player(String name, String role, IslandTile pawn) {
        this.name = name;
        this.role = role;
        this.pawn = pawn;
        this.hand = new ArrayList<>();
    }

    /**
     * 将一张卡牌加入玩家手牌中
     * @param card 卡牌
     */
    public void addCard(Card card) {
        hand.add(card);
    }

    // TODO: 实现 move() 方法，移动玩家的棋子
    public void move(IslandTile destination) {
        // TODO: 根据游戏规则移动玩家
        this.pawn = destination;
    }

    // TODO: 实现 shoreUp() 方法，加固岛屿瓦片
    public void shoreUp(IslandTile tile) {
        // TODO: 实现加固逻辑
    }

    // TODO: 实现 giveCard() 方法，交换卡牌
    public void giveCard(Player target, Card card) {
        if (hand.contains(card)) {
            hand.remove(card);
            target.addCard(card);
        }
    }

    // TODO: 实现 captureTreasure() 方法，根据条件判断是否能捕获宝藏
    public boolean captureTreasure() {
        // TODO: 根据手牌和位置判断是否满足宝藏获取条件
        return false;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public IslandTile getPawn() {
        return pawn;
    }

    public List<Card> getHand() {
        return hand;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", pawn=" + pawn +
                ", hand=" + hand +
                '}';
    }
}
