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

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.EntityAll.KhoanPhi;
import org.example.EntityAll.LichSuGiaoDich;
import org.example.EntityAll.NopPhi;
import org.example.Hibernatedao.KhoanPhiDao;
import org.example.Hibernatedao.NopPhiDao;

import org.example.getData;

import java.net.URL;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Chitietkhoanphi implements Initializable {
    private KhoanPhi khoanPhi;
    private List<NopPhi> nopPhiList;
    private boolean batbuoc=true;

    public void setKhoanPhi(KhoanPhi khoanPhi) {
        this.khoanPhi = khoanPhi;
        tenkhoanphi.setText(khoanPhi.getTenKhoanPhi());
        loaikhoanphi.setText(khoanPhi.getLoaiKhoanPhi());
        sotien.setText(khoanPhi.getDecimalFormatsotien());
        sotiendanop.setText(khoanPhi.getDecimalFormatsotiendanop());
        SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ngaytao.setText(newDateFormat.format(khoanPhi.getBatDau()));
        hannop.setText(newDateFormat.format(khoanPhi.getKetThuc()));
        nopPhiList= NopPhiDao.getInstance().selectById(khoanPhi.getId());
        if(!Objects.equals(khoanPhi.getLoaiKhoanPhi(), "Bắt buộc")) batbuoc=false;
        danhsachhokhau();
        timkiem();
    }

    @FXML
    private TableColumn<NopPhi, Void> lichsu;

    @FXML
    private TableView<NopPhi> danhsachhokhau;

    @FXML
    private TextField hannop;

    @FXML
    private Label loaikhoanphi;

    @FXML
    private Label ngaytao;

    @FXML
    private TableColumn<NopPhi, Integer> sophong;

    @FXML
    private TableColumn<NopPhi, Integer> sotang;

    @FXML
    private TableColumn<NopPhi, Integer> sothutu;

    @FXML
    private TextField sotien;

    @FXML
    private TableColumn<NopPhi, Double> sotienchuanop;

    @FXML
    private Label sotiendanop;

    @FXML
    private TableColumn<NopPhi, Double> sotiendanoptable;

    @FXML
    private TableColumn<NopPhi, String> tenchuho;

    @FXML
    private Label tenkhoanphi;

    @FXML
    private TextField timkiem;
    private ObservableList<NopPhi> nopPhis=FXCollections.observableArrayList();
    public void danhsachhokhau() {

        if(batbuoc) {

            nopPhis = FXCollections.observableArrayList(nopPhiList);
            danhsachhokhau.setRowFactory(tv -> {
                return new javafx.scene.control.TableRow<NopPhi>() {
                    @Override
                    protected void updateItem(NopPhi nopPhi, boolean empty) {
                        super.updateItem(nopPhi, empty);

                        if (nopPhi == null || empty) {
                            setStyle("");
                        } else {
                            // Kiểm tra điều kiện và thiết lập màu cho hàng
                            if (nopPhi.getSotienchuanop() > 0) {
                                setStyle("-fx-background-color: lightcoral;");
                            } else {
                                setStyle("");
                            }
                        }
                    }
                };
            });
            if(khoanPhi.getPhidichvuchungcu()==1){
            sotienchuanop.setCellValueFactory(new PropertyValueFactory<>("decimalFormatSotienchuanop"));
            nopPhis.sort((o1, o2) -> {
                double a=o1.getGiaTri()*o1.getDienTichPhong()-o1.getSoTienDaDong();
                double a1=o2.getGiaTri()*o2.getDienTichPhong()-o2.getSoTienDaDong();
                if(a>0&&a1>0){
                    if(a>a1)return 1;
                    else return 0;
                }else if(a==0&&a1>0)return 0;
                else if (a>0&&a1==0) return 1;
                else {
                    if(o1.getSoTang()>o2.getSoTang())return 1;
                    else if (o1.getSoTang()==o2.getSoTang()) {
                        if(o1.getSoPhong()> o2.getSoPhong())return 1;
                        else return 0;
                    }else return 0;
                }
            });
            }
            else{
                sotienchuanop.setCellValueFactory(new PropertyValueFactory<>("decimalFormatSotienchuanopdonggop"));
                nopPhis.sort((o1, o2) -> {
                    double a=o1.getGiaTri()-o1.getSoTienDaDong();
                    double a1=o2.getGiaTri()-o2.getSoTienDaDong();
                    if(a>0&&a1>0){
                        if(a>a1)return 1;
                        else return 0;
                    }else if(a==0&&a1>0)return 0;
                    else if (a>0&&a1==0) return 1;
                    else {
                        if(o1.getSoTang()>o2.getSoTang())return 1;
                        else if (o1.getSoTang()==o2.getSoTang()) {
                            if(o1.getSoPhong()> o2.getSoPhong())return 1;
                            else return 0;
                        }else return 0;
                    }
                });
            }
        }
        else {
            sotienchuanop.setCellValueFactory(new PropertyValueFactory<>("decimalFormatSotienchuanopdonggop"));
            for(NopPhi nopPhi:nopPhiList){
                if(nopPhi.getSoTienDaDong()>0){
                    nopPhis.add(nopPhi);
                }
            }
            nopPhis.sort(Comparator.comparing(NopPhi::getSoTang).thenComparing(NopPhi::getSoPhong));
        }
        sothutu.setCellValueFactory(cellData -> {
            int rowIndex = cellData.getTableView().getItems().indexOf(cellData.getValue()) + 1;
            return javafx.beans.binding.Bindings.createObjectBinding(() -> rowIndex);
        });
        sophong.setCellValueFactory(new PropertyValueFactory<>("soPhong"));
        sotang.setCellValueFactory(new PropertyValueFactory<>("soTang"));
        tenchuho.setCellValueFactory(new PropertyValueFactory<>("tenchuho"));
        sotiendanoptable.setCellValueFactory(new PropertyValueFactory<>("decimalFormatSotiendanop"));

        lichsu.setCellFactory(cell-> {
                    return new TableCell<NopPhi, Void>() {
                        @Override
                        protected void updateItem(Void item, boolean empty) {
                            super.updateItem(item, empty);

                            if (empty) {
                                setGraphic(null);
                            } else {
                                Button button = new Button();
                                FontAwesomeIconView iconView = new FontAwesomeIconView(FontAwesomeIcon.PENCIL);
                                iconView.setSize("16px");
                                button.setGraphic(iconView);
                                setGraphic(button);
                                button.setOnAction(event -> {
                                    NopPhi person = getTableView().getItems().get(getIndex());
                                    try {
                                        Stage ag0r = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example/Lichsugiaodich.fxml"));
                                        Parent root = loader.load();
                                        Scene scene = new Scene(root);
                                        Stage ag0r1=new Stage();
                                        ag0r1.setScene(scene);
                                        ag0r1.initModality(Modality.APPLICATION_MODAL);
                                        ag0r1.initOwner(ag0r);
                                        Lichsugiaodich lichsugiaodich=loader.getController();
                                        lichsugiaodich.setNopPhi(person);
                                        ag0r1.showAndWait();
                                        timkiem();
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                });
                            }
                        }
                    };
                });
        danhsachhokhau.setItems(nopPhis);
    }
    public void timkiem(){
        FilteredList<NopPhi> filter = new FilteredList<>(nopPhis, e -> true);

        timkiem.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateEmployeeData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                if(predicateEmployeeData.getTenchuho().toLowerCase().contains(newValue.toLowerCase()))
                    return true;
                else if(String.valueOf(predicateEmployeeData.getSoPhong()).contains(newValue)){
                    return  true;
                } else if (String.valueOf(predicateEmployeeData.getSoTang()).contains(newValue)) {
                    return true;
                }
                return false;

            });
        });

        SortedList<NopPhi> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(danhsachhokhau.comparatorProperty());
        danhsachhokhau.setItems(sortList);
    }
    @FXML
    void tracuugiaodich(ActionEvent event) {
        try {
            Stage ag0r = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example/Tracuulichsugiaodich.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage ag0r1=new Stage();
            ag0r1.setScene(scene);
            ag0r1.initModality(Modality.APPLICATION_MODAL);
            ag0r1.initOwner(ag0r);
            Tracuulichsugiaodich tracuulichsugiaodich=loader.getController();
            tracuulichsugiaodich.setKhoanPhi(khoanPhi);
            ag0r1.showAndWait();
            timkiem();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    void quaylai(ActionEvent event) {
        try {
            Stage ag0r1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example/Quanlykhoanphi.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            ag0r1.setScene(scene);
            ag0r1.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    void dieuchinh(ActionEvent event) {
    double d=Double.parseDouble(sotien.getText().replace(",",""));
    khoanPhi.setGiaTri(d);
    for(NopPhi nopPhi :nopPhiList){
        nopPhi.setGiaTri(d);
        NopPhiDao.getInstance().update(nopPhi);
    }
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String date = hannop.getText();
    LocalDate datetime = LocalDate.parse(date, formatter);
    khoanPhi.setKetThuc(Date.valueOf(datetime));
        KhoanPhiDao.getInstance().update(khoanPhi);
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Thành công");
        alert.setContentText("Điều chỉnh khoản phí thành công");
        alert.showAndWait();
        timkiem();
        danhsachhokhau();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
