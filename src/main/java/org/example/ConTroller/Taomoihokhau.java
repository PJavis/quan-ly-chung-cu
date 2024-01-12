package org.example.ConTroller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.Model.EntityAll.HoKhau;
import org.example.Model.EntityAll.NhanKhau;
import org.example.Model.Hibernatedao.HoKhauDao;
import org.example.Model.Hibernatedao.NhanKhauDao;
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
                try {
                        Stage ag0r1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example/Views/Quanlyphong.fxml"));
                        Parent root = loader.load();
                        Scene scene = new Scene(root);
                        ag0r1.setScene(scene);
                        ag0r1.show();
                } catch (Exception e) {
                        System.out.println(e.getMessage());
                }
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
                if (isValidDateFormat(ngaysinh.getText())) {
                        try {
                                HoKhau hoKhau = createHoKhau();
                                NhanKhau nhanKhau = createNhanKhau(hoKhau);

                                HoKhauDao.getInstance().save(hoKhau);
                                getData.getInstance().addHokhau(hoKhau);

                                NhanKhauDao.getInstance().save(nhanKhau);
                                getData.getInstance().addNhankhau(nhanKhau);

                                showSuccessAlert("Tạo mới hộ khẩu thành công");

                                clearInputFields();
                        } catch (Exception e) {
                                showErrorAlert("Số phòng đã tồn tại");
                        }
                } else {
                        showErrorAlert("Vui lòng điền ngày sinh theo dạng dd/mm/yyyy");
                }
        }

        private HoKhau createHoKhau() {
                HoKhau hoKhau = new HoKhau();
                hoKhau.setId(Integer.parseInt(sophong.getText()));
                hoKhau.setDienTichPhong(Double.parseDouble(dientichphong.getText()));
                hoKhau.setSoTang(Integer.parseInt(sotang.getText()));
                hoKhau.setTenchuho(tenchuho.getText());
                hoKhau.setSoDienThoai(sodienthoai.getText());
                hoKhau.setNgaytaohokhau(new Date(System.currentTimeMillis()));
                hoKhau.setSoNhanKhau(1);
                return hoKhau;
        }

        private NhanKhau createNhanKhau(HoKhau hoKhau) {
                NhanKhau nhanKhau = new NhanKhau();
                nhanKhau.setTen(tenchuho.getText());
                nhanKhau.setGioiTinh(nam.isSelected() ? 1 : 0);
                nhanKhau.setSoDienThoai(sodienthoai.getText());
                nhanKhau.setCCCD(cancuoccongdan.getText());
                nhanKhau.setQuocTich(quoctich.getText());

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String date = ngaysinh.getText();
                LocalDate datetime = LocalDate.parse(date, formatter);

                nhanKhau.setNgaySinh(Date.valueOf(datetime));
                nhanKhau.setChuHo(true);
                nhanKhau.setHoKhau(hoKhau);
                nhanKhau.setTrangThai("Đang ở");

                return nhanKhau;
        }

        private void showSuccessAlert(String message) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Thành công");
                alert.setContentText(message);
                alert.showAndWait();
        }

        private void showErrorAlert(String message) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Thất bại");
                alert.setContentText(message);
                alert.showAndWait();
        }

        private void clearInputFields() {
                sophong.clear();
                sotang.clear();
                dientichphong.clear();
                tenchuho.clear();
                sodienthoai.clear();
                cancuoccongdan.clear();
                ngaysinh.clear();
                gioitinh.selectToggle(null);
                quoctich.clear();
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


