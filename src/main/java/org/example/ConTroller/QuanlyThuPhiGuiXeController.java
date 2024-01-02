// QuanlyThuPhiGuiXeController.java
package org.example.ConTroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.example.EntityAll.PhuongTien;

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

    // Các cài đặt và phương thức khác

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
