package org.example.ConTroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.getData;

public class Quanlyphong {

    @FXML
    private TableColumn<?, ?> sophong;

    @FXML
    private TableColumn<?, ?> sotang;

    @FXML
    private TableColumn<?, ?> sothutu;

    @FXML
    private TableColumn<?, ?> tenchuho;

    @FXML
    private TextField timkiem;

    @FXML
    void taomoihokhau(ActionEvent event) {
        try {
            Stage ag0r = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example/Taomoihokhau.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage ag0r1=new Stage();
            ag0r1.setScene(scene);
            ag0r1.initModality(Modality.APPLICATION_MODAL);
            ag0r1.initOwner(ag0r);
            ag0r1.showAndWait();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
