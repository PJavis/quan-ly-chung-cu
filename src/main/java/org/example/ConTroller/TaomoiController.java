package org.example.ConTroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.EntityAll.DanhSachKhoanPhi;
import org.example.EntityAll.HoKhau;
import org.example.EntityAll.NhanKhau;
import org.example.Hibernatedao.DanhSachKhoanPhiDao;
import org.example.Hibernatedao.HoKhauDao;
import org.example.Hibernatedao.NhanKhauDao;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class TaomoiController implements Initializable {

    @FXML
    private Button buttontaomoikhoanphi;

    @FXML
    private Button buttontaomoinhankhau;

    @FXML
    private Button buttontaomoiphong;

    @FXML
    private CheckBox co;

    @FXML
    private TextField dientichphong;

    @FXML
    private TextField gioitinh;

    @FXML
    private TextField hannop;

    @FXML
    private TextField loaikhoanphi;

    @FXML
    private TextField ngaysinh;

    @FXML
    private TextField quoctich;

    @FXML
    private TextField sophongtaonhankhau;

    @FXML
    private TextField sophongtaophong;

    @FXML
    private TextField sotien;

    @FXML
    private TabPane tabpanetaomoi;

    @FXML
    private TextField tenkhoanphi;

    @FXML
    private TextField tennhankhau;

    @FXML
    void taomoikhoanphi(ActionEvent event) {
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

    }

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
        if(co.isSelected()){
            nhanKhau.setChuHo(true);
        }else nhanKhau.setChuHo(false);
        nhanKhau.setHoKhau(HoKhauDao.getInstance().selectById(Integer.parseInt(sophongtaonhankhau.getText())));
        nhanKhau.setTrangThai("Đang ở");
        NhanKhauDao.getInstance().save(nhanKhau);

    }

    @FXML
    void taomoiphong(ActionEvent event) {
        HoKhau hoKhau=new HoKhau();
        hoKhau.setId(Integer.parseInt(sophongtaophong.getText()));
        hoKhau.setDienTichPhong(Double.parseDouble(dientichphong.getText()));
        HoKhauDao.getInstance().save(hoKhau);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TextField[] textFields={tenkhoanphi,loaikhoanphi,sotien,hannop};
        for (TextField textField : textFields) {
            textField.textProperty().addListener((observable, oldValue, newValue) -> checkAllFieldsFilled(textFields,buttontaomoikhoanphi));
        }
        TextField[] textFields1={tennhankhau,sophongtaonhankhau,quoctich,ngaysinh,gioitinh};
        for (TextField textField : textFields1) {
            textField.textProperty().addListener((observable, oldValue, newValue) -> checkAllFieldsFilled(textFields1,buttontaomoinhankhau));
        }
        TextField[] textFields2={sophongtaophong,dientichphong};
        for (TextField textField : textFields2) {
            textField.textProperty().addListener((observable, oldValue, newValue) -> checkAllFieldsFilled(textFields2,buttontaomoiphong));
        }
    }
}