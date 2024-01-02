package org.example.ConTroller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.EntityAll.HoKhau;
import org.example.getData;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TrangchuController implements Initializable {

    @FXML
    private VBox cacitem;
    @FXML
    private Label sophongcontrong = new Label(String.valueOf(getData.getInstance().getHoKhaus().size()));

    @FXML
    private Label tongsonguoio;

    @FXML
    private Label tongsotienthuduoc;
    public void setTongsonguoio(){
        this.tongsonguoio.setText(String.valueOf(getData.getInstance().getNhanKhaus().size()));
    }
    public void setSophongcontrong(){
        this.sophongcontrong.setText(String.valueOf(1000-getData.getInstance().getHoKhaus().size()));
    }
    public void setTongsotienthuduoc(){
        this.tongsotienthuduoc.setText(String.valueOf(10000000*getData.getInstance().getHoKhaus().size()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTongsonguoio();
        setSophongcontrong();
        setTongsotienthuduoc();
        for (HoKhau hoKhau : getData.getInstance().getHoKhaus()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example/thongtinnha.fxml"));
                HBox hbox = loader.load();
                thongtinnhaController thongtinnhaController = loader.getController();
                thongtinnhaController.laydata(hoKhau);

                cacitem.getChildren().add(hbox);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
//    public  String getDecimalFormatsotien(){
//        int giatri = getData.getInstance().getHoKhaus().size()*10000000;
//        String pattern = "#,##0" + (String.valueOf(giatri)) % 1 == 0 ? "" : ".#########");
//        DecimalFormat decimalFormat = new DecimalFormat(pattern);
//        return decimalFormat.format(giatri);
//    }
}