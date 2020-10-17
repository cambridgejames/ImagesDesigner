package cn.powerinv.sssj.listener.utils;

import cn.powerinv.sssj.listener.DragListener;

import javafx.scene.Node;

/**
 * 拖拽工具类
 *
 * @author Powerinv
 * @date 2020-10-17
 */
public final class DragUtil {

    public static void addDragListener(Node rootNode, Node eventNode) {
        new DragListener(rootNode).enableDrag(eventNode);
    }
}
