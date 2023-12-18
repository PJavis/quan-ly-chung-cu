package org.example.ConTroller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.example.getData;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
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
    }
//    public  String getDecimalFormatsotien(){
//        int giatri = getData.getInstance().getHoKhaus().size()*10000000;
//        String pattern = "#,##0" + (String.valueOf(giatri)) % 1 == 0 ? "" : ".#########");
//        DecimalFormat decimalFormat = new DecimalFormat(pattern);
//        return decimalFormat.format(giatri);
//    }
}