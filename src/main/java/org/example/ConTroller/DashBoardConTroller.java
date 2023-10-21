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
import javafx.util.Duration;
public class DashBoardConTroller implements Initializable {
    @FXML
    private Pane tinhnang;

    private boolean isDashboardVisible = false;
    @FXML
    void hiendashbroad(ActionEvent event) {
        if (isDashboardVisible) {
            // Ẩn bảng
            TranslateTransition closeTransition = new TranslateTransition(Duration.seconds(0.5), tinhnang);
            closeTransition.setToX(0);
            closeTransition.play();
            isDashboardVisible = false;
        } else {
            // Hiển thị bảng
            TranslateTransition openTransition = new TranslateTransition(Duration.seconds(0.5), tinhnang);
            openTransition.setToX(240);
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
