package org.example.ConTroller;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
public class DashBoardController {
    @FXML
    private Pane bangthongke;
    @FXML
    private TitledPane dieuchinh;

    @FXML
    private TitledPane thongke;
    @FXML
    private TitledPane taomoi;


    @FXML
    void thuphi1(ActionEvent event) {
        try {
            Stage ag0r1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example/Thuphi.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            ag0r1.setScene(scene);
            ag0r1.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    void home(ActionEvent event) {
        try {
            Stage ag0r1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example/DashBoard.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            ag0r1.setScene(scene);

            ag0r1.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    private TitledPane tracuu;
    private Stage stage;
    private Scene scene;

    @FXML
    void dieuchinhcackhoanphi(ActionEvent event) {
        try {
            Stage ag0r1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example/Dieuchinh.fxml"));
            Parent root = loader.load();
            TabPane newTabPane = (TabPane) root.lookup("#tabpanedieuchinh");
            Tab desiredTab = newTabPane.getTabs().get(0);
            Scene scene = new Scene(root);
            ag0r1.setScene(scene);
            ag0r1.show();
            newTabPane.getSelectionModel().select(desiredTab);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    void dieuchinhdancu(ActionEvent event) {
        try {
            Stage ag0r1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example/Dieuchinh.fxml"));
            Parent root = loader.load();
            TabPane newTabPane = (TabPane) root.lookup("#tabpanedieuchinh");
            Tab desiredTab = newTabPane.getTabs().get(1);
            Scene scene = new Scene(root);
            ag0r1.setScene(scene);
            ag0r1.show();
            newTabPane.getSelectionModel().select(desiredTab);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void dieuchinhphong(ActionEvent event) {
        try {
            Stage ag0r1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example/Dieuchinh.fxml"));
            Parent root = loader.load();
            TabPane newTabPane = (TabPane) root.lookup("#tabpanedieuchinh");
            Tab desiredTab = newTabPane.getTabs().get(2);
            Scene scene = new Scene(root);
            ag0r1.setScene(scene);
            ag0r1.show();
            newTabPane.getSelectionModel().select(desiredTab);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void thongkecackhoanphi(ActionEvent event) {
        try {
            Stage ag0r1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/org.example/Thongke.fxml"));
            TabPane newTabPane = (TabPane) root.lookup("#tabpanethongke");
            Tab desiredTab = newTabPane.getTabs().get(0);
            Scene scene = new Scene(root);
            ag0r1.setScene(scene);
            ag0r1.show();
            newTabPane.getSelectionModel().select(desiredTab);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void thongkephong(ActionEvent event) {
        try {
            Stage ag0r1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/org.example/Thongke.fxml"));
            TabPane newTabPane = (TabPane) root.lookup("#tabpanethongke");
            Tab desiredTab = newTabPane.getTabs().get(2);
            Scene scene = new Scene(root);
            ag0r1.setScene(scene);
            ag0r1.show();
            newTabPane.getSelectionModel().select(desiredTab);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void thongkethaydoi(ActionEvent event) {
        try {
            Stage ag0r1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/org.example/Thongke.fxml"));
            TabPane newTabPane = (TabPane) root.lookup("#tabpanethongke");
            Tab desiredTab = newTabPane.getTabs().get(1);
            Scene scene = new Scene(root);
            ag0r1.setScene(scene);
            ag0r1.show();
            newTabPane.getSelectionModel().select(desiredTab);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean istracuu = false;
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
    @FXML
    void tracuu1(MouseEvent event) {

        int x=0;
        if(!istracuu){
            istracuu=true;
            openTransition(thongke,x);
            if(isthongke)x+=113;
            openTransition(dieuchinh,x);
            if(isdieuchinh)x+=113;
            openTransition(taomoi,x);
        }else {
            istracuu=false;
            closeTransition(thongke,x);
            if(isthongke)x+=113;
            closeTransition(dieuchinh,x);
            if(isdieuchinh)x+=113;
            closeTransition(taomoi,x);
        }

    }
    private boolean isthongke = false;
    @FXML
    void thongke1(MouseEvent event) {
        int x=0;
        if(istracuu)x=113;
        if(!isthongke){
            isthongke=true;
            openTransition(dieuchinh,x);
            if(isdieuchinh)x+=113;
            openTransition(taomoi,x);
        }else {
            isthongke=false;
            closeTransition(dieuchinh,x);
            if(isdieuchinh)x+=113;
            closeTransition(taomoi,x);
        }
       
    }
    private boolean isdieuchinh = false;
    @FXML
    void dieuchinh1(MouseEvent event) {
int x=0;
if(istracuu&&!isthongke||!istracuu&&isthongke)x+=113;
else if(istracuu&&isthongke)x+=226;
if(!isdieuchinh){
    isdieuchinh=true;
    openTransition(taomoi,x);
}else{
    isdieuchinh=false;
    closeTransition(taomoi,x);
}
    }

    @FXML
    private Pane keodanbang;

    private boolean isDashboardVisible = false;
    @FXML
    void hiendashbroad(ActionEvent event) {

        ScaleTransition scaleMainPane = new ScaleTransition(Duration.millis(300),keodanbang);
        TranslateTransition slideInTable = new TranslateTransition(Duration.millis(300), bangthongke);
        TranslateTransition slideInTable1 = new TranslateTransition(Duration.millis(300), keodanbang);
        if (isDashboardVisible) {
            // Hiện bảng
            isDashboardVisible = false;
            slideInTable.setToX(0);
            scaleMainPane.setToX(1);
            slideInTable1.setToX(0);

        } else {
            // Ẩn  bảng
            isDashboardVisible = true;
            scaleMainPane.setToX(1.3);
            slideInTable1.setToX(-97);
            slideInTable.setToX(-213);

        }
        slideInTable1.play();
        slideInTable.play();
        scaleMainPane.play();
    }

    @FXML
    public void dangxuat (ActionEvent event)  {
        try {
            Stage ag0r1 =(Stage) tracuu.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/org.example/Loginscreen.fxml"));
            Scene scene = new Scene(root);
            ag0r1.setScene(scene);
            ag0r1.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    void taokhoanphimoi(ActionEvent event) {
        try {
            Stage ag0r1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/org.example/Taomoi.fxml"));
            TabPane newTabPane = (TabPane) root.lookup("#tabpanetaomoi");
            Tab desiredTab = newTabPane.getTabs().get(0);
            Scene scene = new Scene(root);
            ag0r1.setScene(scene);
            ag0r1.show();
            newTabPane.getSelectionModel().select(desiredTab);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void themnhankhaumoi(ActionEvent event) {
        try {
            Stage ag0r1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/org.example/Taomoi.fxml"));
            TabPane newTabPane = (TabPane) root.lookup("#tabpanetaomoi");
            Tab desiredTab = newTabPane.getTabs().get(1);
            Scene scene = new Scene(root);
            ag0r1.setScene(scene);
            ag0r1.show();
            newTabPane.getSelectionModel().select(desiredTab);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void themphongmoi(ActionEvent event) {
        try {
            Stage ag0r1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/org.example/Taomoi.fxml"));
            TabPane newTabPane = (TabPane) root.lookup("#tabpanetaomoi");
            Tab desiredTab = newTabPane.getTabs().get(2);
            Scene scene = new Scene(root);
            ag0r1.setScene(scene);
            ag0r1.show();
            newTabPane.getSelectionModel().select(desiredTab);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
