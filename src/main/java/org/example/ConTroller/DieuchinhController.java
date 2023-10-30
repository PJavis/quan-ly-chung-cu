package org.example.ConTroller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.example.EntityAll.HoKhau;
import org.example.Hibernatedao.HoKhauDao;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class DieuchinhController implements Initializable {

    @FXML
    private Label chonkhoanphicandieuchinh;

    @FXML
    private TextField chuho;

    @FXML
    private CheckBox co;

    @FXML
    private TextField dientichphong;

    @FXML
    private Button dieuchinhphong;

    @FXML
    private TextField gioitinh;

    @FXML
    private TextField ngaysinh;

    @FXML
    private Label nhankhauduocdieuchinhthanhcon;

    @FXML
    private TextField quoctich;

    @FXML
    private TextField sophong;

    @FXML
    private TextField sophongdieuchinhphong;

    @FXML
    private TabPane tabpanedieuchinh;

    @FXML
    private TextField tennhankhau;

    @FXML
    private TextField tennhankhaucandieuchinh;

    @FXML
    private TextField trangthai;

    @FXML
    private Button xoaphong;

    @FXML
    void dieuchinhnhankhau(ActionEvent event) {

    }

    @FXML
    void timnhankhau(ActionEvent event) {

    }
    @FXML
    private Label khongtimthayphong;

    @FXML
    void timphong(ActionEvent event) {
        HoKhau hoKhau=HoKhauDao.getInstance().selectById(Integer.parseInt(sophongdieuchinhphong.getText()));
        if(hoKhau==null)khongtimthayphong.setVisible(true);
        else {
            khongtimthayphong.setVisible(false);
            dientichphong.setText(String.valueOf(hoKhau.getDienTichPhong()));
        }

    }
    @FXML
    void listviewsophong(MouseEvent event) {
        String selectedItem = listviewsophong.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            sophongdieuchinhphong.setText(selectedItem);
            listviewsophong.setVisible(false);
        }
    }
    @FXML
    private ListView<String> listviewsophong;
   private  List<HoKhau>tenphong= HoKhauDao.getInstance().selectAll();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String []a=new String[tenphong.size()];
        for(int i=0;i<a.length;i++){
            a[i]= String.valueOf(tenphong.get(i).getId());
        }
        sophongdieuchinhphong.textProperty().addListener((observable, oldValue, newValue) -> {
            String input = sophongdieuchinhphong.getText().toLowerCase();
            if (input.isEmpty()) {
                listviewsophong.getItems().clear();
                listviewsophong.setVisible(false);
            } else {
                // Lọc các từ gợi ý dựa trên input của người dùng
                String[] filteredSuggestions =
                        Arrays.stream(a).filter(s -> s.toLowerCase().startsWith(input)).toArray(String[]::new);

                listviewsophong.setItems(FXCollections.observableArrayList(filteredSuggestions));

                // Hiển thị danh sách gợi ý

                listviewsophong.setVisible(true);
            }
        });

    }
}
