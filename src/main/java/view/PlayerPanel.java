package main.java.view;

import main.java.model.Player;
import main.java.game.Game;  // 修改为正确的包路径

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * PlayerPanel 类：显示玩家信息的面板
 * 说明：
 * - 从传入的 Game 实例中获取玩家列表
 * - 为每个玩家创建一个信息显示面板，显示玩家名称、角色、角色图标和当前位置
 * - 采用垂直 BoxLayout 排列所有玩家信息面板
 * - TODO: 根据需要扩展显示更多玩家信息
 */
public class PlayerPanel extends JPanel {
    private List<Player> players;

    /**
     * 构造函数，通过传入 Game 实例获取玩家列表
     * @param game 当前游戏实例
     */
    public PlayerPanel(Game game) {
        setPreferredSize(new Dimension(300, 0));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);

        players = game.getPlayers();
        if (players == null || players.isEmpty()) {
            add(new JLabel("No players available"));
        } else {
            for (Player player : players) {
                add(createPlayerInfoPanel(player));
                add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }
    }

    /**
     * 为单个玩家创建信息显示面板
     * @param player 玩家对象
     * @return JPanel 显示玩家信息的面板
     */
    private JPanel createPlayerInfoPanel(Player player) {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createTitledBorder(player.getName()));

        // 添加角色图标标记（使用角色的 normal 图标）
        JLabel roleIconLabel = new JLabel(player.getRoleObj().getNormalIcon());
        roleIconLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel roleLabel = new JLabel("Role: " + player.getRole());
        JLabel positionLabel = new JLabel("Position: " + player.getPawn().getName());
        roleLabel.setForeground(Color.WHITE);
        positionLabel.setForeground(Color.WHITE);

        panel.add(roleIconLabel);
        panel.add(roleLabel);
        panel.add(positionLabel);
        // TODO: 根据需要添加更多玩家信息，如手牌列表等
        return panel;
    }
}
