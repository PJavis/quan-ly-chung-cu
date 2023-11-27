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
        Parent root = FXMLLoader.load(getClass().getResource("/org.example/Trangchu.fxml"));
        stage2 = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene2 = new Scene(root);
        stage2.setScene(scene2);
        stage2.show();
    }
    @FXML
    private TextField tendangnhap;
    @FXML
    private PasswordField hide_password;
    @FXML
    private TextField show_password;
    @FXML
    private ImageView open_eye_action;
    @FXML
    private ImageView close_eye_action;
    String password;
    @FXML
    public  void initialize(){
        show_password.setVisible(false);
        open_eye_action.setVisible(false);
    }
    @FXML
    public void hidepassword(KeyEvent keyEvent) {
        password= hide_password.getText();
        show_password.setText(password);

    }
    @FXML
    public void showpassword(KeyEvent keyEvent) {
        password= show_password.getText();
        hide_password.setText(password);
    }
    @FXML
    public void momat(MouseEvent mouseEvent) {
        show_password.setVisible(false);
        open_eye_action.setVisible(false);
        close_eye_action.setVisible(true);
        hide_password.setVisible(true);

    }
    @FXML
    public void nhammat(MouseEvent mouseEvent) {
        show_password.setVisible(true);
        open_eye_action.setVisible(true);
        close_eye_action.setVisible(false);
        hide_password.setVisible(false);
    }

}
