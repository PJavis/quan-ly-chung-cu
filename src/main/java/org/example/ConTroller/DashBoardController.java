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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example/Trangchu.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            ag0r1.setScene(scene);

            ag0r1.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

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

    @FXML
    public void dangxuat (ActionEvent event)  {
        try {
            Stage ag0r1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
