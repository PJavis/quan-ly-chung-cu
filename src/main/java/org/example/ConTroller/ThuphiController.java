package org.example.ConTroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.EntityAll.HoKhau;
import org.example.EntityAll.KhoanPhi;
import org.example.EntityAll.LichSuGiaoDich;
import org.example.EntityAll.NopPhi;
import org.example.Hibernatedao.KhoanPhiDao;
import org.example.Hibernatedao.LichSuGiaoDichDao;
import org.example.Hibernatedao.NopPhiDao;
import org.example.getData;

import java.net.URL;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ThuphiController implements Initializable {
    private  KhoanPhi khoanPhi;


    @FXML
    private Label duno;


    @FXML
    private TextField sophong;

    @FXML
    private TextField sotang;

    @FXML
    private Label sotien;

    @FXML
    private Label sotiendanop;
    @FXML
    private TextField nguoinopphi;

    @FXML
    private TextField sotiennop;

    @FXML
    private ComboBox<String> tenphi;
    private NopPhi nopPhi;

    @FXML
    void nopphi(ActionEvent event) {
nopPhi.setSoTienDaDong(nopPhi.getSoTienDaDong()+Double.parseDouble(sotiennop.getText()));
NopPhiDao.getInstance().update(nopPhi);
khoanPhi.setTongsotien(khoanPhi.getTongsotien()+Double.parseDouble(sotiennop.getText()));
        getData.getInstance().updateKhoanphi(khoanPhi);
KhoanPhiDao.getInstance().update(khoanPhi);
        LichSuGiaoDich lichSuGiaoDich=new LichSuGiaoDich();
        lichSuGiaoDich.setSophong(nopPhi.getSoPhong());
        lichSuGiaoDich.setSotang(nopPhi.getSoTang());
        lichSuGiaoDich.setTenKhoanPhi(khoanPhi.getTenKhoanPhi());
        lichSuGiaoDich.setIdKhoanPhi(khoanPhi.getId());
        lichSuGiaoDich.setTennguoinop(nguoinopphi.getText());
        lichSuGiaoDich.setGiaTri(Double.parseDouble(sotiennop.getText()));
        LocalDate today = LocalDate.now();
        Date date=Date.valueOf(today);
        lichSuGiaoDich.setThoigiangiaodich(date);
        LichSuGiaoDichDao.getInstance().save(lichSuGiaoDich);
        sophong.clear();
        sotang.clear();
        sotiennop.clear();
        sotiendanop.setText("");
        Alert alert1=new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setHeaderText("Thành công");
        alert1.setContentText("Nộp phí thành công");
        alert1.showAndWait();
    }

    @FXML
    void timphong(ActionEvent event) {
        nopPhi= NopPhiDao.getInstance().selectByCondition(khoanPhi.getId(),Integer.parseInt(sophong.getText()),Integer.parseInt(sotang.getText()));


        DecimalFormat decimalFormat = new DecimalFormat("#,###.###");

        // Sử dụng phương thức format để định dạng số

        sotiendanop.setText(decimalFormat.format(nopPhi.getSoTienDaDong()));
        if(khoanPhi.getPhidichvuchungcu()==1)
        duno.setText(decimalFormat.format(khoanPhi.getGiaTri()*nopPhi.getDienTichPhong()-nopPhi.getSoTienDaDong())+"     ("+decimalFormat.format(khoanPhi.getGiaTri())+"đồng/m2)");
        else duno.setText(decimalFormat.format(khoanPhi.getGiaTri()));
    }
   private List<KhoanPhi> khoanPhis=getData.getInstance().getKhoanPhis();
    @FXML
    private Label chitiet;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       List<String> khoanphiString=FXCollections.observableArrayList();
       for(KhoanPhi khoanPhi1 : khoanPhis){
           khoanphiString.add(khoanPhi1.getTenKhoanPhi());
       }
        tenphi.setItems((ObservableList<String>) khoanphiString);
        tenphi.setOnAction(event -> {
            khoanPhi= KhoanPhiDao.getInstance().selectByName(tenphi.getValue());
            sotien.setText(khoanPhi.getDecimalFormatsotien());
            chitiet.setText(khoanPhi.getLoaiKhoanPhi());
        });
    }
}
