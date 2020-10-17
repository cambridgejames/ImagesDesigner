package cn.powerinv.sssj;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        StackPane root;
        try {
            root = FXMLLoader.load(getClass().getResource("/template/mainApplication.fxml"));
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            primaryStage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/style/mainApplication.css").toExternalForm());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        primaryStage.show();
    }
}
