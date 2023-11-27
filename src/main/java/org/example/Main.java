package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.Hibernatedao.Hibernate;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("/org.example/Trangchu.fxml"));
        Scene scene=new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();}
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Hibernate.getSessionFactory();
        getData.getInstance().reloadNhankhau();
        getData.getInstance().reloadHokhau();
        launch(args);
        Hibernate.closeSessionFactory(Hibernate.getSessionFactory());
    }
}