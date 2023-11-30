package org.example.EntityAll;

import jakarta.persistence.*;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

@Entity
@Table(name = "danh_sach_khoan_phi")
public class KhoanPhi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ten_khoan_phi")
    private String tenKhoanPhi;

    @Column(name = "loai_khoan_phi")
    private String loaiKhoanPhi;



    @Column(name = "bat_dau")
    private Date batDau;

    @Column(name = "ket_thuc")
    private Date ketThuc;

    @Column(name = "gia_tri")
    private double giaTri;
    @Column(name="tong_so_tien")
    private double tongsotien;

    public KhoanPhi(String tenKhoanPhi, String loaiKhoanPhi, Date batDau, Date ketThuc, double giaTri, double tongsotien) {
        this.tenKhoanPhi = tenKhoanPhi;
        this.loaiKhoanPhi = loaiKhoanPhi;
        this.batDau = batDau;
        this.ketThuc = ketThuc;
        this.giaTri = giaTri;
        this.tongsotien=tongsotien;
    }

    public KhoanPhi() {

    }

    public double getTongsotien() {
        return tongsotien;
    }

    public void setTongsotien(double tongsotien) {
        this.tongsotien = tongsotien;
    }

    public int getId() {
        return id;
    }

    public double getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(double giaTri) {
        this.giaTri = giaTri;
    }

    public String getTenKhoanPhi() {
        return tenKhoanPhi;
    }

    public void setTenKhoanPhi(String tenKhoanPhi) {
        this.tenKhoanPhi = tenKhoanPhi;
    }

    public String getLoaiKhoanPhi() {
        return loaiKhoanPhi;
    }

    public void setLoaiKhoanPhi(String loaiKhoanPhi) {
        this.loaiKhoanPhi = loaiKhoanPhi;
    }

    public Date getBatDau() {
        return batDau;
    }

    public void setBatDau(Date batDau) {
        this.batDau = batDau;
    }

    public Date getKetThuc() {
        return ketThuc;
    }

    public void setKetThuc(Date ketThuc) {
        this.ketThuc = ketThuc;
    }
    public String getFormattedDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(ketThuc);
    }
    public  String getDecimalFormatsotien(){
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        return decimalFormat.format(giaTri);
    }public  String getDecimalFormatsotiendanop(){
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        return decimalFormat.format(tongsotien);
    }
}