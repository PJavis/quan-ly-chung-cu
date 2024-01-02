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

    @Column(name = "bien_so_xe")
    private String bienSoXe;

    @Column(name = "phi_gui_xe")
    private double phiGuiXe;

    @Column(name = "so_tang")
    private int soTang;

    @Column(name = "so_phong")
    private int soPhong;

    public PhuongTien() {
    }

    public PhuongTien(String loaiPhuongTien, String bienSoXe, double phiGuiXe) {
        this.loaiPhuongTien = loaiPhuongTien;
        this.bienSoXe = bienSoXe;
        this.phiGuiXe = phiGuiXe;
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
