package org.example.ConTroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.EntityAll.DanhSachKhoanPhi;
import org.example.Hibernatedao.DanhSachKhoanPhiDao;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class TaophiController implements Initializable {
    @FXML
    private Button buttontaomoi;

    @FXML
    private TextField loaikhoanphi;

    @FXML
    private TextField sotien;

    @FXML
    private TextField tenkhoanphi;
    @FXML
    private TextField hannop;
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date= hannop.getText();
        LocalDate datetime = LocalDate.parse(date, formatter);
        danhSachKhoanPhi.setKetThuc(Date.valueOf(datetime));
        danhSachKhoanPhi.setGiaTri(Double.parseDouble(sotien.getText()));
        DanhSachKhoanPhiDao.getInstance().save(danhSachKhoanPhi);
        Stage a = (Stage) sotien.getScene().getWindow();
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
        TextField[] textFields={tenkhoanphi,loaikhoanphi,sotien,hannop};
        for (TextField textField : textFields) {
            textField.textProperty().addListener((observable, oldValue, newValue) -> checkAllFieldsFilled(textFields));
        }

    }
}
