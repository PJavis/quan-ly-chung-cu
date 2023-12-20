package org.example.ConTroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.EntityAll.HoKhau;
import org.example.EntityAll.KhoanPhi;
import org.example.EntityAll.NopPhi;
import org.example.Hibernatedao.KhoanPhiDao;
import org.example.Hibernatedao.NopPhiDao;

public class ThuphiController {
    private  KhoanPhi khoanPhi;


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
sotien.setText(khoanPhi.getDecimalFormatsotien());

    }

    @FXML
    void timphong(ActionEvent event) {
        NopPhi nopPhi= NopPhiDao.getInstance().selectByCondition(khoanPhi.getId(),Integer.parseInt(sophong.getText()),Integer.parseInt(sotang.getText()));
        sotiendanop.setText(String.valueOf(nopPhi.getSoTienDaDong()));

    }

}
