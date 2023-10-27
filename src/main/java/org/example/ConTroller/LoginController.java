package org.example.ConTroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    private Scene scene1;
    private Stage stage1;
    private Scene scene2;
    private Stage stage2;
    @FXML
    public void dangki (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org.example/SignupScreen.fxml"));
        stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene1 = new Scene(root);
        stage1.setScene(scene1);
        stage1.show();
    }
    @FXML
    public void vedashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org.example/DashBoard.fxml"));
        stage2 = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene2 = new Scene(root);
        stage2.setScene(scene2);
        stage2.show();
    }

}
