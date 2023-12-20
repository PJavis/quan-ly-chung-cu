package org.example.EntityAll;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "lich_su_giao_dich")
public class LichSuGiaoDich {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="ten_chu-ho")
    private String tenchuho;

    @Column(name = "so_phong")
    private int sophong;
    @Column(name ="so_tang")
    private int sotang;

    @Column(name = "ten_khoan_phi")
    private String tenKhoanPhi;

    @Column(name = "thoi_gian_giao_dich")
    private Date thoigiangiaodich;

    @Column(name = "gia_tri")
    private double giaTri;
    public  LichSuGiaoDich(){

    }

    public LichSuGiaoDich(String tenchuho, int sophong, int sotang, String tenKhoanPhi, Date thoigiangiaodich, double giaTri) {
        this.tenchuho = tenchuho;
        this.sophong = sophong;
        this.sotang = sotang;
        this.tenKhoanPhi = tenKhoanPhi;
        this.thoigiangiaodich = thoigiangiaodich;
        this.giaTri = giaTri;
    }

    public String getTenchuho() {
        return tenchuho;
    }

    public void setTenchuho(String tenchuho) {
        this.tenchuho = tenchuho;
    }

    public int getSophong() {
        return sophong;
    }

    public void setSophong(int sophong) {
        this.sophong = sophong;
    }

    public int getSotang() {
        return sotang;
    }

    public void setSotang(int sotang) {
        this.sotang = sotang;
    }

    public String getTenKhoanPhi() {
        return tenKhoanPhi;
    }

    public void setTenKhoanPhi(String tenKhoanPhi) {
        this.tenKhoanPhi = tenKhoanPhi;
    }

    public Date getThoigiangiaodich() {
        return thoigiangiaodich;
    }

    public void setThoigiangiaodich(Date thoigiangiaodich) {
        this.thoigiangiaodich = thoigiangiaodich;
    }

    public double getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(double giaTri) {
        this.giaTri = giaTri;
    }
}
