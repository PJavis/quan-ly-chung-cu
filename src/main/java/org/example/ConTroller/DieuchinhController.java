package org.example.ConTroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.example.EntityAll.DanhSachKhoanPhi;
import org.example.EntityAll.HoKhau;
import org.example.EntityAll.NhanKhau;
import org.example.Hibernatedao.DanhSachKhoanPhiDao;
import org.example.Hibernatedao.HoKhauDao;
import org.example.Hibernatedao.NhanKhauDao;

import java.net.URL;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

public class DieuchinhController implements Initializable {

    @FXML
    private AnchorPane anchopanethanhvienphong;
    @FXML
    private TextField khoanphidieuchinh;
    @FXML
    private GridPane thanhvienphong;

    @FXML
    private TextField loaikhoanphi;

    @FXML
    private TextField sotien;

    @FXML
    private Label chonkhoanphicandieuchinh;

    @FXML
    private TextField chuho;

    @FXML
    private CheckBox co;

    @FXML
    private TextField dientichphong;

    @FXML
    private Button dieuchinhphong;

    @FXML
    private TextField gioitinh;

    @FXML
    private TextField ngaysinh;

    @FXML
    private Label nhankhauduocdieuchinhthanhcon;

    @FXML
    private TextField quoctich;

    @FXML
    private TextField sophong;
    @FXML
    private ListView<String> listviewphi;

