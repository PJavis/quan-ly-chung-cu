package org.example.ConTroller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.EntityAll.*;
import org.example.Hibernatedao.*;
import org.example.getData;

import java.net.URL;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class ThuphiController implements Initializable {
    private List<NopPhi> nopPhikhoanphi;
    private List<NopPhi> nopPhidonggop;
    private List<PhuongTien> phuongTiens;
    private HoKhau hoKhau;
    @FXML
    private TableColumn<NopPhi, Void> chinhsua;
    @FXML
    private TableColumn<NopPhi, Integer> giatri;
    @FXML
    private TableColumn<NopPhi, String> sotiendanop;


    @FXML
    private TextField sophong;

    @FXML
    private TextField sotang;

    @FXML
    private TableColumn<NopPhi, String> ten;

    @FXML
    private TableColumn<NopPhi, Integer> sodiennuoc;


    @FXML
    private GridPane thongtinphuongtien;
    private boolean isInputInvalid() {
        return sophong.getText().isEmpty() || sotang.getText().isEmpty();
    }



    @FXML
    void nopphi(ActionEvent event) {
       /* if (isInputInvalid()) {
            showAlert("Thất bại", "Không tìm thấy hộ khẩu");
            return;
        }

        try {
            double amount = Double.parseDouble(sotiennop.getText());
        } catch (NumberFormatException e) {
            showAlert("Thất bại", "Hãy nhập số tiền bạn muốn nộp vào số tiền nộp");
            return;
        }

        if (Double.parseDouble(sotiennop.getText()) > duNo) {
            showAlert("Thất bại", "Bạn không thể nộp số tiền vượt quá số tiền phải đóng");
            sotiennop.clear();
            return;
        }

        if (nguoinopphi.getText().isEmpty()) {
            showAlert("Thất bại", "Hãy nhập tên người nộp");
            return;
        }

        updateNopPhiAndKhoanPhi();

        saveLichSuGiaoDich();

        clearInputFields();

        showAlert("Thành công", "Nộp phí thành công");*/
    }




   /* private void saveLichSuGiaoDich() {
        LichSuGiaoDich lichSuGiaoDich = createLichSuGiaoDich();
        LichSuGiaoDichDao.getInstance().save(lichSuGiaoDich);
    }

    private LichSuGiaoDich createLichSuGiaoDich() {
        LichSuGiaoDich lichSuGiaoDich = new LichSuGiaoDich();
        lichSuGiaoDich.setSoPhong(nopPhi.getSoPhong());
        lichSuGiaoDich.setSoTang(nopPhi.getSoTang());
        lichSuGiaoDich.setTenKhoanPhi(khoanPhi.getTenKhoanPhi());
        lichSuGiaoDich.setNopPhi(nopPhi);
        lichSuGiaoDich.setTennguoinop(nguoinopphi.getText());
        lichSuGiaoDich.setGiaTri(Double.parseDouble(sotiennop.getText()));
        LocalDate today = LocalDate.now();
        Date date = Date.valueOf(today);
        lichSuGiaoDich.setThoigiangiaodich(date);
        return lichSuGiaoDich;
    }*/

    private void showAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
    public  String getDecimalFormat(Double d){
        String pattern = "#,##0" + (d % 1 == 0 ? "" : ".#########");
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(d);
    }
    @FXML
    private TableView<NopPhi> thongtinkhoanphi;
    @FXML
    void timphong(ActionEvent event) {
        hoKhau = HoKhauDao.getInstance().selectById(Integer.parseInt(sotang.getText()), Integer.parseInt(sophong.getText()));
        if(hoKhau==null){
            showAlert("Lỗi","Không tìm thấy hộ khẩu");
        }else{
            nopPhikhoanphi=NopPhiDao.getInstance().selectByHoKhauandKhoanphi(hoKhau);
            nopPhidonggop=NopPhiDao.getInstance().selectByHoKhauandDonggop(hoKhau);
            phuongTiens= PhuongTienDao.getInstance().selectByHoKhau(hoKhau);
            danhsachkhoanphi();

        }

    }

    public void danhsachkhoanphi(){
        ObservableList<NopPhi> nopPhis=FXCollections.observableArrayList(nopPhikhoanphi);
        ten.setCellValueFactory(new PropertyValueFactory<>("TenKhoanPhi"));
        sodiennuoc.setCellValueFactory(new PropertyValueFactory<>("sodiennuoc"));
        giatri.setCellValueFactory(new PropertyValueFactory<>("DecimalFormatsotien"));
        sotiendanop.setCellValueFactory(new PropertyValueFactory<>("decimalFormatSotiendanop"));
        chinhsua.setCellFactory(cell->{
            return new TableCell<NopPhi,Void>(){
                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                    } else {
                        NopPhi nopPhi= getTableView().getItems().get(getIndex());
                        HBox vbox = new HBox(10); // 10 là khoảng cách giữa các thành phần
                        Button button1 = new Button();
                        FontAwesomeIconView iconView1 = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        iconView1.setSize("16px");
                        button1.setGraphic(iconView1);
                        button1.setOnAction(event1 -> {
                            try {
                                Stage ag0r = (Stage) ((Node) event1.getSource()).getScene().getWindow();
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example/Chitietthuphi.fxml"));
                                Parent root = loader.load();
                                Scene scene = new Scene(root);
                                Stage ag0r1=new Stage();
                                ag0r1.setScene(scene);
                                ag0r1.initModality(Modality.APPLICATION_MODAL);
                                ag0r1.initOwner(ag0r);
                                Chitietthuphi chitietthuphi = loader.getController();
                                chitietthuphi.setNopphi(nopPhi);
                                ag0r1.showAndWait();
                                timphong(event1);

                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        });
                        vbox.getChildren().addAll(button1);
                        if(Objects.equals(nopPhi.getKhoanPhi().getDonVi(), "Số(kWh)") || Objects.equals(nopPhi.getKhoanPhi().getDonVi(), "Khối(m³)")) {
                            Button button = new Button();
                            FontAwesomeIconView iconView = new FontAwesomeIconView(FontAwesomeIcon.PENCIL);
                            iconView.setSize("16px");
                            button.setGraphic(iconView);
                            vbox.getChildren().addAll(button);

                            button.setOnAction(event -> {
                                try {
                                    Stage ag0r = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example/Dieuchinhnopphi.fxml"));
                                    Parent root = loader.load();
                                    Scene scene = new Scene(root);
                                    Stage ag0r1=new Stage();
                                    ag0r1.setScene(scene);
                                    ag0r1.initModality(Modality.APPLICATION_MODAL);
                                    ag0r1.initOwner(ag0r);
                                    Dieuchinhnopphi dieuchinhnopphi = loader.getController();
                                    dieuchinhnopphi.setdieuchinh(nopPhi);
                                    ag0r1.showAndWait();
                                    timphong(event);

                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            });
                        }

                        setGraphic(vbox);
                    }
                }
            };
        });

        thongtinkhoanphi.setItems(nopPhis);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
