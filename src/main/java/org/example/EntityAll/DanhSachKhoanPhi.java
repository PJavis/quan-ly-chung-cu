package org.example.EntityAll;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "danh_sach_khoan_phi")
public class DanhSachKhoanPhi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ten_khoan_phi")
    private String ten_khoan_phi;

    @Column(name = "loai_khoan_phi")
    private String loai_khoan_phi;

    @Column(name = "bat_dau")
    private Date bat_dau;

    @Column(name = "ket_thuc")
    private Date ket_thuc;

    @Column(name = "gia_tri")
    private double gia_tri;

    public DanhSachKhoanPhi(String ten_khoan_phi, String loai_khoan_phi, Date bat_dau, Date ket_thuc, double gia_tri) {
        this.ten_khoan_phi = ten_khoan_phi;
        this.loai_khoan_phi = loai_khoan_phi;
        this.bat_dau = bat_dau;
        this.ket_thuc = ket_thuc;
        this.gia_tri = gia_tri;
    }

    public DanhSachKhoanPhi() {

    }

    public int getId() {
        return id;
    }

    public double getGia_tri() {
        return gia_tri;
    }

    public void setGia_tri(double gia_tri) {
        this.gia_tri = gia_tri;
    }

    public String getTen_khoan_phi() {
        return ten_khoan_phi;
    }

    public void setTen_khoan_phi(String ten_khoan_phi) {
        this.ten_khoan_phi = ten_khoan_phi;
    }

    public String getLoai_khoan_phi() {
        return loai_khoan_phi;
    }

    public void setLoai_khoan_phi(String loai_khoan_phi) {
        this.loai_khoan_phi = loai_khoan_phi;
    }

    public Date getBat_dau() {
        return bat_dau;
    }

    public void setBat_dau(Date bat_dau) {
        this.bat_dau = bat_dau;
    }

    public Date getKet_thuc() {
        return ket_thuc;
    }

    public void setKet_thuc(Date ket_thuc) {
        this.ket_thuc = ket_thuc;
    }
}
