package org.example.ConTroller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.EntityAll.HoKhau;
import org.example.EntityAll.NhanKhau;
import org.example.Hibernatedao.HoKhauDao;
import org.example.Hibernatedao.NhanKhauDao;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class Taomoihokhau implements Initializable {

        @FXML
        private Button buttontaomoihokhau;

        @FXML
        private TextField dientichphong;

        @FXML
        private TextField gioitinh;

        @FXML
        private TextField ngaysinh;

        @FXML
        private TextField quoctich;

        @FXML
        private TextField sophong;

        @FXML
        private TextField sotang;

        @FXML
        private TextField tenchuho;

        @FXML
        void huy(ActionEvent event) {
Stage a=(Stage) tenchuho.getScene().getWindow();
a.close();
        }

        @FXML
        void taomoi(ActionEvent event) {
                HoKhau hoKhau=new HoKhau();
                hoKhau.setId(Integer.parseInt(sophong.getText()));
                hoKhau.setDienTichPhong(Double.parseDouble(dientichphong.getText()));
                try {
                HoKhauDao.getInstance().save(hoKhau);
                NhanKhau nhanKhau=new NhanKhau();
                nhanKhau.setTen(tenchuho.getText());
                nhanKhau.setGioiTinh(gioitinh.getText());
                nhanKhau.setQuocTich(quoctich.getText());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String date= ngaysinh.getText();
                LocalDate datetime = LocalDate.parse(date, formatter);
                nhanKhau.setNgaySinh(Date.valueOf(datetime));
                nhanKhau.setChuHo(true);
                nhanKhau.setHoKhau(Integer.parseInt(sophong.getText()));
                nhanKhau.setTrangThai("Đang ở");
                NhanKhauDao.getInstance().save(nhanKhau);
                Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Thành công");
                alert.setContentText("Tạo mới nhân khẩu thành công");
                alert.show();}
                catch (Exception e){
                        Alert alert=new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Thất bại");
                        alert.setContentText("Số phòng đã tồn tại");
                        alert.show();
                }
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
                TextField[] textFields2={sophong,dientichphong,tenchuho,gioitinh,quoctich,ngaysinh};
                for (TextField textField : textFields2) {
                        textField.textProperty().addListener((observable, oldValue, newValue) -> checkAllFieldsFilled(textFields2,buttontaomoihokhau));
                }
        }
}


