package org.example.ConTroller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import org.example.EntityAll.HoKhau;
import org.example.EntityAll.NhanKhau;
import org.example.getData;

import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class Quanlyphong implements Initializable {

    @FXML
    private TableView<HoKhau> danhsachhokhau;

    @FXML
    private TableColumn<HoKhau, Void> dieuchinh;

    @FXML
    private TableColumn<HoKhau, Integer> sophong;

    @FXML
    private TableColumn<HoKhau, Integer> sotang;



    @FXML
    private TableColumn<HoKhau, Integer> sothutu;

    @FXML
    private TableColumn<HoKhau, String> tenchuho;

    @FXML
    private TextField timkiem;
    @FXML
    private Label tongsohokhau;

    private List<HoKhau> hoKhauList=getData.getInstance().getHoKhaus();
    private ObservableList<HoKhau> hoKhaus;
    public void danhsachhokhau(){
        tongsohokhau.setText(String.valueOf(hoKhauList.size()));
        hoKhaus= FXCollections.observableArrayList(hoKhauList);
        hoKhaus.sort(Comparator.comparingInt(HoKhau::getSoTang).thenComparing(HoKhau::getId));
        sothutu.setCellValueFactory(cellData -> {
            int rowIndex = cellData.getTableView().getItems().indexOf(cellData.getValue()) + 1;
            return javafx.beans.binding.Bindings.createObjectBinding(() -> rowIndex);
        });
        sophong.setCellValueFactory(new PropertyValueFactory<>("id"));
        sotang.setCellValueFactory(new PropertyValueFactory<>("soTang"));
        tenchuho.setCellValueFactory(new PropertyValueFactory<>("tenchuho"));

        dieuchinh.setCellFactory(cell->{
            return new TableCell<HoKhau,Void>(){
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
                            HoKhau person = getTableView().getItems().get(getIndex());
                            try {
                                Stage ag0r = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example/Dieuchinhhokhau.fxml"));
                                Parent root = loader.load();
                                Scene scene = new Scene(root);
                                Stage ag0r1=new Stage();
                                ag0r1.setScene(scene);
                                ag0r1.initModality(Modality.APPLICATION_MODAL);
                                ag0r1.initOwner(ag0r);
                                Dieuchinhhokhau dieuchinhhokhau=loader.getController();
                                dieuchinhhokhau.setHokhau(person);
                                ag0r1.showAndWait();
                                hoKhauList=getData.getInstance().getHoKhaus();
                                danhsachhokhau();
                                timkiemhokhau();
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        });
                    }
                }
            };
        });
        danhsachhokhau.setItems(hoKhaus);
    }
    public void timkiemhokhau(){
        FilteredList<HoKhau> filter = new FilteredList<>(hoKhaus, e -> true);

        timkiem.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateEmployeeData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();
                if(String.valueOf(predicateEmployeeData.getId()).contains(searchKey)){
                    return  true;
                } else if (String.valueOf(predicateEmployeeData.getSoTang()).contains(searchKey)) {
                    return true;
                }
                else if(predicateEmployeeData.getTenchuho().toLowerCase().contains(searchKey)){
                    return  true;
                }

               return false;

            });
        });

        SortedList<HoKhau> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(danhsachhokhau.comparatorProperty());
        danhsachhokhau.setItems(sortList);
    }

    @FXML
    void taomoihokhau(ActionEvent event) {
        try {
            Stage ag0r = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example/Taomoihokhau.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage ag0r1=new Stage();
            ag0r1.setScene(scene);
            ag0r1.initModality(Modality.APPLICATION_MODAL);
            ag0r1.initOwner(ag0r);
            ag0r1.showAndWait();
            hoKhauList=getData.getInstance().getHoKhaus();
            danhsachhokhau();
            timkiemhokhau();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        danhsachhokhau();
        timkiemhokhau();
    }
}
