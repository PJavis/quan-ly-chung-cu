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
import org.example.Hibernatedao.PhuongTienDao;
import org.example.getData;

import java.util.Comparator;
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

    private  ObservableList<PhuongTien> phuongTiens;

    private List<PhuongTien> phuongTienList = getData.getInstance().getPhuongTiens();

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

        // Thiết lập chính sách tự động resize cho TableView
        vehicleTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    @FXML
    private void searchButtonClicked(ActionEvent event) {
        String bienso = searchTextField.getText();

        String sotangtemp = sotangField.getText();
        String sophongtemp = sophongField.getText();

        int sotang = (sotangtemp.isEmpty()) ? 0 : Integer.parseInt(sotangtemp);
        int sophong = (sophongtemp.isEmpty()) ? 0 : Integer.parseInt(sotangtemp);

        updateTableView(bienso, sotang, sophong);
    }

    private void updateTableView(String bienso, int sotang, int sophong) {
        // Giả sử có một danh sách xe tìm kiếm
        ObservableList<PhuongTien> searchResult = null;
        if (sotang == 0) {
            searchResult = getSearchResultOnLicensePlate(bienso);
        }
        else {
            searchResult = FXCollections.observableArrayList(PhuongTienDao.getInstance().selectByHoKhau(sotang,sophong));
        }
        vehicleTableView.setItems(searchResult);
    }

    private ObservableList<PhuongTien> getSearchResultOnLicensePlate(String searchTerm) {

        ObservableList<PhuongTien> searchResult = FXCollections.observableArrayList(PhuongTienDao.getInstance().selectByName(searchTerm));
        return searchResult;
    }

    public void taomoi(ActionEvent actionEvent) {
    }
}
