package main.java.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * CoverPage 类：显示封面窗口
 */
public class CoverPage extends JFrame {
    public CoverPage() {
        setTitle("Forbidden Island"); // 设置窗口标题
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 530); // 设置窗口分辨率为 1000x530
        setLocationRelativeTo(null); // 窗口居中显示

        // 创建背景标签并加载背景图片
        // 背景图片应放置在资源目录下，如：/images/coverBackground.jpg
        ImageIcon bgIcon = new ImageIcon(getClass().getResource("/images/TitleScreen.png"));
        JLabel backgroundLabel = new JLabel(bgIcon);
        backgroundLabel.setLayout(new BorderLayout()); // 设置背景标签的布局

        // 创建按钮面板，使用 FlowLayout 居中排列按钮
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setOpaque(false);  // 使面板背景透明

        // 创建三个小按钮
        JButton startButton = new JButton("Start Game");
        JButton helpButton = new JButton("Help");
        JButton exitButton = new JButton("Exit");

        // 将按钮添加到面板中
        buttonPanel.add(startButton);
        buttonPanel.add(helpButton);
        buttonPanel.add(exitButton);

        // 将按钮面板添加到背景标签的 SOUTH 区域，使其显示在屏幕正下方居中
        backgroundLabel.add(buttonPanel, BorderLayout.SOUTH);


        // 将背景标签添加到窗口中
        add(backgroundLabel);

        // “Start Game” 按钮事件：启动游戏主界面并关闭封面窗口
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameView(); // 假设 GameView 类已经实现
                dispose();
            }
        });

        // “Help” 按钮事件：打开教程窗口
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TutorialFrame().setVisible(true);
            }
        });

        // “Exit” 按钮事件：退出程序
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CoverPage().setVisible(true));
    }
}
