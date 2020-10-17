package cn.powerinv.sssj.controller.listener;

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

    private double offsetX = 0;
    private double offsetY = 0;

    private static final class CursorDirection {
        private boolean north;
        private boolean south;
        private boolean west;
        private boolean east;

        private Cursor cursor;

        public CursorDirection() {
            this.cursor = Cursor.DEFAULT;
        }

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

        public boolean isNorth() {
            return north;
        }

        public boolean isSouth() {
            return south;
        }

        public boolean isEast() {
            return east;
        }

        public boolean isWest() {
            return west;
        }

        public Cursor getCursor() {
            return this.cursor;
        }
    }

    private final int BORDER_WIDTH;
    private CursorDirection cursorDirection;
    private Node rootNode;
    private Stage primaryStage;

    public ResizeListener(Node rootNode) {
        this(rootNode, 10);
    }

    public ResizeListener(Node rootNode, int borderWidth) {
        this.rootNode = rootNode;
        BORDER_WIDTH = borderWidth;
        this.cursorDirection = new CursorDirection();
    }

    public void enableResize() {
        // 鼠标移动事件
        rootNode.setOnMouseMoved((MouseEvent event) -> {
            if (primaryStage == null) {
                primaryStage = (Stage) rootNode.getScene().getWindow();
            }

            event.consume();
            double x = event.getSceneX();
            double y = event.getSceneY();
            double width = primaryStage.getWidth();
            double height = primaryStage.getHeight();

            this.cursorDirection.updateCursorDirection(y < BORDER_WIDTH, y > height - BORDER_WIDTH,
                    x < BORDER_WIDTH, x > width - BORDER_WIDTH);
            rootNode.setCursor(cursorDirection.getCursor());
        });

        // 鼠标按下事件
        rootNode.setOnMousePressed((MouseEvent event) -> {
            offsetX = event.getSceneX();
            offsetY = event.getSceneY();
        });

        // 鼠标拖拽事件
        rootNode.setOnMouseDragged((MouseEvent event) -> {
            if (primaryStage == null) {
                primaryStage = (Stage) rootNode.getScene().getWindow();
            }

            // 保存窗口改变后的x、y坐标和宽度、高度，用于预判是否会小于最小宽度、最小高度
            double oldX = primaryStage.getX();
            double oldY = primaryStage.getY();

            double nextX = oldX;
            double nextY = oldY;
            double nextWidth = primaryStage.getWidth();
            double nextHeight = primaryStage.getHeight();

            if (cursorDirection.isNorth()) {
                nextY = event.getScreenY() - offsetY;
                nextHeight -= nextY - oldY;
            } else if (cursorDirection.isSouth()) {
                nextHeight = event.getSceneY();
            }
            if (cursorDirection.isWest()) {
                nextX = event.getScreenX() - offsetX;
                nextWidth -= nextX - oldX;
            } else if (cursorDirection.isEast()) {
                nextWidth = event.getSceneX();
            }

            // 统一改变窗口的x、y坐标和宽度、高度，防止刷新频繁出现的屏闪情况
            primaryStage.setX(nextX);
            primaryStage.setY(nextY);
            primaryStage.setWidth(nextWidth);
            primaryStage.setHeight(nextHeight);
        });
    }

    public void unableResize() {
        rootNode.setOnMouseMoved(null);
        rootNode.setOnMousePressed(null);
        rootNode.setOnMouseDragged(null);
        rootNode.setCursor(Cursor.DEFAULT);
    }
}
