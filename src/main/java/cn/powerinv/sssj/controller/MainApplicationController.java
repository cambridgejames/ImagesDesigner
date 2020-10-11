package cn.powerinv.sssj.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainApplicationController {

    @FXML public VBox applicationPanel;

    @FXML public MenuBar systemMenu;

    @FXML public HBox filePathPanel;
    @FXML public BorderPane mainPanel;
    @FXML public VBox projectButtonPanel;
    @FXML public VBox pluginButtonPanel;
    @FXML public ToggleButton dataSourceButton;
    @FXML public ToggleButton layerButton;
    @FXML public VBox bottomPanel;
    @FXML public HBox consoleButtonPanel;
    @FXML public HBox statePanel;

    @FXML public Label leftStatusBar;
    @FXML public Label centerStatusBar;
    @FXML public Label rightStatusBar;

    @FXML
    public void initialize() {
        leftStatusBar.setText("Card designer is ready (a minute ago)");
    }
}
