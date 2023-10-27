package org.example.ConTroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.EntityAll.DanhSachKhoanPhi;
import org.example.Hibernatedao.DanhSachKhoanPhiDao;

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
        danhSachKhoanPhi.setLoai_khoan_phi(loaikhoanphi.getText());
        danhSachKhoanPhi.setTen_khoan_phi(tenkhoanphi.getText());
        LocalDate currentDate = LocalDate.now();
        danhSachKhoanPhi.setBat_dau(Date.valueOf(currentDate));
        danhSachKhoanPhi.setKet_thuc(Date.valueOf(hannop.getValue()));
        danhSachKhoanPhi.setGia_tri(Double.parseDouble(sotien.getText()));
        DanhSachKhoanPhiDao.getInstance().save(danhSachKhoanPhi);
        Stage a = (Stage) sotien.getScene().getWindow();
        a.close();
    }
}
