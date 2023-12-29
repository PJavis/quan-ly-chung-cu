package org.example.ConTroller;


import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import org.example.EntityAll.HoKhau;
import org.example.EntityAll.KhoanPhi;
import org.example.EntityAll.NhanKhau;
import org.example.EntityAll.NopPhi;
import org.example.Hibernatedao.HoKhauDao;
import org.example.Hibernatedao.NhanKhauDao;
import org.example.Hibernatedao.NopPhiDao;
import org.example.getData;


import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;

public class Dieuchinhhokhau implements Initializable {

    @FXML
    private TableColumn<NhanKhau, Void> chuho;
    @FXML
    private TableView<NhanKhau> danhsachthanhvien;

    @FXML
    private TextField dientichphong;


    @FXML
    private TableColumn<NhanKhau, Void> dieuchinhnhankhau;

    @FXML
    private TableColumn<NhanKhau, String> ngaysinh;

    @FXML
    private Label sophong;

    @FXML
    private Label sotang;

    @FXML
    private Label sothanhvien;


    @FXML
    private TableColumn<NhanKhau, Integer> sothutu;

    @FXML
    private TableColumn<NhanKhau, String> tenthanhvien;

    @FXML
    private TableColumn<NhanKhau, String> trangthai;

    private HoKhau hoKhaus;
     private  List<NhanKhau> nhanKhaus;
     private NhanKhau nhanKhau;
    private ToggleGroup toggleGroup=new ToggleGroup();
    public void setHokhau(HoKhau hoKhau){
        this.hoKhaus=hoKhau;
sophong.setText(String.valueOf(hoKhau.getId()));
sotang.setText(String.valueOf(hoKhau.getSoTang()));
dientichphong.setText(String.valueOf(hoKhau.getDienTichPhong()));
sothanhvien.setText(String.valueOf(hoKhau.getSoNhanKhau()));
nhanKhaus=NhanKhauDao.getInstance().selectNhanKhauById(hoKhaus.getId(),hoKhaus.getSoTang());
nopPhiList= NopPhiDao.getInstance().selectByHoKhau(hoKhau.getSoTang(), hoKhau.getId());
        danhsachthanhvien();
        danhsachkhoanphi();
    }
 public void danhsachthanhvien(){

     ObservableList<NhanKhau>nhanKhauObservableList= FXCollections.observableArrayList(nhanKhaus);
     nhanKhauObservableList.sort(Comparator.comparing(NhanKhau::isChuHo).reversed());

     sothutu.setCellValueFactory(cellData -> {
         int rowIndex = cellData.getTableView().getItems().indexOf(cellData.getValue()) + 1;
         return javafx.beans.binding.Bindings.createObjectBinding(() -> rowIndex);
     });
     tenthanhvien.setCellValueFactory(new PropertyValueFactory<>("ten"));
     ngaysinh.setCellValueFactory(new PropertyValueFactory<>("formattedDate"));
     trangthai.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
     chuho.setCellFactory(cell->{
         return new TableCell<NhanKhau, Void>(){
             @Override
             protected void updateItem(Void item, boolean empty) {
                 super.updateItem(item, empty);

                 if (empty) {
                     setGraphic(null);
                 } else {
                     NhanKhau person = getTableView().getItems().get(getIndex());
                     RadioButton radioButton=new RadioButton();
                     radioButton.setSelected(person.isChuHo());
                     radioButton.setToggleGroup(toggleGroup);
                     radioButton.setOnAction(event -> {
                         nhanKhau=person;
                     });
                     setGraphic(radioButton);
                 }
             }
         };
     });
     dieuchinhnhankhau.setCellFactory(cell->{
         return new TableCell<NhanKhau,Void>(){
             @Override
             protected void updateItem(Void item, boolean empty) {
                 super.updateItem(item, empty);

                 if (empty) {
                     setGraphic(null);
                 } else {
                    Button button=new Button();
                     FontAwesomeIconView iconView = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                     iconView.setSize("16px");
                     button.setGraphic(iconView);
                    setGraphic(button);
                    button.setOnAction(event -> {
                        Alert alert=new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText("Bạn chắc chắn muốn xóa nhân khẩu ?");
                        alert.setContentText("Khi đó thông tin về nhân khẩu sẽ không còn");

                        ButtonType buttonTypeOK = new ButtonType("OK", ButtonType.OK.getButtonData());
                        // Thêm nút "Hủy"
                        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonType.CANCEL.getButtonData());

                        alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);

                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.isPresent() && result.get() == buttonTypeOK) {
                            NhanKhau person = getTableView().getItems().get(getIndex());
                            if(person.isChuHo()){
                                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                                alert1.setHeaderText("Thất bại");
                                alert1.setContentText("Bạn không được xóa chủ hộ");
                                alert1.showAndWait();
                            }
                            else {
                                Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                                alert1.setHeaderText("Thành công");
                                alert1.setContentText("Xóa nhân khẩu thành công");
                                alert1.showAndWait();
                                NhanKhauDao.getInstance().delete(person);
                            getData.getInstance().removeNhankhau(person);
                            nhanKhaus.remove(person);
                            danhsachthanhvien();
                            }
                        }
                    });
                 }
             }
         };
     });


     danhsachthanhvien.setItems(nhanKhauObservableList);

 }
    @FXML
    void dieuchinh(ActionEvent event) {

            getData.getInstance().removeHokhau(hoKhaus);
            NhanKhau q=danhsachthanhvien.getItems().get(0);
            try{
                nhanKhau.setChuHo(true);
                getData.getInstance().setNhankhau(nhanKhau);
                NhanKhauDao.getInstance().update(nhanKhau);
                q.setChuHo(false);
                NhanKhauDao.getInstance().update(q);
            }
            catch (Exception e){
                nhanKhau=q;
            }
            getData.getInstance().setNhankhau(q);
            hoKhaus.setDienTichPhong(Double.parseDouble(dientichphong.getText()));
            hoKhaus.setTenchuho(nhanKhau.getTen());

            HoKhauDao.getInstance().update(hoKhaus);
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setHeaderText("Thành công");
            alert1.setContentText("Điều chỉnh hộ khẩu thành công");
            alert1.showAndWait();
            getData.getInstance().addHokhau(hoKhaus);
            Stage ag0r = (Stage) ((Node) event.getSource()).getScene().getWindow();
            ag0r.close();


    }


    @FXML
    void xoahokhau(ActionEvent event) {
        Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Bạn chắc chắn muốn xóa ho khẩu ?");
        alert.setContentText("Khi đó thông tin về nhân khẩu của hộ khẩu sẽ không còn");
        ButtonType buttonTypeOK = new ButtonType("OK", ButtonType.OK.getButtonData());
        // Thêm nút "Hủy"
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonType.CANCEL.getButtonData());

        alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeOK) {
            for(NhanKhau nhanKhau : nhanKhaus) {
                NhanKhauDao.getInstance().delete(nhanKhau);
                getData.getInstance().removeNhankhau(nhanKhau);
            }
            HoKhauDao.getInstance().delete(hoKhaus);
            getData.getInstance().removeHokhau(hoKhaus);
            Alert alert1=new Alert(Alert.AlertType.CONFIRMATION);
            alert1.setHeaderText("Thành công");
            alert1.setContentText("Xóa hộ khẩu thành công");
            alert1.showAndWait();
            Stage ag0r = (Stage) ((Node) event.getSource()).getScene().getWindow();
            ag0r.close();
        }

    }
    @FXML
    private TableView<NopPhi> danhsachkhoanphi;
    @FXML
    private TableColumn<NopPhi, Void> lichsugiaodich;

    @FXML
    private TableColumn<NopPhi, String> loaikhoanphi;
    @FXML
    private TableColumn<NopPhi, Integer> sothutu1;

    @FXML
    private TableColumn<NopPhi, String> sotienchuanop;

    @FXML
    private TableColumn<NopPhi, Double> sotiendanop;

    @FXML
    private TableColumn<NopPhi, String> tenkhoanphi;
    private List<NopPhi> nopPhiList;
    private ObservableList<NopPhi> nopPhis=FXCollections.observableArrayList();
    public void danhsachkhoanphi(){
        nopPhis=FXCollections.observableArrayList(nopPhiList);
        sothutu1.setCellValueFactory(cellData -> {
            int rowIndex = cellData.getTableView().getItems().indexOf(cellData.getValue()) + 1;
            return javafx.beans.binding.Bindings.createObjectBinding(() -> rowIndex);
        });
        tenkhoanphi.setCellValueFactory(new PropertyValueFactory<>("tenKhoanPhi"));
        loaikhoanphi.setCellValueFactory(new PropertyValueFactory<>("loaiKhoanPhi"));
        sotiendanop.setCellValueFactory(new PropertyValueFactory<>("decimalFormatSotiendanop"));
        sotienchuanop.setCellValueFactory(new PropertyValueFactory<>("decimalFormatSotien"));
        lichsugiaodich.setCellFactory(cell-> {
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
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        });
                    }
                }
            };
        });
        danhsachkhoanphi.setItems(nopPhis);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
