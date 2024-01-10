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
import org.example.EntityAll.PhuongTien;
import org.example.Hibernatedao.KhoanPhiDao;
import org.example.Hibernatedao.LichSuGiaoDichDao;
import org.example.Hibernatedao.NopPhiDao;
import org.example.Hibernatedao.PhuongTienDao;
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
        duno.setText(String.valueOf((phuongTien.getSoTienDaNop()+phuongTien.getPhiGuiXe())));
    }
    @FXML
    void nopphi(ActionEvent event) {
        // Tìm kiếm hoặc tạo mới đối tượng KhoanPhi
        KhoanPhi khoanPhi = KhoanPhiDao.getInstance().selectByName("Tiền gửi xe" + phuongTien.getLoaiPhuongTien());
        if (khoanPhi == null) {
            khoanPhi = new KhoanPhi("Tiền gửi xe" + phuongTien.getLoaiPhuongTien(), "Bắt buộc", Date.valueOf(LocalDate.now().withDayOfMonth(1)), Date.valueOf(LocalDate.now().plusMonths(1).withDayOfMonth(1)),phuongTien.getPhiGuiXe(),0,null);
            KhoanPhiDao.getInstance().save(khoanPhi);
        }

        // Cập nhật ngày bắt đầu và kết thúc nếu cần thiết
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfMonth = LocalDate.of(today.getYear(), today.getMonth(), 1);
        if (khoanPhi.getKetThuc().toLocalDate().isBefore(today)) {
            khoanPhi.setBatDau(Date.valueOf(firstDayOfMonth));
            khoanPhi.setKetThuc(Date.valueOf(firstDayOfMonth.plusMonths(1)));
            getData.getInstance().updatePhiGuiXe();
        }

        // Tạo và lưu đối tượng NopPhi
        NopPhi nopPhi1 = new NopPhi(khoanPhi, khoanPhi.getGiaTri(), phuongTien.getHoKhau(), false, 0, null);
        NopPhiDao.getInstance().save(nopPhi1);

        // Tạo và lưu đối tượng LichSuGiaoDich
        LichSuGiaoDich lichSuGiaoDich = new LichSuGiaoDich();
        lichSuGiaoDich.setNopPhi(nopPhi1);
        lichSuGiaoDich.setTennguoinop(tennguoinopphi.getText());
        lichSuGiaoDich.setGiaTri(Double.parseDouble(sotiennop.getText()));
        lichSuGiaoDich.setThoigiangiaodich(Date.valueOf(today));
        LichSuGiaoDichDao.getInstance().save(lichSuGiaoDich);

        // Cập nhật số tiền đã đóng của NopPhi và lưu lại
        nopPhi1.setSoTienDaDong(nopPhi1.getSoTienDaDong() + Double.parseDouble(sotiennop.getText()));
        NopPhiDao.getInstance().update(nopPhi1);

        // Cập nhật tổng số tiền của KhoanPhi và lưu lại
        khoanPhi.setTongsotien(khoanPhi.getTongsotien() + Double.parseDouble(sotiennop.getText()));
        KhoanPhiDao.getInstance().update(khoanPhi);
        getData.getInstance().updateKhoanphi(khoanPhi);

        // Hiển thị thông báo thành công
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Thành công");
        alert.setContentText("Nộp phí thành công");
        phuongTien.setSoTienDaNop(nopPhi1.getSoTienDaDong());
        PhuongTienDao.getInstance().update(phuongTien);
        alert.showAndWait();

        // Đóng cửa sổ hiện tại
        Stage ag0r = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ag0r.close();
    }


}
