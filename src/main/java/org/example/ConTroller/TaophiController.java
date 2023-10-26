package org.example.ConTroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TaophiController {

    @FXML
    private TextField loaikhoanphi;

    @FXML
    private TextField sotien;

    @FXML
    private TextField tenkhoanphi;
    @FXML
    private DatePicker hannop;
    @FXML
    void huy(ActionEvent event) {
        Stage a = (Stage) sotien.getScene().getWindow();
        a.close();
    }

    @FXML
    void taomoi(ActionEvent event) {
        Stage a = (Stage) sotien.getScene().getWindow();
        a.close();
    }
}
