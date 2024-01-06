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

public class Taomoinhankhau implements Initializable {
    @FXML
    private Button buttontaomoinhankhau;

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
    private TextField cancuoccongdan;

    @FXML
    private ToggleGroup gioitinh;

    @FXML
    private RadioButton nam;
    @FXML
    private TextField sodienthoai;
    @FXML
    private RadioButton nu;
    private boolean isValidDateFormat(String date) {
        // Biểu thức chính quy cho định dạng dd/mm/yyyy
        String regex = "^\\d{2}/\\d{2}/\\d{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);

        return matcher.matches();
    }

    @FXML
    void taomoinhankhau(ActionEvent event) {
        if(isValidDateFormat(ngaysinh.getText())) {
            NhanKhau nhanKhau = new NhanKhau();
            nhanKhau.setTen(tennhankhau.getText());
            if(nam.isSelected())nhanKhau.setGioiTinh(1);
            else nhanKhau.setGioiTinh(0);
            nhanKhau.setSoDienThoai(sodienthoai.getText());
            nhanKhau.setCCCD(cancuoccongdan.getText());
            nhanKhau.setQuocTich(quoctich.getText());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String date = ngaysinh.getText();
            LocalDate datetime = LocalDate.parse(date, formatter);
            nhanKhau.setNgaySinh(Date.valueOf(datetime));
            nhanKhau.setChuHo(false);
            HoKhau hoKhau = HoKhauDao.getInstance().selectById(Integer.parseInt(sophongtaonhankhau.getText()), Integer.parseInt(sotang.getText()));
            try {
                hoKhau.setSoNhanKhau(hoKhau.getSoNhanKhau()+1);
                HoKhauDao.getInstance().update(hoKhau);
                nhanKhau.setHoKhau(hoKhau);
                nhanKhau.setTrangThai("Đang ở");
                NhanKhauDao.getInstance().save(nhanKhau);
                getData.getInstance().addNhankhau(nhanKhau);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Thành công");
                alert.setContentText("Tạo mới nhân khẩu thành công");
                alert.showAndWait();
                tennhankhau.clear();
                gioitinh.selectToggle(null);
                quoctich.clear();
                sotang.clear();
                sodienthoai.clear();
                cancuoccongdan.clear();
                sophongtaonhankhau.clear();
                ngaysinh.clear();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Thất bại");
                alert.setContentText("Không tìm thấy phòng");
                alert.showAndWait();
            }

        }
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
    @FXML
    void huy(ActionEvent event) {
        try {
            Stage ag0r1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example/Quanlynhankhau.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            ag0r1.setScene(scene);
            ag0r1.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TextField[] textFields1={sotang,tennhankhau,sophongtaonhankhau,quoctich,ngaysinh,sodienthoai,cancuoccongdan};
        for (TextField textField : textFields1) {
            textField.textProperty().addListener((observable, oldValue, newValue) -> checkAllFieldsFilled(textFields1,buttontaomoinhankhau));
        }
    }
}
