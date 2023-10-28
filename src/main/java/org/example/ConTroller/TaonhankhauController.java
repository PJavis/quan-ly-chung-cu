package org.example.ConTroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.EntityAll.NhanKhau;
import org.example.Hibernatedao.HoKhauDao;
import org.example.Hibernatedao.NhanKhauDao;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaonhankhauController {

    @FXML
    private CheckBox co;

    @FXML
    private TextField gioitinh;


    @FXML
    private TextField ngaysinh;

    @FXML
    private TextField quoctich;

    @FXML
    private TextField sophong;


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
        nhanKhau.setGioiTinh(gioitinh.getText());
        nhanKhau.setQuocTich(quoctich.getText());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date= ngaysinh.getText();
        LocalDate datetime = LocalDate.parse(date, formatter);
        nhanKhau.setNgaySinh(Date.valueOf(datetime));
        if(co.isSelected()){
            nhanKhau.setChuHo(true);
        }else nhanKhau.setChuHo(false);
nhanKhau.setTrangThai("Đang ở");
        NhanKhauDao.getInstance().save(nhanKhau);
        Stage a = (Stage) sophong.getScene().getWindow();
        a.close();
    }

}
