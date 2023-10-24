package org.example.ConTroller;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
public class ThuphiConTroller implements Initializable {
    @FXML
    private Pane keodanbang;
    @FXML
    private Pane bangthongke;
    @FXML
    private TitledPane dieuchinh;

    @FXML
    private TitledPane thongke;

    @FXML
    void home(ActionEvent event) {
        try {
            Stage ag0r1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/org.example/DashBoard.fxml"));
            Scene scene = new Scene(root);
            ag0r1.setScene(scene);
            ag0r1.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private TitledPane tracuu;
    private <T> void openTransition(T t,int x){
        TranslateTransition openTransition = new TranslateTransition(Duration.seconds(0.3), (Node) t);
        openTransition.setToY(113+x);
        openTransition.play();
    }
    private <T> void closeTransition(T t,int x){
        TranslateTransition closeTransition = new TranslateTransition(Duration.seconds(0.1), (Node) t);
        closeTransition.setToY(x);
        closeTransition.play();
    }


    private boolean istracuu = false;
    @FXML
    void tracuu1(MouseEvent event) {

        int x=0;

        if(!istracuu){
            openTransition(thongke,x);
            if(isthongke)x+=113;
            openTransition(dieuchinh,x);
            istracuu=true;
        }else {
            closeTransition(thongke,x);
            if(isthongke)x+=113;
            closeTransition(dieuchinh,x);
            istracuu=false;
        }

    }
    private boolean isthongke = false;
    @FXML
    void thongke1(MouseEvent event) {

        int x=0;
        if(istracuu)x=113;
        if(!isthongke){
            openTransition(dieuchinh,x);
            isthongke=true;
        }else {
            closeTransition(dieuchinh,x);
            isthongke=false;
        }

    }
    @FXML
    private AnchorPane bangchinh;
    //   Stage a = (Stage) desciption1.getScene().getWindow();
    private boolean isDashboardVisible = false;
    @FXML
    void hiendashbroad(ActionEvent event) {

        ScaleTransition scaleMainPane = new ScaleTransition(Duration.millis(500),keodanbang);
        TranslateTransition slideInTable = new TranslateTransition(Duration.millis(500), bangthongke);
        TranslateTransition slideInTable1 = new TranslateTransition(Duration.millis(500), keodanbang);
        if (isDashboardVisible) {
            // Hiện bảng
            slideInTable.setToX(0);
            scaleMainPane.setToX(1);
            slideInTable1.setToX(0);
            isDashboardVisible = false;
        } else {
            // Ẩn  bảng
            scaleMainPane.setToX(1.3);
            slideInTable1.setToX(-97);
            slideInTable.setToX(-213);
            isDashboardVisible = true;
        }
        slideInTable1.play();
        slideInTable.play();
        scaleMainPane.play();
    }


    @FXML
    void login(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}
