package org.example.EntityAll;


import jakarta.persistence.*;

@Entity
@Table(name = "phuong_tien")
public class PhuongTien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "loai_phuong_tien")
    private String loaiPhuongTien;

    @Column(name = "bien_so_xe", length = 50)
    private String bienSoXe;

    @Column(name = "phi_gui_xe")
    private double phiGuiXe;

    @Column(name = "so_tang")
    private int soTang;

    @Column(name = "so_phong")
    private int soPhong;

    @Column(name = "ten_chu_xe")
    private String tenChuXe;

    public PhuongTien() {
    }

    public PhuongTien(String loaiPhuongTien, String bienSoXe, double phiGuiXe, int soTang, int soPhong, String tenChuXe) {
        this.loaiPhuongTien = loaiPhuongTien;
        this.bienSoXe = bienSoXe;
        this.phiGuiXe = phiGuiXe;
        this.soTang = soTang;
        this.soPhong = soPhong;
        this.tenChuXe = tenChuXe;
    }

    public String getTenChuXe() {
        return tenChuXe;
    }

    public void setTenChuXe(String tenChuXe) {
        this.tenChuXe = tenChuXe;
    }

    public int getSoTang() {
        return soTang;
    }

    public void setSoTang(int soTang) {
        this.soTang = soTang;
    }

    public int getSoPhong() {
        return soPhong;
    }

    public void setSoPhong(int soPhong) {
        this.soPhong = soPhong;
    }

    public String getLoaiPhuongTien() {
        return loaiPhuongTien;
    }

    public void setLoaiPhuongTien(String loaiPhuongTien) {
        this.loaiPhuongTien = loaiPhuongTien;
    }

    public String getBienSoXe() {
        return bienSoXe;
    }

    public void setBienSoXe(String bienSoXe) {
        this.bienSoXe = bienSoXe;
    }

    public double getPhiGuiXe() {
        return phiGuiXe;
    }

    public void setPhiGuiXe(double phiGuiXe) {
        this.phiGuiXe = phiGuiXe;
    }
}
