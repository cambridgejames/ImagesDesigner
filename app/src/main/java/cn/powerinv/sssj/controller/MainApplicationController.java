package cn.powerinv.sssj.controller;

import cn.powerinv.sssj.controller.listener.DragListener;
import cn.powerinv.sssj.controller.listener.ResizeListener;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainApplicationController implements Initializable {

    @FXML public StackPane applicationPanel;
    @FXML public VBox mainBoxPanel;

    @FXML public HBox titlePanel;
    @FXML public MenuBar systemMenu;
    @FXML public Button minimumButton;
    @FXML public Button maximumButton;
    @FXML public Button cancelButton;

    @FXML public HBox filePathPanel;
    @FXML public BorderPane mainPanel;

    @FXML public VBox projectButtonPanel;
    @FXML public BorderPane titleBarPanel;
    @FXML public Label titleBarLabel;
    @FXML public ToggleButton projectTreeButton;

    @FXML public VBox pluginButtonPanel;
    @FXML public ToggleButton dataSourceButton;
    @FXML public ToggleButton layerButton;

    @FXML public VBox bottomPanel;
    @FXML public HBox consoleButtonPanel;
    @FXML public HBox statePanel;

    @FXML public Label leftStatusBar;
    @FXML public Label centerStatusBar;
    @FXML public Label rightStatusBar;

    private DragListener dragListener;
    private ResizeListener resizeListener;

    private boolean isMaximize;
    private SVGPath maximizeSVGPath;
    private SVGPath restoreSVGPath;

    private ToggleGroup projectButtonGroup;
    private ToggleGroup pluginButtonGroup;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dragListener = new DragListener(applicationPanel);
        dragListener.enableDrag(titleBarPanel);
        resizeListener = new ResizeListener(applicationPanel);
        resizeListener.enableResize();

        maximizeSVGPath = new SVGPath();
        maximizeSVGPath.setContent("M0 0 L0 10 L10 10 L10 0 L1 0 L1 1 L9 1 L9 9 L1 9 L1 0 Z");
        restoreSVGPath = new SVGPath();
        restoreSVGPath.setContent("M0 2 L0 10 L8 10 L8 8 L10 8 L10 0 L2 0 L2 2 L1 2 L1 3 L3 3 " +
                "L3 1 L9 1 L9 7 L8 7 L8 2 L3 2 L3 3 L7 3 L7 9 L1 9 L1 2 Z");
        if (isMaximize) {
            maximumButton.setGraphic(restoreSVGPath);
        } else {
            maximumButton.setGraphic(maximizeSVGPath);
        }

        applicationPanel.setPadding(new Insets(10));

        this.projectButtonGroup = new ToggleGroup();
        projectTreeButton.setToggleGroup(projectButtonGroup);

        this.pluginButtonGroup = new ToggleGroup();
        dataSourceButton.setToggleGroup(pluginButtonGroup);
        layerButton.setToggleGroup(pluginButtonGroup);

        leftStatusBar.setText("Card designer is ready (a minute ago)");
    }

    @FXML
    public void minimizeWindow(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) applicationPanel.getScene().getWindow();
        primaryStage.setIconified(true);
    }

    @FXML
    public void maximizeWindow(ActionEvent actionEvent) {
        if (isMaximize) {
            isMaximize= false;
            applicationPanel.setPadding(new Insets(10));
            Stage primaryStage = (Stage) applicationPanel.getScene().getWindow();
            primaryStage.setMaximized(false);
            maximumButton.setGraphic(maximizeSVGPath);
            resizeListener.enableResize();
        } else {
            isMaximize = true;
            applicationPanel.setPadding(new Insets(0));
            Stage primaryStage = (Stage) applicationPanel.getScene().getWindow();
            primaryStage.setMaximized(true);
            maximumButton.setGraphic(restoreSVGPath);
            resizeListener.unableResize();
        }
    }

    @FXML
    public void closeWindow(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) applicationPanel.getScene().getWindow();
        primaryStage.close();
    }
}
