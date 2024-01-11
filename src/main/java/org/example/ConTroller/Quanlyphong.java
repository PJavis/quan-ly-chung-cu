package org.example.ConTroller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.EntityAll.HoKhau;
import org.example.EntityAll.NhanKhau;
import org.example.Hibernatedao.HoKhauDao;
import org.example.Hibernatedao.NhanKhauDao;
import org.example.getData;

import java.net.URL;
import java.util.*;

public class Quanlyphong implements Initializable {

    @FXML
    private TableView<HoKhau> danhsachhokhau;

    @FXML
    private TableColumn<HoKhau, Void> dieuchinh;

    @FXML
    private TableColumn<HoKhau, Integer> sophong;

    @FXML
    private TableColumn<HoKhau, Integer> sotang;



    @FXML
    private TableColumn<HoKhau, Integer> sothutu;

    @FXML
    private TableColumn<HoKhau, String> tenchuho;

    @FXML
    private TableColumn<HoKhau, String> sodienthoai;

    @FXML
    private TextField timkiem;
    @FXML
    private Label tongsohokhau;

    private List<HoKhau> hoKhauList=getData.getInstance().getHoKhaus();

    public List<HoKhau> getHoKhauList() {
        return hoKhauList;
    }

    public void setHoKhauList() {
        this.hoKhauList = getData.getInstance().getHoKhaus();
    }

    private ObservableList<HoKhau> hoKhaus;
    @FXML
    private BarChart<String,Number> hokhauchart;
    private  Map<Integer,NhanKhau> nhanKhauList=getData.getInstance().getNhanKhaus();

