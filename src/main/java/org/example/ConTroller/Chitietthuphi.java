package org.example.ConTroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.EntityAll.KhoanPhi;
import org.example.EntityAll.LichSuGiaoDich;
import org.example.EntityAll.NopPhi;
import org.example.Hibernatedao.KhoanPhiDao;
import org.example.Hibernatedao.LichSuGiaoDichDao;
import org.example.Hibernatedao.NopPhiDao;
import org.example.getData;

import java.sql.Date;
import java.time.LocalDate;

public class Chitietthuphi {

    @FXML
    private Label duno;

    @FXML
    private TextField sotiennop;

    @FXML
    private Label tenkhoanphi;
    private NopPhi nopPhi1;

    @FXML
    private TextField tennguoinopphi;
    public void setNopphi(NopPhi nopphi){
        this.nopPhi1=nopphi;
        tenkhoanphi.setText(nopphi.getTenKhoanPhi());
        duno.setText(nopphi.getDecimalFormatsotien());
    }
    @FXML
    void nopphi(ActionEvent event) {
        LichSuGiaoDich lichSuGiaoDich = new LichSuGiaoDich();
        lichSuGiaoDich.setNopPhi(nopPhi1);
        lichSuGiaoDich.setTennguoinop(tennguoinopphi.getText());
        lichSuGiaoDich.setGiaTri(Double.parseDouble(sotiennop.getText()));
        LocalDate today = LocalDate.now();
        Date date = Date.valueOf(today);
        lichSuGiaoDich.setThoigiangiaodich(date);
        LichSuGiaoDichDao.getInstance().save(lichSuGiaoDich);
        nopPhi1.setSoTienDaDong(nopPhi1.getSoTienDaDong()+Double.parseDouble(sotiennop.getText()));
        NopPhiDao.getInstance().update(nopPhi1);
        KhoanPhi khoanPhi=nopPhi1.getKhoanPhi();
        khoanPhi.setTongsotien(khoanPhi.getTongsotien()+Double.parseDouble(sotiennop.getText()));
        KhoanPhiDao.getInstance().update(khoanPhi);
        getData.getInstance().updateKhoanphi(khoanPhi);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Thành công");
        alert.setContentText("Nộp phí thành công");
        alert.showAndWait();
        Stage ag0r = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ag0r.close();
    }

}
