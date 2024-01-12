package org.example.ConTroller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.example.Model.EntityAll.HoKhau;

import java.net.URL;
import java.util.ResourceBundle;

public class thongtinnhaController implements Initializable {

    @FXML
    private Label dientich;
    @FXML
    private Label sotang;

    @FXML
    private Label sonhankhau;

    @FXML
    private Label sophong;

    @FXML
    private Label tenchuho;

    @FXML
    private Label tienthue;
    //dang lam gio

    public void laydata(HoKhau hoKhau){
        sophong.setText(String.valueOf(hoKhau.getId()));
        sotang.setText(String.valueOf(hoKhau.getSoTang()));
        tenchuho.setText(hoKhau.getTenchuho());
        sonhankhau.setText(String.valueOf(hoKhau.getSoNhanKhau()));
        dientich.setText(String.valueOf(hoKhau.getDienTichPhong()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
