package org.example.ConTroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import org.example.EntityAll.KhoanPhi;
import org.example.Hibernatedao.KhoanPhiDao;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Quanlykhoanphi implements Initializable {

    @FXML
    private TableColumn<KhoanPhi, Void> chitiet;

    @FXML
    private TextField hannop;

    @FXML
    private ComboBox<String> loaikhoanphi;
    @FXML
    private TableColumn<KhoanPhi, String> hannoptable;
    @FXML
    private TableColumn<KhoanPhi, String> loaikhoanphitable;

    @FXML
    private TableColumn<KhoanPhi, Integer> sothutu;
    @FXML
    private TextField sotien;


    @FXML
    private TableColumn<KhoanPhi, Double> sotiendanop;

    @FXML
    private TextField tenkhoanphi;
    @FXML
    private TableColumn<KhoanPhi, String> tenkhoanphitable;
    @FXML
    private TextField timkiem;

    @FXML
    void dieuchinh(ActionEvent event) {

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
if(hannop.getText().isEmpty()||tenkhoanphi.getText().isEmpty()||loaikhoanphi.getSelectionModel().isEmpty()){
    Alert alert=new Alert(Alert.AlertType.ERROR);
    alert.setHeaderText("Thất bại");
    alert.setContentText("Vui lòng điền đầy đủ thông tin");
    alert.showAndWait();
} else if (!isValidDateFormat(hannop.getText())) {
    Alert alert=new Alert(Alert.AlertType.ERROR);
    alert.setHeaderText("Thất bại");
    alert.setContentText("Vui lòng điền hạn nộp theo dạng dd/mm/yyyy");
    alert.showAndWait();
}else{
    KhoanPhi khoanPhi=new KhoanPhi();
    khoanPhi.setTenKhoanPhi(tenkhoanphi.getText());
    khoanPhi.setTongsotien(0);
    khoanPhi.setLoaiKhoanPhi(loaikhoanphi.getSelectionModel().getSelectedItem());
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String date = hannop.getText();
    LocalDate datetime = LocalDate.parse(date, formatter);
    khoanPhi.setKetThuc(Date.valueOf(datetime));
    khoanPhi.setGiaTri(Double.parseDouble(sotien.getText()));
    LocalDate currentDate = LocalDate.now();
    khoanPhi.setBatDau(Date.valueOf(currentDate));
    KhoanPhiDao.getInstance().save(khoanPhi);
    Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
    alert.setHeaderText("Thành công");
    alert.setContentText("Tạo khoản phí mới thành công");
    alert.showAndWait();
    tenkhoanphi.clear();
    hannop.clear();
    sotien.clear();
    loaikhoanphi.getEditor().clear();
}
    }

    @FXML
    void xoa(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}
