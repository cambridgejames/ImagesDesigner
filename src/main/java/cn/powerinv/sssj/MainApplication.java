package cn.powerinv.sssj;

import cn.powerinv.sssj.controller.MainApplicationController;

import cn.powerinv.sssj.util.ThemeUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class MainApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        StackPane root;
        try {
            URL location = getClass().getResource("/template/mainApplication.fxml");

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(location);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            root = fxmlLoader.load(location.openStream());
            MainApplicationController control = fxmlLoader.getController();

            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            primaryStage.setScene(scene);

            control.setTheme(ThemeUtil.Theme.DARCULAR);

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        primaryStage.show();
    }
}
