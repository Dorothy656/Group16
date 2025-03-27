package main.java.model;

import javax.swing.*;

/**
 * IslandTile 类：表示岛屿瓦片
 * 说明：
 * - 增加属性 tileImage 保存瓦片对应的地图图片
 */
public class IslandTile {
    private String name;         // 瓦片名称
    private TileState state;     // 瓦片状态（NORMAL, FLOODED, SUNK等）
    private ImageIcon tileImage; // 瓦片对应的地图图片

    public IslandTile(String name, ImageIcon tileImage) {
        this.name = name;
        this.tileImage = tileImage;
        this.state = TileState.NORMAL; // 默认状态
    }

    // 以下为 getter 和 setter 方法
    public String getName() {
        return name;
    }

    public ImageIcon getTileImage() {
        return tileImage;
    }

    public TileState getState() {
        return state;
    }

    public void setState(TileState state) {
        this.state = state;
    }
}
