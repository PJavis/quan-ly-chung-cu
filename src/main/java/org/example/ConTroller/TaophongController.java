package org.example.ConTroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.EntityAll.HoKhau;
import org.example.Hibernatedao.HoKhauDao;

public class TaophongController {

    @FXML
    private TextField dientichphong;

    @FXML
    private TextField sophong;

    @FXML
    void huy(ActionEvent event) {
        Stage a = (Stage) sophong.getScene().getWindow();
        a.close();
    }

    @FXML
    void taomoi(ActionEvent event) {
        HoKhau hoKhau=new HoKhau();
        hoKhau.setId(Integer.parseInt(sophong.getText()));
        hoKhau.setDienTichPhong(Double.parseDouble(dientichphong.getText()));
        HoKhauDao.getInstance().save(hoKhau);
        Stage a = (Stage) sophong.getScene().getWindow();
        a.close();
    }

}
