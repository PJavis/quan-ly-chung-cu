package org.example.ConTroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.Model.EntityAll.NopPhi;
import org.example.Model.Hibernatedao.NopPhiDao;

import java.text.DecimalFormat;

public class Dieuchinhnopphi {

    @FXML
    private Label giatri;

    @FXML
    private TextField sodiennuoc;

    @FXML
    private Label tenkhoanphi;
    private NopPhi nopPhi;
    public  String getDecimalFormatsotien(double giaTri){
        String pattern = "#,##0" + (giaTri% 1 == 0 ? "" : ".#########");
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(giaTri);
    }
    public void setdieuchinh(NopPhi nopPhi1){

        this.nopPhi=nopPhi1;
        tenkhoanphi.setText(nopPhi1.getTenKhoanPhi());
        sodiennuoc.setText(String.valueOf(nopPhi1.getSodiennuoc()));
        giatri.setText(String.valueOf(nopPhi1.getGiaTri()));
        sodiennuoc.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                newValue.replace(",", "");
                Double numericValue = Double.parseDouble(newValue);
                // Nếu có thể chuyển đổi thành số, cập nhật giá trị của Label
                giatri.setText(getDecimalFormatsotien(numericValue*nopPhi1.getKhoanPhi().getGiaTri()));
            } catch (NumberFormatException e) {
                // Nếu không thể chuyển đổi thành số, không làm gì hoặc có thể xử lý theo ý bạn
            }
        });
    }
    @FXML
    void capnhat(ActionEvent event) {
        nopPhi.setSodiennuoc(Double.parseDouble(sodiennuoc.getText()));
        nopPhi.setGiaTri(Double.parseDouble(giatri.getText().replace(",", "")));
        NopPhiDao.getInstance().update(nopPhi);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Thành công");
        alert.setContentText("Cập nhật khoản phí thành công");
        alert.showAndWait();
        Stage ag0r = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ag0r.close();
    }

}
