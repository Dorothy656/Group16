package main.java.view;

import main.java.model.Game;
import main.java.model.IslandTile;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * GameView 类：主界面窗口
 * 说明：
 * - 中间显示地图（由游戏类生成）
 * - 右侧显示玩家信息面板
 * - 底部显示操作按钮面板
 * - 所有地图由 Game 类 setup() 生成并传入 IslandMapPanel
 */
public class GameView extends JFrame {
    private IslandMapPanel islandMapPanel;
    private PlayerPanel playerPanel;
    private ActionPanel actionPanel;
    private Game game; // 游戏逻辑类实例

    public GameView() {
        super("Forbidden Island");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLayout(new BorderLayout());
        initGame();        // 初始化游戏逻辑
        initComponents();  // 初始化组件
        setLocationRelativeTo(null); // 窗口居中显示
        setVisible(true);
    }

    // 初始化游戏逻辑，生成地图瓦片
    private void initGame() {
        game = new Game();
        game.setup();  // 调用setup生成随机地图
    }

    // 初始化UI组件，并将瓦片传入地图面板
    private void initComponents() {
        islandMapPanel = new IslandMapPanel();
        List<IslandTile> tiles = game.getIslandTiles(); // 获取24张地图瓦片
        islandMapPanel.setTiles(tiles); // 传入地图面板

        playerPanel = new PlayerPanel();      // 右侧：玩家信息
        actionPanel = new ActionPanel();      // 底部：操作按钮

        add(islandMapPanel, BorderLayout.CENTER);
        add(playerPanel, BorderLayout.EAST);
        add(actionPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameView());
    }
}
