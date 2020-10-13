package cn.powerinv.sssj.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainApplicationController {

    @FXML public VBox applicationPanel;

    @FXML public MenuBar systemMenu;

    @FXML public HBox filePathPanel;
    @FXML public BorderPane mainPanel;

    @FXML public VBox projectButtonPanel;
    private ToggleGroup projectButtonGroup;
    @FXML public ToggleButton projectTreeButton;

    @FXML public VBox pluginButtonPanel;
    private ToggleGroup pluginButtonGroup;
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
        this.projectButtonGroup = new ToggleGroup();
        projectTreeButton.setToggleGroup(pluginButtonGroup);

        this.pluginButtonGroup = new ToggleGroup();
        dataSourceButton.setToggleGroup(pluginButtonGroup);
        layerButton.setToggleGroup(pluginButtonGroup);

        leftStatusBar.setText("Card designer is ready (a minute ago)");
    }
}
