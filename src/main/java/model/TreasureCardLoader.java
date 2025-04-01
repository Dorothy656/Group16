package main.java.model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * TreasureCardLoader 类：宝藏卡片加载器
 * 说明：
 * - 从 resources/cards/TreasureCards 目录下加载所有宝藏卡片
 * - 每种宝藏卡片加载5次（共20张），打乱顺序后返回全部卡片对象列表
 */
public class TreasureCardLoader {
    private static final String[] TREASURE_CARD_NAMES = {
            "Card_Crystal_of_Fire.png",
            "Card_Earth_Stone.png",
            "Card_Oceans_Chalice.png",
            "Card_Statue_of_the_Wind.png"
    };

    /**
     * 加载所有宝藏卡片（每种卡片加载5次，共20张），并返回宝藏卡片对象列表
     * @return List<ImageIcon> 宝藏卡片对象列表
     */
    public static List<ImageIcon> loadRandomTreasure() {
        // 将所有宝藏卡片文件名各加载5次，生成总共20个文件名
        List<String> fileList = new ArrayList<>();
        for (String file : TREASURE_CARD_NAMES) {
            for (int i = 0; i < 5; i++) {
                fileList.add(file);
            }
        }
        // 打乱图片文件顺序
        Collections.shuffle(fileList);
        List<ImageIcon> treasureIcons = new ArrayList<>();
        // 遍历整个列表生成 ImageIcon 对象
        for (String fileName : fileList) {
            // 构造资源路径，例如：/images/TreasureCards/Card_Earth_Stone.png
            String path = "/images/TreasureCards/" + fileName;
            System.out.println("Loading treasure card: " + path);
            ImageIcon icon = new ImageIcon(TreasureCardLoader.class.getResource(path));
            treasureIcons.add(icon);
        }
        return treasureIcons;
    }
}
