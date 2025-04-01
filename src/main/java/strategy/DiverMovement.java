package main.java.strategy;

import main.java.model.Player;
import main.java.model.IslandTile;

/**
 * DiverMovement 类：潜水员移动策略
 * TODO: 实现潜水员特殊移动逻辑，比如可以穿越水域等
 */
public class DiverMovement implements MovementStrategy {
    @Override
    public void move(Player player, IslandTile destination) {
        // TODO: 实现潜水员特殊移动逻辑
        System.out.println("Diver movement: moving " + player.getName() + " to " + destination.getName() + " with diving ability");
        player.move(destination);
    }
}
