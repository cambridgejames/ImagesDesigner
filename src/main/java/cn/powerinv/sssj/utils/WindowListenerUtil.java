package cn.powerinv.sssj.utils;

import cn.powerinv.sssj.utils.listener.DragListener;

import cn.powerinv.sssj.utils.listener.ResizeListener;
import javafx.scene.Node;

/**
 * 窗口事件监听工具类
 *
 * @author Powerinv
 * @date 2020-10-17
 */
public final class WindowListenerUtil {

    public static void addDragListener(Node rootNode, Node eventNode) {
        new DragListener(rootNode).enableDrag(eventNode);
    }

    public static void addResizeListener(Node rootNode) {
        new ResizeListener(rootNode).enableResize();
    }
}
