package org.example.ConTroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.Model.EntityAll.TaiKhoanBQT;
import org.example.Model.Hibernatedao.TaiKhoanBQTDao;
import com.jfoenix.controls.JFXToggleButton;
import javax.swing.*;
import java.io.IOException;

public class LoginController {
    private TaiKhoanBQT taiKhoanBQT;
    private Scene scene1;
    private Stage stage1;
    private Scene scene2;
    private Stage stage2;
    @FXML
    private Button loginbutton;

    @FXML
    private Button signupbutton;
    @FXML
    private Button returnhome;
    @FXML
    private JFXToggleButton hideshowbutton;

    @FXML
    private PasswordField password_field;

    @FXML
    private TextField matkhau;
    @FXML
    private TextField tendangnhap;
    String pass;

//    @FXML
//    public void dangki (ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("/org.example/SignupScreen.fxml"));
//        stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene1 = new Scene(root);
//        stage1.setScene(scene1);
//        stage1.show();
//    }
    @FXML
    public void vedashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org.example/Views/Trangchu.fxml"));
        stage2 = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene2 = new Scene(root);
        stage2.setScene(scene2);
        stage2.show();
    }
    String password;
    @FXML
    public  void initialize(){
        matkhau.setVisible(false);
        password_field.setVisible(true);
        password_field.textProperty().addListener((observable, oldValue, newValue) -> {
            pass = newValue;
        });
        matkhau.textProperty().addListener((observable,oldValue,newValue) -> {
            pass = newValue;
        });
    }

    @FXML
    public void dangnhap(ActionEvent event) throws IOException {
        String taiKhoan = tendangnhap.getText();
        String matKhau = password_field.getText();

        TaiKhoanBQTDao taiKhoanBQTDao = TaiKhoanBQTDao.getInstance();
        TaiKhoanBQT taiKhoanBQT = taiKhoanBQTDao.kiemTraDangNhap(taiKhoan, matKhau);

        if (taiKhoanBQT != null) {
            luuthongtin.setThongTinDangNhap(taiKhoan,matKhau);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example/Views/Trangchu.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi Đăng Nhập");
            alert.setHeaderText(null);
            alert.setContentText("Tài khoản hoặc mật khẩu không chính xác");
            alert.showAndWait();
        }
    }
    @FXML
    public void hienmk(ActionEvent event) throws IOException {
        if (hideshowbutton.isSelected()) {
            matkhau.setText(pass);
            matkhau.setVisible(true);
            password_field.setVisible(false);
        } else {
            password_field.setVisible(true);
            matkhau.setVisible(false);
        }
    }
}
