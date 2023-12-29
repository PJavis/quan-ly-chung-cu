package org.example.ConTroller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Taomoihokhau implements Initializable {

        @FXML
        private Button buttontaomoihokhau;

        @FXML
        private TextField dientichphong;


        @FXML
        private TextField cancuoccongdan;
        @FXML
        private ToggleGroup gioitinh;

        @FXML
        private RadioButton nam;
        @FXML
        private RadioButton nu;
        @FXML
        private TextField sodienthoai;
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
        private boolean isValidDateFormat(String date) {
                // Biểu thức chính quy cho định dạng dd/mm/yyyy
                String regex = "^\\d{2}/\\d{2}/\\d{4}$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(date);

                return matcher.matches();
        }
        @FXML
        void taomoi(ActionEvent event) {
                if(isValidDateFormat(ngaysinh.getText())){
                HoKhau hoKhau=new HoKhau();
                hoKhau.setId(Integer.parseInt(sophong.getText()));
                hoKhau.setDienTichPhong(Double.parseDouble(dientichphong.getText()));
                hoKhau.setSoTang(Integer.parseInt(sotang.getText()));
                hoKhau.setTenchuho(tenchuho.getText());
                hoKhau.setSoDienThoai(sodienthoai.getText());
                hoKhau.setSoNhanKhau(1);
                try {
                        LocalDate currentDate = LocalDate.now();
                        hoKhau.setNgaytaohokhau(Date.valueOf(currentDate));
                        HoKhauDao.getInstance().save(hoKhau);
                        getData.getInstance().addHokhau(hoKhau);
                NhanKhau nhanKhau=new NhanKhau();
                nhanKhau.setTen(tenchuho.getText());
                if(nam.isSelected())
                nhanKhau.setGioiTinh(1);
                else nhanKhau.setGioiTinh(0);
                nhanKhau.setSoDienThoai(sodienthoai.getText());
                nhanKhau.setCCCD(cancuoccongdan.getText());
                nhanKhau.setQuocTich(quoctich.getText());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String date= ngaysinh.getText();
                LocalDate datetime = LocalDate.parse(date, formatter);
                nhanKhau.setNgaySinh(Date.valueOf(datetime));
                nhanKhau.setChuHo(true);
                nhanKhau.setSophong(hoKhau.getId());
                nhanKhau.setSotang(hoKhau.getSoTang());
                nhanKhau.setTrangThai("Đang ở");
                NhanKhauDao.getInstance().save(nhanKhau);
                getData.getInstance().addNhankhau(nhanKhau);
                Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Thành công");
                alert.setContentText("Tạo mới hộ khẩu thành công");
                alert.showAndWait();
                sophong.clear();
                sotang.clear();
                dientichphong.clear();
                tenchuho.clear();
                sodienthoai.clear();
                cancuoccongdan.clear();
                ngaysinh.clear();
                gioitinh.selectToggle(null);
                quoctich.clear();}
                catch (Exception e){
                        Alert alert=new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Thất bại");
                        alert.setContentText("Số phòng đã tồn tại");
                        alert.showAndWait();
                }}
                else {
                        Alert alert=new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Thất bại");
                        alert.setContentText("Vui lòng điền ngày sinh theo dạng dd/mm/yyyy");
                        alert.showAndWait();
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
                TextField[] textFields2={sophong,dientichphong,tenchuho,cancuoccongdan,sodienthoai,quoctich,ngaysinh,sotang};
                for (TextField textField : textFields2) {
                        textField.textProperty().addListener((observable, oldValue, newValue) -> checkAllFieldsFilled(textFields2,buttontaomoihokhau));
                }
        }
}


