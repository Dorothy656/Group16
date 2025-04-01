package main.java.strategy;

import main.java.model.Player;
import main.java.model.IslandTile;

/**
 * MovementStrategy 接口：定义玩家移动策略
 * TODO: 根据游戏规则扩展移动逻辑参数和返回值
 */
public interface MovementStrategy {
    /**
     * 将玩家移动到目标岛屿瓦片
     * @param player 当前玩家
     * @param destination 目标岛屿瓦片
     */
    void move(Player player, IslandTile destination);
}
