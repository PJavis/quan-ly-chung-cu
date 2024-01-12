package org.example.ConTroller;
import javafx.scene.control.PasswordField;
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
    private PasswordField pass1;

    @FXML
    private PasswordField pass2;

    @FXML
    private Button returnhome;

    @FXML
    private Button lgbutton;
    @FXML
    private Button subutton;
    @FXML
    private TextField username;
    @FXML
    public void returnlogin (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org.example/Views/Loginscreen.fxml"));
        stage2 = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene2 = new Scene(root);
        stage2.setScene(scene2);
        stage2.show();
    }
//    void dangki(){
//        if(pass1.getText()!=pass2.getText()||pass1.getText().isEmpty()||pass2.getText().isEmpty()){
//            Alert alert=new Alert(Alert.AlertType.ERROR);
//            alert.setHeaderText("Thất bại");
//            alert.setContentText("Vui lòng điền đầy đủ thông tin");
//            alert.showAndWait();
//        }
//        else {
//            TaiKhoanBQT taiKhoan = new TaiKhoanBQT();
//            taiKhoan.setTaiKhoan(username.getText());
//            taiKhoan.setMatKhau(pass1.getText());
//        }
//    }
}
