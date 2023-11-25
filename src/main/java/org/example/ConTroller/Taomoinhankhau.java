package org.example.ConTroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.EntityAll.HoKhau;
import org.example.EntityAll.NhanKhau;
import org.example.Hibernatedao.HoKhauDao;
import org.example.Hibernatedao.NhanKhauDao;
import org.example.getData;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class Taomoinhankhau implements Initializable {
    @FXML
    private Button buttontaomoinhankhau;

    @FXML
    private TextField gioitinh;

    @FXML
    private TextField ngaysinh;

    @FXML
    private TextField quoctich;

    @FXML
    private TextField sophongtaonhankhau;

    @FXML
    private TextField tennhankhau;
    @FXML
    private TextField sotang;


    @FXML
    void taomoinhankhau(ActionEvent event) {
        NhanKhau nhanKhau=new NhanKhau();
        nhanKhau.setTen(tennhankhau.getText());
        nhanKhau.setGioiTinh(gioitinh.getText());
        nhanKhau.setQuocTich(quoctich.getText());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date= ngaysinh.getText();
        LocalDate datetime = LocalDate.parse(date, formatter);
        nhanKhau.setNgaySinh(Date.valueOf(datetime));
        nhanKhau.setChuHo(false);
        HoKhau hoKhau=HoKhauDao.getInstance().selectById(Integer.parseInt(sophongtaonhankhau.getText()),Integer.parseInt(sotang.getText()));
        try {
            nhanKhau.setSophong(hoKhau.getId());
            nhanKhau.setSotang(hoKhau.getSoTang());
        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Thất bại");
            alert.setContentText("Không tìm thấy phòng");
            alert.showAndWait();
        }
        nhanKhau.setTrangThai("Đang ở");
        NhanKhauDao.getInstance().save(nhanKhau);
        getData.getInstance().addNhankhau(nhanKhau);
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Thành công");
        alert.setContentText("Tạo mới nhân khẩu thành công");
        alert.show();
        tennhankhau.clear();
        gioitinh.clear();
        quoctich.clear();
        sophongtaonhankhau.clear();
        ngaysinh.clear();

    }
    private void checkAllFieldsFilled(TextField[] textFields,Button buttontaomoi ) {
        boolean allFilled = true;
        for (TextField textField : textFields) {
            if (textField.getText().isEmpty()) {
                allFilled = false;
                break;
            }
        }

        buttontaomoi.setDisable(!allFilled);
    }
    @FXML
    void huy(ActionEvent event) {
        Stage a= (Stage) tennhankhau.getScene().getWindow();
        a.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TextField[] textFields1={tennhankhau,sophongtaonhankhau,quoctich,ngaysinh,gioitinh};
        for (TextField textField : textFields1) {
            textField.textProperty().addListener((observable, oldValue, newValue) -> checkAllFieldsFilled(textFields1,buttontaomoinhankhau));
        }
    }
}
