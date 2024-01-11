package org.example;

import org.example.EntityAll.*;
import org.example.Hibernatedao.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class getData {
    private static getData instance;
    private List<KhoanPhi> khoanPhis;
    private List<HoKhau> hoKhaus;
    private Map<Integer,NhanKhau> nhanKhaus;
    private Map<Integer, TaiKhoanBQT> taiKhoanBQTs;
    private Map<Integer, QuanTriChungCu> quanTriChungCus;

    private List<NhanKhau> nhanKhauList;

    private List<PhuongTien> phuongTiens;

    private getData() {
        // Khởi tạo dữ liệu khi lớp được tạo ra
        reloadHokhau();
        reloadNhankhau();
        reloadKhoanPhi();
        reloadPhuongTien();
        reloadTaiKhoanBQT();
        reloadQuanTriChungCu();
    }

    public static getData getInstance() {
        if (instance == null) {
            instance = new getData();
        }
        return instance;
    }
    public void setTaiKhoanBQT(int id, TaiKhoanBQT taiKhoanBQT) {
        taiKhoanBQTs.put(id, taiKhoanBQT);
    }

    public void setQuanTriChungCu(int id, QuanTriChungCu quanTriChungCu) {
        quanTriChungCus.put(id, quanTriChungCu);
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
    public void updateKhoanphi(KhoanPhi khoanPhi){
        for(KhoanPhi khoanPhi1 : khoanPhis){
            if(khoanPhi1.getId()== khoanPhi.getId()){
                khoanPhi1.setTongsotien(khoanPhi.getTongsotien());
            }
        }
    }
    public boolean addKhoanphi(KhoanPhi khoanPhi) {
        for (KhoanPhi k : khoanPhis) {
            if (k.getTenKhoanPhi().equals(khoanPhi.getTenKhoanPhi())) {
                LocalDate currentTime = LocalDate.now();
                if ( currentTime.isBefore(khoanPhi.getKetThuc().toLocalDate())) {
                    return false;
                }
            }
        }
        khoanPhis.add(khoanPhi);
        return true;
    }
    public void removeKhoanphi(KhoanPhi khoanPhi){khoanPhis.remove(khoanPhi);}

    public List<PhuongTien> getPhuongTiens(){return phuongTiens;}
    public boolean addPhuongTien(PhuongTien phuongTien) {
        boolean isDuplicate = phuongTiens.stream()
                .anyMatch(p -> p.getBienSoXe().equals(phuongTien.getBienSoXe()));

        if (!isDuplicate) {
            phuongTiens.add(phuongTien);
        }
        return !isDuplicate;
    }
    public void updateHokhau(HoKhau hoKhau){
        for (int i = 0; i < hoKhaus.size(); i++) {
            HoKhau hoKhau1 = hoKhaus.get(i);
            if (hoKhau1.getSoTang() == hoKhau.getSoTang() && hoKhau1.getId() == hoKhau.getId()) {
                hoKhaus.set(i, hoKhau);
                return;
            }
        }
    }
    public void removePhuongTien(PhuongTien phuongTien){phuongTiens.remove(phuongTien);}

    public void reloadNhankhau() {
        // Làm mới dữ liệu từ HoKhauDao và NhanKhauDao
        this.nhanKhauList = NhanKhauDao.getInstance().selectAll();
        this.nhanKhaus = this.nhanKhauList.stream().collect(Collectors.toMap(NhanKhau::getIdNguoiDan, nhanKhau -> nhanKhau));
    }
    public void reloadHokhau(){this.hoKhaus = HoKhauDao.getInstance().selectAll();}
    public void reloadKhoanPhi(){this.khoanPhis= KhoanPhiDao.getInstance().selectAll();}

    public void  reloadPhuongTien(){this.phuongTiens = PhuongTienDao.getInstance().selectAll();
    updatePhiGuiXe();
    }


    public void updatePhiGuiXe() {
        if (LocalDate.now().getDayOfMonth() == 1) {
            for(PhuongTien phuongTien : phuongTiens) {
                phuongTien.setSoTienDaNop(0);
                PhuongTienDao.getInstance().save(phuongTien);
            }
        }
    }
    public void reloadTaiKhoanBQT() {
        List<TaiKhoanBQT>taiKhoanBQTList=(List<TaiKhoanBQT>) TaiKhoanBQTDao.getInstance().selectAll();
        this.taiKhoanBQTs=taiKhoanBQTList.stream().collect(Collectors.toMap(TaiKhoanBQT::getId, taiKhoanBQT -> taiKhoanBQT));
    }

    public void reloadQuanTriChungCu() {
        List<QuanTriChungCu> quanTriChungCuList = (List<QuanTriChungCu>) QuanTriChungCuDao.getInstance().selectAll();
        this.quanTriChungCus = quanTriChungCuList.stream().collect(Collectors.toMap(QuanTriChungCu::getId, quanTriChungCu -> quanTriChungCu));
    }
}
