package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.stage.StageStyle;

public class Main extends Application {
double x,y = 0;
    @Override
    public void start(Stage primaryStage) {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("/org.example/DashBoard.fxml"));


            root.setOnMousePressed(event -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });

            root.setOnMouseDragged(event -> {
                primaryStage.setX(event.getScreenX() - x);
                primaryStage.setY(event.getScreenY() - y);
            });
Scene scene=new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();}
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}