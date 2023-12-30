package org.example.ConTroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.example.EntityAll.TaiKhoanBQT;

public class ThongtinController {

    @FXML
    private Label account;

    @FXML
    private ImageView close_eye_action;

    @FXML
    private Label ngaytao;

    @FXML
    private ImageView open_eye_action;
    private TaiKhoanBQT taiKhoanBQT;

    @FXML
    private Label pass;
    public void initialize(TaiKhoanBQT taiKhoanBQT) {
        this.taiKhoanBQT = taiKhoanBQT;
        hienthongtin();
    }
    public void hienthongtin() {
        if (taiKhoanBQT != null) {
            account.setText(taiKhoanBQT.getTaiKhoan());
            pass.setText(taiKhoanBQT.getMatKhau());
        }
    }

}
