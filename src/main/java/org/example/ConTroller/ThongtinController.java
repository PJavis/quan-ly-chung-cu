package org.example.ConTroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.example.EntityAll.QuanTriChungCu;
import org.example.EntityAll.TaiKhoanBQT;
import org.example.Hibernatedao.TaiKhoanBQTDao;

public class ThongtinController {

    @FXML
    private ImageView img;

    @FXML
    private Label account;

    @FXML
    private Label diachi;

    @FXML
    private Label id;

    @FXML
    private Label idnqt;

    @FXML
    private Label mail;

    @FXML
    private Label mota;

    @FXML
    private Label pass;

    @FXML
    private Label sdt;

    @FXML
    private Label tennqt;

    private TaiKhoanBQT taiKhoanBQT;


    public void initialize() {
        String taiKhoan = luuthongtin.getTaiKhoan();
        String matKhau = luuthongtin.getMatKhau();
        TaiKhoanBQTDao taiKhoanBQTDao = TaiKhoanBQTDao.getInstance();
        taiKhoanBQT = taiKhoanBQTDao.laythongtin(taiKhoan, matKhau);

        if (taiKhoanBQT != null) {
            account.setText( taiKhoanBQT.getTaiKhoan());
            pass.setText(  taiKhoanBQT.getMatKhau());
            id.setText(""+taiKhoanBQT.getId());
            idnqt.setText(""+taiKhoanBQT.getIdNguoiQuanTri());
            QuanTriChungCu quanTriChungCu = taiKhoanBQTDao.layThongTinQuanTriChungCu(taiKhoanBQT.getId());
            // lay anh tuy theo so thich nhe:)))
//            String image = quanTriChungCu.laylinhanh();
//            Image image1 = new Image("file:" + image);
//            img.setImage(image1);
            tennqt.setText(quanTriChungCu.getTenNguoiQuanTri());
            diachi.setText(quanTriChungCu.getDiaChi());
            mail.setText(quanTriChungCu.getEmail());
            mota.setText(quanTriChungCu.getMoTa());
            sdt.setText(quanTriChungCu.getSoDienThoai());

        }
    }
    private void initializeData() {
        if (taiKhoanBQT != null) {
            System.out.println("TaiKhoanBQT: " + taiKhoanBQT);
            account.setText("Tài khoản: " + taiKhoanBQT.getTaiKhoan());
            pass.setText("Mật khẩu: " + taiKhoanBQT.getMatKhau());

        }
    }

}