    @FXML
    private Pane panehokhau;
    @FXML
    private TableColumn<HoKhau, Integer> sonhankhau;
    @FXML
    private ComboBox<String> boxphong;
    @FXML
    private ComboBox<String> boxthang;
    @FXML
    private ComboBox<String> boxNam;
    public void danhsachhokhau(){
        tongsohokhau.setText(String.valueOf(hoKhauList.size()));
        hoKhaus= FXCollections.observableArrayList(hoKhauList);
        hoKhaus.sort(Comparator.comparingInt(HoKhau::getSoTang).thenComparing(HoKhau::getId));
        sothutu.setCellValueFactory(cellData -> {
            int rowIndex = cellData.getTableView().getItems().indexOf(cellData.getValue()) + 1;
            return javafx.beans.binding.Bindings.createObjectBinding(() -> rowIndex);
        });
        sophong.setCellValueFactory(new PropertyValueFactory<>("id"));
        sotang.setCellValueFactory(new PropertyValueFactory<>("soTang"));
        tenchuho.setCellValueFactory(new PropertyValueFactory<>("tenchuho"));
        sodienthoai.setCellValueFactory(new PropertyValueFactory<>("soDienThoai"));
        sonhankhau.setCellValueFactory(new  PropertyValueFactory<>("soNhanKhau"));
        dieuchinh.setCellFactory(cell->{
            return new TableCell<HoKhau,Void>(){
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
                            HoKhau person = getTableView().getItems().get(getIndex());

                            try {
                                Stage ag0r = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example/Dieuchinhhokhau.fxml"));
                                Parent root = loader.load();
                                Scene scene = new Scene(root);
                                ag0r.setScene(scene);
                                Dieuchinhhokhau dieuchinhhokhau=loader.getController();
                                dieuchinhhokhau.setHokhau(person);
                                ag0r.show();

                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        });
                    }
                }
            };
        });
        danhsachhokhau.setItems(hoKhaus);
    }
    public void timkiemhokhau(){
        FilteredList<HoKhau> filter = new FilteredList<>(hoKhaus, e -> true);

        timkiem.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateEmployeeData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();
                if(String.valueOf(predicateEmployeeData.getId()).contains(searchKey)){
                    return  true;
                } else if (String.valueOf(predicateEmployeeData.getSoTang()).contains(searchKey)) {
                    return true;
                }
                else if(predicateEmployeeData.getTenchuho().toLowerCase().contains(searchKey)){
                    return  true;
                }

               return false;

            });
        });

        SortedList<HoKhau> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(danhsachhokhau.comparatorProperty());
        danhsachhokhau.setItems(sortList);
    }

    @FXML
    void taomoihokhau(ActionEvent event) {
        try {
            Stage ag0r = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example/Taomoihokhau.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            ag0r.setScene(scene);
            ag0r.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
    //chac k can thong ke nam nu trong ho khau dau
//    public void chartnamnu() {
//        try {
//            Map<String, Long> genderDistribution = NhanKhauDao.getInstance().tinhtilenamnu();
//            updateGenderChart(genderDistribution);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
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
    public void chartthang() {
        try {
            String selectedNam = boxNam.getSelectionModel().getSelectedItem();
            if (selectedNam != null) {
                int nam = Integer.parseInt(selectedNam);
                HoKhauDao hokhauDao = HoKhauDao.getInstance();
                Map<Integer, Long> monthlyStatistics = hokhauDao.thongketheothang(nam);
                updatecharthang(monthlyStatistics);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updatecharthang(Map<Integer, Long> monthlyStatistics) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        for (Map.Entry<Integer, Long> entry : monthlyStatistics.entrySet()) {
            String monthLabel = laytenthang(entry.getKey());
            series.getData().add(new XYChart.Data<>(monthLabel, entry.getValue()));
        }

        barChart.getData().clear();
        barChart.getData().add(series);

        panehokhau.getChildren().clear();
        panehokhau.getChildren().add(barChart);

        barChart.setPrefSize(panehokhau.getPrefWidth(), panehokhau.getPrefHeight());
    }
    private String laytenthang(int month) {
        switch (month) {
            case 1: return "January";
            case 2: return "February";
            case 3: return "March";
            case 4: return "April";
            case 5: return "May";
            case 6: return "June";
            case 7: return "July";
            case 8: return "August";
            case 9: return "September";
            case 10: return "October";
            case 11: return "November";
            case 12: return "December";
            default: return "Unknown";
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

        panehokhau.getChildren().clear();
        panehokhau.getChildren().add(barChart);

        barChart.setPrefSize(panehokhau.getPrefWidth(), panehokhau.getPrefHeight());
    }
    public void chartnam() {
        try {
            HoKhauDao hokhauDao = HoKhauDao.getInstance();
            Map<Integer, Long> yearlyStatistics = hokhauDao.thongKeTongSoPhongtheonam();
            updatechartnam(yearlyStatistics);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updatechartnam(Map<Integer, Long> yearlyStatistics) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        for (Map.Entry<Integer, Long> entry : yearlyStatistics.entrySet()) {
            series.getData().add(new XYChart.Data<>(String.valueOf(entry.getKey()), entry.getValue()));
        }

        barChart.getData().clear();
        barChart.getData().add(series);

        panehokhau.getChildren().clear();
        panehokhau.getChildren().add(barChart);

        barChart.setPrefSize(panehokhau.getPrefWidth(), panehokhau.getPrefHeight());
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

        panehokhau.getChildren().clear();
        panehokhau.getChildren().add(barChart);

        barChart.setPrefSize(panehokhau.getPrefWidth(), panehokhau.getPrefHeight());
    }
    private final String[] namArr = {"2022", "2023", "2024", "2025",};

    public void setBoxNam() {
        ObservableList<String> namList = FXCollections.observableArrayList(namArr);
        boxNam.setItems(namList);
        boxNam.getSelectionModel().selectFirst();
    }




    private  String luachon[] = {"Thống kê theo tháng","Thống kê theo tuổi","Thống kê theo năm"};

    public void setBoxluachon( ) {
        ObservableList<String>boxbox = FXCollections.observableArrayList(luachon);
        boxphong.setItems(boxbox);
    }
    private  String[] thangArr = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};

    public void setBoxThang() {
        ObservableList<String> thangList = FXCollections.observableArrayList(thangArr);
        boxthang.setItems(thangList);
        boxthang.getSelectionModel().selectFirst();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        danhsachhokhau();
        timkiemhokhau();
//        chart();
        setBoxluachon();
        setBoxNam();
        boxphong.setOnAction(event -> {
            String selectedOption = boxphong.getSelectionModel().getSelectedItem();
            if (selectedOption != null) {
                switch (selectedOption) {
                    case "Thống kê theo tháng":
                        chartthang();
                        break;
                    case "Thống kê theo tuổi":
                        chartnamsinh();
                        break;
                    case "Thống kê theo năm":
                        chartnam();
                        break;
                }
            }
        });
    }
}
