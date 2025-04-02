package main.java.view;

import main.java.game.Game;
import main.java.model.Card;
import main.java.model.IslandTile;
import main.java.model.Player;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

public class GameView extends JFrame {
    private IslandMapPanel islandMapPanel;
    private JPanel leftPlayersPanel;   // 左侧玩家区域
    private JPanel roundPanel;         // 右侧显示当前回合玩家的面板
    private JPanel rightPanel;         // 右侧容器，包含 roundPanel 和水位计面板
    private JPanel bottomPanel;        // 底部新容器，包含三个栏位
    private ActionPanel actionPanel;   // 原操作按钮面板
    private Game game;                 // 游戏逻辑类实例

    public GameView() {
        super("Forbidden Island");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1400, 1000);

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

        // 将内容面板中的所有子组件设置为透明，使背景颜色得以显示
        setAllOpaqueFalse(getContentPane());

        setLocationRelativeTo(null); // 窗口居中显示
        setVisible(true);
    }

    // 初始化游戏逻辑
    private void initGame() {
        game = new Game();
        game.setup();
    }

    // 初始化各个界面组件
    private void initComponents() {
        // 中间：岛屿地图区域
        islandMapPanel = new IslandMapPanel();
        List<IslandTile> tiles = game.getIslandTiles();
        islandMapPanel.setTiles(tiles);
        add(islandMapPanel, BorderLayout.CENTER);
        List<Player> players = game.getPlayers();
        islandMapPanel.setPlayers(players);
        islandMapPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        // 左侧：玩家区域
        leftPlayersPanel = createLeftPlayersPanel();
        leftPlayersPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        add(leftPlayersPanel, BorderLayout.WEST);

        // 右侧：创建垂直容器，将当前回合玩家信息与水位计面板组合
        roundPanel = createRoundPanel();
        JPanel floodMeterPanel = createFloodMeterPanel();
        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        rightPanel.add(roundPanel);
        rightPanel.add(floodMeterPanel);
        rightPanel.setPreferredSize(new Dimension(220, rightPanel.getPreferredSize().height));
        add(rightPanel, BorderLayout.EAST);

        // 底部：创建三个栏位的底部容器，用于添加元素
        // 第1栏保留原来的 actionPanel，其它两栏预留后续使用
        actionPanel = new ActionPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        // 给每个底部子面板加上白色边框
        actionPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        panel2.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        panel3.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        // 可设置底部容器固定高度，例如150像素
        bottomPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        bottomPanel.setPreferredSize(new Dimension(getWidth(), 200));
        bottomPanel.add(actionPanel);
        bottomPanel.add(panel2);
        bottomPanel.add(panel3);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * createRoundPanel(): 创建一个面板，用于显示当前回合玩家的信息，
     * 格式为 "It's XXX's Round"。这里示例取第一个玩家的角色名称。
     */
    private JPanel createRoundPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(300, 100));
        JLabel label;
        if (game.getPlayers() != null && !game.getPlayers().isEmpty()) {
            // 示例：取第一个玩家的角色名称作为当前回合标识
            String roleName = game.getPlayers().get(0).getRole();
            label = new JLabel("<html>" + roleName + "'s<br>Round</html>", SwingConstants.CENTER);
        } else {
            label = new JLabel("No player", SwingConstants.CENTER);
        }
        label.setFont(new Font("Arial", Font.BOLD, 36));
        label.setForeground(Color.WHITE);
        panel.add(label, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        return panel;
    }

    // 创建左侧玩家区域
    private JPanel createLeftPlayersPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        int gap = 10;

        for (int i = 0; i <= 3; i++) {
            Player player = game.getPlayers().get(i);
            String roleName = player.getRole();
            ImageIcon roleIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                    "/images/Icons/RoleTable_Icon_" + roleName + "@2x.png")));
            Image originalImage = roleIcon.getImage();
            int newWidth = roleIcon.getIconWidth() / 5 * 2;
            int newHeight = roleIcon.getIconHeight() / 5 * 2;
            Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            String playerName = player.getName();
            JLabel playerLabel = new JLabel(playerName, scaledIcon, JLabel.LEFT);
            playerLabel.setFont(new Font("Arial", Font.BOLD, 16));
            playerLabel.setForeground(Color.WHITE);
            playerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel cardHolder = new JPanel();
            cardHolder.setLayout(new BoxLayout(cardHolder, BoxLayout.X_AXIS));
            int cardHolderWidth = 5 * 64 + 4 * gap;
            int cardHolderHeight = 90;
            Dimension cardHolderDim = new Dimension(cardHolderWidth, cardHolderHeight);
            cardHolder.setPreferredSize(cardHolderDim);
            cardHolder.setMaximumSize(cardHolderDim);
            cardHolder.setMinimumSize(cardHolderDim);

            // 遍历当前玩家手牌，如果有卡牌则显示，否则空白
            List<Card> hand = player.getHand();
            for (int j = 0; j < 5; j++) {
                JLabel placeholder = new JLabel();
                Dimension cardDim = new Dimension(64, 94);
                placeholder.setPreferredSize(cardDim);
                placeholder.setMaximumSize(cardDim);
                placeholder.setMinimumSize(cardDim);
                placeholder.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
                if (j < hand.size()) {
                    ImageIcon originalIcon = hand.get(j).getImageIcon();
                    int scaledWidth = originalIcon.getIconWidth() / 2;
                    int scaledHeight = originalIcon.getIconHeight() / 2;
                    Image scaledCard = originalIcon.getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
                    placeholder.setIcon(new ImageIcon(scaledCard));
                    placeholder.setHorizontalAlignment(SwingConstants.CENTER); // 居中对齐
                }

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


    // 创建水位计面板
    private JPanel createFloodMeterPanel() {
        ImageIcon waterLevelIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/FloodMeter/WaterLevelMeter.jpg")));
        Image originalImage = waterLevelIcon.getImage();
        int newWidth = waterLevelIcon.getIconWidth() / 2;
        int newHeight = waterLevelIcon.getIconHeight() / 2;
        Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLayeredPane layeredPane = new JLayeredPane();
        Dimension meterSize = new Dimension(newWidth, newHeight);
        layeredPane.setPreferredSize(meterSize);
        layeredPane.setBounds(0, 0, meterSize.width, meterSize.height);

        JLabel backgroundLabel = new JLabel(scaledIcon);
        backgroundLabel.setBounds(0, 0, meterSize.width, meterSize.height);
        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);

        ImageIcon arrowIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/FloodMeter/Water_Meter_Hand.png")));
        JLabel arrowLabel = new JLabel(arrowIcon);
        int arrowX = 50;
        int arrowY = 460 - game.getWaterLevel() * 58;
        arrowLabel.setBounds(arrowX, arrowY, arrowIcon.getIconWidth(), arrowIcon.getIconHeight());
        layeredPane.add(arrowLabel, JLayeredPane.PALETTE_LAYER);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        panel.add(layeredPane, BorderLayout.CENTER);
        return panel;
    }

    // 递归设置容器中所有组件为透明
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
