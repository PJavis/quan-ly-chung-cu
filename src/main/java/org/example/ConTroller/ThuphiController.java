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
    private TableColumn<NopPhi, String> sotiendanop1;
    @FXML
    private TableColumn<NopPhi, Void> chinhsua1;
    @FXML
    private TableColumn<NopPhi, Void> giatri1;

    @FXML
    private TableColumn<NopPhi, String> ten1;
    @FXML
    private TableView<NopPhi> thongtindonggop;
    @FXML
    private TextField sophong;

    @FXML
    private TextField sotang;

    @FXML
    private TableColumn<NopPhi, String> ten;

    @FXML
    private TableColumn<NopPhi, Integer> sodiennuoc;


    @FXML
    private TableColumn<PhuongTien, String> loaixe;
    @FXML
    private TableColumn<PhuongTien, String> sotiendanop11;
    @FXML
    private TableColumn<PhuongTien, String> ten11;
    @FXML
    private TableView<PhuongTien> thongtinphuongtien;
    @FXML
    private TableColumn<PhuongTien, Void> chinhsua11;
    @FXML
    private TableColumn<PhuongTien, String> duno;
    @FXML
    private TextField tennguoinopphi;


    @FXML
    private Label tongsotiennop;
    private Double tongsotiennop1 = 0.0;
    private boolean isInputInvalid() {
        return sophong.getText().isEmpty() || sotang.getText().isEmpty();
    }



    @FXML
    void nopphi(ActionEvent event) {
        if (isInputInvalid()) {
            showAlert("Thất bại", "Không tìm thấy hộ khẩu");
            return;
        }


        if (tennguoinopphi.getText().isEmpty()) {
            showAlert("Thất bại", "Hãy nhập tên người nộp");
            return;
        }

        for(NopPhi nopPhi :nopPhikhoanphi){
            LichSuGiaoDich lichSuGiaoDich=new LichSuGiaoDich();
            lichSuGiaoDich.setTennguoinop(tennguoinopphi.getText());
            lichSuGiaoDich.setNopPhi(nopPhi);
            lichSuGiaoDich.setGiaTri(nopPhi.getSotienchuanop());
            LocalDate today = LocalDate.now();
            Date date = Date.valueOf(today);
            lichSuGiaoDich.setThoigiangiaodich(date);
            LichSuGiaoDichDao.getInstance().save(lichSuGiaoDich);
            nopPhi.setSoTienDaDong(nopPhi.getGiaTri());
            NopPhiDao.getInstance().update(nopPhi);
            KhoanPhi khoanPhi=nopPhi.getKhoanPhi();
            khoanPhi.setTongsotien(khoanPhi.getTongsotien()+nopPhi.getSotienchuanop());
            KhoanPhiDao.getInstance().update(khoanPhi);
            getData.getInstance().updateKhoanphi(khoanPhi);
        }
        for(PhuongTien nopPhi :phuongTiens){
            LichSuGiaoDichPhiGuiXe lichSuGiaoDich=new LichSuGiaoDichPhiGuiXe();
            lichSuGiaoDich.setTennguoinop(tennguoinopphi.getText());
            lichSuGiaoDich.setGiaTri(nopPhi.getPhiGuiXe()-nopPhi.getSoTienDaNop());
            LocalDate today = LocalDate.now();
            Date date = Date.valueOf(today);
            lichSuGiaoDich.setThoigiangiaodich(date);
            lichSuGiaoDich.setPhuongTien(nopPhi);
            LichSuGiaoDichPhiGuiXeDao.getInstance().save(lichSuGiaoDich);
            nopPhi.setSoTienDaNop(nopPhi.getPhiGuiXe());
            PhuongTienDao.getInstance().update(nopPhi);
        }


        tennguoinopphi.clear();
        sophong.clear();
        sotang.clear();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Thành công");
        alert.setContentText("Nộp phí thành công");
        alert.showAndWait();
    }


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
        hoKhau = HoKhauDao.getInstance().selectById(Integer.parseInt(sophong.getText()), Integer.parseInt(sotang.getText()));
        if(hoKhau==null){
            showAlert("Lỗi","Không tìm thấy hộ khẩu");
        }else{
            tongsotiennop1=0.0;
            nopPhikhoanphi=NopPhiDao.getInstance().selectByHoKhauandKhoanphi(hoKhau);
            for(NopPhi nopPhi :nopPhikhoanphi){
                tongsotiennop1+=nopPhi.getGiaTri()-nopPhi.getSoTienDaDong();
            }


            nopPhidonggop=NopPhiDao.getInstance().selectByHoKhauandDonggop(hoKhau);
            phuongTiens= PhuongTienDao.getInstance().selectByHoKhau(hoKhau);
            for(PhuongTien nopPhi :phuongTiens){
                tongsotiennop1+=nopPhi.getPhiGuiXe()-nopPhi.getSoTienDaNop();
            }
            tongsotiennop.setText(getDecimalFormat(tongsotiennop1));
            danhsachkhoanphi();
            danhsachdonggop();
            danhsachphuongtien();
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
                        FontAwesomeIconView iconView1 = new FontAwesomeIconView(FontAwesomeIcon.MONEY);
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
    public void danhsachdonggop(){
        ObservableList<NopPhi> nopPhis=FXCollections.observableArrayList(nopPhidonggop);
        ten1.setCellValueFactory(new PropertyValueFactory<>("TenKhoanPhi"));
        giatri1.setCellValueFactory(new PropertyValueFactory<>("DecimalFormatsotien"));
        sotiendanop1.setCellValueFactory(new PropertyValueFactory<>("decimalFormatSotiendanop"));
        chinhsua1.setCellFactory(cell->{
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
                        FontAwesomeIconView iconView1 = new FontAwesomeIconView(FontAwesomeIcon.MONEY);
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
                        CheckBox checkBox=new CheckBox();
                        checkBox.setOnAction(event1->{
                            if(checkBox.isSelected()){
                                tongsotiennop1+=nopPhi.getGiaTri()-nopPhi.getSoTienDaDong();
                                tongsotiennop.setText(getDecimalFormat(tongsotiennop1));
                            }else {
                                tongsotiennop1-=nopPhi.getGiaTri()-nopPhi.getSoTienDaDong();
                                tongsotiennop.setText(getDecimalFormat(tongsotiennop1));
                            }
                        });
                        vbox.getChildren().addAll(button1,checkBox);
                        setGraphic(vbox);
                    }
                }
            };
        });
        thongtindonggop.setItems(nopPhis);
    }
    public void danhsachphuongtien(){
        ObservableList<PhuongTien> phuongTiens1=FXCollections.observableArrayList(phuongTiens);
        ten11.setCellValueFactory(new PropertyValueFactory<>("tenChuXe"));
        duno.setCellValueFactory(new PropertyValueFactory<>("DecimalFormatsotien"));
        loaixe.setCellValueFactory(new PropertyValueFactory<>("loaiPhuongTien"));
        sotiendanop11.setCellValueFactory(new PropertyValueFactory<>("decimalFormatSotiendanop"));
        chinhsua11.setCellFactory(cell->{
            return new TableCell<PhuongTien,Void>(){
                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                    } else {
                        PhuongTien nopPhi= getTableView().getItems().get(getIndex());
                        HBox vbox = new HBox(10); // 10 là khoảng cách giữa các thành phần
                        Button button1 = new Button();
                        FontAwesomeIconView iconView1 = new FontAwesomeIconView(FontAwesomeIcon.MONEY);
                        iconView1.setSize("16px");
                        button1.setGraphic(iconView1);
                        button1.setOnAction(event1 -> {
                            try {
                                Stage ag0r = (Stage) ((Node) event1.getSource()).getScene().getWindow();
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example/Chitietthuphiguixe.fxml"));
                                Parent root = loader.load();
                                Scene scene = new Scene(root);
                                Stage ag0r1=new Stage();
                                ag0r1.setScene(scene);
                                ag0r1.initModality(Modality.APPLICATION_MODAL);
                                ag0r1.initOwner(ag0r);
                                Thuphiguixe chitietthuphi = loader.getController();
                                chitietthuphi.setPhuongTien(nopPhi);
                                ag0r1.showAndWait();
                                timphong(event1);

                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        });
                        vbox.getChildren().addAll(button1);
                        setGraphic(vbox);
                    }
                }
            };
        });
        thongtinphuongtien.setItems(phuongTiens1);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
