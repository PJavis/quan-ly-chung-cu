package org.example.ConTroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class TaophiController {
    @FXML
    private Label sotien;
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
