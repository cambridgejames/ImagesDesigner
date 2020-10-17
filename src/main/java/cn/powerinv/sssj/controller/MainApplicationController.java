package cn.powerinv.sssj.controller;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class MainApplicationController {

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
    @FXML public Label titleBarLabel;
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
        applicationPanel.setPadding(new Insets(20));

        this.projectButtonGroup = new ToggleGroup();
        projectTreeButton.setToggleGroup(projectButtonGroup);

        this.pluginButtonGroup = new ToggleGroup();
        dataSourceButton.setToggleGroup(pluginButtonGroup);
        layerButton.setToggleGroup(pluginButtonGroup);

        leftStatusBar.setText("Card designer is ready (a minute ago)");
    }
}
