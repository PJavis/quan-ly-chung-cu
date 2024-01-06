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
import java.util.Objects;
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
        if(tenphi.getValue()==null||sophong.getText().isEmpty()||sotang.getText().isEmpty()){
            Alert alert1=new Alert(Alert.AlertType.ERROR);
            alert1.setHeaderText("Thất bại");
            alert1.setContentText("Không tìm thấy khoản phí hoặc hộ khẩu");
            alert1.showAndWait();
            return;
        }
        try{
        double d = Double.parseDouble(sotiennop.getText());}
        catch (NumberFormatException e){
            Alert alert1=new Alert(Alert.AlertType.ERROR);
            alert1.setHeaderText("Thất bại");
            alert1.setContentText("Hãy nhập số tiền bạn muốn nộp vào số tiền nộp");
            alert1.showAndWait();
            return;
        }

        if(Double.parseDouble(sotiennop.getText())>duNo){
            Alert alert1=new Alert(Alert.AlertType.ERROR);
            alert1.setHeaderText("Thất bại");
            alert1.setContentText("Bạn không thể nộp số tiền vượt quá số tiền phải đóng");
            alert1.showAndWait();
            sotiennop.setText("");
            return;
        }
        if(nguoinopphi.getText().isEmpty()){
            Alert alert1=new Alert(Alert.AlertType.ERROR);
            alert1.setHeaderText("Thất bại");
            alert1.setContentText("Hãy nhập tên người nôp;");
            alert1.showAndWait();
            return;
        }
        nopPhi.setSoTienDaDong(nopPhi.getSoTienDaDong()+Double.parseDouble(sotiennop.getText()));
        NopPhiDao.getInstance().update(nopPhi);
        khoanPhi.setTongsotien(khoanPhi.getTongsotien()+Double.parseDouble(sotiennop.getText()));
        getData.getInstance().updateKhoanphi(khoanPhi);
        KhoanPhiDao.getInstance().update(khoanPhi);
        LichSuGiaoDich lichSuGiaoDich=new LichSuGiaoDich();
        lichSuGiaoDich.setHoKhau(nopPhi.getHoKhau());
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
        duno.setText("");
        sotiendanop.setText("");
        nguoinopphi.clear();
        Alert alert1=new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setHeaderText("Thành công");
        alert1.setContentText("Nộp phí thành công");
        alert1.showAndWait();


    }
    private double duNo=0;

    @FXML
    void timphong(ActionEvent event) {
        nopPhi= NopPhiDao.getInstance().selectByCondition(khoanPhi.getId(),Integer.parseInt(sophong.getText()),Integer.parseInt(sotang.getText()));
        DecimalFormat decimalFormat = new DecimalFormat("#,###.###");
        try {
            sotiendanop.setText(decimalFormat.format(nopPhi.getSoTienDaDong()));
            if(khoanPhi.getPhidichvuchungcu()==1) {
                duNo = nopPhi.getGiaTri() - nopPhi.getSoTienDaDong();
                duno.setText(decimalFormat.format(duNo)+ "     (" + decimalFormat.format(khoanPhi.getGiaTri()) + "đồng/m2)");
            }
            else {
//                duno1 = khoanPhi.getGiaTri() - nopPhi.getSoTienDaDong();
//                double updatedDuNo = duno1 - Double.parseDouble(sotiendanop.getText());
//                duno.setText(decimalFormat.format(updatedDuNo));
                duNo = nopPhi.getGiaTri() - nopPhi.getSoTienDaDong();
                duno.setText(decimalFormat.format(duNo)+"đồng");
            }
        }catch (Exception e){
            sotiennop.clear();
            sotiendanop.setText("");
            nguoinopphi.clear();
            duno.setText("");
            Alert alert1=new Alert(Alert.AlertType.ERROR);
            alert1.setHeaderText("Lỗi");
            alert1.setContentText("Không tìm thấy hộ khẩu");
            alert1.showAndWait();
        }


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
            sophong.clear();
            sotang.clear();
            sotiendanop.setText("");
            sotiennop.clear();
            duno.setText("");
        });
    }
}
