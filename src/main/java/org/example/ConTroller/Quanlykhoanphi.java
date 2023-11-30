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
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.EntityAll.KhoanPhi;
import org.example.EntityAll.NhanKhau;
import org.example.Hibernatedao.KhoanPhiDao;
import org.example.getData;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
    private TableColumn<KhoanPhi, Double> sotientable;

    @FXML
    private TableColumn<KhoanPhi, Double> sotiendanop;

    @FXML
    private TextField tenkhoanphi;
    @FXML
    private TableColumn<KhoanPhi, String> tenkhoanphitable;
    @FXML
    private TextField timkiem;
    private List<KhoanPhi> khoanPhiList= getData.getInstance().getKhoanPhis();

    private ObservableList<KhoanPhi> khoanPhis;
    public void danhsachkhoanphi(){
        khoanPhis = FXCollections.observableArrayList(khoanPhiList);
        sothutu.setCellValueFactory(cellData -> {
            int rowIndex = cellData.getTableView().getItems().indexOf(cellData.getValue()) + 1;
            return javafx.beans.binding.Bindings.createObjectBinding(() -> rowIndex);
        });
        tenkhoanphitable.setCellValueFactory(new PropertyValueFactory<>("tenKhoanPhi"));
        loaikhoanphitable.setCellValueFactory(new PropertyValueFactory<>("loaiKhoanPhi"));
        hannoptable.setCellValueFactory(new PropertyValueFactory<>("ketThuc"));
        sotientable.setCellValueFactory(new PropertyValueFactory<>("giaTri"));
        sotiendanop.setCellValueFactory(new PropertyValueFactory<>("tongsotien"));
        chitiet.setCellFactory(cell->{
            return new TableCell<KhoanPhi,Void>(){
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
                            KhoanPhi person = getTableView().getItems().get(getIndex());
                            try {
                                Stage ag0r = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example/Chitiet.fxml"));
                                Parent root = loader.load();
                                Scene scene = new Scene(root);
                                Stage ag0r1=new Stage();
                                ag0r1.setScene(scene);
                                ag0r1.initModality(Modality.APPLICATION_MODAL);
                                ag0r1.initOwner(ag0r);
                                Chitiet chitietkhoanphi=loader.getController();
                                chitietkhoanphi.setKhoanPhi(person);
                                ag0r1.showAndWait();
                                khoanPhiList=getData.getInstance().getKhoanPhis();
                                danhsachkhoanphi();

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
        KhoanPhi khoanPhi=danhsachkhoanphi.getSelectionModel().getSelectedItem();
        int num = danhsachkhoanphi.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }
        tenkhoanphi.setText(khoanPhi.getTenKhoanPhi());
        sotien.setText(String.valueOf(khoanPhi.getGiaTri()));
        loaikhoanphi.setValue(khoanPhi.getLoaiKhoanPhi());
        hannop.setText(String.valueOf(khoanPhi.getKetThuc()));
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
    khoanPhi.setLoaiKhoanPhi(loaikhoanphi.getSelectionModel().getSelectedItem());
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String date = hannop.getText();
    LocalDate datetime = LocalDate.parse(date, formatter);
    khoanPhi.setKetThuc(Date.valueOf(datetime));
    khoanPhi.setGiaTri(Double.parseDouble(sotien.getText()));
    LocalDate currentDate = LocalDate.now();
    khoanPhi.setBatDau(Date.valueOf(currentDate));
    KhoanPhiDao.getInstance().save(khoanPhi);
    Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
    alert.setHeaderText("Thành công");
    alert.setContentText("Tạo khoản phí mới thành công");
    alert.showAndWait();
    tenkhoanphi.clear();
    hannop.clear();
    sotien.clear();
    loaikhoanphi.getEditor().clear();
getData.getInstance().addKhoanphi(khoanPhi);
khoanPhiList=getData.getInstance().getKhoanPhis();
danhsachkhoanphi();
}
    }

    @FXML
    void xoa(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loaikhoanphi.getItems().addAll("Bắt buộc", "Đóng góp");
        danhsachkhoanphi();

    }
}
