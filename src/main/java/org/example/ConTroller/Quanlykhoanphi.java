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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.example.EntityAll.HoKhau;
import org.example.EntityAll.KhoanPhi;
import org.example.EntityAll.NhanKhau;
import org.example.EntityAll.NopPhi;
import org.example.Hibernatedao.KhoanPhiDao;
import org.example.Hibernatedao.NopPhiDao;
import org.example.getData;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Quanlykhoanphi implements Initializable {
    @FXML
    private TableColumn< KhoanPhi, Void> chitiet;


    @FXML
    private TableView<KhoanPhi> danhsachkhoanphi;


    @FXML
    private TextField hannop;

    @FXML
    private ComboBox<String> loaikhoanphi;
    @FXML
    private TableColumn<KhoanPhi, String> hannoptable;
    @FXML
    private TableColumn<KhoanPhi, String> loaikhoanphitable;

    @FXML
    private TableColumn<KhoanPhi, Integer> sothutu;
    @FXML
    private TextField sotien;
    @FXML
    private TableColumn<KhoanPhi, String> sotientable;

    @FXML
    private TableColumn<KhoanPhi, String> sotiendanop;

    @FXML
    private TextField tenkhoanphi;
    @FXML
    private TableColumn<KhoanPhi, String> tenkhoanphitable;
    @FXML
    private TableColumn<KhoanPhi, String> ngaybatdau;
    @FXML
    private TextField timkiem;
    private List<KhoanPhi> khoanPhiList= getData.getInstance().getKhoanPhis();
 private KhoanPhi khoanPhi1;
    private ObservableList<KhoanPhi> khoanPhis;
    public void danhsachkhoanphi(){
        khoanPhis = FXCollections.observableArrayList(khoanPhiList);
        khoanPhis.sort((date1, date2) ->
                date2.getKetThuc().getYear() != date1.getKetThuc().getYear() ? Integer.compare(date2.getKetThuc().getYear(), date1.getKetThuc().getYear()) :
                        date2.getKetThuc().getMonth() != date1.getKetThuc().getMonth() ? Integer.compare(date2.getKetThuc().getMonth(), date1.getKetThuc().getMonth()) :
                                Integer.compare(date2.getKetThuc().getDay(), date1.getKetThuc().getDay()));
        sothutu.setCellValueFactory(cellData -> {
            int rowIndex = cellData.getTableView().getItems().indexOf(cellData.getValue()) + 1;
            return javafx.beans.binding.Bindings.createObjectBinding(() -> rowIndex);
        });
        tenkhoanphitable.setCellValueFactory(new PropertyValueFactory<>("tenKhoanPhi"));
        loaikhoanphitable.setCellValueFactory(new PropertyValueFactory<>("loaiKhoanPhi"));
        hannoptable.setCellValueFactory(new PropertyValueFactory<>("formattedDate"));
        sotientable.setCellValueFactory(new PropertyValueFactory<>("decimalFormatsotien"));
        sotiendanop.setCellValueFactory(new PropertyValueFactory<>("decimalFormatsotiendanop"));
        ngaybatdau.setCellValueFactory(new PropertyValueFactory<>("FormattedDatebatdau"));
        chitiet.setCellFactory(cell->{
            return new TableCell<KhoanPhi,Void>(){
                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                    } else {
                        HBox vbox = new HBox(10); // 10 là khoảng cách giữa các thành phần
                        Button button1 = new Button();
                        FontAwesomeIconView iconView1 = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        iconView1.setSize("16px");
                        button1.setGraphic(iconView1);
                        button1.setOnAction(event1 -> {

                            Alert alert=new Alert(Alert.AlertType.WARNING);
                            alert.setHeaderText("Bạn chắc chắn muốn xóa khoản phí này ?");
                            alert.setContentText("Khi đó thông tin về khoản phí sẽ không còn");
                            ButtonType buttonTypeOK = new ButtonType("OK", ButtonType.OK.getButtonData());
                            // Thêm nút "Hủy"
                            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonType.CANCEL.getButtonData());

                            alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);

                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.isPresent() && result.get() == buttonTypeOK) {
                                KhoanPhi person = getTableView().getItems().get(getIndex());
                                KhoanPhiDao.getInstance().delete(person);
                                Alert alert1=new Alert(Alert.AlertType.CONFIRMATION);
                                alert1.setHeaderText("Thành công");
                                alert1.setContentText("Xóa khoản phí thành công");
                                alert1.showAndWait();
                                getData.getInstance().removeKhoanphi(person);
                                khoanPhiList=getData.getInstance().getKhoanPhis();
                                danhsachkhoanphi();
                                timkiem();
                        }});
                        Button button = new Button();
                        FontAwesomeIconView iconView = new FontAwesomeIconView(FontAwesomeIcon.PENCIL);
                        iconView.setSize("16px");

                        button.setGraphic(iconView);
                        vbox.getChildren().addAll(button, button1);
                        setGraphic(vbox);
                        button.setOnAction(event -> {
                            KhoanPhi person = getTableView().getItems().get(getIndex());
                            try {
                                Stage ag0r = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example/Chitiet.fxml"));
                                Parent root = loader.load();
                                Scene scene = new Scene(root);
                                ag0r.setScene(scene);
                                Chitietkhoanphi chitietkhoanphi=loader.getController();
                                chitietkhoanphi.setKhoanPhi(person);
                                ag0r.show();
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        });
                    }
                }
            };
        });
        danhsachkhoanphi.setItems(khoanPhis);
    }
    @FXML
    void predieuchinh(MouseEvent event) {
        khoanPhi1=danhsachkhoanphi.getSelectionModel().getSelectedItem();
        int num = danhsachkhoanphi.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }
        tenkhoanphi.setText(khoanPhi1.getTenKhoanPhi());
        sotien.setText(khoanPhi1.getDecimalFormatsotien());
        loaikhoanphi.setValue(khoanPhi1.getLoaiKhoanPhi());
        hannop.setText(khoanPhi1.getFormattedDate());
    }

    @FXML
    void dieuchinh(ActionEvent event) {

    }
    private boolean isValidDateFormat(String date) {
        // Biểu thức chính quy cho định dạng dd/mm/yyyy
        String regex = "^\\d{2}/\\d{2}/\\d{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);

        return matcher.matches();
    }
    @FXML
    void taomoi(ActionEvent event) {
if(hannop.getText().isEmpty()||tenkhoanphi.getText().isEmpty()||loaikhoanphi.getSelectionModel().isEmpty()){
    Alert alert=new Alert(Alert.AlertType.ERROR);
    alert.setHeaderText("Thất bại");
    alert.setContentText("Vui lòng điền đầy đủ thông tin");
    alert.showAndWait();
} else if (!isValidDateFormat(hannop.getText())) {
    Alert alert=new Alert(Alert.AlertType.ERROR);
    alert.setHeaderText("Thất bại");
    alert.setContentText("Vui lòng điền hạn nộp theo dạng dd/mm/yyyy");
    alert.showAndWait();
}else{
    KhoanPhi khoanPhi=new KhoanPhi();
    khoanPhi.setTenKhoanPhi(tenkhoanphi.getText());
    khoanPhi.setTongsotien(0);
    if(Objects.equals(tenkhoanphi.getText(), "Phí dịch vụ chung cư")||Objects.equals(tenkhoanphi.getText(), "Phí quản lý chung cư")) khoanPhi.setPhidichvuchungcu(1);
    else khoanPhi.setPhidichvuchungcu(0);
    khoanPhi.setLoaiKhoanPhi(loaikhoanphi.getSelectionModel().getSelectedItem());
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String date = hannop.getText();
    LocalDate datetime = LocalDate.parse(date, formatter);
    khoanPhi.setKetThuc(Date.valueOf(datetime));
    khoanPhi.setGiaTri(Double.parseDouble(sotien.getText().replace(",","")));
    LocalDate currentDate = LocalDate.now();
    khoanPhi.setBatDau(Date.valueOf(currentDate));
    KhoanPhiDao.getInstance().save(khoanPhi);
    Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
    alert.setHeaderText("Thành công");
    alert.setContentText("Tạo khoản phí mới thành công");
    alert.showAndWait();
    if(Objects.equals(loaikhoanphi.getValue(), "Bắt buộc")){
        List<HoKhau> hoKhaus=getData.getInstance().getHoKhaus();
        for(HoKhau hoKhau : hoKhaus){
            NopPhi nopPhi=new NopPhi();
            nopPhi.setIdKhoanPhi(khoanPhi.getId());
            nopPhi.setSoPhong(hoKhau.getId());
            nopPhi.setSoTang(hoKhau.getSoTang());
            nopPhi.setTenchuho(hoKhau.getTenchuho());
            nopPhi.setDienTichPhong(hoKhau.getDienTichPhong());
            nopPhi.setSoTienDaDong(0);
            nopPhi.setGiaTri(khoanPhi.getGiaTri());
            NopPhiDao.getInstance().save(nopPhi);
        }
    }
    tenkhoanphi.clear();
    hannop.clear();
    sotien.clear();
    loaikhoanphi.getEditor().clear();
getData.getInstance().addKhoanphi(khoanPhi);
khoanPhiList=getData.getInstance().getKhoanPhis();
danhsachkhoanphi();
timkiem();
}
    }
 public void timkiem(){
     FilteredList<KhoanPhi> filter = new FilteredList<>(khoanPhis, e -> true);

     timkiem.textProperty().addListener((Observable, oldValue, newValue) -> {

         filter.setPredicate(predicateEmployeeData -> {

             if (newValue == null || newValue.isEmpty()) {
                 return true;
             }

           if(predicateEmployeeData.getTenKhoanPhi().toLowerCase().contains(newValue.toLowerCase())){
               return true;
           }else if(predicateEmployeeData.getLoaiKhoanPhi().toLowerCase().contains(newValue.toLowerCase())){
               return true;
           }else if(predicateEmployeeData.getFormattedDate().contains(newValue)){
               return  true;
           }

             return false;
         });
     });

     SortedList<KhoanPhi> sortList = new SortedList<>(filter);

     sortList.comparatorProperty().bind(danhsachkhoanphi.comparatorProperty());
     danhsachkhoanphi.setItems(sortList);
 }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loaikhoanphi.getItems().addAll("Bắt buộc", "Đóng góp");
        danhsachkhoanphi();
        timkiem();
    }
}
