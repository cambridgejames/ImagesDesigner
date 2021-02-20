package cn.powerinv.sssj.app.controller.listener;

import lombok.Getter;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * 拖拽更改窗口大小事件监听类
 *
 * @author Powerinv
 * @date 2020-10-17
 */
public class ResizeListener {
    private final int MIN_WIDTH;
    private final int MIN_HEIGHT;
    private final int BORDER_WIDTH;

    /**
     * 鼠标方位及指针形状处理内部类
     */
    @Getter
    private static final class CursorDirection {
        private boolean north;
        private boolean south;
        private boolean west;
        private boolean east;
        private Cursor cursor = Cursor.DEFAULT;

        /**
         * 根据鼠标在窗口的方位计算指针形状
         *
         * @param north 指针是否在窗口上方边框上
         * @param south 指针是否在窗口下方边框上
         * @param west 指针是否在窗口左侧边框上
         * @param east 指针是否在窗口右侧边框上
         */
        public void updateCursorDirection(boolean north, boolean south, boolean west, boolean east) {
            this.north = north;
            this.south = south;
            this.west = west;
            this.east = east;

            if (north) {
                if (west) {
                    this.cursor = Cursor.NW_RESIZE;
                } else if (east) {
                    this.cursor = Cursor.NE_RESIZE;
                } else {
                    this.cursor = Cursor.N_RESIZE;
                }
            } else if (south) {
                if (west) {
                    this.cursor = Cursor.SW_RESIZE;
                } else if (east) {
                    this.cursor = Cursor.SE_RESIZE;
                } else {
                    this.cursor = Cursor.S_RESIZE;
                }
            } else {
                if (west) {
                    this.cursor = Cursor.W_RESIZE;
                } else if (east) {
                    this.cursor = Cursor.E_RESIZE;
                } else {
                    this.cursor = Cursor.DEFAULT;
                }
            }
        }
    }

    private final CursorDirection CURSOR_DIRECTION = new CursorDirection();
    private final Node ROOT_NODE;

    private Stage primaryStage;

    private double offsetX = 0;
    private double offsetY = 0;

    public ResizeListener(Node rootNode, int borderWidth, int minWidth, int minHeight) {
        this.ROOT_NODE = rootNode;
        this.BORDER_WIDTH = borderWidth;
        this.MIN_WIDTH = minWidth;
        this.MIN_HEIGHT = minHeight;
    }

    /**
     * 禁用修改窗口大小
     */
    public void unable() {
        ROOT_NODE.setOnMouseMoved(null);
        ROOT_NODE.setOnMousePressed(null);
        ROOT_NODE.setOnMouseDragged(null);
        ROOT_NODE.setCursor(Cursor.DEFAULT);
    }

    /**
     * 允许窗口修改大小
     */
    public void enable() {
        ROOT_NODE.setOnMouseMoved(this::onMouseMoved);
        ROOT_NODE.setOnMousePressed(this::onMousePressed);
        ROOT_NODE.setOnMouseDragged(this::onMouseDragged);
    }

    /**
     * 鼠标移动事件处理方法
     *
     * @param event 鼠标事件对象
     */
    private void onMouseMoved(MouseEvent event) {
        if (primaryStage == null) {
            primaryStage = (Stage) ROOT_NODE.getScene().getWindow();
        }
        event.consume();
        double x = event.getSceneX();
        double y = event.getSceneY();
        double width = primaryStage.getWidth();
        double height = primaryStage.getHeight();

        this.CURSOR_DIRECTION.updateCursorDirection(y < BORDER_WIDTH, y > height - BORDER_WIDTH,
                x < BORDER_WIDTH, x > width - BORDER_WIDTH);
        ROOT_NODE.setCursor(CURSOR_DIRECTION.getCursor());
    }

    /**
     * 鼠标按下事件处理方法
     *
     * @param event 鼠标事件对象
     */
    private void onMousePressed(MouseEvent event) {
        // 记录鼠标按下时的位置
        event.consume();
        offsetX = event.getSceneX();
        offsetY = event.getSceneY();
    }

    /**
     * 鼠标拖拽时间处理方法，用于修改窗口大小
     *
     * @param event 鼠标事件对象
     */
    private void onMouseDragged(MouseEvent event) {
        if (primaryStage == null) {
            primaryStage = (Stage) ROOT_NODE.getScene().getWindow();
        }
        event.consume();

        // 保存窗口改变后的x、y坐标和宽度、高度，用于预判是否会小于最小宽度、最小高度
        double oldX = primaryStage.getX();
        double oldY = primaryStage.getY();

        double nextX = oldX;
        double nextY = oldY;
        double nextWidth = primaryStage.getWidth();
        double nextHeight = primaryStage.getHeight();

        if (CURSOR_DIRECTION.isNorth()) {
            nextY = event.getScreenY() - offsetY;
            nextHeight -= nextY - oldY;
        } else if (CURSOR_DIRECTION.isSouth()) {
            nextHeight = event.getSceneY();
        }
        if (CURSOR_DIRECTION.isWest()) {
            nextX = event.getScreenX() - offsetX;
            nextWidth -= nextX - oldX;
        } else if (CURSOR_DIRECTION.isEast()) {
            nextWidth = event.getSceneX();
        }

        // 统一改变窗口的x、y坐标和宽度、高度，防止刷新频繁出现的屏闪情况
        if (nextWidth >= MIN_WIDTH) {
            primaryStage.setX(nextX);
            primaryStage.setWidth(nextWidth);
        }
        if (nextHeight >= MIN_HEIGHT) {
            primaryStage.setY(nextY);
            primaryStage.setHeight(nextHeight);
        }
    }
}
