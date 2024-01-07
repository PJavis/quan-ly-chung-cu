package org.example.ConTroller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import org.example.EntityAll.HoKhau;
import org.example.EntityAll.KhoanPhi;
import org.example.EntityAll.LichSuGiaoDich;
import org.example.EntityAll.NopPhi;
import org.example.Hibernatedao.LichSuGiaoDichDao;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class Tracuulichsugiaodich implements Initializable {
    private KhoanPhi khoanPhi;

    public KhoanPhi getKhoanPhi() {
        return khoanPhi;
    }

    public void setKhoanPhi(KhoanPhi khoanPhi) {
        this.khoanPhi = khoanPhi;
    }

    @FXML
    private DatePicker den;


    @FXML
    private DatePicker tu;

    @FXML
    private TableColumn<LichSuGiaoDich, String> nguoinoptien;

    @FXML
    private TableColumn<LichSuGiaoDich, Integer> sothutu;

    @FXML
    private TableColumn<LichSuGiaoDich, Double> sotiennop;

    @FXML
    private TableColumn<LichSuGiaoDich, String> thoigiannop;

    @FXML
    private TableView<LichSuGiaoDich> danhsachgiaodich;
    @FXML
    private TableColumn<LichSuGiaoDich, Integer> sophong;

    @FXML
    private TableColumn<LichSuGiaoDich, Integer> sotang;


    private List<LichSuGiaoDich> lichSuGiaoDichList;
    private ObservableList<LichSuGiaoDich> lichSuGiaoDichObservableList;
    public void danhsachgiaodich(){
        lichSuGiaoDichObservableList= FXCollections.observableArrayList(lichSuGiaoDichList);
        lichSuGiaoDichObservableList.sort(Comparator.comparing(LichSuGiaoDich::getFormattedDate));
        sothutu.setCellValueFactory(cellData -> {
            int rowIndex = cellData.getTableView().getItems().indexOf(cellData.getValue()) + 1;
            return javafx.beans.binding.Bindings.createObjectBinding(() -> rowIndex);
        });
        nguoinoptien.setCellValueFactory(new PropertyValueFactory<>("tennguoinop"));
        sotiennop.setCellValueFactory(new PropertyValueFactory<>("decimalFormatsotiennop"));
        thoigiannop.setCellValueFactory(new PropertyValueFactory<>("formattedDate"));
        sophong.setCellValueFactory(cellData -> {
            HoKhau hoKhau = cellData.getValue().getNopPhi().getHoKhau();
            return hoKhau != null ? new SimpleIntegerProperty(hoKhau.getId()).asObject() : null;
        });

        sotang.setCellValueFactory(cellData -> {
            HoKhau hoKhau = cellData.getValue().getNopPhi().getHoKhau();
            return hoKhau != null ? new SimpleIntegerProperty(hoKhau.getSoTang()).asObject() : null;
        });
        danhsachgiaodich.setItems(lichSuGiaoDichObservableList);
    }

    @FXML
    void tim(ActionEvent event) {
        LocalDate localDate = tu.getValue();
        LocalDate localDate1=den.getValue();
        if(localDate!=null&&localDate1!=null){
            if(localDate.isBefore(localDate1)){
        lichSuGiaoDichList=LichSuGiaoDichDao.getInstance().selectInTime(Date.valueOf(localDate),Date.valueOf(localDate1), khoanPhi.getId());
        danhsachgiaodich();}
            else{
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Thất bại");
                alert.setContentText("Vui lòng điền thời gian chính xác");
                alert.showAndWait();
            }
        }
        else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Thất bại");
            alert.setContentText("Vui lòng điền đầy đủ thông tin");
            alert.showAndWait();
        }
    }
    private void formatDatePickerValue(DatePicker datePicker) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        StringConverter<LocalDate> converter = new StringConverter<>() {
            @Override
            public String toString(LocalDate object) {
                if (object != null) {
                    return dateFormatter.format(object);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                try {
                    if (string != null && !string.isEmpty()) {
                        return LocalDate.parse(string, dateFormatter);
                    }
                } catch (Exception e) {
                    // Xử lý nếu giá trị nhập không đúng định dạng
                }
                return null;
            }
        };

        datePicker.setConverter(converter);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tu.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
               formatDatePickerValue(tu);
            }
        });
        den.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                formatDatePickerValue(den);
            }
        });
    }
}
