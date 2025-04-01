package main.java.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Player 类：表示游戏中的玩家
 * 说明：
 * - 增加了 Role 属性，用于表示玩家的角色
 */
public class Player {
    private String name;          // 玩家名称
    private Role role;            // 玩家角色
    private IslandTile pawn;      // 玩家当前位置
    private List<Card> hand;      // 玩家手牌列表

    /**
     * 构造函数
     * @param name 玩家名称
     * @param role 玩家角色
     * @param pawn 玩家初始位置
     */
    public Player(String name, Role role, IslandTile pawn) {
        this.name = name;
        this.role = role;
        this.pawn = pawn;
        this.hand = new ArrayList<>();
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public String getName() {
        return name;
    }

    // 返回角色名称
    public String getRole() {
        return role.getRoleName();
    }

    // 返回角色对象，用于获取图标
    public Role getRoleObj() {
        return role;
    }

    public IslandTile getPawn() {
        return pawn;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void move(IslandTile destination) {
        this.pawn = destination;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", role=" + role +
                ", pawn=" + pawn +
                ", hand=" + hand +
                '}';
    }
}