    @FXML
    private TextField hannop;
    @FXML
    void listviewphi(MouseEvent event) {
        String selectedItem = listviewphi.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            khoanphidieuchinh.setText(selectedItem);
            listviewphi.setVisible(false);
        }
    }
    private DanhSachKhoanPhi danhSachKhoanPhi;
    @FXML
    void timphi(ActionEvent event) {

danhSachKhoanPhi=DanhSachKhoanPhiDao.getInstance().selectByName(khoanphidieuchinh.getText()).get(0);
if(danhSachKhoanPhi==null){
    Alert alert1=new Alert(Alert.AlertType.ERROR);
    alert1.setContentText("Không tìm thấy phí");
    alert1.showAndWait();
}else{
loaikhoanphi.setText(danhSachKhoanPhi.getLoaiKhoanPhi());
sotien.setText(String.valueOf(danhSachKhoanPhi.getGiaTri()));
hannop.setText(String.valueOf(danhSachKhoanPhi.getKetThuc()));
    }}
    @FXML
    void dieuchinhkhoanphi(ActionEvent event) {
danhSachKhoanPhi.setLoaiKhoanPhi(loaikhoanphi.getText());
danhSachKhoanPhi.setGiaTri(Double.parseDouble(sotien.getText()));
danhSachKhoanPhi.setKetThuc(Date.valueOf(hannop.getText()));
danhSachKhoanPhi.setTenKhoanPhi(khoanphidieuchinh.getText());
DanhSachKhoanPhiDao.getInstance().update(danhSachKhoanPhi);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Điều chỉnh thành công");
        alert.showAndWait();

    }

    @FXML
    private TextField sophongdieuchinhphong;

    @FXML
    private TabPane tabpanedieuchinh;

    @FXML
    private TextField tennhankhau;

    @FXML
    private TextField tennhankhaucandieuchinh;

    @FXML
    private TextField trangthai;



    @FXML
    private ListView<String> listviewnhankhaucandieuchinh;
    private  List<NhanKhau> nhanKhauList= NhanKhauDao.getInstance().selectAll();
    @FXML
    void dieuchinhnhankhau(ActionEvent event) {

    }


    @FXML
    void timnhankhau(ActionEvent event) {

    }
    @FXML
    private Label khongtimthayphong;
    private HoKhau hoKhau=new HoKhau();
    private NhanKhau nhanKhau=new NhanKhau();
    List<NhanKhau> nhanKhaucuaphong=new ArrayList<>();

    @FXML
    void timphong(ActionEvent event) {
        thanhvienphong.getChildren().clear();
        listviewsophong.setVisible(false);
        hoKhau=HoKhauDao.getInstance().selectById(Integer.parseInt(sophongdieuchinhphong.getText()));
        if(hoKhau==null){
            Alert alert1=new Alert(Alert.AlertType.ERROR);
            alert1.setContentText("Không tìm thấy phòng");
            alert1.showAndWait();
        }
        else {

            nhanKhau= HoKhauDao.getInstance().layChuHo(hoKhau);
            nhanKhaucuaphong=HoKhauDao.getInstance().layNhanKhau(hoKhau);
            khongtimthayphong.setVisible(false);
            dientichphong.setText(String.valueOf(hoKhau.getDienTichPhong()));
            chuho.setText(nhanKhau.getTen());
Label label2=new Label("Số thứ tự");
Label label3=new Label("Tên thành viên");
thanhvienphong.add(label2,0 ,0);
thanhvienphong.add(label3,1,0);
            anchopanethanhvienphong.setPrefHeight(nhanKhaucuaphong.size()*30);
           for(int i=0;i<nhanKhaucuaphong.size();i++){
               Label label=new Label(""+(i+1));
               Label label1=new Label(nhanKhaucuaphong.get(i).getTen());
               Button button=new Button("Xóa nhân khẩu");

               NhanKhau nhanKhau1 = nhanKhaucuaphong.get(i);
               button.setOnAction(event1 -> {
                   Alert alert=new Alert(Alert.AlertType.WARNING);
                   alert.setContentText("Bạn chắc chắn muốn xóa nhân khẩu khỏi phòng ?");
                   ButtonType buttonTypeYes = new ButtonType("Yes");
                   ButtonType buttonTypeNo = new ButtonType("No");
                   alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

                   // Hiển thị Alert và xử lý kết quả
                   Optional<ButtonType> result = alert.showAndWait();
                   if (result.isPresent() && result.get() == buttonTypeYes) {
                       nhanKhau1.setHoKhau(null);
                    NhanKhauDao.getInstance().update(nhanKhau1);
                       Alert alert1=new Alert(Alert.AlertType.CONFIRMATION);
                       alert1.setContentText("Xóa thành công");
                       alert1.showAndWait();
                   }

               });
               thanhvienphong.add(label,0,i+1);
               thanhvienphong.add(label1,1,i+1);
               thanhvienphong.add(button,2,i+1);
           }
        }

    }
    @FXML
    void listviewsophong(MouseEvent event) {
        String selectedItem = String.valueOf(listviewsophong.getSelectionModel().getSelectedItem());
        if (selectedItem != null) {
            sophongdieuchinhphong.setText(selectedItem);
            listviewsophong.setVisible(false);
        }
    }
    @FXML
    void listviewnhankhaucandieuchinh(MouseEvent event) {
        String selectedItem = listviewnhankhaucandieuchinh.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            tennhankhaucandieuchinh.setText(selectedItem);
            listviewnhankhaucandieuchinh.setVisible(false);
        }
    }
    @FXML
    private ListView<Integer> listviewsophong;
    private List<DanhSachKhoanPhi>danhSachKhoanPhiList= DanhSachKhoanPhiDao.getInstance().selectAll();

   private List<HoKhau>tenphong= HoKhauDao.getInstance().selectAll();
    @FXML
    void dieuchinhphong(ActionEvent event) {
hoKhau.setId(Integer.parseInt(sophongdieuchinhphong.getText()));
hoKhau.setDienTichPhong(Double.parseDouble(dientichphong.getText()));
HoKhauDao.getInstance().update(hoKhau);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Điều chỉnh thành công");
        alert.showAndWait();
    }
    @FXML
    void xoaphong(ActionEvent event) {
Alert alert=new Alert(Alert.AlertType.WARNING);
alert.setHeaderText("Bạn chắc chắn muốn xóa phòng ?");
alert.setContentText("Khi đó thông tin phòng sẽ không còn");
        ButtonType buttonTypeOK = new ButtonType("OK", ButtonType.OK.getButtonData());
        // Thêm nút "Hủy"
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonType.CANCEL.getButtonData());

        alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeOK) {
            for(NhanKhau nhanKhau1: nhanKhaucuaphong){
                nhanKhau1.setHoKhau(null);
                nhanKhau1.setChuHo(false);
            }
            HoKhauDao.getInstance().delete(hoKhau.getId());
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        sophongdieuchinhphong.textProperty().addListener((observable, oldValue, newValue) -> {
            String input = sophongdieuchinhphong.getText().toLowerCase();
            if (input.isEmpty()) {
                listviewsophong.getItems().clear();
                listviewsophong.setVisible(false);
            } else {
                // Lọc các từ gợi ý dựa trên input của người dùng
                List<Integer> list=tenphong.stream().map(HoKhau::getId).filter(s->String.valueOf(s).toLowerCase().startsWith(input)).collect(Collectors.toList());
                ObservableList<Integer>observableList=FXCollections.observableList(list);
                // Hiển thị danh sách gợi ý
                listviewsophong.setItems(observableList);
                listviewsophong.setVisible(true);
            }
        });
        tennhankhaucandieuchinh.textProperty().addListener((observable, oldValue, newValue) -> {
                String input=tennhankhaucandieuchinh.getText().toLowerCase();
                if(input.isEmpty()){
                    listviewnhankhaucandieuchinh.setVisible(false);
                    listviewnhankhaucandieuchinh.getItems().clear();
                }
                else{
                    List<String> list=nhanKhauList.stream().map(NhanKhau::getTen).filter(s -> s.toLowerCase().startsWith(input)).collect(Collectors.toList());
                    ObservableList<String> observableList = FXCollections.observableList(list);
                    listviewnhankhaucandieuchinh.setItems(observableList);
                    listviewnhankhaucandieuchinh.setVisible(true);
                }
            });
        khoanphidieuchinh.textProperty().addListener((observable, oldValue, newValue) -> {
            String input=khoanphidieuchinh.getText().toLowerCase();
            if(input.isEmpty()){
                listviewphi.getItems().clear();
                listviewphi.setVisible(false);
            }else{
                List<String>list=danhSachKhoanPhiList.stream().map(DanhSachKhoanPhi::getTenKhoanPhi).filter(s->s.toLowerCase().startsWith(input)).collect(Collectors.toList());
                ObservableList<String> observableList = FXCollections.observableList(list);
                listviewphi.setItems(observableList);
                listviewphi.setVisible(true);
            }
        });
    }
}
