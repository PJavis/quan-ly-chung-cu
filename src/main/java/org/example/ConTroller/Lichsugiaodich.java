package org.example.ConTroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Lighting;
import org.example.EntityAll.LichSuGiaoDich;
import org.example.EntityAll.NopPhi;
import org.example.Hibernatedao.LichSuGiaoDichDao;

import java.util.List;

public class Lichsugiaodich {
    private NopPhi nopPhi;

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
    private TextField timkiem;


    public void setNopPhi(NopPhi nopPhi) {
        this.nopPhi = nopPhi;
        lichSuGiaoDichList=LichSuGiaoDichDao.getInstance().selectByCondition(nopPhi.getIdKhoanPhi(), nopPhi.getSoTang(), nopPhi.getSoPhong());
        danhsachgiaodich();
    }
    private List<LichSuGiaoDich> lichSuGiaoDichList;
    private ObservableList<LichSuGiaoDich> lichSuGiaoDichObservableList;
    public void danhsachgiaodich(){
        lichSuGiaoDichObservableList= FXCollections.observableArrayList(lichSuGiaoDichList);
        sothutu.setCellValueFactory(cellData -> {
            int rowIndex = cellData.getTableView().getItems().indexOf(cellData.getValue()) + 1;
            return javafx.beans.binding.Bindings.createObjectBinding(() -> rowIndex);
        });
        nguoinoptien.setCellValueFactory(new PropertyValueFactory<>("tennguoinop"));
        sotiennop.setCellValueFactory(new PropertyValueFactory<>("decimalFormatsotiennop"));
        thoigiannop.setCellValueFactory(new PropertyValueFactory<>("formattedDate"));
        danhsachgiaodich.setItems(lichSuGiaoDichObservableList);
    }
}
