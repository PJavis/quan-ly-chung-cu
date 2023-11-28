package org.example.ConTroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import org.example.EntityAll.KhoanPhi;

import java.net.URL;
import java.util.ResourceBundle;

public class Quanlykhoanphi implements Initializable {

    @FXML
    private TableColumn<KhoanPhi, Void> chitiet;

    @FXML
    private TextField hannop;

    @FXML
    private ComboBox<?> loaikhoanphi;
    @FXML
    private TableColumn<KhoanPhi, String> hannoptable;
    @FXML
    private TableColumn<KhoanPhi, String> loaikhoanphitable;

    @FXML
    private TableColumn<KhoanPhi, Integer> sothutu;

    @FXML
    private TableColumn<KhoanPhi, Double> sotiendanop;

    @FXML
    private TextField tenkhoanphi;
    @FXML
    private TableColumn<KhoanPhi, String> tenkhoanphitable;

    @FXML
    void dieuchinh(ActionEvent event) {

    }

    @FXML
    void taomoi(ActionEvent event) {

    }

    @FXML
    void xoa(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}
