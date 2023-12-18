package org.example.ConTroller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.example.getData;
import java.net.URL;
import java.util.ResourceBundle;

public class HboxController implements Initializable {
    @FXML
    private Button chitiet;

    @FXML
    private Label dientich;

    @FXML
    private Label sonhankhau;

    @FXML
    private Label sophong;

    @FXML
    private Label tenchuho;

    @FXML
    private Label tienthue;
    //dang lam gio
//    public void setChitiet(){
//        this.sophong.setText(String.valueOf(getData.getInstance().get));
//        this.tenchuho
//        this.sonhankhau
//        this.tienthue
//        this.dientich
//    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
