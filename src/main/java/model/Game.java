package main.java.model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Game 类：游戏逻辑核心类
 * 说明：
 * - 在 setup() 方法中初始化游戏，包括加载随机地图并创建岛屿瓦片
 */
public class Game {
    private List<Player> players;        // 玩家列表
    private List<IslandTile> islandTiles;  // 岛屿瓦片列表
    // 其他属性，如 floodDeck、treasureDeck、waterLevel 等

    public Game() {
        players = new ArrayList<>();
        islandTiles = new ArrayList<>();
    }

    /**
     * 初始化游戏：设置岛屿瓦片、洗牌、发牌等
     */
    public void setup() {
        // 调用 MapLoader 随机加载24张地图图片
        List<ImageIcon> mapIcons = MapLoader.loadRandomMaps();
        islandTiles.clear();
        // 假设岛屿瓦片总数为24
        for (int i = 0; i < 24; i++) {
            String tileName = "Island " + (i + 1); // 瓦片名称
            // 获取对应的地图图片
            ImageIcon tileIcon = mapIcons.get(i);
            // 创建 IslandTile 对象，并加入列表
            IslandTile tile = new IslandTile(tileName, tileIcon);
            islandTiles.add(tile);
        }
        // 其他初始化操作……

    }

    public List<IslandTile> getIslandTiles() {
        return islandTiles;
    }

    public void setIslandTiles(List<IslandTile> islandTiles) {
        this.islandTiles = islandTiles;
    }

    // 以下为 Game 类其他方法，如 drawFloodCards(), drawTreasureCards() 等
}
