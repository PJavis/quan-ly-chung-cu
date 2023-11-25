package org.example.ConTroller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.Node;

import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.Stage;
import org.example.EntityAll.HoKhau;
import org.example.EntityAll.NhanKhau;
import org.example.Hibernatedao.HoKhauDao;
import org.example.Hibernatedao.NhanKhauDao;
import org.example.getData;

import javax.sound.midi.VoiceStatus;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class Dieuchinhhokhau implements Initializable {

    @FXML
    private TableColumn<NhanKhau, Void> chuho;
    @FXML
    private TableView<NhanKhau> danhsachthanhvien;

    @FXML
    private TextField dientichphong;

    @FXML
    private Button dieuchinh;

    @FXML
    private TableColumn<NhanKhau, Void> dieuchinhnhankhau;

    @FXML
    private TableColumn<NhanKhau, String> ngaysinh;

    @FXML
    private TextField sophong;

    @FXML
    private TextField sotang;

    @FXML
    private TableColumn<NhanKhau, Integer> sothutu;

    @FXML
    private TableColumn<NhanKhau, String> tenthanhvien;

    @FXML
    private TableColumn<NhanKhau, String> trangthai;

    private HoKhau hoKhaus;
     private  List<NhanKhau> nhanKhaus;
    public void setHokhau(HoKhau hoKhau){
        this.hoKhaus=hoKhau;
sophong.setText(String.valueOf(hoKhau.getId()));
sotang.setText(String.valueOf(hoKhau.getSoTang()));
dientichphong.setText(String.valueOf(hoKhau.getDienTichPhong()));
nhanKhaus=NhanKhauDao.getInstance().selectNhanKhauById(hoKhaus.getId(),hoKhaus.getSoTang());
        danhsachthanhvien();
    }
 public void danhsachthanhvien(){

     ObservableList<NhanKhau>nhanKhauObservableList= FXCollections.observableArrayList(nhanKhaus);
     sothutu.setCellValueFactory(cellData -> {
         int rowIndex = cellData.getTableView().getItems().indexOf(cellData.getValue()) + 1;
         return javafx.beans.binding.Bindings.createObjectBinding(() -> rowIndex);
     });
     tenthanhvien.setCellValueFactory(new PropertyValueFactory<>("ten"));
     ngaysinh.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
     trangthai.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
     dieuchinhnhankhau.setCellFactory(cell->{
         return new TableCell<NhanKhau,Void>(){
             @Override
             protected void updateItem(Void item, boolean empty) {
                 super.updateItem(item, empty);

                 if (empty) {
                     setGraphic(null);
                 } else {
                    Button button=new Button("Xóa nhân khẩu");
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
                            NhanKhauDao.getInstance().delete(person);
                            getData.getInstance().removeNhankhau(person);
                        }
                    });
                 }
             }
         };
     });
     chuho.setCellFactory(cell->{
         return new TableCell<NhanKhau, Void>(){
             @Override
             protected void updateItem(Void item, boolean empty) {
                 super.updateItem(item, empty);

                 if (empty) {
                     setGraphic(null);
                 } else {
                     NhanKhau person = getTableView().getItems().get(getIndex());
                     CheckBox checkBox=new CheckBox();
                    checkBox.setSelected(person.isChuHo());
                     setGraphic(checkBox);
                 }
             }
         };
     });
     danhsachthanhvien.setItems(nhanKhauObservableList);

 }
    @FXML
    void dieuchinh(ActionEvent event) {

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
        }
        Stage ag0r = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ag0r.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
