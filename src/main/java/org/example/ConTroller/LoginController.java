package org.example.ConTroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.example.EntityAll.TaiKhoanBQT;
import org.example.Hibernatedao.TaiKhoanBQTDao;

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
    private PasswordField password_field;


    @FXML
    private TextField tendangnhap;

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
    String password;
    @FXML
    public  void initialize(){
    }

    @FXML
    public void dangnhap(ActionEvent event) throws IOException {
        String taiKhoan = tendangnhap.getText();
        String matKhau = password_field.getText();

        TaiKhoanBQTDao taiKhoanBQTDao = TaiKhoanBQTDao.getInstance();
        TaiKhoanBQT taiKhoanBQT = taiKhoanBQTDao.kiemTraDangNhap(taiKhoan, matKhau);

        if (taiKhoanBQT != null) {
            luuthongtin.setThongTinDangNhap(taiKhoan,matKhau);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example/Trangchu.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi Đăng Nhập");
            alert.setHeaderText(null);
            alert.setContentText("Cau oi sai roi");
            alert.showAndWait();
        }
    }

}
