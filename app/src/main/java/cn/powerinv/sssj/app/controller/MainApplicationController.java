package cn.powerinv.sssj.app.controller;

import cn.powerinv.sssj.app.constant.SvgPathConstant;
import cn.powerinv.sssj.app.controller.listener.DragListener;
import cn.powerinv.sssj.app.controller.listener.ResizeListener;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainApplicationController implements Initializable {

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
        dragListener.enableDrag(titleBarPanel);
        resizeListener = new ResizeListener(applicationPanel);
        resizeListener.enableResize();

        initSystemButton();

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
        setMinMaxWindow();
    }

    @FXML
    public void closeWindow(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) applicationPanel.getScene().getWindow();
        primaryStage.close();
    }

    private void initSystemButton() {
        minimumButton.setGraphic(SvgPathConstant.MINIMIZE_SVG_PATH);
        cancelButton.setGraphic(SvgPathConstant.CANCEL_SVG_PATH);
        setMinMaxWindow();
    }

    private void setMinMaxWindow() {
        if (isMaximize) {
            isMaximize= false;
            applicationPanel.setPadding(new Insets(10));
            Stage primaryStage = (Stage) applicationPanel.getScene().getWindow();
            primaryStage.setMaximized(false);
            maximumButton.setGraphic(SvgPathConstant.RESTORE_SVG_PATH);
            resizeListener.enableResize();
        } else {
            isMaximize = true;
            applicationPanel.setPadding(new Insets(0));
            Stage primaryStage = (Stage) applicationPanel.getScene().getWindow();
            primaryStage.setMaximized(true);
            maximumButton.setGraphic(SvgPathConstant.MAXIMIZE_SVG_PATH);
            resizeListener.unableResize();
        }
    }
}
