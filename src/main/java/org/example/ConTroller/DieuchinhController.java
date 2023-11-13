package org.example.ConTroller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import org.example.EntityAll.HoKhau;
import org.example.EntityAll.NhanKhau;
import org.example.Hibernatedao.HoKhauDao;
import org.example.Hibernatedao.NhanKhauDao;

import java.net.URL;
import java.util.*;

public class DieuchinhController implements Initializable {
    @FXML
    private GridPane thanhvienphong;


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
    List<NhanKhau> nhanKhauList= NhanKhauDao.getInstance().selectAll();

    @FXML
    void dieuchinhnhankhau(ActionEvent event) {

    }

    @FXML
    void timnhankhau(ActionEvent event) {

    }
    @FXML
    private Label khongtimthayphong;
    private HoKhau hoKhau=new HoKhau();
    private NhanKhau nhanKhau=new NhanKhau();


    @FXML
    void timphong(ActionEvent event) {
        thanhvienphong.getChildren().clear();
        listviewsophong.setVisible(false);
        hoKhau=HoKhauDao.getInstance().selectById(Integer.parseInt(sophongdieuchinhphong.getText()));
        if(hoKhau==null)khongtimthayphong.setVisible(true);
        else {
            List<NhanKhau> nhanKhaucuaphong=new ArrayList<>();
            nhanKhau= HoKhauDao.getInstance().layChuHo(hoKhau);
            nhanKhaucuaphong=HoKhauDao.getInstance().layNhanKhau(hoKhau);
            khongtimthayphong.setVisible(false);
            dientichphong.setText(String.valueOf(hoKhau.getDienTichPhong()));
            chuho.setText(nhanKhau.getTen());
Label label2=new Label("Số thứ tự");
Label label3=new Label("Tên thành viên");
thanhvienphong.add(label2,0 ,0);
thanhvienphong.add(label3,1,0);
           for(int i=0;i<nhanKhaucuaphong.size();i++){
               Label label=new Label(""+(i+1));
               Label label1=new Label(nhanKhaucuaphong.get(i).getTen());
               Button button=new Button("Xóa nhân khẩu");

               NhanKhau nhanKhau1 = nhanKhaucuaphong.get(i);
               button.setOnAction(event1 -> {
                   Alert alert=new Alert(Alert.AlertType.WARNING);
                   alert.setContentText("Bạn chắc chắn muốn xóa nhân khẩu khỏi phòng ?");
                   ButtonType buttonTypeYes = new ButtonType("Yes");
                   ButtonType buttonTypeNo = new ButtonType("No");
                   alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

                   // Hiển thị Alert và xử lý kết quả
                   Optional<ButtonType> result = alert.showAndWait();
                   if (result.isPresent() && result.get() == buttonTypeYes) {
                       nhanKhau1.setHoKhau(null);
                    NhanKhauDao.getInstance().update(nhanKhau1);
                       Alert alert1=new Alert(Alert.AlertType.CONFIRMATION);
                       alert1.setContentText("Xóa thành công");
                       alert1.showAndWait();
                   }

               });
               thanhvienphong.add(label,0,i+1);
               thanhvienphong.add(label1,1,i+1);
               thanhvienphong.add(button,2,i+1);
           }
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
    @FXML
    void dieuchinhphong(ActionEvent event) {
hoKhau.setId(Integer.parseInt(sophongdieuchinhphong.getText()));
hoKhau.setDienTichPhong(Double.parseDouble(dientichphong.getText()));
HoKhauDao.getInstance().update(hoKhau);

    }
    @FXML
    void xoaphong(ActionEvent event) {
Alert alert=new Alert(Alert.AlertType.WARNING);
alert.setHeaderText("Bạn chắc chắn muốn xóa phòng ?");
alert.setContentText("Khi đó thông tin về các thành viên của phòng sẽ không còn");
        ButtonType buttonTypeOK = new ButtonType("OK", ButtonType.OK.getButtonData());
        // Thêm nút "Hủy"
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonType.CANCEL.getButtonData());

        alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeOK) {
            HoKhauDao.getInstance().delete(hoKhau.getId());
        }
    }
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
