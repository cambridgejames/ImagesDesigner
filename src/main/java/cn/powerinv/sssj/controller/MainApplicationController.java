package cn.powerinv.sssj.controller;

import cn.powerinv.sssj.controller.listener.DragListener;
import cn.powerinv.sssj.controller.listener.ResizeListener;

import cn.powerinv.sssj.util.ThemeUtil;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
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
    private ImageView maximizeImageView;
    private ImageView restoreImageView;

    private ToggleGroup projectButtonGroup;
    private ToggleGroup pluginButtonGroup;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dragListener = new DragListener(applicationPanel);
        dragListener.enableDrag(titleBarPanel);
        resizeListener = new ResizeListener(applicationPanel);
        resizeListener.enableResize();

        applicationPanel.setPadding(new Insets(10));

        this.projectButtonGroup = new ToggleGroup();
        projectTreeButton.setToggleGroup(projectButtonGroup);

        this.pluginButtonGroup = new ToggleGroup();
        dataSourceButton.setToggleGroup(pluginButtonGroup);
        layerButton.setToggleGroup(pluginButtonGroup);

        leftStatusBar.setText("Card designer is ready (a minute ago)");
    }

    public void setTheme(ThemeUtil.Theme theme) {
        ThemeUtil.theme = theme;
        String resourceRoot = ThemeUtil.getResourceRoot();

        maximizeImageView = new ImageView(new Image(getClass().getResourceAsStream(
                resourceRoot + "/images/systemButton/maximize_normal_10.png")));
        restoreImageView = new ImageView(new Image(getClass().getResourceAsStream(
                resourceRoot + "/images/systemButton/restore_normal_10.png")));
        maximumButton.setGraphic(isMaximize ? maximizeImageView : restoreImageView);

        Scene scene = applicationPanel.getScene();
        ObservableList<String> stylesheets = scene.getStylesheets();
        stylesheets.clear();
        stylesheets.add(getClass().getResource(resourceRoot + "/style/mainApplication.css").toExternalForm());
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
            maximumButton.setGraphic(maximizeImageView);
            resizeListener.enableResize();
        } else {
            isMaximize = true;
            applicationPanel.setPadding(new Insets(0));
            Stage primaryStage = (Stage) applicationPanel.getScene().getWindow();
            primaryStage.setMaximized(true);
            maximumButton.setGraphic(restoreImageView);
            resizeListener.unableResize();
        }
    }

    @FXML
    public void closeWindow(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) applicationPanel.getScene().getWindow();
        primaryStage.close();
    }
}
