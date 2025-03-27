package main.java.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionPanel extends JPanel {
    private JButton moveButton;
    private JButton shoreUpButton;
    private JButton exchangeCardButton;
    private JButton captureTreasureButton;

    public ActionPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        moveButton = new JButton("移动");
        shoreUpButton = new JButton("加固");
        exchangeCardButton = new JButton("交换卡牌");
        captureTreasureButton = new JButton("获取宝藏");

        add(moveButton);
        add(shoreUpButton);
        add(exchangeCardButton);
        add(captureTreasureButton);

        // 简单事件监听器，后续可与控制器联动实现具体逻辑
        moveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(ActionPanel.this, "移动操作被触发");
            }
        });

        shoreUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(ActionPanel.this, "加固操作被触发");
            }
        });

        exchangeCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(ActionPanel.this, "交换卡牌操作被触发");
            }
        });

        captureTreasureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(ActionPanel.this, "获取宝藏操作被触发");
            }
        });
    }
}
