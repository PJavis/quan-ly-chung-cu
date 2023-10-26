package org.example.ConTroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DieuchinhController {
    @FXML
    private Label chonkhoanphicandieuchinh;
    @FXML
    void taophi(ActionEvent event) {
        try {
            Stage a = (Stage) chonkhoanphicandieuchinh.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example/Taophi.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage ag0r1=new Stage();
            ag0r1.setScene(scene);
            ag0r1.initModality(Modality.APPLICATION_MODAL);
            ag0r1.initOwner(a);
            ag0r1.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
