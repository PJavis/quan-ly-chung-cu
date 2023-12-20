package org.example.ConTroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.EntityAll.HoKhau;
import org.example.EntityAll.KhoanPhi;
import org.example.EntityAll.LichSuGiaoDich;
import org.example.EntityAll.NopPhi;
import org.example.Hibernatedao.KhoanPhiDao;
import org.example.Hibernatedao.LichSuGiaoDichDao;
import org.example.Hibernatedao.NopPhiDao;

import java.sql.Date;
import java.time.LocalDate;

public class ThuphiController {
    private  KhoanPhi khoanPhi;


    @FXML
    private Label duno;


    @FXML
    private TextField sophong;

    @FXML
    private TextField sotang;

    @FXML
    private Label sotien;

    @FXML
    private Label sotiendanop;

    @FXML
    private TextField sotiennop;

    @FXML
    private TextField tenphi;
    private NopPhi nopPhi;

    @FXML
    void nopphi(ActionEvent event) {
nopPhi.setSoTienDaDong(nopPhi.getSoTienDaDong()+Double.parseDouble(sotiennop.getText()));
NopPhiDao.getInstance().update(nopPhi);
        LichSuGiaoDich lichSuGiaoDich=new LichSuGiaoDich();
        lichSuGiaoDich.setSophong(nopPhi.getSoPhong());
        lichSuGiaoDich.setSotang(nopPhi.getSoTang());
        lichSuGiaoDich.setTenKhoanPhi(khoanPhi.getTenKhoanPhi());
        lichSuGiaoDich.setGiaTri(Double.parseDouble(sotiennop.getText()));
        LocalDate today = LocalDate.now();
        Date date=Date.valueOf(today);
        lichSuGiaoDich.setThoigiangiaodich(date);
        LichSuGiaoDichDao.getInstance().save(lichSuGiaoDich);
        Alert alert1=new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setHeaderText("Thành công");
        alert1.setContentText("Nộp phí thành công");
        alert1.showAndWait();
    }

    @FXML
    void timphi(ActionEvent event) {
khoanPhi= KhoanPhiDao.getInstance().selectByName(tenphi.getText());
sotien.setText(khoanPhi.getDecimalFormatsotien());

    }

    @FXML
    void timphong(ActionEvent event) {
        nopPhi= NopPhiDao.getInstance().selectByCondition(khoanPhi.getId(),Integer.parseInt(sophong.getText()),Integer.parseInt(sotang.getText()));
        sotiendanop.setText(String.valueOf(nopPhi.getSoTienDaDong()));

    }

}
