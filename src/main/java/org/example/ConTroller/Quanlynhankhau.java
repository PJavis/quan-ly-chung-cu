package org.example.ConTroller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.Model.EntityAll.HoKhau;
import org.example.Model.EntityAll.NhanKhau;
import org.example.Model.Hibernatedao.NhanKhauDao;
import org.example.getData;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class Quanlynhankhau implements Initializable {

    @FXML
    private BarChart<String, Number> thongkechart;
    @FXML
    private TableColumn<NhanKhau, String> sodienthoai;
    @FXML
    private TextField timkiem;
    @FXML
    private TableView<NhanKhau> danhsachnhankhau;
    @FXML
    private TableColumn<NhanKhau, String> hovaten;

    @FXML
    private TableColumn<NhanKhau, String> ngaysinh;

    @FXML
    private TableColumn<NhanKhau, Integer> sophongdango;
    @FXML
    private TableColumn<NhanKhau, Integer> sotang;

    @FXML
    private TableColumn<NhanKhau, Integer> sothutu;

    @FXML
    private TableColumn<NhanKhau, String> trangthai;
    @FXML
    private TableColumn<NhanKhau, Void> dieuchinh;
    private  Map<Integer,NhanKhau> nhanKhauList=getData.getInstance().getNhanKhaus();


    private ObservableList<NhanKhau> nhanKhaus;

    @FXML
    private ComboBox<String> boxluachon;
    @FXML
    private Label tongsonhankhau;
    @FXML
    private TableColumn<NhanKhau, String> cancuoccongdan;
    @FXML
    private Pane panethongke;
    public void danhsachnhankhau(){
        tongsonhankhau.setText(String.valueOf(nhanKhauList.size()));
        nhanKhaus= FXCollections.observableArrayList(nhanKhauList.values());
        nhanKhaus.sort((o1, o2) -> {
            String[] words1 = o1.getTen().split(" ");
            String[] words2 = o2.getTen().split(" ");
            String lastWord1 = words1[words1.length - 1];
            String lastWord2 = words2[words2.length - 1];
            return lastWord1.compareTo(lastWord2);

        });
        sothutu.setCellValueFactory(cellData -> {
            int rowIndex = cellData.getTableView().getItems().indexOf(cellData.getValue()) + 1;
            return javafx.beans.binding.Bindings.createObjectBinding(() -> rowIndex);
        });
        cancuoccongdan.setCellValueFactory(new PropertyValueFactory<>("CCCD"));
        hovaten.setCellValueFactory(new PropertyValueFactory<>("ten"));
        ngaysinh.setCellValueFactory(new PropertyValueFactory<>("formattedDate"));

        sophongdango.setCellValueFactory(cellData -> {
            HoKhau hoKhau = cellData.getValue().getHoKhau();
            return hoKhau != null ? new SimpleIntegerProperty(hoKhau.getId()).asObject() : null;
        });

        sotang.setCellValueFactory(cellData -> {
            HoKhau hoKhau = cellData.getValue().getHoKhau();
            return hoKhau != null ? new SimpleIntegerProperty(hoKhau.getSoTang()).asObject() : null;
        });



        trangthai.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
        sodienthoai.setCellValueFactory(new PropertyValueFactory<>("soDienThoai"));
        dieuchinh.setCellFactory(cell-> new TableCell<>() {
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    Button button = new Button();
                    FontAwesomeIconView iconView = new FontAwesomeIconView(FontAwesomeIcon.PENCIL);
                    iconView.setSize("16px");
                    setGraphic(button);
                    button.setGraphic(iconView);

                    // Xử lý sự kiện khi nút được nhấp
                    button.setOnAction(event -> {
                        NhanKhau person = getTableView().getItems().get(getIndex());
                        try {
                            Stage ag0r = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example/Views/Dieuchinhnhankhau.fxml"));
                            Parent root = loader.load();
                            Scene scene = new Scene(root);
                            ag0r.setScene(scene);
                            Dieuchinhnhankhau dieuchinhnhankhau = loader.getController();
                            dieuchinhnhankhau.setNhanKhau(person);
                            ag0r.show();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    });
                }
            }
        });
        danhsachnhankhau.setItems(nhanKhaus);
    }

    @FXML
    void taomoinhankhau(ActionEvent event) {
        try {
            Stage ag0r = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example/Views/Taomoinhankhau.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            ag0r.setScene(scene);
            ag0r.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void timkiem(){
        FilteredList<NhanKhau> filter = new FilteredList<>(nhanKhaus, e -> true);

        timkiem.textProperty().addListener((Observable, oldValue, newValue) -> filter.setPredicate(predicateEmployeeData -> {

            if (newValue == null || newValue.isEmpty()) {
                return true;
            }

            String searchKey = newValue.toLowerCase();


            return predicateEmployeeData.getTen().toLowerCase().contains(searchKey);

        }));

        SortedList<NhanKhau> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(danhsachnhankhau.comparatorProperty());
        danhsachnhankhau.setItems(sortList);
    }
    public void charttuoi() {
        try {
            // lay mot chut data ha
            List<NhanKhau> nhanKhauList1 = new ArrayList<>(nhanKhauList.values());
            // tinh distribution
            Map<Integer, Long> ageDistribution = NhanKhauDao.getInstance().calculateTimeDistribution(nhanKhauList1);
            // cap nhat bieu do
            updateChart(ageDistribution);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void chartnamnu() {
        try {
            Map<String, Long> genderDistribution = NhanKhauDao.getInstance().tinhtilenamnu();
            updateGenderChart(genderDistribution);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void chartnamsinh() {
        try {
            // Thực hiện thống kê theo năm
            Map<Integer, Long> yearDistribution = NhanKhauDao.getInstance().tinhnamsinh();

            // Cập nhật biểu đồ
            updateChart(yearDistribution);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateGenderChart(Map<String, Long> genderDistribution) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        for (Map.Entry<String, Long> entry : genderDistribution.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        barChart.getData().clear();
        barChart.getData().add(series);

        panethongke.getChildren().clear();
        panethongke.getChildren().add(barChart);

        barChart.setPrefSize(panethongke.getPrefWidth(), panethongke.getPrefHeight());
    }


    public void updateChart(Map<Integer, Long> ageDistribution) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        for (Map.Entry<Integer, Long> entry : ageDistribution.entrySet()) {
            series.getData().add(new XYChart.Data<>(String.valueOf(entry.getKey()), entry.getValue()));
        }
        barChart.getData().clear();
        barChart.getData().add(series);
        panethongke.getChildren().clear();
        panethongke.getChildren().add(barChart);
        barChart.setPrefSize(panethongke.getPrefWidth(), panethongke.getPrefHeight());
    }




    private String[] luachon = {"Thống kê nam nữ","Thống kê theo năm sinh","Thống kê theo tuổi"};

    public void setBoxluachon( ) {
        ObservableList<String>boxbox = FXCollections.observableArrayList(luachon);
        boxluachon.setItems(boxbox);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        danhsachnhankhau();
        timkiem();
//        chart();
        setBoxluachon();
        boxluachon.setOnAction(event -> {
            String selectedOption = boxluachon.getSelectionModel().getSelectedItem();
            if (selectedOption != null) {
                switch (selectedOption) {
                    case "Thống kê nam nữ":
                        chartnamnu();
                        break;
                    case "Thống kê theo năm sinh":
                        chartnamsinh();
                        break;
                    case "Thống kê theo tuổi":
                        charttuoi();
                        break;
                }
            }
        });
    }

}
