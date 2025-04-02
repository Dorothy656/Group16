package main.java.model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * MapLoader 类：随机加载地图图片
 * 说明：
 * - 从资源目录（/images/maps/）中加载所有地图文件
 * - 打乱图片顺序后，取出前24张地图图片，并返回 ImageIcon 列表
 */
public class MapLoader {
    // 地图文件名数组，请根据实际情况调整文件名
    private static final String[] MAP_FILES = {
            "Breakers Bridge@2x.png", "Bronze Gate@2x.png", "Cave of Embers@2x.png", "Cave of Shadows@2x.png", "Cliffs of Abandon@2x.png",
            "Copper Gate@2x.png", "Coral Palace@2x.png", "Crimson Forest@2x.png", "Dunes of Deception@2x.png",
            "Fools_ Landing@2x.png", "Gold Gate@2x.png", "Howling Garden@2x.png", "Iron Gate@2x.png", "Lost Lagoon@2x.png",
            "Misty Marsh@2x.png", "Observatory@2x.png", "Phantom Rock@2x.png", "Silver Gate@2x.png", "Temple of the Moon@2x.png",
            "Temple of the Sun@2x.png", "Tidal Palace@2x.png", "Twilight Hollow@2x.png", "Watchtower@2x.png", "Whispering Garden@2x.png"
    };

    /**
     * 随机加载24张地图图片，并返回 ImageIcon 列表
     * @return List<ImageIcon> 随机选取的24张地图图片
     */
    public static List<ImageIcon> loadRandomMaps() {
        List<String> fileList = new ArrayList<>();
        for (String file : MAP_FILES) {
            fileList.add(file);
        }
        // 打乱图片文件顺序
        Collections.shuffle(fileList);
        List<ImageIcon> mapIcons = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            // 构造资源路径，例如：/images/maps/map1.jpg
            String path = "/images/Tiles/" + fileList.get(i);
            ImageIcon icon = new ImageIcon(MapLoader.class.getResource(path));
            mapIcons.add(icon);
        }
        return mapIcons;
    }
}
