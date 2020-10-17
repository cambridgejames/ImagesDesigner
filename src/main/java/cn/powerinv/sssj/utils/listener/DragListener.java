package cn.powerinv.sssj.utils.listener;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * 拖拽事件监听类
 *
 * @author Powerinv
 * @date 2020-10-17
 */
public class DragListener implements EventHandler<MouseEvent> {

    private double xOffset = 0;
    private double yOffset = 0;

    private final Node rootNode;
    private Stage primaryStage;

    public DragListener(Node rootNode) {
        this.rootNode = rootNode;
    }

    @Override
    public void handle(MouseEvent event) {
        if (primaryStage == null) {
            primaryStage = (Stage) rootNode.getScene().getWindow();
        }

        event.consume();
        if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        } else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        }
    }

    public void enableDrag(Node node) {
        node.setOnMousePressed(this);
        node.setOnMouseDragged(this);
    }
}
