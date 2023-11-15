package org.example.ConTroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.example.EntityAll.DanhSachKhoanPhi;
import org.example.EntityAll.HoKhau;
import org.example.EntityAll.NopPhi;
import org.example.Hibernatedao.DanhSachKhoanPhiDao;
import org.example.Hibernatedao.HoKhauDao;
import org.example.Hibernatedao.NopPhiDao;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ThuphiController implements Initializable {

    @FXML
    private Label duno;

    @FXML
    private ListView<String> listviewtenphi;

    @FXML
    private ListView<Integer> listviewtenphong;

    @FXML
    private Label loaikhoanphi;

    @FXML
    private TextField sophong;

    @FXML
    private TextField tenphi;
    @FXML
    private Label sotien;
    @FXML
    private Label sotiendanop;
    @FXML
    private TextField sotiennop;


    @FXML
    void listviewtenphi(MouseEvent event) {
        String selectedItem = String.valueOf(listviewtenphi.getSelectionModel().getSelectedItem());
        if (selectedItem != null) {
            tenphi.setText(selectedItem);
            listviewtenphi.setVisible(false);
        }
    }

    @FXML
    void listviewtenphong(MouseEvent event) {
        String selectedItem = String.valueOf(listviewtenphong.getSelectionModel().getSelectedItem());
        if (selectedItem != null) {
            sophong.setText(selectedItem);
            listviewtenphong.setVisible(false);
        }
    }
private DanhSachKhoanPhi danhSachKhoanPhi=new DanhSachKhoanPhi();
    @FXML
    void timphi(ActionEvent event) {
        listviewtenphi.setVisible(false);
try{danhSachKhoanPhi=DanhSachKhoanPhiDao.getInstance().selectByName(tenphi.getText()).get(0);}
catch (Exception e){
    Alert alert1=new Alert(Alert.AlertType.ERROR);
    alert1.setContentText("Không tìm thấy phí");
    alert1.showAndWait();
        }
        loaikhoanphi.setText(danhSachKhoanPhi.getLoaiKhoanPhi());
            sotien.setText(String.valueOf(danhSachKhoanPhi.getGiaTri()));

    }
private HoKhau hoKhau=new HoKhau();
    private NopPhi nopPhi=new NopPhi();
    @FXML
    void timphong(ActionEvent event) {
listviewtenphong.setVisible(false);
    hoKhau=HoKhauDao.getInstance().selectById(Integer.parseInt(sophong.getText()));

    if(Objects.equals(danhSachKhoanPhi.getLoaiKhoanPhi(), "Bắt buộc")){
           try {

                   nopPhi=NopPhiDao.getInstance().selectByTenKhoanPhiVaHoKhau(danhSachKhoanPhi.getTenKhoanPhi(),hoKhau);

               if(nopPhi==null) {
                   nopPhi=new NopPhi();

                   nopPhi.setHoKhau(hoKhau.getId());
                   nopPhi.setIdKhoanPhi(danhSachKhoanPhi.getId());
                   nopPhi.setSoTienDaDong(0);
                   nopPhi.setTrangThaiDongPhi(false);
                   nopPhi.setNgayNopPhi(null);

                   NopPhiDao.getInstance().save(nopPhi);

               }
                    duno.setText(String.valueOf(danhSachKhoanPhi.getGiaTri()*hoKhau.getDienTichPhong()-nopPhi.getSoTienDaDong()));
               sotiendanop.setText(String.valueOf(nopPhi.getSoTienDaDong()));
           }catch (Exception e){
               Alert alert1=new Alert(Alert.AlertType.ERROR);
               alert1.setContentText("Không tìm thấy phòng");
               alert1.showAndWait();
           }}
            else duno.setText("0");


    }

    @FXML
    void nopphi(ActionEvent event) {
nopPhi.setSoTienDaDong(Double.parseDouble(sotiennop.getText()));
NopPhiDao.getInstance().update(nopPhi);
    }
    private List<HoKhau> tenphong= HoKhauDao.getInstance().selectAll();
    private List<DanhSachKhoanPhi>danhSachKhoanPhiList= DanhSachKhoanPhiDao.getInstance().selectAll();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sophong.textProperty().addListener((observable, oldValue, newValue) -> {
            String input = sophong.getText().toLowerCase();
            if (input.isEmpty()) {
                listviewtenphong.getItems().clear();
                listviewtenphong.setVisible(false);
            } else {
                // Lọc các từ gợi ý dựa trên input của người dùng
                List<Integer> list=tenphong.stream().map(HoKhau::getId).filter(s->String.valueOf(s).toLowerCase().startsWith(input)).collect(Collectors.toList());
                ObservableList<Integer> observableList= FXCollections.observableList(list);
                // Hiển thị danh sách gợi ý
                listviewtenphong.setItems(observableList);
                listviewtenphong.setVisible(true);
            }
        });
        tenphi.textProperty().addListener((observable, oldValue, newValue) -> {
            String input=tenphi.getText().toLowerCase();
            if(input.isEmpty()){
                listviewtenphi.getItems().clear();
                listviewtenphi.setVisible(false);
            }else{
                List<String>list=danhSachKhoanPhiList.stream().map(DanhSachKhoanPhi::getTenKhoanPhi).filter(s->s.toLowerCase().startsWith(input)).collect(Collectors.toList());
                ObservableList<String> observableList = FXCollections.observableList(list);
                listviewtenphi.setItems(observableList);
                listviewtenphi.setVisible(true);
            }
        });
    }
}
