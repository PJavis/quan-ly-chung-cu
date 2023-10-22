package org.example.ConTroller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
public class DashBoardConTroller implements Initializable {
    @FXML
    private Pane bangthongke;

    @FXML
    private AnchorPane bangchinh;
//   Stage a = (Stage) desciption1.getScene().getWindow();
    private boolean isDashboardVisible = false;
    @FXML
    void hiendashbroad(ActionEvent event) {
        if (isDashboardVisible) {
            // Hiện bảng
            TranslateTransition closeTransition = new TranslateTransition(Duration.seconds(0.5), bangthongke);
            closeTransition.setToX(0);
            closeTransition.play();
            isDashboardVisible = false;
        } else {
            // Ẩn  bảng
            TranslateTransition openTransition = new TranslateTransition(Duration.seconds(0.5), bangthongke);
            openTransition.setToX(-213);
            openTransition.play();
            isDashboardVisible = true;
        }
    }

    @FXML
    void login(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}
