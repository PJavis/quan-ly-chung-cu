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
import org.example.Model.EntityAll.LichSuThayDoi;
import org.example.Model.EntityAll.NhanKhau;
import org.example.Model.Hibernatedao.HoKhauDao;
import org.example.Model.Hibernatedao.LichSuThayDoiDao;
import org.example.Model.Hibernatedao.NhanKhauDao;
import org.example.getData;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dieuchinhnhankhau implements Initializable {
    @FXML
    private Button buttoncapnhat;

    @FXML
    private CheckBox chuho;


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
        cancuoccongdan.setText(nhanKhau.getCCCD());
        sodienthoai.setText(nhanKhau.getSoDienThoai());
        if(nhanKhau.getGioiTinh()==1){
            nam.setSelected(true);
        }else nu.setSelected(true);
        sophong.setText(String.valueOf(nhanKhau.getHoKhau().getId()));
        sotang.setText(String.valueOf(nhanKhau.getHoKhau().getSoTang()));
        trangthai.setText(nhanKhau.getTrangThai());
        quoctich.setText(nhanKhau.getQuocTich());
        chuho.setSelected(nhanKhau.isChuHo());
        lydo.setText(nhanKhau.getLydo());
    }
    private boolean isValidDateFormat(String date) {
        // Biểu thức chính quy cho định dạng dd/mm/yyyy
        String regex = "^\\d{2}/\\d{2}/\\d{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);

        return matcher.matches();
    }

    @FXML
    private TextArea lydo;

    @FXML
        void dieuchinhnhankhau(ActionEvent event) {
         if(!isValidDateFormat(ngaysinh.getText())){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Thất bại");
            alert.setContentText("Vui lòng điền ngày sinh theo dạng dd/mm/yyyy");
            alert.showAndWait();
        }
        else {
             nhanKhau.setTen(tennhankhau.getText());
             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
             String date= ngaysinh.getText();
             LocalDate datetime = LocalDate.parse(date, formatter);
             nhanKhau.setNgaySinh(Date.valueOf(datetime));
             if(nam.isSelected())nhanKhau.setGioiTinh(1);
             else nhanKhau.setGioiTinh(0);
             nhanKhau.setTrangThai(trangthai.getText());
             nhanKhau.setQuocTich(quoctich.getText());
            nhanKhau.setLydo(lydo.getText());
             HoKhau hoKhau1=nhanKhau.getHoKhau();
             if(Integer.parseInt(sotang.getText())== hoKhau1.getSoTang()&&Integer.parseInt(sophong.getText())== hoKhau1.getId()){
                 if(nhanKhau.isChuHo()){
                     hoKhau1.setTenchuho(tennhankhau.getText());
                     hoKhau1.setSoDienThoai(sodienthoai.getText());
                     HoKhauDao.getInstance().update(hoKhau1);
                     getData.getInstance().updateHokhau(hoKhau1);
                 }
                 NhanKhauDao.getInstance().update(nhanKhau);
                 getData.getInstance().setNhankhau(nhanKhau);
                 Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                 alert1.setHeaderText("Thành công");
                 alert1.setContentText("Điều chỉnh nhân khẩu thành công");
                 alert1.showAndWait();
                 try {
                     Stage ag0r1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                     FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example/Views/Quanlynhankhau.fxml"));
                     Parent root = loader.load();
                     Scene scene = new Scene(root);
                     ag0r1.setScene(scene);
                     ag0r1.show();
                 } catch (Exception e) {
                     System.out.println(e.getMessage());
                 }
             }else {
                 HoKhau hoKhau2=HoKhauDao.getInstance().selectById(Integer.parseInt(sophong.getText()),Integer.parseInt(sotang.getText()));
                 if(hoKhau2==null){
                     Alert alert = new Alert(Alert.AlertType.ERROR);
                     alert.setHeaderText("Thất bại");
                     alert.setContentText("Không tìm thấy phòng");
                     alert.showAndWait();
                 } else if (nhanKhau.isChuHo()) {
                     Alert alert = new Alert(Alert.AlertType.ERROR);
                     alert.setHeaderText("Thất bại");
                     alert.setContentText("Không thể đổi chủ hộ sang phòng khác");
                     alert.showAndWait();
                 }else {
                     nhanKhau.setHoKhau(hoKhau2);
                     NhanKhauDao.getInstance().update(nhanKhau);
                     getData.getInstance().setNhankhau(nhanKhau);

                     hoKhau1.setSoNhanKhau(hoKhau1.getSoNhanKhau()-1);
                     HoKhauDao.getInstance().update(hoKhau1);
                     getData.getInstance().updateHokhau(hoKhau1);
                     LichSuThayDoi lichSuThayDoi=new LichSuThayDoi();
                     lichSuThayDoi.setHoKhau(hoKhau1);
                     lichSuThayDoi.setNgayThayDoi(Date.valueOf(LocalDate.now()));
                     lichSuThayDoi.setThayDoi("Xóa nhân khẩu có tên là "+nhanKhau.getTen());
                     LichSuThayDoiDao.getInstance().save(lichSuThayDoi);

                     hoKhau2.setSoNhanKhau(hoKhau2.getSoNhanKhau()+1);
                     HoKhauDao.getInstance().update(hoKhau2);
                     getData.getInstance().updateHokhau(hoKhau2);
                     LichSuThayDoi lichSuThayDoi1=new LichSuThayDoi();
                     lichSuThayDoi1.setHoKhau(hoKhau2);
                     lichSuThayDoi1.setNgayThayDoi(Date.valueOf(LocalDate.now()));
                     lichSuThayDoi1.setThayDoi("Thêm nhân khẩu có tên là "+nhanKhau.getTen());
                     LichSuThayDoiDao.getInstance().save(lichSuThayDoi1);

                     Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                     alert1.setHeaderText("Thành công");
                     alert1.setContentText("Điều chỉnh nhân khẩu thành công");
                     alert1.showAndWait();
                     try {
                         Stage ag0r1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                         FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example/Views/Quanlynhankhau.fxml"));
                         Parent root = loader.load();
                         Scene scene = new Scene(root);
                         ag0r1.setScene(scene);
                         ag0r1.show();
                     } catch (Exception e) {
                         System.out.println(e.getMessage());
                     }
                 }
             }





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
                HoKhau hoKhau = nhanKhau.getHoKhau();
                hoKhau.setSoNhanKhau(hoKhau.getSoNhanKhau()-1);
                HoKhauDao.getInstance().update(hoKhau);
                getData.getInstance().updateHokhau(hoKhau);
                LichSuThayDoi lichSuThayDoi=new LichSuThayDoi();
                lichSuThayDoi.setHoKhau(hoKhau);
                lichSuThayDoi.setNgayThayDoi(Date.valueOf(LocalDate.now()));
                lichSuThayDoi.setThayDoi("Xóa nhân khẩu có tên là "+nhanKhau.getTen());
                LichSuThayDoiDao.getInstance().save(lichSuThayDoi);
                NhanKhauDao.getInstance().delete(nhanKhau);
                getData.getInstance().removeNhankhau(nhanKhau);
                try {
                    Stage ag0r1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example/Views/Quanlynhankhau.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    ag0r1.setScene(scene);
                    ag0r1.show();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                }
        }

    }
    @FXML
    void quaylai(ActionEvent event) {
        try {
            Stage ag0r1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example/Views/Quanlynhankhau.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            ag0r1.setScene(scene);
            ag0r1.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
        TextField[] textFields1={sotang,tennhankhau,sophong,trangthai,quoctich,ngaysinh,sodienthoai,cancuoccongdan};
        for (TextField textField : textFields1) {
            textField.textProperty().addListener((observable, oldValue, newValue) -> checkAllFieldsFilled(textFields1,buttoncapnhat));
        }
    }
}
