package org.example.ConTroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.EntityAll.HoKhau;
import org.example.EntityAll.NhanKhau;
import org.example.Hibernatedao.HoKhauDao;
import org.example.Hibernatedao.NhanKhauDao;
import org.example.getData;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dieuchinhnhankhau {


    @FXML
    private CheckBox chuho;


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
        private TextField sotang;


        @FXML
        private TextField trangthai;
        private NhanKhau nhanKhau;

    public void setNhanKhau(NhanKhau nhanKhau) {
        this.nhanKhau = nhanKhau;
        tennhankhau.setText(nhanKhau.getTen());
        ngaysinh.setText(nhanKhau.getFormattedDate());
        gioitinh.setText(nhanKhau.getGioiTinh());
        sophong.setText(String.valueOf(nhanKhau.getSophong()));
        sotang.setText(String.valueOf(nhanKhau.getSotang()));
        trangthai.setText(nhanKhau.getTrangThai());
        quoctich.setText(nhanKhau.getQuocTich());
        chuho.setSelected(nhanKhau.isChuHo());
    }
    private boolean isValidDateFormat(String date) {
        // Biểu thức chính quy cho định dạng dd/mm/yyyy
        String regex = "^\\d{2}/\\d{2}/\\d{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);

        return matcher.matches();
    }
    @FXML
        void dieuchinhnhankhau(ActionEvent event) {
        if(isValidDateFormat(ngaysinh.getText())){
            nhanKhau.setTen(tennhankhau.getText());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String date= ngaysinh.getText();
            LocalDate datetime = LocalDate.parse(date, formatter);
            nhanKhau.setNgaySinh(Date.valueOf(datetime));
            nhanKhau.setGioiTinh(gioitinh.getText());
            HoKhau hoKhau1=HoKhauDao.getInstance().selectById(Integer.parseInt(sophong.getText()),Integer.parseInt(sotang.getText()));

        try {
            nhanKhau.setSophong(hoKhau1.getId());
            nhanKhau.setSotang(hoKhau1.getSoTang());
            nhanKhau.setTrangThai(trangthai.getText());
            nhanKhau.setQuocTich(quoctich.getText());
            NhanKhauDao.getInstance().update(nhanKhau);
            getData.getInstance().setNhankhau(nhanKhau);
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setHeaderText("Thành công");
            alert1.setContentText("Điều chỉnh nhân khẩu thành công");
            alert1.showAndWait();
            Stage ag0r = (Stage) ((Node) event.getSource()).getScene().getWindow();
            ag0r.close();
        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Thất bại");
            alert.setContentText("Không tìm thấy phòng");
            alert.showAndWait();

        }}
        else {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Thất bại");
            alert.setContentText("Vui lòng điền ngày sinh theo dạng dd/mm/yyyy");
            alert.showAndWait();
        }

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
            if(nhanKhau.isChuHo()){
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setHeaderText("Thất bại");
                alert1.setContentText("Bạn không được xóa chủ hộ");
                alert1.showAndWait();
            }
            else {
                Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                alert2.setHeaderText("Thành công");
                alert2.setContentText("Xóa nhân khẩu thành công");
                alert2.showAndWait();
                NhanKhauDao.getInstance().delete(nhanKhau);
            getData.getInstance().removeNhankhau(nhanKhau);
                Stage ag0r = (Stage) ((Node) event.getSource()).getScene().getWindow();
                ag0r.close();}
        }

    }

    }
