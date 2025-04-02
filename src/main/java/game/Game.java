package main.java.game;

import main.java.model.*;
import main.java.model.Deck;
import main.java.model.TreasureCardLoader;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Game 类：游戏逻辑核心类
 * 说明：
 * - 在 setup() 方法中初始化游戏，包括加载随机地图并创建 24 张岛屿瓦片、
 *   初始化玩家（从六个角色中随机抽取四个角色，各自的起始位置不同）以及牌堆初始化（宝藏卡牌等）
 * - 提供 movePlayer() 方法作为玩家移动的接口
 */
public class Game {
    private List<Player> players;           // 玩家列表
    private List<IslandTile> islandTiles;     // 岛屿瓦片列表
    private Deck<Card> treasureDeck;          // 宝藏卡牌牌堆
    private int waterLevel;                   // 当前水位

    public Game() {
        players = new ArrayList<>();
        islandTiles = new ArrayList<>();
        waterLevel = 1;  // 默认初始水位
    }

    /**
     * setup() 方法：初始化游戏逻辑
     * 包括：
     * - 加载随机地图并创建 24 张岛屿瓦片
     * - 初始化 4 个玩家，每个玩家起始位置不同，且角色从 6 个候选角色中随机抽取
     * - 初始化宝藏牌堆，并为每个玩家抽取 2 张卡牌
     */
    public void setup() {
        initIslandTiles();
        initPlayers();
        initDecks();
        // TODO: 根据需求初始化其他牌堆或组件
    }

    /**
     * initIslandTiles() 方法：加载随机地图并创建 24 张岛屿瓦片
     */
    private void initIslandTiles() {
        List<ImageIcon> mapIcons = MapLoader.loadRandomMaps();
        islandTiles.clear();
        waterLevel = 0; // 根据需求设置起始水位，此处示例为 0
        for (int i = 0; i < 24; i++) {
            String tileName = "Island " + (i + 1);
            ImageIcon tileIcon = mapIcons.get(i);
            IslandTile tile = new IslandTile(tileName, tileIcon);
            islandTiles.add(tile);
        }
    }

    /**
     * initPlayers() 方法：初始化玩家列表
     * 示例中从 6 个角色中随机抽取 4 个角色
     */
    private void initPlayers() {
        players.clear();
        // 定义6个角色
        List<Role> availableRoles = new ArrayList<>();
        availableRoles.add(new Role("Diver", "/images/Icons/Diver_Adventurer_Icon@2x.png",
                "/images/Icons/Diver_Adventurer_Icon_active@2x.png",
                "/images/Icons/Diver_Adventurer_Icon_give@2x.png",
                "/images/Icons/Diver_Adventurer_Icon_move@2x.png",
                "/images/Icons/Diver_Adventurer_IconSelect@2x.png"));
        availableRoles.add(new Role("Engineer", "/images/Icons/Engineer_Adventurer_Icon@2x.png",
                "/images/Icons/Engineer_Adventurer_Icon_active@2x.png",
                "/images/Icons/Engineer_Adventurer_Icon_give@2x.png",
                "/images/Icons/Engineer_Adventurer_Icon_move@2x.png",
                "/images/Icons/Engineer_Adventurer_IconSelect@2x.png"));
        availableRoles.add(new Role("Explorer", "/images/Icons/Explorer_Adventurer_Icon@2x.png",
                "/images/Icons/Explorer_Adventurer_Icon_active@2x.png",
                "/images/Icons/Explorer_Adventurer_Icon_give@2x.png",
                "/images/Icons/Explorer_Adventurer_Icon_move@2x.png",
                "/images/Icons/Explorer_Adventurer_IconSelect@2x.png"));
        availableRoles.add(new Role("Navigator", "/images/Icons/Navigator_Adventurer_Icon@2x.png",
                "/images/Icons/Navigator_Adventurer_Icon_active@2x.png",
                "/images/Icons/Navigator_Adventurer_Icon_give@2x.png",
                "/images/Icons/Navigator_Adventurer_Icon_move@2x.png",
                "/images/Icons/Navigator_Adventurer_IconSelect@2x.png"));
        availableRoles.add(new Role("Pilot", "/images/Icons/Pilot_Adventurer_Icon@2x.png",
                "/images/Icons/Pilot_Adventurer_Icon_active@2x.png",
                "/images/Icons/Pilot_Adventurer_Icon_give@2x.png",
                "/images/Icons/Pilot_Adventurer_Icon_move@2x.png",
                "/images/Icons/Pilot_Adventurer_IconSelect@2x.png"));
        availableRoles.add(new Role("Messenger", "/images/Icons/Messenger_Adventurer_Icon@2x.png",
                "/images/Icons/Messenger_Adventurer_Icon_active@2x.png",
                "/images/Icons/Messenger_Adventurer_Icon_give@2x.png",
                "/images/Icons/Messenger_Adventurer_Icon_move@2x.png",
                "/images/Icons/Messenger_Adventurer_IconSelect@2x.png"));
        // 随机抽取 4 个角色
        Collections.shuffle(availableRoles);
        List<Role> selectedRoles = availableRoles.subList(0, 4);
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < islandTiles.size(); i++) {
            indices.add(i);
        }
        Collections.shuffle(indices); // 随机打乱岛屿索引
        for (int i = 0; i < 4; i++) {
            int startIndex = indices.get(i);
            Player player = new Player("Player " + (i + 1), selectedRoles.get(i), islandTiles.get(startIndex));
            players.add(player);
        }
        // 输出初始位置和角色
        for (Player p : players) {
            System.out.println(p.getName() + " (" + p.getRole() + ") starts at " + p.getPawn().getName());
        }
    }

    /**
     * initDecks() 方法：初始化宝藏卡牌牌堆
     * 说明：
     * - 通过 TreasureCardLoader 加载宝藏卡牌图片列表，构造 TreasureCard 对象加入牌堆，并洗牌
     * - 为每个玩家抽取初始 2 张宝藏卡牌
     */
    private void initDecks() {
        treasureDeck = new Deck<>();
        List<ImageIcon> treasureIcons = TreasureCardLoader.loadRandomTreasure();
        for (ImageIcon icon : treasureIcons) {
            Card treasureCard = new TreasureCard(icon);
            treasureDeck.addCard(treasureCard);
        }
        treasureDeck.shuffle();
        for (Player player : players) {
            drawInitialCards(player);
        }
        for (Player player : players) {
            System.out.println(player.getName() + " 的手牌：");
            for (Card card : player.getHand()) {
                System.out.println("   " + card.toString());
            }
        }
    }

    /**
     * drawInitialCards() 方法：为每个玩家抽取 2 张宝藏卡牌
     */
    private void drawInitialCards(Player player) {
        for (int i = 0; i < 2; i++) {
            Card card = treasureDeck.draw();
            if (card != null) {
                player.addCard(card);
            }
        }
    }


    /**
     * movePlayer() 方法：为指定玩家移动到目标岛屿
     * 说明：
     * - 为后续玩家移动提供接口，可在此加入额外的检查逻辑
     * @param player 要移动的玩家
     * @param destination 目标岛屿瓦片
     */
    public void movePlayer(Player player, IslandTile destination) {
        // TODO: 添加移动合法性检查、事件触发等逻辑
        player.move(destination);
        System.out.println(player.getName() + " moved to " + destination.getName());
    }

    public int getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(int level) {
        this.waterLevel = level;
    }

    public void increaseWaterLevel() {
        waterLevel++;
    }

    public List<IslandTile> getIslandTiles() {
        return islandTiles;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
