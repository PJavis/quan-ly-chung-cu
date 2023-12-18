package org.example;

import org.example.EntityAll.HoKhau;
import org.example.EntityAll.KhoanPhi;
import org.example.EntityAll.NhanKhau;
import org.example.Hibernatedao.HoKhauDao;
import org.example.Hibernatedao.KhoanPhiDao;
import org.example.Hibernatedao.NhanKhauDao;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class getData {
    private static getData instance;
    private List<KhoanPhi> khoanPhis;
    private List<HoKhau> hoKhaus;
    private Map<Integer,NhanKhau> nhanKhaus;

    private List<NhanKhau> nhanKhauList;

    private getData() {
        // Khởi tạo dữ liệu khi lớp được tạo ra
        reloadHokhau();
        reloadNhankhau();
        reloadKhoanPhi();
    }

    public static getData getInstance() {
        if (instance == null) {
            instance = new getData();
        }
        return instance;
    }

    public Map<Integer,NhanKhau> getNhanKhaus() {
        return nhanKhaus;
    }
    public void addNhankhau(NhanKhau nhanKhau){
        nhanKhaus.put(nhanKhau.getIdNguoiDan(), nhanKhau);
    }
    public void removeNhankhau(NhanKhau nhanKhau){
        nhanKhaus.remove(nhanKhau.getIdNguoiDan());
    }
    public void setNhankhau(NhanKhau nhanKhau){
        nhanKhaus.replace(nhanKhau.getIdNguoiDan(), nhanKhau);
    }

    public List<HoKhau> getHoKhaus() {
        return hoKhaus;
    }
    public void addHokhau(HoKhau hoKhau){
        hoKhaus.add(hoKhau);
    }
    public void removeHokhau(HoKhau hoKhau){
        hoKhaus.remove(hoKhau);
    }

    public List<KhoanPhi> getKhoanPhis() {
        return khoanPhis;
    }
    public void addKhoanphi(KhoanPhi khoanPhi){khoanPhis.add(khoanPhi);}
    public void removeKhoanphi(KhoanPhi khoanPhi){khoanPhis.remove(khoanPhi);}

    public void reloadNhankhau() {
        // Làm mới dữ liệu từ HoKhauDao và NhanKhauDao
        this.nhanKhauList = NhanKhauDao.getInstance().selectAll();
        this.nhanKhaus = this.nhanKhauList.stream().collect(Collectors.toMap(NhanKhau::getIdNguoiDan, nhanKhau -> nhanKhau));
    }
    public void reloadHokhau(){
        this.hoKhaus = HoKhauDao.getInstance().selectAll();
    }
    public void reloadKhoanPhi(){this.khoanPhis= KhoanPhiDao.getInstance().selectAll();}
}
