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
import org.example.EntityAll.LichSuGiaoDich;
import org.example.EntityAll.LichSuGiaoDichPhiGuiXe;
import org.example.EntityAll.NopPhi;
import org.example.EntityAll.PhuongTien;
import org.example.Hibernatedao.LichSuGiaoDichDao;
import org.example.Hibernatedao.LichSuGiaoDichPhiGuiXeDao;

import java.util.Comparator;
import java.util.List;

public class Lichsugiaodichthuphiguixe {

    @FXML
    private TableView<LichSuGiaoDichPhiGuiXe> danhsachgiaodich;

    @FXML
    private TableColumn<LichSuGiaoDichPhiGuiXe, String> nguoinoptien;

    @FXML
    private TableColumn<LichSuGiaoDichPhiGuiXe, Integer> sothutu;

    @FXML
    private TableColumn<LichSuGiaoDichPhiGuiXe, String> sotiennop;

    @FXML
    private TableColumn<LichSuGiaoDichPhiGuiXe, String> thoigiannop;

    @FXML
    private TextField timkiem;
    public void timkiem(){
        FilteredList<LichSuGiaoDichPhiGuiXe> filter = new FilteredList<>(lichSuGiaoDichObservableList, e -> true);

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

        SortedList<LichSuGiaoDichPhiGuiXe> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(danhsachgiaodich.comparatorProperty());
        danhsachgiaodich.setItems(sortList);
    }
    public void setNopPhi(PhuongTien nopPhi) {

        lichSuGiaoDichList= LichSuGiaoDichPhiGuiXeDao.getInstance().selectByCondition(nopPhi);
        danhsachgiaodich();
        timkiem();
    }
    private List<LichSuGiaoDichPhiGuiXe> lichSuGiaoDichList;
    private ObservableList<LichSuGiaoDichPhiGuiXe> lichSuGiaoDichObservableList;
    public void danhsachgiaodich(){
        lichSuGiaoDichObservableList= FXCollections.observableArrayList(lichSuGiaoDichList);
        lichSuGiaoDichObservableList.sort(Comparator.comparing(LichSuGiaoDichPhiGuiXe::getFormattedDate));
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
