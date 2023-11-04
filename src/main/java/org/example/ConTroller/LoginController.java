package org.example.ConTroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class LoginController {
    private Scene scene1;
    private Stage stage1;
    private Scene scene2;
    private Stage stage2;

    @FXML
    private Button signupbutton;
    @FXML
    private Button returnhome;
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
    public TextField txtusername;
    public PasswordField txt_hide_Password;
    public TextField txt_show_passwod;
    public ImageView lbl_open_eye;
    public ImageView lbl_close_eye;
    String password;
    @FXML
    public  void initialize(){
        txt_show_passwod.setVisible(false);
        lbl_open_eye.setVisible(false);
    }
    @FXML
    public void hidePasswordOnAction(KeyEvent keyEvent) {
        password=txt_hide_Password.getText();
        txt_show_passwod.setText(password);

    }
    @FXML
    public void showPasswordOnAction(KeyEvent keyEvent) {
        password=txt_show_passwod.getText();
        txt_hide_Password.setText(password);
    }
    @FXML
    public void open_Eye_ClickOnAction(MouseEvent mouseEvent) {
        txt_show_passwod.setVisible(false);
        lbl_open_eye.setVisible(false);
        lbl_close_eye.setVisible(true);
        txt_hide_Password.setVisible(true);

    }
    @FXML
    public void close_Eye_Click_OnAction(MouseEvent mouseEvent) {
        txt_show_passwod.setVisible(true);
        lbl_open_eye.setVisible(true);
        lbl_close_eye.setVisible(false);
        txt_hide_Password.setVisible(false);
    }

}
