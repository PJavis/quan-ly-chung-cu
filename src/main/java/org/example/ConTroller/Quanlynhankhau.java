package org.example.ConTroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.EntityAll.NhanKhau;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;


import org.example.getData;
import java.net.URL;
import java.util.*;

public class Quanlynhankhau implements Initializable {
    @FXML
    private TextField timkiem;
    @FXML
    private TableView<NhanKhau> danhsachnhankhau;
    @FXML
    private TableColumn<NhanKhau, String> hovaten;

    @FXML
    private TableColumn<NhanKhau, String> ngaysinh;

    @FXML
    private TableColumn<NhanKhau, Integer> sophongdango;
    @FXML
    private TableColumn<NhanKhau, Integer> sotang;

    @FXML
    private TableColumn<NhanKhau, Integer> sothutu;

    @FXML
    private TableColumn<NhanKhau, String> trangthai;
    @FXML
    private TableColumn<NhanKhau, Void> dieuchinh;
    private  Map<Integer,NhanKhau> nhanKhauList=getData.getInstance().getNhanKhaus();


    private ObservableList<NhanKhau> nhanKhaus;
    @FXML
    private Label tongsonhankhau;
    public void danhsachnhankhau(){
        tongsonhankhau.setText(String.valueOf(nhanKhauList.size()));
       nhanKhaus= FXCollections.observableArrayList(nhanKhauList.values());
        nhanKhaus.sort((o1, o2) -> {
            String[] words1 = o1.getTen().split(" ");
            String[] words2 = o2.getTen().split(" ");
            String lastWord1 = words1[words1.length - 1];
            String lastWord2 = words2[words2.length - 1];
            return lastWord1.compareTo(lastWord2);

        });
        sothutu.setCellValueFactory(cellData -> {
            int rowIndex = cellData.getTableView().getItems().indexOf(cellData.getValue()) + 1;
            return javafx.beans.binding.Bindings.createObjectBinding(() -> rowIndex);
        });
        hovaten.setCellValueFactory(new PropertyValueFactory<>("ten"));
        ngaysinh.setCellValueFactory(new PropertyValueFactory<>("formattedDate"));
        sophongdango.setCellValueFactory(new PropertyValueFactory<>("sophong"));
        trangthai.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
        sotang.setCellValueFactory(new PropertyValueFactory<>("sotang"));
        dieuchinh.setCellFactory(cell->{
            return new TableCell<NhanKhau,Void>(){
                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                    } else {
                        Button button = new Button();
                        FontAwesomeIconView iconView = new FontAwesomeIconView(FontAwesomeIcon.PENCIL);
                        iconView.setSize("16px");
                        setGraphic(button);
                        button.setGraphic(iconView);

                        // Xử lý sự kiện khi nút được nhấp
                        button.setOnAction(event -> {
                           NhanKhau person = getTableView().getItems().get(getIndex());
                            try {
                                Stage ag0r = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example/Dieuchinhnhankhau.fxml"));
                                Parent root = loader.load();
                                Scene scene = new Scene(root);
                                Stage ag0r1=new Stage();
                                ag0r1.setScene(scene);
                                ag0r1.initModality(Modality.APPLICATION_MODAL);
                                ag0r1.initOwner(ag0r);
                                Dieuchinhnhankhau dieuchinhnhankhau=loader.getController();
                                dieuchinhnhankhau.setNhanKhau(person);
                                ag0r1.showAndWait();
                                nhanKhauList=getData.getInstance().getNhanKhaus();
                                danhsachnhankhau();
                                timkiem();
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        });
                    }
                }
            };
        });
        danhsachnhankhau.setItems(nhanKhaus);
    }

    @FXML
    void taomoinhankhau(ActionEvent event) {
        try {
            Stage ag0r = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example/Taomoinhankhau.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage ag0r1=new Stage();
            ag0r1.setScene(scene);
            ag0r1.initModality(Modality.APPLICATION_MODAL);
            ag0r1.initOwner(ag0r);
            ag0r1.showAndWait();
            nhanKhauList=getData.getInstance().getNhanKhaus();
            danhsachnhankhau();
            timkiem();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void timkiem(){
        FilteredList<NhanKhau> filter = new FilteredList<>(nhanKhaus, e -> true);

        timkiem.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateEmployeeData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();


                return predicateEmployeeData.getTen().toLowerCase().contains(searchKey);

            });
        });

        SortedList<NhanKhau> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(danhsachnhankhau.comparatorProperty());
        danhsachnhankhau.setItems(sortList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        danhsachnhankhau();
        timkiem();
    }
}
