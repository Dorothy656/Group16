package main.java.strategy;

import main.java.model.Player;
import main.java.model.IslandTile;

/**
 * NormalMovement 类：普通移动策略
 * TODO: 实现普通玩家的移动逻辑
 */
public class NormalMovement implements MovementStrategy {
    @Override
    public void move(Player player, IslandTile destination) {
        // TODO: 实现普通移动逻辑，比如简单调用玩家的 move 方法
        System.out.println("Normal movement: moving " + player.getName() + " to " + destination.getName());
        player.move(destination);
    }
}
