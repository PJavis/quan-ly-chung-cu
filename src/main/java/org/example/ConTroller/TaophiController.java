package org.example.ConTroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.EntityAll.DanhSachKhoanPhi;

import java.sql.Date;
import java.time.LocalDate;

public class TaophiController {

    @FXML
    private TextField loaikhoanphi;

    @FXML
    private TextField sotien;

    @FXML
    private TextField tenkhoanphi;
    @FXML
    private DatePicker hannop;
    @FXML
    void huy(ActionEvent event) {
        Stage a = (Stage) sotien.getScene().getWindow();
        a.close();
    }

    @FXML
    void taomoi(ActionEvent event) {
        DanhSachKhoanPhi danhSachKhoanPhi=new DanhSachKhoanPhi();
        danhSachKhoanPhi.setLoaiKhoanPhi(loaikhoanphi.getText());
        danhSachKhoanPhi.setTenKhoanPhi(tenkhoanphi.getText());
        LocalDate currentDate = LocalDate.now();
        danhSachKhoanPhi.setBatDau(Date.valueOf(currentDate));
        danhSachKhoanPhi.setKetThuc(Date.valueOf(hannop.getValue()));

        Stage a = (Stage) sotien.getScene().getWindow();
        a.close();
    }
}
