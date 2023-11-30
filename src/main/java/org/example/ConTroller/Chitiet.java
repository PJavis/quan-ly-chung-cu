package org.example.ConTroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.EntityAll.KhoanPhi;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class Chitiet {
    private KhoanPhi khoanPhi;

    public void setKhoanPhi(KhoanPhi khoanPhi) {
        this.khoanPhi = khoanPhi;
        tenkhoanphi.setText(khoanPhi.getTenKhoanPhi());
        loaikhoanphi.setText(khoanPhi.getLoaiKhoanPhi());
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        sotien.setText(decimalFormat.format(khoanPhi.getGiaTri()));
        sotiendanop.setText(decimalFormat.format(khoanPhi.getTongsotien()));
        SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ngaytao.setText(newDateFormat.format(khoanPhi.getBatDau()));
        hannop.setText(newDateFormat.format(khoanPhi.getKetThuc()));

    }

    @FXML
    private TableColumn<?, ?> chitiet;

    @FXML
    private TableView<?> danhsachhokhau;

    @FXML
    private Label hannop;

    @FXML
    private Label loaikhoanphi;

    @FXML
    private Label ngaytao;

    @FXML
    private TableColumn<?, ?> sophong;

    @FXML
    private TableColumn<?, ?> sotang;

    @FXML
    private TableColumn<?, ?> sothutu;

    @FXML
    private Label sotien;

    @FXML
    private TableColumn<?, ?> sotienchuanop;

    @FXML
    private Label sotiendanop;

    @FXML
    private TableColumn<?, ?> sotiendanoptable;

    @FXML
    private TableColumn<?, ?> tenchuho;

    @FXML
    private Label tenkhoanphi;

    @FXML
    private TextField timkiem;

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

}
