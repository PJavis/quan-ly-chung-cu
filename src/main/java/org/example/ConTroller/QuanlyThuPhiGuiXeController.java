// QuanlyThuPhiGuiXeController.java
package org.example.ConTroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.EntityAll.PhuongTien;
import org.example.getData;

import java.util.List;

public class QuanlyThuPhiGuiXeController {

    @FXML
    private TextField searchTextField;

    @FXML
    private TextField sotangField;

    @FXML
    private TextField sophongField;

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

    private List<PhuongTien> phuongTienList = getData.getInstance().getPhuongTiens();

    public void initialize() {
        // Khởi tạo cột và cài đặt dữ liệu cho TableView
        biensoxeColumn.setCellValueFactory(new PropertyValueFactory<>("bienSoXe"));
        loaixeColumn.setCellValueFactory(new PropertyValueFactory<>("loaiXe"));
        sotangColumn.setCellValueFactory(new PropertyValueFactory<>("soTang"));
        sophongColumn.setCellValueFactory(new PropertyValueFactory<>("soPhong"));

        // Thiết lập chính sách tự động resize cho TableView
        vehicleTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    @FXML
    private void searchButtonClicked(ActionEvent event) {
        String searchTerm = searchTextField.getText();
        int sotang = Integer.parseInt(sotangField.getText());
        int sophong = Integer.parseInt(sophongField.getText());

        updateTableView(searchTerm);
    }

    private void updateTableView(String searchTerm) {
        // Giả sử có một danh sách xe tìm kiếm
        ObservableList<PhuongTien> searchResult = getSearchResult(searchTerm);
        vehicleTableView.setItems(searchResult);
    }

    private ObservableList<PhuongTien> getSearchResult(String searchTerm) {
        // Thực hiện tìm kiếm trong cơ sở dữ liệu hoặc danh sách xe
        // và trả về danh sách kết quả
        // Đây là một ví dụ đơn giản, bạn cần thay thế bằng logic thực tế
        ObservableList<PhuongTien> searchResult = FXCollections.observableArrayList();
        return searchResult;
    }

    public void taomoi(ActionEvent actionEvent) {
    }
}
