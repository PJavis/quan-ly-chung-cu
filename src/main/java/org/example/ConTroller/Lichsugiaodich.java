package org.example.ConTroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Lighting;
import org.example.EntityAll.LichSuGiaoDich;
import org.example.EntityAll.NhanKhau;
import org.example.EntityAll.NopPhi;
import org.example.Hibernatedao.LichSuGiaoDichDao;

import java.util.Comparator;
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
    public void timkiem(){
        FilteredList<LichSuGiaoDich> filter = new FilteredList<>(lichSuGiaoDichObservableList, e -> true);

        timkiem.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateEmployeeData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                if(predicateEmployeeData.getTennguoinop().toLowerCase().contains(newValue.toLowerCase()))
                    return true;
                else if(predicateEmployeeData.getFormattedDate().contains(newValue))
                    return true;
                else return false;

            });
        });

        SortedList<LichSuGiaoDich> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(danhsachgiaodich.comparatorProperty());
        danhsachgiaodich.setItems(sortList);
    }


    public void setNopPhi(NopPhi nopPhi) {
        this.nopPhi = nopPhi;
        lichSuGiaoDichList=LichSuGiaoDichDao.getInstance().selectByCondition(nopPhi);
        danhsachgiaodich();
        timkiem();
    }
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
        danhsachgiaodich.setItems(lichSuGiaoDichObservableList);
    }
}
