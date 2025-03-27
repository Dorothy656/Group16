package main.java.view;

import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends JPanel {
    private JTextArea playerInfoArea;

    public PlayerPanel() {
        setPreferredSize(new Dimension(200, 600));
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        playerInfoArea = new JTextArea();
        playerInfoArea.setEditable(false);
        playerInfoArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        add(new JScrollPane(playerInfoArea), BorderLayout.CENTER);

        // 初始显示示例信息，后续可通过updatePlayerInfo方法更新
        playerInfoArea.setText("玩家信息显示区域\n- 玩家1: Explorer\n- 玩家2: Pilot\n...");
    }

    // 更新玩家信息的接口
    public void updatePlayerInfo(String info) {
        playerInfoArea.setText(info);
    }
}
