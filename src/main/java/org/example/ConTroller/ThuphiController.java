package org.example.ConTroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.EntityAll.HoKhau;
import org.example.EntityAll.KhoanPhi;
import org.example.Hibernatedao.KhoanPhiDao;

public class ThuphiController {
    private  KhoanPhi khoanPhi;
    private HoKhau hoKhau;

    @FXML
    private Label duno;

    @FXML
    private Label loaikhoanphi;

    @FXML
    private TextField sophong;

    @FXML
    private TextField sotang;

    @FXML
    private Label sotien;

    @FXML
    private Label sotiendanop;

    @FXML
    private TextField sotiennop;

    @FXML
    private TextField tenphi;

    @FXML
    void nopphi(ActionEvent event) {

    }

    @FXML
    void timphi(ActionEvent event) {
khoanPhi= KhoanPhiDao.getInstance().selectByName(tenphi.getText());
sotiennop.setText(khoanPhi.getDecimalFormatsotien());

    }

    @FXML
    void timphong(ActionEvent event) {

    }

}
