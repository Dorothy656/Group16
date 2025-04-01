package main.java.strategy;

import main.java.model.Player;
import main.java.model.IslandTile;

/**
 * PilotMovement 类：飞行员移动策略
 * TODO: 实现飞行员特殊移动逻辑，比如可以飞行到任意岛屿
 */
public class PilotMovement implements MovementStrategy {
    @Override
    public void move(Player player, IslandTile destination) {
        // TODO: 实现飞行员特殊移动逻辑
        System.out.println("Pilot movement: flying " + player.getName() + " to " + destination.getName());
        player.move(destination);
    }
}
