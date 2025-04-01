package main.java.view;

import main.java.model.Game;
import main.java.model.IslandTile;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

/**
 * GameView 类：主界面窗口
 * 说明：
 * - 中间显示地图（由 Game 类 setup() 生成的 IslandMapPanel）
 * - 左侧显示玩家区域，每个玩家面板上方显示 "Player i"，下方横向排列的手牌占位区域（每个占位固定尺寸 64×94）
 * - 右侧显示一个垂直容器，包含原有的个人信息窗口（PlayerPanel）和水位计面板（水位计图片）
 * - 底部显示操作按钮面板（ActionPanel）
 * - 全局背景颜色统一在内容面板设置为 new Color(45,53,60)
 */
public class GameView extends JFrame {
    private IslandMapPanel islandMapPanel;
    private JPanel leftPlayersPanel;   // 左侧玩家区域
    private PlayerPanel playerPanel;   // 个人信息窗口（原右侧）
    private JPanel rightPanel;         // 右侧容器，包含 playerPanel 和水位计面板
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
        initComponents(); // 初始化界面组件

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

        // 右侧：创建垂直容器，将原有的个人信息窗口与水位计面板组合
        playerPanel = new PlayerPanel();
        JPanel floodMeterPanel = new JPanel();
        floodMeterPanel.setOpaque(false);
        // 加载水位计图片（确保资源路径正确，并将 src/main/resources 标记为资源根目录）
        ImageIcon waterLevelIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/FloodMeter/WaterLevelMeter.jpg")));
        Image originalImage = waterLevelIcon.getImage();
        int newWidth = waterLevelIcon.getIconWidth() / 2;
        int newHeight = waterLevelIcon.getIconHeight() / 2;
        Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel waterLevelLabel = new JLabel(scaledIcon);
        waterLevelLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        floodMeterPanel.add(waterLevelLabel);
        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setOpaque(false);
        rightPanel.add(playerPanel);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        rightPanel.add(floodMeterPanel);
        add(rightPanel, BorderLayout.EAST);

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
        // 默认继承全局背景颜色

        // 假设共有 4 个玩家
        for (int i = 1; i <= 4; i++) {
            JLabel playerLabel = new JLabel("Player " + i, SwingConstants.CENTER);
            playerLabel.setFont(new Font("Arial", Font.BOLD, 16));
            playerLabel.setForeground(Color.WHITE);
            playerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel cardHolder = new JPanel();
            cardHolder.setLayout(new BoxLayout(cardHolder, BoxLayout.X_AXIS));
            // 固定手牌占位区域尺寸：宽度 = 5*64 + 4*5, 高度 = 94
            int gap = 5;
            int cardHolderWidth = 5 * 64 + 4 * gap;
            int cardHolderHeight = 94;
            Dimension cardHolderDim = new Dimension(cardHolderWidth, cardHolderHeight);
            cardHolder.setPreferredSize(cardHolderDim);
            cardHolder.setMaximumSize(cardHolderDim);
            cardHolder.setMinimumSize(cardHolderDim);

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

    // 递归设置容器中所有组件为透明，使其继承父容器背景颜色
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
