package org.example.ConTroller;
import org.example.ConTroller.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class SignupController {
    private Scene scene2;
    private Stage stage2;

    @FXML
    private Button logi1;
//    @FXML
//    public void signup (ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("/org.example/SignupScreen.fxml"));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
    @FXML
    public void returnlogin (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org.example/Loginscreen.fxml"));
        stage2 = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene2 = new Scene(root);
        stage2.setScene(scene2);
        stage2.show();
    }
}
