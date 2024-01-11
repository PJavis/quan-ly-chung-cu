package org.example.ConTroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.EntityAll.*;
import org.example.Hibernatedao.*;
import org.example.getData;

import java.sql.Date;
import java.time.LocalDate;

public class Thuphiguixe {

    @FXML
    private Label duno;

    @FXML
    private TextField sotiennop;

    private PhuongTien phuongTien;

    @FXML
    private TextField tennguoinopphi;
    public void setPhuongTien(PhuongTien phuongTien){
        this.phuongTien = phuongTien;
        duno.setText(phuongTien.getDecimalFormatsotien());
    }
    @FXML
    void nopphi(ActionEvent event) {
        // Tìm kiếm hoặc tạo mới đối tượng KhoanPhi


        // Cập nhật ngày bắt đầu và kết thúc nếu cần thiết
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfMonth = LocalDate.of(today.getYear(), today.getMonth(), 1);


        // Tạo và lưu đối tượng LichSuGiaoDich
        LichSuGiaoDichPhiGuiXe lichSuGiaoDich = new LichSuGiaoDichPhiGuiXe();
        lichSuGiaoDich.setPhuongTien(phuongTien);
        lichSuGiaoDich.setTennguoinop(tennguoinopphi.getText());
        lichSuGiaoDich.setGiaTri(Double.parseDouble(sotiennop.getText()));
        lichSuGiaoDich.setThoigiangiaodich(Date.valueOf(today));
        LichSuGiaoDichPhiGuiXeDao.getInstance().save(lichSuGiaoDich);

        // Cập nhật số tiền đã đóng của NopPhi và lưu lại


        // Cập nhật tổng số tiền của KhoanPhi và lưu lại

        // Hiển thị thông báo thành công
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Thành công");
        alert.setContentText("Nộp phí thành công");
        phuongTien.setSoTienDaNop(Double.parseDouble(sotiennop.getText()));
        PhuongTienDao.getInstance().update(phuongTien);
        alert.showAndWait();

        // Đóng cửa sổ hiện tại
        Stage ag0r = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ag0r.close();
    }


}
