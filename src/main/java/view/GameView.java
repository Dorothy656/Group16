package main.java.view;

import main.java.model.Game;
import main.java.model.IslandTile;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * GameView 类：主界面窗口
 * 说明：
 * - 中间显示地图（由 Game 类 setup() 生成的 IslandMapPanel）
 * - 左侧显示玩家区域，每个玩家面板上方显示 "Player i"，下方横向排列的手牌占位区域（每个占位固定尺寸 64×94）
 * - 右侧保留原有的个人信息窗口（PlayerPanel）
 * - 底部显示操作按钮面板（ActionPanel）
 * - 全局背景颜色统一设置为 new Color(45,53,60)，并通过递归设置所有子组件透明以显示该背景
 */
public class GameView extends JFrame {
    private IslandMapPanel islandMapPanel;
    private JPanel leftPlayersPanel;   // 左侧玩家区域
    private PlayerPanel playerPanel;   // 右侧个人信息窗口
    private ActionPanel actionPanel;   // 底部操作按钮
    private Game game;                 // 游戏逻辑类实例

    public GameView() {
        super("Forbidden Island");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1600, 900);

        // 使用自定义内容面板统一绘制背景颜色
        JPanel contentPane = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(45, 53, 60));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        setContentPane(contentPane);
        setLayout(new BorderLayout());

        initGame();       // 初始化游戏逻辑
        initComponents(); // 初始化各个界面组件

        // 将内容面板中的所有子组件设置为透明，使背景颜色得以显示
        setAllOpaqueFalse(getContentPane());

        setLocationRelativeTo(null); // 窗口居中显示
        setVisible(true);
    }

    // 初始化游戏逻辑，生成随机地图瓦片
    private void initGame() {
        game = new Game();
        game.setup();  // 调用 setup() 生成随机地图瓦片
    }

    // 初始化各个界面组件
    private void initComponents() {
        // 中间：岛屿地图区域
        islandMapPanel = new IslandMapPanel();
        List<IslandTile> tiles = game.getIslandTiles(); // 获取24张地图瓦片
        islandMapPanel.setTiles(tiles); // 将瓦片传入地图面板
        add(islandMapPanel, BorderLayout.CENTER);

        // 左侧：玩家区域，每个玩家面板包含上方显示 "Player i" 标签和下方横向排列的手牌占位区域
        leftPlayersPanel = createLeftPlayersPanel();
        add(leftPlayersPanel, BorderLayout.WEST);

        // 右侧：个人信息窗口
        playerPanel = new PlayerPanel();
        add(playerPanel, BorderLayout.EAST);

        // 底部：操作按钮面板
        actionPanel = new ActionPanel();
        add(actionPanel, BorderLayout.SOUTH);
    }

    /**
     * createLeftPlayersPanel(): 创建左侧玩家区域
     * 每个玩家面板包含：
     * - 上方显示 "Player i" 标签
     * - 下方横向排列的 5 个手牌占位，固定尺寸为 64×94（宽×高）
     */
    private JPanel createLeftPlayersPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // 不再单独设置背景颜色，使用全局背景

        // 假设共有 4 个玩家
        for (int i = 1; i <= 4; i++) {
            // 创建显示 "Player i" 的标签
            JLabel playerLabel = new JLabel("Player " + i, SwingConstants.CENTER);
            playerLabel.setFont(new Font("Arial", Font.BOLD, 16));
            playerLabel.setForeground(Color.WHITE);
            playerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            // 创建手牌占位区域，采用 BoxLayout 横向排列
            JPanel cardHolder = new JPanel();
            cardHolder.setLayout(new BoxLayout(cardHolder, BoxLayout.X_AXIS));
            // 固定手牌占位区域尺寸：宽度 = 5*64 + 4*gap, 高度 = 94
            int gap = 5;
            int cardHolderWidth = 5 * 64 + 4 * gap;
            int cardHolderHeight = 94;
            Dimension cardHolderDim = new Dimension(cardHolderWidth, cardHolderHeight);
            cardHolder.setPreferredSize(cardHolderDim);
            cardHolder.setMaximumSize(cardHolderDim);
            cardHolder.setMinimumSize(cardHolderDim);

            // 依次添加 5 个卡牌占位组件，尺寸为 64×94
            for (int j = 0; j < 5; j++) {
                JLabel placeholder = new JLabel();
                Dimension cardDim = new Dimension(64, 94);
                placeholder.setPreferredSize(cardDim);
                placeholder.setMaximumSize(cardDim);
                placeholder.setMinimumSize(cardDim);
                placeholder.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
                cardHolder.add(placeholder);
                if (j < 4) {
                    cardHolder.add(Box.createRigidArea(new Dimension(gap, 0)));
                }
            }

            // 单个玩家面板，采用垂直 BoxLayout，将 "Player i" 标签放在上方，手牌占位区域放在下方
            JPanel singlePlayerPanel = new JPanel();
            singlePlayerPanel.setLayout(new BoxLayout(singlePlayerPanel, BoxLayout.Y_AXIS));
            singlePlayerPanel.setBorder(BorderFactory.createTitledBorder("Player " + i));
            singlePlayerPanel.add(playerLabel);
            singlePlayerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            singlePlayerPanel.add(cardHolder);

            panel.add(singlePlayerPanel);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        return panel;
    }

    // 递归设置容器中所有组件为透明，使其继承父容器的背景颜色
    private void setAllOpaqueFalse(Container container) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JComponent) {
                ((JComponent) comp).setOpaque(false);
            }
            if (comp instanceof Container) {
                setAllOpaqueFalse((Container) comp);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameView::new);
    }
}
