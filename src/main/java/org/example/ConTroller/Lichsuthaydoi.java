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
import org.example.Model.EntityAll.HoKhau;
import org.example.Model.EntityAll.KhoanPhi;
import org.example.Model.EntityAll.LichSuThayDoi;
import org.example.Model.Hibernatedao.LichSuThayDoiDao;

import java.util.List;

public class Lichsuthaydoi {

    @FXML
    private TableView<LichSuThayDoi> danhsachthaydoi;

    @FXML
    private TableColumn<LichSuThayDoi, String> mota;

    @FXML
    private TableColumn<LichSuThayDoi, Integer> sothutu;

    @FXML
    private TableColumn<LichSuThayDoi, String> thoigian;

    @FXML
    private TextField timkiem;
    private List<LichSuThayDoi> lichsuthaydoiList;
    ObservableList<LichSuThayDoi> lichSuThayDois;

    public void setlichsu(HoKhau hoKhau){
        lichsuthaydoiList= LichSuThayDoiDao.getInstance().selectByHoKhau(hoKhau);
        lichSuThayDois= FXCollections.observableArrayList(lichsuthaydoiList);
        danhsachthaydoi();
        timkiem();
    }
    public void danhsachthaydoi(){

        sothutu.setCellValueFactory(cellData -> {
            int rowIndex = cellData.getTableView().getItems().indexOf(cellData.getValue()) + 1;
            return javafx.beans.binding.Bindings.createObjectBinding(() -> rowIndex);
        });
        mota.setCellValueFactory(new PropertyValueFactory<>("ThayDoi"));
        thoigian.setCellValueFactory(new PropertyValueFactory<>("FormattedDatebatdau"));
        danhsachthaydoi.setItems(lichSuThayDois);
    }
    public void timkiem(){
        FilteredList<LichSuThayDoi> filter = new FilteredList<>(lichSuThayDois, e -> true);

        timkiem.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateEmployeeData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                if(predicateEmployeeData.getThayDoi().toLowerCase().contains(newValue.toLowerCase())){
                    return true;
                }else if(predicateEmployeeData.getFormattedDatebatdau().contains(newValue)){
                    return  true;
                }

                return false;
            });
        });

        SortedList<LichSuThayDoi> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(danhsachthaydoi.comparatorProperty());
        danhsachthaydoi.setItems(sortList);
    }

}
