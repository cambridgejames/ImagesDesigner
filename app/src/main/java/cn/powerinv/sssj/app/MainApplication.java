package cn.powerinv.sssj.app;

import cn.powerinv.sssj.app.util.ThemeUtil;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        try {
            Pane root = FXMLLoader.load(getClass().getResource("/template/mainApplication.fxml"));
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            primaryStage.setScene(scene);

            List<String> styleList = new ArrayList<>();
            styleList.add("/mainApplication.css");
            styleList.add("/pluginButton.css");
            ThemeUtil.registerScene(scene, styleList);
            ThemeUtil.setTheme(ThemeUtil.Theme.DARCULAR);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        primaryStage.show();
    }
}
