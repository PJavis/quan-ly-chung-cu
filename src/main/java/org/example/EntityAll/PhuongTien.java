package org.example.EntityAll;


import jakarta.persistence.*;

import java.text.DecimalFormat;

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

    @Column(name = "so_tien_da_nop")
    private double soTienDaNop;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "so_tang", referencedColumnName = "so_tang"),
            @JoinColumn(name = "so_phong", referencedColumnName = "id")
    })
    private HoKhau hoKhau;

    @Column(name = "ten_chu_xe")
    private String tenChuXe;

    public PhuongTien() {
    }

    public PhuongTien(String loaiPhuongTien, String bienSoXe, double phiGuiXe, HoKhau hoKhau, String tenChuXe) {
        this.loaiPhuongTien = loaiPhuongTien;
        this.bienSoXe = bienSoXe;
        this.phiGuiXe = phiGuiXe;
        this.hoKhau = hoKhau;
        this.tenChuXe = tenChuXe;
    }

    public HoKhau getHoKhau() {
        return hoKhau;
    }

    public void setHoKhau(HoKhau hoKhau) {
        this.hoKhau = hoKhau;
    }

    public String getTenChuXe() {
        return tenChuXe;
    }

    public void setTenChuXe(String tenChuXe) {
        this.tenChuXe = tenChuXe;
    }

    public int getSoTang() {
        return this.getHoKhau().getSoTang();
    }

    public int getSoPhong() {
        return this.getHoKhau().getId();
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

    public double getSoTienDaNop() {
        return soTienDaNop;
    }

    public void setSoTienDaNop(double soTienDaNop) {
        this.soTienDaNop = soTienDaNop;
    }
    public  String getDecimalFormatsotien(){
        double duno=phiGuiXe-soTienDaNop;
        String pattern = "#,##0" + (duno % 1 == 0 ? "" : ".#########");
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(duno);
    }
    public  String getDecimalFormatSotiendanop(){

        String pattern = "#,##0" + (soTienDaNop % 1 == 0 ? "" : ".#########");
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(soTienDaNop);
    }
}
