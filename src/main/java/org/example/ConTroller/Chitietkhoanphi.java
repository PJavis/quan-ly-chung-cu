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
import javafx.stage.Stage;
import org.example.EntityAll.KhoanPhi;
import org.example.EntityAll.NopPhi;
import org.example.Hibernatedao.NopPhiDao;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class Chitietkhoanphi implements Initializable {
    private KhoanPhi khoanPhi;
    private List<NopPhi> nopPhiList;

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
        danhsachhokhau();
    }

    @FXML
    private TableColumn<NopPhi, Void> lichsu;

    @FXML
    private TableView<NopPhi> danhsachhokhau;

    @FXML
    private Label hannop;

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
    private Label sotien;

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
    private ObservableList<NopPhi> nopPhis;
    public void danhsachhokhau() {
        nopPhis = FXCollections.observableArrayList(nopPhiList);

        sothutu.setCellValueFactory(cellData -> {
            int rowIndex = cellData.getTableView().getItems().indexOf(cellData.getValue()) + 1;
            return javafx.beans.binding.Bindings.createObjectBinding(() -> rowIndex);
        });
        sophong.setCellValueFactory(new PropertyValueFactory<>("soPhong"));
        sotang.setCellValueFactory(new PropertyValueFactory<>("soTang"));
        tenchuho.setCellValueFactory(new PropertyValueFactory<>("tenchuho"));
        sotiendanoptable.setCellValueFactory(new PropertyValueFactory<>("soTienDaDong"));
        sotienchuanop.setCellValueFactory(new PropertyValueFactory<>("Sotienchuanop"));
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

                                });
                            }
                        }
                    };
                });
        danhsachhokhau.setItems(nopPhis);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
