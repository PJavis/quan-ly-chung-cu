// QuanlyThuPhiGuiXeController.java
package org.example.ConTroller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.Model.EntityAll.HoKhau;
import org.example.Model.EntityAll.LichSuGiaoDichPhiGuiXe;
import org.example.Model.EntityAll.NhanKhau;
import org.example.Model.EntityAll.PhuongTien;
import org.example.Model.Hibernatedao.HoKhauDao;
import org.example.Model.Hibernatedao.LichSuGiaoDichPhiGuiXeDao;
import org.example.Model.Hibernatedao.NhanKhauDao;
import org.example.Model.Hibernatedao.PhuongTienDao;
import org.example.getData;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class QuanlyThuPhiGuiXeController {
    @FXML
    private TextField tenchuxe;

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
    private TextField biensoxe;

    @FXML
    private TableView<PhuongTien> vehicleTableView;

    @FXML
    private TableColumn<PhuongTien, String> biensoxeColumn;

    @FXML
    private TableColumn<PhuongTien, String> chuxeColumn;
    @FXML
    private TableColumn<PhuongTien, Double> phiguixeColumn;
    @FXML
    private TableColumn<PhuongTien, Double> phidanopColumn;

    @FXML
    private TableColumn<PhuongTien, String> loaixeColumn;

    @FXML
    private TableColumn<PhuongTien, Integer> sotangColumn;

    @FXML
    private TableColumn<PhuongTien, Integer> sophongColumn;

    @FXML
    public TableColumn<PhuongTien, Void> deleteColumn;

    private  ObservableList<PhuongTien> phuongTiens;

    private List<PhuongTien> phuongTienList = getData.getInstance().getPhuongTiens();

    private String selectedType;

    public void initialize() {
        // Khởi tạo cột và cài đặt dữ liệu cho TableView
        phuongTiens = FXCollections.observableArrayList(phuongTienList);
        phuongTiens.sort(
                Comparator.comparingInt(PhuongTien::getSoTang)
                        .thenComparingInt(PhuongTien::getSoPhong)
        );

        biensoxeColumn.setCellValueFactory(new PropertyValueFactory<>("bienSoXe"));
        loaixeColumn.setCellValueFactory(new PropertyValueFactory<>("loaiPhuongTien"));
        sophongColumn.setCellValueFactory(cellData -> {
            HoKhau hoKhau = cellData.getValue().getHoKhau();
            return hoKhau != null ? new SimpleIntegerProperty(hoKhau.getId()).asObject() : null;
        });

        sotangColumn.setCellValueFactory(cellData -> {
            HoKhau hoKhau = cellData.getValue().getHoKhau();
            return hoKhau != null ? new SimpleIntegerProperty(hoKhau.getSoTang()).asObject() : null;
        });
        loaiphuongtien.setItems(FXCollections.observableArrayList("Xe Máy", "Ô Tô", "Xe Đạp"));
        chuxeColumn.setCellValueFactory(new PropertyValueFactory<>("tenChuXe"));
        phiguixeColumn.setCellValueFactory(new PropertyValueFactory<>("DecimalFormatphigui"));
        phidanopColumn.setCellValueFactory(new PropertyValueFactory<>("soTienDaNop"));

        deleteColumn.setCellFactory(param -> new TableCell<PhuongTien, Void>() {
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    Button deleteButton = new Button("Xoá");
                    deleteButton.setOnAction(event -> {

                        Alert alert=new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText("Bạn chắc chắn muốn xóa phương tiện?");
                        alert.setContentText("Khi đó thông tin về phương tiện sẽ không còn");

                        ButtonType buttonTypeOK = new ButtonType("OK", ButtonType.OK.getButtonData());
                        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonType.CANCEL.getButtonData());

                        alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);

                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.isPresent() && result.get() == buttonTypeOK) {
                            PhuongTien phuongTien = getTableView().getItems().get(getIndex());
                            // Thực hiện logic xoá tại đây
                            List<LichSuGiaoDichPhiGuiXe> lichSuGiaoDichPhiGuiXes= LichSuGiaoDichPhiGuiXeDao.getInstance().selectByCondition(phuongTien);
                            for(LichSuGiaoDichPhiGuiXe lichSuGiaoDichPhiGuiXe:lichSuGiaoDichPhiGuiXes){
                                LichSuGiaoDichPhiGuiXeDao.getInstance().delete(lichSuGiaoDichPhiGuiXe);
                            }
                            getData.getInstance().removePhuongTien(phuongTien);
                            PhuongTienDao.getInstance().delete(phuongTien);

                            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                            alert1.setHeaderText("Thành công");
                            alert1.setContentText("Xóa phương tiện thành công");
                            alert1.showAndWait();

                            phuongTiens = FXCollections.observableArrayList(phuongTienList);
                            vehicleTableView.setItems(phuongTiens);
                        }
                    });
                    setGraphic(deleteButton);
                }
            }
        });

        // Thiết lập chính sách tự động resize cho TableView
        vehicleTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        vehicleTableView.setItems(phuongTiens);
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
            HoKhau hoKhau= HoKhauDao.getInstance().selectById(sotang1,sophong1);
            searchResult = FXCollections.observableArrayList(PhuongTienDao.getInstance().selectByHoKhau(hoKhau));
        }
        vehicleTableView.setItems(searchResult);
    }

    public void taomoi(ActionEvent actionEvent) {
        // Lấy thông tin từ các trường nhập liệu
        String bienSoXe = biensoxe.getText();
        String loaiPhuongTien = loaiphuongtien.getValue();
        String sotangTemp = sotang.getText();
        String sophongTemp = sophong.getText();
        String chuxe = tenchuxe.getText();

        try {
            // Kiểm tra xem các trường thông tin có được nhập hay không
            if (bienSoXe.isEmpty() || loaiPhuongTien == null
                    || sotangTemp.isEmpty() || sophongTemp.isEmpty()
                    || chuxe.isEmpty()) {
                showAlert("Lỗi", "Vui lòng nhập đầy đủ thông tin.");
                return;
            }
            HoKhau hoKhau = null;
            // Chuyển đổi số tầng và số phòng từ chuỗi sang số
            try {
                int soTang1 = Integer.parseInt(sotangTemp);
                int soPhong1 = Integer.parseInt(sophongTemp);

                // Nếu chuyển đổi thành công, tiếp tục xử lý
                hoKhau = HoKhauDao.getInstance().selectById(soPhong1, soTang1);

                // Tiếp tục thực hiện các thao tác khác với hoKhau nếu cần
            } catch (NumberFormatException e) {
                // Xử lý khi chuyển đổi không thành công
                showAlert("Lỗi", "Xin hãy nhập số tầng và số phòng là số nguyên dương.");
            }

            // Tạo mới một đối tượng PhuongTien
            double phiguixe = 0;
            switch (loaiPhuongTien.toLowerCase()) {
                case "xe máy":
                    phiguixe = 70000;
                    break;
                case "ô tô":
                    phiguixe = 1200000;
                    break;
                // Thêm các trường hợp khác nếu cần
                default:
                    phiguixe = 50000;
                    break;
            }
            

            boolean checkName = false;
            List<NhanKhau> nhanKhaus = NhanKhauDao.getInstance().selectNhanKhauById(hoKhau);
            String ten = "";
            for (NhanKhau nhanKhau : nhanKhaus) {
                 ten = nhanKhau.getTen();
                if (ten.toLowerCase().equals(chuxe.toLowerCase())) {
                    checkName = true;
                    break;
                }
            }

            if (!checkName) {
                showAlert("Lỗi", "Chủ xe không có trong hộ khẩu.");
            } else {
                // Thêm mới vào danh sách và cập nhật TableView
                PhuongTien newPhuongTien = new PhuongTien(loaiPhuongTien, bienSoXe, phiguixe, hoKhau, ten);
                boolean isAdded = getData.getInstance().addPhuongTien(newPhuongTien);

                if (isAdded) {
                    // Hiển thị thông báo khi thêm thành công
                    PhuongTienDao.getInstance().save(newPhuongTien);

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Thành công");
                    alert.setContentText("Đăng ký phương tiện mới thành công.");
                    alert.showAndWait();
                    phuongTiens = FXCollections.observableArrayList(phuongTienList);
                } else {
                    // Hiển thị thông báo khi biển số xe đã tồn tại
                    showAlert("Lỗi", "Biển số xe đã tồn tại trong danh sách.");
                }
            }

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
        tenchuxe.clear();
    }

}
