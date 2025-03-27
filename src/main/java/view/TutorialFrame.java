package main.java.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//TutorialFrame 类：显示游戏教程的窗口

public class TutorialFrame extends JFrame {
    private CardLayout cardLayout;  // 卡片布局，用于切换教程页面
    private JPanel cardPanel;       // 存放所有教程页面的面板
    private int currentPage = 1;    // 当前页码
    private final int totalPages = 8;  // 总共页面数
    private JLabel pageIndicator;   // 页面指示标签

    public TutorialFrame() {
        setTitle("Game Tutorial");             // 设置窗口标题（英文）
        setSize(600, 950);                     // 设置窗口尺寸
        setLocationRelativeTo(null);            // 窗口居中显示
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 关闭窗口时释放资源

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        for (int i = 1; i <= totalPages; i++) {
            JPanel page = new JPanel(new BorderLayout());

            JLabel titleLabel = new JLabel("Tutorial Page " + i, SwingConstants.CENTER);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
            page.add(titleLabel, BorderLayout.NORTH);

            ImageIcon icon = new ImageIcon(getClass().getResource("/images/Rules/000" + i + ".jpg"));
            JLabel imageLabel = new JLabel(icon);
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

            JTextArea contentArea = new JTextArea("\tThis is the explanation content for tutorial page " + i + ".\n Here you can provide a detailed introduction of the game rules and instructions.");
            contentArea.setLineWrap(true);               // 自动换行
            contentArea.setWrapStyleWord(true);          // 按单词换行
            contentArea.setEditable(false);              // 不允许编辑
            contentArea.setOpaque(false);                // 设置文本区域背景透明
            contentArea.setFont(new Font("Arial", Font.PLAIN, 16));
            JScrollPane textScrollPane = new JScrollPane(contentArea);
            textScrollPane.setBorder(BorderFactory.createEmptyBorder());
            textScrollPane.setOpaque(false);
            textScrollPane.getViewport().setOpaque(false);

            JPanel centerPanel = new JPanel(new BorderLayout());
            centerPanel.setOpaque(false);
            centerPanel.add(imageLabel, BorderLayout.CENTER);
            centerPanel.add(textScrollPane, BorderLayout.SOUTH);

            page.add(centerPanel, BorderLayout.CENTER);
            cardPanel.add(page, "Page" + i);
        }

        JPanel navPanel = new JPanel(new FlowLayout());
        JButton prevButton = new JButton("Previous");
        JButton nextButton = new JButton("Next");
        JButton closeButton = new JButton("Return");
        pageIndicator = new JLabel("Page " + currentPage + " / " + totalPages);

        navPanel.add(prevButton);
        navPanel.add(pageIndicator);
        navPanel.add(nextButton);
        navPanel.add(closeButton);

        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPage > 1) {
                    currentPage--;
                    cardLayout.show(cardPanel, "Page" + currentPage);
                    updatePageIndicator();
                }
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPage < totalPages) {
                    currentPage++;
                    cardLayout.show(cardPanel, "Page" + currentPage);
                    updatePageIndicator();
                }
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        add(cardPanel, BorderLayout.CENTER);
        add(navPanel, BorderLayout.SOUTH);
    }

    private void updatePageIndicator() {
        pageIndicator.setText("Page " + currentPage + " / " + totalPages);
    }
}
