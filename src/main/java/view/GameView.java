package main.java.view;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    private IslandMapPanel islandMapPanel;
    private PlayerPanel playerPanel;
    private ActionPanel actionPanel;

    public GameView() {
        super("Forbidden Island");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLayout(new BorderLayout());
        initComponents();
        setLocationRelativeTo(null); // 窗口居中显示
        setVisible(true);
    }

    private void initComponents() {
        islandMapPanel = new IslandMapPanel();  // 中央显示岛屿地图
        playerPanel = new PlayerPanel();          // 右侧显示玩家信息
        actionPanel = new ActionPanel();          // 底部显示操作按钮

        add(islandMapPanel, BorderLayout.CENTER);
        add(playerPanel, BorderLayout.EAST);
        add(actionPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameView());
    }
}
