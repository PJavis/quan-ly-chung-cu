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
import org.example.Model.EntityAll.*;
import org.example.Model.Hibernatedao.*;
import org.example.getData;


import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class Dieuchinhhokhau implements Initializable {
    @FXML
    private TableColumn<PhuongTien, String> biensoxe;
    @FXML
    private TableColumn<PhuongTien, Void> lichsugiaodich1;
    @FXML
    private TableColumn<PhuongTien, String> loaixe;

    @FXML
    private TableColumn<PhuongTien, String> sotiendanop11;
    @FXML
    private TableColumn<PhuongTien, String> ten11;
    @FXML
    private TableView<PhuongTien> thongtinphuongtien;


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
nhanKhaus=NhanKhauDao.getInstance().selectNhanKhauById(hoKhau);
nopPhiList= NopPhiDao.getInstance().selectByHoKhau(hoKhau);
phuongTiens= PhuongTienDao.getInstance().selectByHoKhau(hoKhau);
        danhsachthanhvien();
        danhsachkhoanphi();
        danhsachphuongtien();
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
                                LichSuThayDoi lichSuThayDoi=new LichSuThayDoi();
                                lichSuThayDoi.setHoKhau(hoKhaus);
                                lichSuThayDoi.setNgayThayDoi(Date.valueOf(LocalDate.now()));
                                lichSuThayDoi.setThayDoi("Xóa nhân khẩu có tên là "+person.getTen());
                                LichSuThayDoiDao.getInstance().save(lichSuThayDoi);
                                sothanhvien.setText(String.valueOf(hoKhaus.getSoNhanKhau()));
                                NhanKhauDao.getInstance().delete(person);
                            getData.getInstance().removeNhankhau(person);
                            hoKhaus.setSoNhanKhau(hoKhaus.getSoNhanKhau()-1);
                            HoKhauDao.getInstance().update(hoKhaus);
                            getData.getInstance().updateHokhau(hoKhaus);
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
            NhanKhau q=danhsachthanhvien.getItems().get(0);

            try{
                if(!(nhanKhau.getIdNguoiDan()==q.getIdNguoiDan())){
                nhanKhau.setChuHo(true);
                getData.getInstance().setNhankhau(nhanKhau);
                NhanKhauDao.getInstance().update(nhanKhau);
                q.setChuHo(false);
                NhanKhauDao.getInstance().update(q);
                    LichSuThayDoi lichSuThayDoi=new LichSuThayDoi();
                    lichSuThayDoi.setHoKhau(hoKhaus);
                    lichSuThayDoi.setNgayThayDoi(Date.valueOf(LocalDate.now()));
                    lichSuThayDoi.setThayDoi("Đổi chủ hộ có tên là "+q.getTen()+" sang chủ hộ có tên là  "+nhanKhau.getTen());
                    LichSuThayDoiDao.getInstance().save(lichSuThayDoi);
                }
            }
            catch (Exception e){
                nhanKhau=q;
            }
            getData.getInstance().setNhankhau(q);
            hoKhaus.setDienTichPhong(Double.parseDouble(dientichphong.getText()));
            hoKhaus.setTenchuho(nhanKhau.getTen());
            hoKhaus.setSoDienThoai(nhanKhau.getSoDienThoai());
            HoKhauDao.getInstance().update(hoKhaus);
            getData.getInstance().updateHokhau(hoKhaus);
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setHeaderText("Thành công");
            alert1.setContentText("Điều chỉnh hộ khẩu thành công");
            alert1.showAndWait();
            
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
                getData.getInstance().removeNhankhau(nhanKhau);
                NhanKhauDao.getInstance().delete(nhanKhau);
            }
            List<PhuongTien> phuongTiens1=PhuongTienDao.getInstance().selectByHoKhau(hoKhaus);
            for(PhuongTien phuongTien:phuongTiens1){

                List<LichSuGiaoDichPhiGuiXe>lichSuGiaoDichPhiGuiXes= LichSuGiaoDichPhiGuiXeDao.getInstance().selectByCondition(phuongTien);
                for(LichSuGiaoDichPhiGuiXe lichSuGiaoDichPhiGuiXe : lichSuGiaoDichPhiGuiXes){
                    LichSuGiaoDichPhiGuiXeDao.getInstance().delete(lichSuGiaoDichPhiGuiXe);
                }
                PhuongTienDao.getInstance().delete(phuongTien);
            }
            for(NopPhi nopPhi :nopPhiList){
                NopPhiDao.getInstance().delete(nopPhi);
            }
            List<LichSuThayDoi>lichSuThayDois=LichSuThayDoiDao.getInstance().selectByHoKhau(hoKhaus);
            for(LichSuThayDoi lichSuThayDoi : lichSuThayDois){
                LichSuThayDoiDao.getInstance().delete(lichSuThayDoi);
            }
            HoKhauDao.getInstance().delete(hoKhaus);

            getData.getInstance().removeHokhau(hoKhaus);
            Alert alert1=new Alert(Alert.AlertType.CONFIRMATION);
            alert1.setHeaderText("Thành công");
            alert1.setContentText("Xóa hộ khẩu thành công");
            alert1.showAndWait();
            quaylai(event);
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
        for (NopPhi nopPhi : nopPhiList){
            if(!(nopPhi.getSotienchuanop()==0)){
                nopPhis.add(nopPhi);
            }
        }
        sothutu1.setCellValueFactory(cellData -> {
            int rowIndex = cellData.getTableView().getItems().indexOf(cellData.getValue()) + 1;
            return javafx.beans.binding.Bindings.createObjectBinding(() -> rowIndex);
        });
        tenkhoanphi.setCellValueFactory(new PropertyValueFactory<>("tenKhoanPhi"));
        loaikhoanphi.setCellValueFactory(new PropertyValueFactory<>("loaiKhoanPhi"));
        sotiendanop.setCellValueFactory(new PropertyValueFactory<>("decimalFormatSotiendanop"));
        sotienchuanop.setCellValueFactory(new PropertyValueFactory<>("DecimalFormatsotien"));
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
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example/Views/Lichsugiaodich.fxml"));
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

    @FXML
    void quaylai(ActionEvent event) {
        try {
            Stage ag0r1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example/Views/Quanlyphong.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            ag0r1.setScene(scene);
            ag0r1.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private List<PhuongTien> phuongTiens;
    public void danhsachphuongtien(){
        ObservableList<PhuongTien> phuongTiens1=FXCollections.observableArrayList(phuongTiens);
        ten11.setCellValueFactory(new PropertyValueFactory<>("tenChuXe"));
        biensoxe.setCellValueFactory(new PropertyValueFactory<>("BienSoXe"));
        loaixe.setCellValueFactory(new PropertyValueFactory<>("loaiPhuongTien"));
        sotiendanop11.setCellValueFactory(new PropertyValueFactory<>("decimalFormatSotiendanop"));
        lichsugiaodich1.setCellFactory(cell-> {
            return new TableCell<PhuongTien, Void>() {
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
                            PhuongTien person = getTableView().getItems().get(getIndex());
                            try {
                                Stage ag0r = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example/Views/Lichsugiaodichthuphiguixe.fxml"));
                                Parent root = loader.load();
                                Scene scene = new Scene(root);
                                Stage ag0r1=new Stage();
                                ag0r1.setScene(scene);
                                ag0r1.initModality(Modality.APPLICATION_MODAL);
                                ag0r1.initOwner(ag0r);
                                Lichsugiaodichthuphiguixe lichsugiaodich=loader.getController();
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
        thongtinphuongtien.setItems(phuongTiens1);
    }

    @FXML
    void lichsuthaydoi(ActionEvent event) {
        try {
            Stage ag0r = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example/Views/Lichsuthaydoi.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage ag0r1=new Stage();
            ag0r1.setScene(scene);
            ag0r1.initModality(Modality.APPLICATION_MODAL);
            ag0r1.initOwner(ag0r);
            Lichsuthaydoi lichsugiaodich=loader.getController();
            lichsugiaodich.setlichsu(hoKhaus);
            ag0r1.showAndWait();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
