package org.example.ConTroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.EntityAll.NhanKhau;
import org.example.Hibernatedao.HoKhauDao;
import org.example.Hibernatedao.NhanKhauDao;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class TaonhankhauController implements Initializable {
    private boolean hienbuttontaomoi=false;
    @FXML
    private Button buttontaomoi;

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
        nhanKhau.setHoKhau(HoKhauDao.getInstance().selectById(Integer.parseInt(sophong.getText())));
nhanKhau.setTrangThai("Đang ở");
        NhanKhauDao.getInstance().save(nhanKhau);
        Stage a = (Stage) sophong.getScene().getWindow();
        a.close();
    }
    private void checkAllFieldsFilled(TextField[] textFields) {
        boolean allFilled = true;
        for (TextField textField : textFields) {
            if (textField.getText().isEmpty()) {
                allFilled = false;
                break;
            }
        }
        buttontaomoi.setDisable(!allFilled);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TextField[] textFields={tennhankhau,sophong,quoctich,ngaysinh,gioitinh};
        for (TextField textField : textFields) {
            textField.textProperty().addListener((observable, oldValue, newValue) -> checkAllFieldsFilled(textFields));
        }

    }
}
