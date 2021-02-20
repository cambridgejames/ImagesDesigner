package cn.powerinv.sssj.app.controller;

import cn.powerinv.sssj.app.constant.SvgPathConstant;
import cn.powerinv.sssj.app.constant.WindowConstant;
import cn.powerinv.sssj.app.controller.listener.DragListener;
import cn.powerinv.sssj.app.controller.listener.ResizeListener;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MainApplicationController implements Initializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainApplicationController.class);

    @FXML public StackPane applicationPanel;
    @FXML public VBox mainBoxPanel;

    @FXML public MenuBar systemMenu;
    @FXML public Button minimumButton;
    @FXML public Button maximumButton;
    @FXML public Button cancelButton;

    @FXML public BorderPane mainPanel;

    @FXML public BorderPane titleBarPanel;
    @FXML public Label titleBarLabel;
    @FXML public ToggleButton projectTreeButton;

    @FXML public ToggleButton dataSourceButton;
    @FXML public ToggleButton layerButton;

    @FXML public VBox bottomPanel;

    @FXML public Label leftStatusBar;
    @FXML public Label centerStatusBar;
    @FXML public Label rightStatusBar;

    private DragListener dragListener;
    private ResizeListener resizeListener;

    private boolean isMaximize;

    private ToggleGroup projectButtonGroup;
    private ToggleGroup pluginButtonGroup;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dragListener = new DragListener(applicationPanel);
        dragListener.enable(titleBarPanel);
        resizeListener = new ResizeListener(applicationPanel, WindowConstant.WINDOW_BORDER_WIDTH_DEFAULT,
                WindowConstant.WINDOW_MIN_WIDTH, WindowConstant.WINDOW_MIN_HEIGHT);

        initSystemButton();
        initNormalWindow();

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
        setMinMaxWindow(!isMaximize);
    }

    @FXML
    public void closeWindow(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) applicationPanel.getScene().getWindow();
        primaryStage.close();
    }

    private void initSystemButton() {
        minimumButton.setGraphic(SvgPathConstant.MINIMIZE_SVG_PATH);
        cancelButton.setGraphic(SvgPathConstant.CANCEL_SVG_PATH);
    }

    private void initNormalWindow() {
        isMaximize = false;
        applicationPanel.setPadding(new Insets(10));
        maximumButton.setGraphic(SvgPathConstant.MAXIMIZE_SVG_PATH);
        resizeListener.enable();
    }

    private void setMinMaxWindow(boolean targetMaximizeStatus) {
        Scene currentScene = applicationPanel.getScene();
        String targetStatusStr = targetMaximizeStatus ? "maximize" : "restore";
        if (currentScene == null) {
            LOGGER.warn("Get scene failed, abort to {} the window.", targetStatusStr);
            initNormalWindow();
            return;
        }
        Stage primaryStage = (Stage) currentScene.getWindow();
        if (primaryStage == null) {
            LOGGER.warn("Get stage failed, abort to {} the window.", targetStatusStr);
            initNormalWindow();
            return;
        }
        if (targetMaximizeStatus) {
            applicationPanel.setPadding(new Insets(0));
            maximumButton.setGraphic(SvgPathConstant.RESTORE_SVG_PATH);
            resizeListener.unable();
        } else {
            applicationPanel.setPadding(new Insets(10));
            maximumButton.setGraphic(SvgPathConstant.MAXIMIZE_SVG_PATH);
            resizeListener.enable();
        }
        this.isMaximize = targetMaximizeStatus;
        primaryStage.setMaximized(isMaximize);
    }
}
