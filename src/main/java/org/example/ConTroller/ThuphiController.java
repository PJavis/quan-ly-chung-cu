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
    private KhoanPhi khoanPhi;
    private NopPhi nopPhi;
    private double duNo = 0;

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

    @FXML
    private TextField sodiennuoc;

    @FXML
    private Label chitiet;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeComboBox();
    }

    private void initializeComboBox() {
        List<String> khoanphiString = getKhoanPhiNames();
        tenphi.setItems(FXCollections.observableArrayList(khoanphiString));

        tenphi.setOnAction(event -> {
            khoanPhi = KhoanPhiDao.getInstance().selectByName(tenphi.getValue());
            updateKhoanPhiDetails();
            clearInputFields();
        });
    }

    private List<String> getKhoanPhiNames() {
        List<KhoanPhi> khoanPhis = getData.getInstance().getKhoanPhis();
        ObservableList<String> khoanphiString = FXCollections.observableArrayList();
        for (KhoanPhi khoanPhi1 : khoanPhis) {
            khoanphiString.add(khoanPhi1.getTenKhoanPhi());
        }
        return khoanphiString;
    }

    private void updateKhoanPhiDetails() {
        sotien.setText(khoanPhi.getDecimalFormatsotien());
        chitiet.setText(khoanPhi.getLoaiKhoanPhi());
    }

    private void clearInputFields() {
        sophong.clear();
        sotang.clear();
        sotiendanop.setText("");
        sotiennop.clear();
        duno.setText("");
    }

    @FXML
    void nopphi(ActionEvent event) {
        if (isInputInvalid()) {
            showAlert("Thất bại", "Không tìm thấy khoản phí hoặc hộ khẩu");
            return;
        }

        try {
            double amount = Double.parseDouble(sotiennop.getText());
        } catch (NumberFormatException e) {
            showAlert("Thất bại", "Hãy nhập số tiền bạn muốn nộp vào số tiền nộp");
            return;
        }

        if (Double.parseDouble(sotiennop.getText()) > duNo) {
            showAlert("Thất bại", "Bạn không thể nộp số tiền vượt quá số tiền phải đóng");
            sotiennop.clear();
            return;
        }

        if (nguoinopphi.getText().isEmpty()) {
            showAlert("Thất bại", "Hãy nhập tên người nộp");
            return;
        }

        updateNopPhiAndKhoanPhi();

        saveLichSuGiaoDich();

        clearInputFields();

        showAlert("Thành công", "Nộp phí thành công");
    }

    private boolean isInputInvalid() {
        return tenphi.getValue() == null || sophong.getText().isEmpty() || sotang.getText().isEmpty();
    }

    private void updateNopPhiAndKhoanPhi() {
        nopPhi.setSoTienDaDong(nopPhi.getSoTienDaDong() + Double.parseDouble(sotiennop.getText()));
        NopPhiDao.getInstance().update(nopPhi);

        khoanPhi.setTongsotien(khoanPhi.getTongsotien() + Double.parseDouble(sotiennop.getText()));
        getData.getInstance().updateKhoanphi(khoanPhi);
        KhoanPhiDao.getInstance().update(khoanPhi);
    }

    private void saveLichSuGiaoDich() {
        LichSuGiaoDich lichSuGiaoDich = createLichSuGiaoDich();
        LichSuGiaoDichDao.getInstance().save(lichSuGiaoDich);
    }

    private LichSuGiaoDich createLichSuGiaoDich() {
        LichSuGiaoDich lichSuGiaoDich = new LichSuGiaoDich();
        lichSuGiaoDich.setSoPhong(nopPhi.getSoPhong());
        lichSuGiaoDich.setSoTang(nopPhi.getSoTang());
        lichSuGiaoDich.setTenKhoanPhi(khoanPhi.getTenKhoanPhi());
        lichSuGiaoDich.setNopPhi(nopPhi);
        lichSuGiaoDich.setTennguoinop(nguoinopphi.getText());
        lichSuGiaoDich.setGiaTri(Double.parseDouble(sotiennop.getText()));
        LocalDate today = LocalDate.now();
        Date date = Date.valueOf(today);
        lichSuGiaoDich.setThoigiangiaodich(date);
        return lichSuGiaoDich;
    }

    private void showAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    void timphong(ActionEvent event) {
        try {
            nopPhi = NopPhiDao.getInstance().selectByCondition(khoanPhi.getId(),
                    Integer.parseInt(sotang.getText()), Integer.parseInt(sophong.getText()));
            updateNopPhiDetails();
        } catch (Exception e) {
            handleException(e);
        }
    }

    private void updateNopPhiDetails() {
        DecimalFormat decimalFormat = new DecimalFormat("#,###.###");
        sotiendanop.setText(decimalFormat.format(nopPhi.getSoTienDaDong()));
        duNo = nopPhi.getGiaTri() - nopPhi.getSoTienDaDong();
        duno.setText(decimalFormat.format(duNo));
    }

    private void handleException(Exception e) {
        sotiennop.clear();
        sotiendanop.setText("");
        nguoinopphi.clear();
        duno.setText("");
        showAlert("Lỗi", "Không tìm thấy hộ khẩu");
    }
}
