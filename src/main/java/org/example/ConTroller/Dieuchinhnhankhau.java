package org.example.ConTroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.EntityAll.NhanKhau;
import org.example.Hibernatedao.HoKhauDao;
import org.example.Hibernatedao.NhanKhauDao;
import org.example.getData;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Dieuchinhnhankhau {

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
        private TextField trangthai;
        private NhanKhau nhanKhau;

    public void setNhanKhau(NhanKhau nhanKhau) {
        this.nhanKhau = nhanKhau;
        tennhankhau.setText(nhanKhau.getTen());
        SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ngaysinh.setText(newDateFormat.format(nhanKhau.getNgaySinh()));
        gioitinh.setText(nhanKhau.getGioiTinh());
        sophong.setText(String.valueOf(nhanKhau.getHoKhau()));
        trangthai.setText(nhanKhau.getTrangThai());
        quoctich.setText(nhanKhau.getQuocTich());
        co.setSelected(nhanKhau.isChuHo());
    }

    @FXML
        void dieuchinhnhankhau(ActionEvent event) {
        getData.getInstance().removeNhankhau(nhanKhau);
            nhanKhau.setTen(tennhankhau.getText());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String date= ngaysinh.getText();
            LocalDate datetime = LocalDate.parse(date, formatter);
            nhanKhau.setNgaySinh(Date.valueOf(datetime));
            nhanKhau.setGioiTinh(gioitinh.getText());
            nhanKhau.setHoKhau(HoKhauDao.getInstance().selectById(Integer.parseInt(sophong.getText())).getId());
            nhanKhau.setTrangThai(trangthai.getText());
            nhanKhau.setQuocTich(quoctich.getText());
            nhanKhau.setChuHo(co.isSelected());
            NhanKhauDao.getInstance().update(nhanKhau);
        getData.getInstance().addNhankhau(nhanKhau);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Thành công");
            alert.setContentText("Điều chỉnh nhân khẩu thành công");
            alert.showAndWait();
        Stage ag0r = (Stage) ((Node) event.getSource()).getScene().getWindow();
            ag0r.close();
        }
    @FXML
    void xoanhankhau(ActionEvent event) {
        Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Bạn chắc chắn muốn xóa nhân khẩu ?");
        alert.setContentText("Khi đó thông tin về nhân khẩu sẽ không còn");
        ButtonType buttonTypeOK = new ButtonType("OK", ButtonType.OK.getButtonData());
        // Thêm nút "Hủy"
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonType.CANCEL.getButtonData());

        alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeOK) {
            NhanKhauDao.getInstance().delete(nhanKhau.getIdNguoiDan());
            getData.getInstance().removeNhankhau(nhanKhau);
        }
        Stage ag0r = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ag0r.close();
    }

    }