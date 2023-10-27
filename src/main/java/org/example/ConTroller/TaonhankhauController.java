package org.example.ConTroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.EntityAll.NhanKhau;

public class TaonhankhauController {

    @FXML
    private CheckBox co;

    @FXML
    private Label gioitinh;

    @FXML
    private CheckBox khong;

    @FXML
    private TextField ngaysinh;

    @FXML
    private TextField quoctich;

    @FXML
    private TextField sophong;

    @FXML
    private TextField sotien;

    @FXML
    private TextField tennhankhau;

    @FXML
    void huy(ActionEvent event) {
        Stage a = (Stage) sophong.getScene().getWindow();
        a.close();
    }

    @FXML
    void taomoi(ActionEvent event) {
        NhanKhau nhanKhau=new NhanKhau();
        nhanKhau.setTen(tennhankhau.getText());
        nhanKhau.getNgaySinh();
        Stage a = (Stage) sophong.getScene().getWindow();
        a.close();
    }

}
