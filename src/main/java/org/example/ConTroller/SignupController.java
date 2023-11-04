package org.example.ConTroller;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.example.ConTroller.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import java.awt.*;
import java.io.IOException;

public class SignupController {
    private Scene scene2;
    private Stage stage2;
    @FXML
    private TextField username2;
    @FXML
    private PasswordField hide_pass;
    @FXML
    private TextField show_pass;
    @FXML
    private ImageView open_eye;
    @FXML
    private ImageView close_eye;
    @FXML
    private ImageView open_eye1;
    @FXML
    private ImageView close_eye1;
    String password;

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
    @FXML
    public  void initialize(){
        show_pass.setVisible(false);
        open_eye.setVisible(false);
    }
    @FXML
    public void hidePasswordOnAction(KeyEvent keyEvent) {
        password=hide_pass.getText();
        show_pass.setText(password);

    }
    @FXML
    public void showPasswordOnAction(KeyEvent keyEvent) {
        password=show_pass.getText();
        hide_pass.setText(password);
    }
    @FXML
    public void open_Eye_ClickOnAction(MouseEvent mouseEvent) {
        show_pass.setVisible(false);
        open_eye.setVisible(false);
        close_eye.setVisible(true);
        hide_pass.setVisible(true);

    }
    @FXML
    public void close_Eye_Click_OnAction(MouseEvent mouseEvent) {
        show_pass.setVisible(true);
        open_eye.setVisible(true);
        close_eye.setVisible(false);
        hide_pass.setVisible(false);
    }
}
