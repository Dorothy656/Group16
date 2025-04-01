package main.java.view;

import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends JPanel {
    private JTextArea playerInfoArea;

    public PlayerPanel() {
        setPreferredSize(new Dimension(200, 600));
        setLayout(new BorderLayout());

        playerInfoArea = new JTextArea();
        playerInfoArea.setEditable(false);
        playerInfoArea.setFont(new Font("SansSerif", Font.PLAIN, 20));
        add(new JScrollPane(playerInfoArea), BorderLayout.CENTER);

        // 初始显示示例信息，后续可通过updatePlayerInfo方法更新
        playerInfoArea.setForeground(Color.WHITE);
        playerInfoArea.setLineWrap(true);
        playerInfoArea.setWrapStyleWord(true);
        playerInfoArea.setText("XX's Round*****没写完 用updatePlayerInfo更新");
    }

    // 更新玩家信息的接口
    public void updatePlayerInfo(String info) {
        playerInfoArea.setText(info);
    }
}
