package main.java.test;

import main.java.model.TreasureCardLoader;
import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * TreasureCardTestFrame 类：测试窗口，用于显示随机生成的宝藏卡牌
 * 说明：
 * - 从主程序的 TreasureCardLoader 加载宝藏卡牌（ImageIcon列表）
 * - 使用 FlowLayout 将宝藏卡牌依照随机顺序顺次显示在窗口中
 * - 用于测试宝藏卡牌是否正确生成
 */
public class TreasureCardTestFrame extends JFrame {
    public TreasureCardTestFrame() {
        super("Treasure Cards Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800); // 设置窗口大小
        setLocationRelativeTo(null); // 窗口居中显示

        // 创建一个面板，使用 FlowLayout 顺次排列宝藏卡牌
        JPanel cardPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        cardPanel.setBackground(Color.DARK_GRAY);

        // 调用 TreasureCardLoader 加载随机宝藏卡牌列表（例如20张）
        List<ImageIcon> treasureIcons = TreasureCardLoader.loadRandomTreasure();

        // 依次将每个宝藏卡牌添加到面板中
        for (ImageIcon icon : treasureIcons) {
            JLabel cardLabel = new JLabel(icon);
            cardPanel.add(cardLabel);
        }

        // 使用 JScrollPane 使卡牌面板可以滚动查看
        JScrollPane scrollPane = new JScrollPane(cardPanel);
        add(scrollPane);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TreasureCardTestFrame());
    }
}
