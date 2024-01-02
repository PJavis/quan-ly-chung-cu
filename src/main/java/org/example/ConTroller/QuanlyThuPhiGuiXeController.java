// QuanlyThuPhiGuiXeController.java
package org.example.ConTroller;

import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.EntityAll.PhuongTien;
import org.example.Hibernatedao.PhuongTienDao;
import org.example.getData;

import java.util.Comparator;
import java.util.List;

public class QuanlyThuPhiGuiXeController {

    @FXML
    private TextField searchTextField;

    @FXML
    private ComboBox<String> loaiphuongtien;

    @FXML
    private TextField sotangField;

    @FXML
    private TextField sophongField;

    @FXML
    private TextField sotang;

    @FXML
    private TextField sophong;

    @FXML
    private Button taomoiButton;

    @FXML
    private TextField biensoxe;

    @FXML
    private TableView<PhuongTien> vehicleTableView;

    @FXML
    private TableColumn<PhuongTien, String> biensoxeColumn;

    @FXML
    private TableColumn<PhuongTien, String> loaixeColumn;

    @FXML
    private TableColumn<PhuongTien, Integer> sotangColumn;

    @FXML
    private TableColumn<PhuongTien, Integer> sophongColumn;

    private  ObservableList<PhuongTien> phuongTiens;

    private List<PhuongTien> phuongTienList = getData.getInstance().getPhuongTiens();

    private String selectedType;

    public void initialize() {
        // Khởi tạo cột và cài đặt dữ liệu cho TableView
        phuongTiens = FXCollections.observableArrayList(phuongTienList);
        phuongTienList.sort(
                Comparator.comparingInt(PhuongTien::getSoTang)
                        .thenComparingInt(PhuongTien::getSoPhong)
        );

        biensoxeColumn.setCellValueFactory(new PropertyValueFactory<>("bienSoXe"));
        loaixeColumn.setCellValueFactory(new PropertyValueFactory<>("loaiPhuongTien"));
        sotangColumn.setCellValueFactory(new PropertyValueFactory<>("soTang"));
        sophongColumn.setCellValueFactory(new PropertyValueFactory<>("soPhong"));
        loaiphuongtien.setItems(FXCollections.observableArrayList("Xe Máy", "Ô Tô", "Xe Đạp"));

        // Thiết lập chính sách tự động resize cho TableView
        vehicleTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void searchButtonClicked(ActionEvent event) {
        String bienso = searchTextField.getText();

        String sotangtemp = sotangField.getText();
        String sophongtemp = sophongField.getText();

        try {
            int sotang1 = (sotangtemp.isEmpty()) ? 0 : Integer.parseInt(sotangtemp);
            int sophong1 = (sophongtemp.isEmpty()) ? 0 : Integer.parseInt(sophongtemp);

            updateTableView(bienso, sotang1, sophong1);
        } catch (NumberFormatException e) {
            // Xử lý khi có lỗi chuyển đổi chuỗi thành số
            showAlert("Lỗi", "Vui lòng nhập số hợp lệ cho số tầng và số phòng.");
        }
    }

    private void setupComboBoxEvent() {
        loaiphuongtien.setOnAction(event -> selectedType = loaiphuongtien.getValue());
    }

    private void updateTableView(String bienso, int sotang1, int sophong1) {
        // Giả sử có một danh sách xe tìm kiếm
        ObservableList<PhuongTien> searchResult = null;
        if (sotang1 == 0) {
            searchResult = FXCollections.observableArrayList(PhuongTienDao.getInstance().selectByName(bienso));
        }
        else {
            searchResult = FXCollections.observableArrayList(PhuongTienDao.getInstance().selectByHoKhau(sotang1,sophong1));
        }
        vehicleTableView.setItems(searchResult);
    }

    public void taomoi(ActionEvent actionEvent) {
        // Lấy thông tin từ các trường nhập liệu
        String bienSoXe = biensoxe.getText();
        String loaiPhuongTien = loaiphuongtien.getValue();
        String sotangTemp = sotang.getText();
        String sophongTemp = sophong.getText();

        try {
            // Kiểm tra xem các trường thông tin có được nhập hay không
            if (bienSoXe.isEmpty() || loaiPhuongTien == null || sotangTemp.isEmpty() || sophongTemp.isEmpty()) {
                showAlert("Lỗi", "Vui lòng nhập đầy đủ thông tin.");
                return;
            }

            // Chuyển đổi số tầng và số phòng từ chuỗi sang số
            int soTang1 = Integer.parseInt(sotangTemp);
            int soPhong1 = Integer.parseInt(sophongTemp);

            // Tạo mới một đối tượng PhuongTien
            PhuongTien newPhuongTien = new PhuongTien(loaiPhuongTien, bienSoXe, 0, soTang1, soPhong1);

            // Thêm mới vào danh sách và cập nhật TableView
            phuongTiens.add(newPhuongTien);
            PhuongTienDao.getInstance().save(newPhuongTien);
            showAlert("Thành công", "Đăng ký phương tiện mới thành công.");
            vehicleTableView.setItems(phuongTiens);

            // (Optional) Clear các trường nhập liệu sau khi thêm mới
            clearInputFields();
        } catch (NumberFormatException e) {
            showAlert("Lỗi", "Vui lòng nhập số hợp lệ cho số tầng và số phòng.");
        }
    }

    private void clearInputFields() {
        biensoxe.clear();
        loaiphuongtien.getSelectionModel().clearSelection();
        sotang.clear();
        sophong.clear();
    }

}
