package main.java.model;

import javax.swing.*;
import java.util.Objects;

/**
 * Role 类：表示游戏角色
 * 说明：
 * - 保存角色名称及对应状态的图标路径
 * - 提供获取各状态图标的方法
 */
public class Role {
    private String roleName;
    private String normalIconPath;
    private String activeIconPath;
    private String giveIconPath;
    private String moveIconPath;
    private String selectIconPath;

    public Role(String roleName, String normalIconPath, String activeIconPath,
                String giveIconPath, String moveIconPath, String selectIconPath) {
        this.roleName = roleName;
        this.normalIconPath = normalIconPath;
        this.activeIconPath = activeIconPath;
        this.giveIconPath = giveIconPath;
        this.moveIconPath = moveIconPath;
        this.selectIconPath = selectIconPath;
    }

    public String getRoleName() {
        return roleName;
    }

    public ImageIcon getNormalIcon() {
        return new ImageIcon(Objects.requireNonNull(getClass().getResource(normalIconPath)));
    }

    // TODO: 根据需要添加 active、give、move、select 状态图标的获取方法

    @Override
    public String toString() {
        return roleName;
    }
}
