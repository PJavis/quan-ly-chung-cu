package org.example.ConTroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.example.EntityAll.QuanTriChungCu;
import org.example.EntityAll.TaiKhoanBQT;
import org.example.Hibernatedao.QuanTriChungCuDao;
import org.example.Hibernatedao.TaiKhoanBQTDao;
import org.example.getData;

public class ThongtinController {

    @FXML
    private ImageView img;

    @FXML
    private TextField account;

    @FXML
    private TextField diachi;

    @FXML
    private TextField id;

    @FXML
    private TextField idnqt;

    @FXML
    private TextField mail;

    @FXML
    private TextField mota;

    @FXML
    private TextField pass;

    @FXML
    private TextField sdt;

    @FXML
    private TextField tennqt;

    private TaiKhoanBQT taiKhoanBQT = new TaiKhoanBQT();
    private QuanTriChungCu quanTriChungCu = new QuanTriChungCu();

    @FXML
    private Button savedata;

    public void initialize() {
//        taiKhoanBQT = new TaiKhoanBQT();
//        quanTriChungCu = new QuanTriChungCu();
        initializeData(taiKhoanBQT,quanTriChungCu);
    }
    private void initializeData(TaiKhoanBQT taiKhoanBQT, QuanTriChungCu quanTriChungCu) {
        String taiKhoan = luuthongtin.getTaiKhoan();
        String matKhau = luuthongtin.getMatKhau();
        TaiKhoanBQTDao taiKhoanBQTDao = TaiKhoanBQTDao.getInstance();
        taiKhoanBQT = taiKhoanBQTDao.laythongtin(taiKhoan, matKhau);

        if (taiKhoanBQT != null) {
            account.setText(taiKhoanBQT.getTaiKhoan());
            pass.setText(taiKhoanBQT.getMatKhau());
            id.setText("" + taiKhoanBQT.getId());
            idnqt.setText("" + taiKhoanBQT.getIdNguoiQuanTri());
            quanTriChungCu = taiKhoanBQTDao.layThongTinQuanTriChungCu(taiKhoanBQT.getId());

            if (quanTriChungCu != null) {
                tennqt.setText(quanTriChungCu.getTenNguoiQuanTri());
                diachi.setText(quanTriChungCu.getDiaChi());
                mail.setText(quanTriChungCu.getEmail());
                mota.setText(quanTriChungCu.getMoTa());
                sdt.setText(quanTriChungCu.getSoDienThoai());
            }
        }
    }

    public void updatedata(ActionEvent actionEvent) {
        if (account.getText().isEmpty() || idnqt.getText().isEmpty() || id.getText().isEmpty() || tennqt.getText().isEmpty()
                || diachi.getText().isEmpty() || mail.getText().isEmpty() || mota.getText().isEmpty() || sdt.getText().isEmpty()
                || pass.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Lỗi");
            alert.setContentText("Vui lòng điền đủ các thông tin");
            alert.showAndWait();
        } else {
            taiKhoanBQT.setTaiKhoan(account.getText());
            taiKhoanBQT.setMatKhau(pass.getText());
            taiKhoanBQT.setIdNguoiQuanTri(Integer.parseInt(idnqt.getText()));
            taiKhoanBQT.setid(Integer.parseInt(id.getText()));
            quanTriChungCu.setDiaChi(diachi.getText());
            quanTriChungCu.setEmail(mail.getText());
            quanTriChungCu.setMoTa(mota.getText());
            quanTriChungCu.setTenNguoiQuanTri(tennqt.getText());
            quanTriChungCu.setSoDienThoai(sdt.getText());
            quanTriChungCu.setId(Integer.parseInt(idnqt.getText()));
            QuanTriChungCuDao.getInstance().update(quanTriChungCu);
            getData.getInstance().setQuanTriChungCu(quanTriChungCu.getId(),quanTriChungCu);
            TaiKhoanBQTDao.getInstance().update(taiKhoanBQT);
            getData.getInstance().setTaiKhoanBQT(taiKhoanBQT.getId(),taiKhoanBQT);
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setHeaderText("Thành công");
            alert1.setContentText("Điều chỉnh thông tin thành công");
            alert1.showAndWait();
        }
    }

}

